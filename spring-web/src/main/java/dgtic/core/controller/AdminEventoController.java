// AdminEventoController.java
package dgtic.core.controller;

import dgtic.core.model.Evento;
import dgtic.core.service.EventoService;
import dgtic.core.service.TipoEventoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/admin/eventos")
public class AdminEventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private TipoEventoService tipoEventoService;

    @GetMapping
    public String listarEventos(Model model, HttpSession session) {
        model.addAttribute("titulo", "Gestión de Eventos");
        model.addAttribute("eventos", eventoService.obtenerTodos());
        return "admin/eventos_admin";
    }

    @GetMapping("/nuevo")
    public String nuevoEvento(Model model) {
        model.addAttribute("titulo", "Nuevo Evento");
        model.addAttribute("evento", new Evento());
        model.addAttribute("tipos", tipoEventoService.obtenerTodos());
        return "admin/form_evento";
    }

    @PostMapping("/guardar")
    public String guardarEvento(@ModelAttribute("evento") Evento evento) {
        eventoService.guardar(evento);
        return "redirect:/admin/eventos";
    }

    @GetMapping("/editar/{id}")
    public String editarEvento(@PathVariable Integer id, Model model) {
        Evento evento = eventoService.buscarPorId(id);
        if (evento == null) {
            return "redirect:/admin/eventos";
        }
        model.addAttribute("titulo", "Editar Evento");
        model.addAttribute("evento", evento);
        model.addAttribute("tipos", tipoEventoService.obtenerTodos());
        return "admin/form_evento";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEvento(@PathVariable Integer id) {
        eventoService.eliminar(id);
        return "redirect:/admin/eventos";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
