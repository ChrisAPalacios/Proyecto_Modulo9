package dgtic.core.repository;

import dgtic.core.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByCorreoAndPassword(String correo, String password);
    boolean existsByCorreo(String correo);
}
