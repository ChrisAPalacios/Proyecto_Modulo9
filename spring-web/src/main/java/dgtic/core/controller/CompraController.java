package dgtic.core.controller;

import dgtic.core.model.*;
import dgtic.core.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/cliente/compras")
public class CompraController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private AsientoService asientoService;

    @Autowired
    private CompraService compraService;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private AsientoEventoService asientoEventoService;

    @PostMapping("/finalizar")
    public String finalizarCompra(@RequestParam("asientos") List<Integer> idsAsientos,
                                  @RequestParam("idEvento") Integer idEvento,
                                  HttpSession session,
                                  Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login";
        }

        Compra compra = new Compra();
        compra.setUsuario(usuario);
        compra.setFechaCompra(LocalDateTime.now());
        compra.setMontoTotal(0.0); // Se actualizará más adelante
        compra = compraService.guardar(compra);

        double total = 0.0;

        for (Integer idAsiento : idsAsientos) {
            AsientoEventoId id = new AsientoEventoId(idEvento, idAsiento);
            AsientoEvento asientoEvento = asientoEventoService.buscarPorId(id).orElse(null);

            if (asientoEvento != null && "DISPONIBLE".equalsIgnoreCase(asientoEvento.getEstado())) {
                Boleto boleto = new Boleto();
                boleto.setEstado("VENDIDO");
                boleto.setUsuario(usuario);
                boleto.setAsiento(asientoEvento.getAsiento());
                boleto.setEvento(eventoService.buscarPorId(idEvento));
                boleto.setCompra(compra);
                boletoService.guardar(boleto);
                asientoEvento.setEstado("OCUPADO");
                asientoEventoService.guardar(asientoEvento);

                total += asientoEvento.getAsiento().getZona().getPrecio(); // Precio por zona
            }
        }

        compra.setMontoTotal(total);
        compraService.guardar(compra);

        model.addAttribute("compra", compra);
        model.addAttribute("mensaje", "¡Compra realizada con éxito!");

        return "cliente/resumen_compra";
    }
}
