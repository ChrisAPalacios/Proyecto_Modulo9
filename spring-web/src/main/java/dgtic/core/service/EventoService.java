package dgtic.core.service;

import dgtic.core.model.Evento;
import dgtic.core.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> obtenerEventosDestacados() {
        return eventoRepository.findTop5ByOrderByFechaDesc();
    }

    public List<Evento> obtenerTodos() {
        return eventoRepository.findAll();
    }

    public List<Evento> obtenerPorCategoria(Integer categoriaId) {
        return eventoRepository.findByTipoEvento_IdTipoEvento(categoriaId);
    }

    public Evento buscarPorId(Integer id) {
        Optional<Evento> optional = eventoRepository.findById(id);
        return optional.orElse(null);
    }

    public Evento guardar(Evento evento) {
        return eventoRepository.save(evento);
    }


    public void eliminar(Integer id) {
        eventoRepository.deleteById(id);
    }

    public Evento findById(Integer idEvento) {
        return eventoRepository.findById(idEvento).orElse(null);
    }
}
