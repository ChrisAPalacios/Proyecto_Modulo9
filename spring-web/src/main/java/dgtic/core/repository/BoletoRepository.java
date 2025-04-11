package dgtic.core.repository;

import dgtic.core.model.Boleto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoletoRepository extends JpaRepository<Boleto, Integer> {
    List<Boleto> findByEvento_IdEvento(Integer idEvento);
    List<Boleto> findByCompra_IdCompra(Integer idCompra);
    List<Boleto> findByAsiento_IdAsiento(Integer idAsiento);
    List<Boleto> findByEvento_IdEventoAndAsiento_Zona_IdZona(Integer idEvento, Integer idZona);

}
