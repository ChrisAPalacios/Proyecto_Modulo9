package dgtic.core.controller;

import dgtic.core.model.Boleto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cliente/canasta")
public class CanastaController {

    @SuppressWarnings("unchecked")
    private List<Boleto> obtenerCanasta(HttpSession session) {
        List<Boleto> canasta = (List<Boleto>) session.getAttribute("canasta");
        if (canasta == null) {
            canasta = new ArrayList<>();
            session.setAttribute("canasta", canasta);
        }
        return canasta;
    }

    @GetMapping
    public String verCanasta(Model model, HttpSession session) {
        List<Boleto> canasta = obtenerCanasta(session);
        model.addAttribute("canasta", canasta);
        return "cliente/canasta";
    }

    @PostMapping("/agregar")
    public String agregarABoleta(@ModelAttribute Boleto boleto, HttpSession session) {
        List<Boleto> canasta = obtenerCanasta(session);
        canasta.add(boleto);
        session.setAttribute("canasta", canasta);
        return "redirect:/cliente/canasta";
    }

    @GetMapping("/eliminar/{index}")
    public String eliminarDeCanasta(@PathVariable int index, HttpSession session) {
        List<Boleto> canasta = obtenerCanasta(session);
        if (index >= 0 && index < canasta.size()) {
            canasta.remove(index);
        }
        return "redirect:/cliente/canasta";
    }
}
