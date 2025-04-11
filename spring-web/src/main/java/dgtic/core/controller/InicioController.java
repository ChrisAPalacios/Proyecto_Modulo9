package dgtic.core.controller;

import dgtic.core.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class InicioController {

    @Autowired
    private EventoService eventoService;

    @GetMapping("/")
    public String mostrarInicio(Model model) {
        model.addAttribute("titulo", "Inicio - Eventia");
        model.addAttribute("eventosDestacados", eventoService.obtenerEventosDestacados());
        return "inicio";
    }
}








