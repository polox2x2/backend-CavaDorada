package Api_CavaDorada.CavaDorada.repository;

import Api_CavaDorada.CavaDorada.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
}
