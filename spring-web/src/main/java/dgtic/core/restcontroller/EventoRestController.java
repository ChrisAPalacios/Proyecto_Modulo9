package dgtic.core.restcontroller;

import dgtic.core.model.Evento;
import dgtic.core.service.EventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
@Tag(name = "Eventos", description = "Operaciones CRUD para la gestión de eventos")
public class EventoRestController {

    @Autowired
    private EventoService eventoService;

    @Operation(summary = "Obtener todos los eventos")
    @GetMapping
    public List<Evento> obtenerTodos() {
        return eventoService.obtenerTodos();
    }

    @Operation(summary = "Obtener un evento por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Evento> obtenerPorId(
            @Parameter(description = "ID del evento", example = "1")
            @PathVariable Integer id) {
        Evento evento = eventoService.findById(id);
        return evento != null ? ResponseEntity.ok(evento) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo evento")
    @PostMapping
    public ResponseEntity<Evento> crearEvento(
            @Parameter(description = "Datos del evento a crear")
            @RequestBody Evento evento) {
        Evento nuevo = eventoService.guardar(evento);
        return ResponseEntity.ok(nuevo);
    }

    @Operation(summary = "Actualizar un evento existente")
    @PutMapping("/{id}")
    public ResponseEntity<Evento> actualizarEvento(
            @Parameter(description = "ID del evento a actualizar", example = "1")
            @PathVariable Integer id,
            @Parameter(description = "Datos actualizados del evento")
            @RequestBody Evento evento) {
        Evento existente = eventoService.findById(id);
        if (existente == null) return ResponseEntity.notFound().build();

        evento.setIdEvento(id);
        Evento actualizado = eventoService.guardar(evento);
        return ResponseEntity.ok(actualizado);
    }

    @Operation(summary = "Eliminar un evento por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEvento(
            @Parameter(description = "ID del evento a eliminar", example = "1")
            @PathVariable Integer id) {
        Evento existente = eventoService.findById(id);
        if (existente == null) return ResponseEntity.notFound().build();

        eventoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
