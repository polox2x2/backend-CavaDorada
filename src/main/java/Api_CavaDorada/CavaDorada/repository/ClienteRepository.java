package Api_CavaDorada.CavaDorada.repository;


import Api_CavaDorada.CavaDorada.entity.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ClienteRepository extends JpaRepository<Clientes, Integer> {
    Optional<Clientes> findByNombre(String nombre);
    Clientes findByEmailAndClave(String email, String clave);


    @Query("SELECT c.nombre , c.apellido, c.email, c.direccion FROM Clientes c WHERE c.estado=true")
    List<Object[]> findClientesPersonalizados();

    @Query("SELECT c FROM Clientes c WHERE c.estado = true")
    List<Clientes> findAllActivos();
}
