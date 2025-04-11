package dgtic.core.controller;

import dgtic.core.model.*;
import dgtic.core.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private TipoEventoService tipoEventoService;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private AsientoService asientoService;

    @Autowired
    private ZonaService zonaService;

    @Autowired
    private AsientoEventoService asientoEventoService;

    @GetMapping("/inicio")
    public String inicioCliente(Model model) {
        model.addAttribute("titulo", "Inicio Cliente - Eventia");
        return "/cliente/inicio_cliente";
    }

    @GetMapping("/eventos")
    public String eventosCliente(@RequestParam(name = "categoria", required = false) Integer categoriaId, Model model) {
        List<Evento> eventos;
        if (categoriaId != null) {
            eventos = eventoService.obtenerPorCategoria(categoriaId);
        } else {
            eventos = eventoService.obtenerTodos();
        }
        model.addAttribute("eventos", eventos);
        return "/cliente/eventos_cliente";

    }

    @GetMapping("/categorias")
    public String categoriasCliente(Model model) {
        model.addAttribute("titulo", "Categorías - Eventia");
        model.addAttribute("categorias", tipoEventoService.obtenerTodos());
        return "/cliente/categorias_cliente";
    }

    @GetMapping("/perfil")
    public String perfilCliente(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login";
        }
        model.addAttribute("usuario", usuario);
        return "cliente/perfil_cliente"; // Asegúrate que sea este nombre
    }

    @GetMapping("/mis-boletos")
    public String boletosCliente(Model model) {
        model.addAttribute("titulo", "Mis Boletos - Eventia");
        return "cliente/boletos";
    }

    @GetMapping("/eventos/{id}")
    public String mostrarCompraBoletos(@PathVariable("id") Integer idEvento, Model model) {
        Evento evento = eventoService.findById(idEvento);

        List<AsientoEvento> asientoEventos = asientoEventoService.obtenerDisponiblesPorEvento(idEvento);
        Set<Zona> zonas = asientoEventos.stream()
                .map(ae -> ae.getAsiento().getZona())
                .collect(Collectors.toSet());

        model.addAttribute("evento", evento);
        model.addAttribute("zonas", zonas);
        //model.addAttribute("asientosDisponibles", asientoEventoService.buscarDisponiblesPorZonaYEvento(idZona, idEvento));

        return "cliente/comprar_boletos";
    }

    @GetMapping("/asientos-disponibles")
    @ResponseBody
    public List<AsientoEvento> obtenerAsientosDisponiblesPorZonaYEvento(
            @RequestParam("idZona") Integer idZona,
            @RequestParam("idEvento") Integer idEvento) {
        return asientoEventoService.buscarDisponiblesPorZonaYEvento(idZona, idEvento);
    }

    @GetMapping("/eventos/{id}/seleccion")
    public String seleccionarZonaYMostrarAsientos(
            @PathVariable("id") Integer idEvento,
            @RequestParam("idZona") Integer idZona,
            @RequestParam("cantidad") Integer cantidad,
            Model model) {

        Evento evento = eventoService.findById(idEvento);

        List<AsientoEvento> asientoEventos = asientoEventoService.obtenerDisponiblesPorEvento(idEvento);
        Set<Zona> zonas = asientoEventos.stream()
                .map(ae -> ae.getAsiento().getZona())
                .collect(Collectors.toSet());

        List<AsientoEvento> asientosDisponibles = asientoEventoService.buscarDisponiblesPorZonaYEvento(idZona, idEvento);

        model.addAttribute("evento", evento);
        model.addAttribute("zonas", zonas);
        model.addAttribute("cantidad", cantidad);
        model.addAttribute("idZona", idZona);
        model.addAttribute("asientosDisponibles", asientosDisponibles);

        return "cliente/comprar_boletos";
    }


    @GetMapping("/pagos")
    public String pagosCliente(Model model) {
        model.addAttribute("titulo", "Formas de Pago - Eventia");
        return "cliente/pagos";
    }
}
