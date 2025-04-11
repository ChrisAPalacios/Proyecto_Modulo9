package dgtic.core.controller;

import dgtic.core.model.Evento;
import dgtic.core.model.Usuario;
import dgtic.core.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public String mostrarEventos(@RequestParam(name = "categoria", required = false) Integer categoriaId, Model model) {
        List<Evento> eventos;
        if (categoriaId != null) {
            eventos = eventoService.obtenerPorCategoria(categoriaId);
        } else {
            eventos = eventoService.obtenerTodos();
        }
        model.addAttribute("eventos", eventos);
        return "principal/eventos";
    }


}
