package dgtic.core.restcontroller;

import dgtic.core.model.AsientoEvento;
import dgtic.core.service.AsientoEventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asientos")
@Tag(name = "Asientos", description = "Operaciones para consultar asientos disponibles por zona y evento")
public class AsientoRestController {

    @Autowired
    private AsientoEventoService asientoEventoService;

    @Operation(summary = "Obtener asientos disponibles por zona y evento")
    @GetMapping
    public List<AsientoEvento> obtenerDisponiblesPorZonaYEvento(
            @Parameter(description = "ID de la zona", example = "1") @RequestParam("zonaId") Integer zonaId,
            @Parameter(description = "ID del evento", example = "2") @RequestParam("eventoId") Integer eventoId) {
        return asientoEventoService.buscarDisponiblesPorZonaYEvento(zonaId, eventoId);
    }

    @Operation(summary = "Obtener información de un asiento por ID")
    @GetMapping("/{idAsiento}")
    public ResponseEntity<AsientoEvento> obtenerPorId(
            @Parameter(description = "ID del asiento", example = "1") @PathVariable Integer idAsiento) {
        Optional<AsientoEvento> resultado = asientoEventoService.buscarPorIdAsiento(idAsiento);
        return resultado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
