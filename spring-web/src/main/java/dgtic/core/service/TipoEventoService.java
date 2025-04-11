package dgtic.core.service;

import dgtic.core.model.TipoEvento;
import dgtic.core.repository.TipoEventoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoEventoService {
    private final TipoEventoRepository tipoEventoRepository;

    public TipoEventoService(TipoEventoRepository tipoEventoRepository) {
        this.tipoEventoRepository = tipoEventoRepository;
    }

    public List<TipoEvento> obtenerTodos() {
        return tipoEventoRepository.findAll();
    }

    public Optional<TipoEvento> obtenerPorId(Integer id) {
        return tipoEventoRepository.findById(id);
    }

    public TipoEvento guardar(TipoEvento tipoEvento) {
        return tipoEventoRepository.save(tipoEvento);
    }

    public void eliminarPorId(Integer id) {
        tipoEventoRepository.deleteById(id);
    }

    public Optional<TipoEvento> buscarPorId(Integer id) {
        return tipoEventoRepository.findById(id);
    }
}
