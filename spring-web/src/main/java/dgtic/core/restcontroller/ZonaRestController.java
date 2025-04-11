package dgtic.core.restcontroller;

import dgtic.core.model.Zona;
import dgtic.core.service.ZonaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zonas")
@Tag(name = "Zonas", description = "Operaciones para gestionar las zonas del evento")
public class ZonaRestController {

    @Autowired
    private ZonaService zonaService;

    @Operation(summary = "Obtener todas las zonas disponibles")
    @GetMapping
    public List<Zona> obtenerTodas() {
        return zonaService.obtenerTodas();
    }

    @Operation(summary = "Obtener una zona específica por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Zona> obtenerPorId(
            @Parameter(description = "ID de la zona a consultar", example = "1")
            @PathVariable Integer id) {
        Zona zona = zonaService.buscarPorId(id);
        return zona != null ? ResponseEntity.ok(zona) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear una nueva zona")
    @PostMapping
    public ResponseEntity<Zona> crearZona(
            @Parameter(description = "Datos de la zona a crear")
            @RequestBody Zona zona) {
        return ResponseEntity.ok(zonaService.guardar(zona));
    }

    @Operation(summary = "Actualizar una zona existente por ID")
    @PutMapping("/{id}")
    public ResponseEntity<Zona> actualizarZona(
            @Parameter(description = "ID de la zona a actualizar", example = "1")
            @PathVariable Integer id,
            @RequestBody Zona zona) {
        Zona existente = zonaService.buscarPorId(id);
        if (existente == null) return ResponseEntity.notFound().build();

        zona.setIdZona(id);
        return ResponseEntity.ok(zonaService.guardar(zona));
    }

    @Operation(summary = "Eliminar una zona por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarZona(
            @Parameter(description = "ID de la zona a eliminar", example = "1")
            @PathVariable Integer id) {
        Zona existente = zonaService.buscarPorId(id);
        if (existente == null) return ResponseEntity.notFound().build();

        zonaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
