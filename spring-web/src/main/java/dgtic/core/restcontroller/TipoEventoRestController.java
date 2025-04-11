package dgtic.core.restcontroller;

import dgtic.core.model.TipoEvento;
import dgtic.core.service.TipoEventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipo-evento")
@Tag(name = "Tipo de Evento", description = "Operaciones para gestionar los tipos de evento")
public class TipoEventoRestController {

    @Autowired
    private TipoEventoService tipoEventoService;

    @Operation(summary = "Obtener todos los tipos de evento")
    @GetMapping
    public List<TipoEvento> obtenerTodos() {
        return tipoEventoService.obtenerTodos();
    }

    @Operation(summary = "Obtener un tipo de evento por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<TipoEvento> obtenerPorId(
            @Parameter(description = "ID del tipo de evento a buscar", example = "1")
            @PathVariable Integer id) {
        Optional<TipoEvento> tipo = tipoEventoService.buscarPorId(id);
        return tipo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo tipo de evento")
    @PostMapping
    public ResponseEntity<TipoEvento> crearTipoEvento(
            @Parameter(description = "Objeto TipoEvento a crear")
            @RequestBody TipoEvento tipoEvento) {
        return ResponseEntity.ok(tipoEventoService.guardar(tipoEvento));
    }

    @Operation(summary = "Actualizar un tipo de evento existente")
    @PutMapping("/{id}")
    public ResponseEntity<TipoEvento> actualizarTipoEvento(
            @Parameter(description = "ID del tipo de evento a actualizar", example = "1")
            @PathVariable Integer id,
            @Parameter(description = "Datos actualizados del tipo de evento")
            @RequestBody TipoEvento actualizado) {
        Optional<TipoEvento> tipoExistente = tipoEventoService.buscarPorId(id);
        if (tipoExistente.isPresent()) {
            actualizado.setIdTipoEvento(id);
            return ResponseEntity.ok(tipoEventoService.guardar(actualizado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un tipo de evento por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipoEvento(
            @Parameter(description = "ID del tipo de evento a eliminar", example = "1")
            @PathVariable Integer id) {
        Optional<TipoEvento> tipo = tipoEventoService.buscarPorId(id);
        if (tipo.isPresent()) {
            tipoEventoService.eliminarPorId(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
