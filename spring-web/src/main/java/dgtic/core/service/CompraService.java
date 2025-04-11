package dgtic.core.service;

import dgtic.core.model.Compra;
import dgtic.core.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    public List<Compra> obtenerTodas() {
        return compraRepository.findAll();
    }

    public Compra guardar(Compra compra) {
        return compraRepository.save(compra);
    }

    public Compra buscarPorId(Integer id) {
        Optional<Compra> optional = compraRepository.findById(id);
        return optional.orElse(null);
    }

    public void eliminar(Integer id) {
        compraRepository.deleteById(id);
    }
}
