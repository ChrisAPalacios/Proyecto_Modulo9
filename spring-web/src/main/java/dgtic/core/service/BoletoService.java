package dgtic.core.service;

import dgtic.core.model.Boleto;
import dgtic.core.repository.BoletoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoletoService {

    @Autowired
    private BoletoRepository boletoRepository;

    public List<Boleto> obtenerTodos() {
        return boletoRepository.findAll();
    }

    public Boleto guardar(Boleto boleto) {
        return boletoRepository.save(boleto);
    }

    public Boleto buscarPorId(Integer id) {
        Optional<Boleto> optional = boletoRepository.findById(id);
        return optional.orElse(null);
    }

    public void eliminar(Integer id) {
        boletoRepository.deleteById(id);
    }

    public List<Boleto> buscarPorEvento(Integer idEvento) {
        return boletoRepository.findByEvento_IdEvento(idEvento);
    }

    public List<Boleto> buscarPorCompra(Integer idCompra) {
        return boletoRepository.findByCompra_IdCompra(idCompra);
    }

    public List<Boleto> buscarPorAsiento(Integer idAsiento) {
        return boletoRepository.findByAsiento_IdAsiento(idAsiento);
    }

    public List<Boleto> obtenerPorCompra(Integer idCompra) {
        return boletoRepository.findByCompra_IdCompra(idCompra);
    }

    public List<Boleto> obtenerBoletosPorEvento(Integer idEvento) {
        return boletoRepository.findByEvento_IdEvento(idEvento);
    }

    public List<Boleto> obtenerBoletosPorEventoYZona(Integer idEvento, Integer idZona) {
        return boletoRepository.findByEvento_IdEventoAndAsiento_Zona_IdZona(idEvento, idZona);
    }
}
