package Api_CavaDorada.CavaDorada.repository;



import Api_CavaDorada.CavaDorada.entity.Categoria;
import Api_CavaDorada.CavaDorada.entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ProductoRepository extends JpaRepository<Productos, Integer> {


    @Query("SELECT new map(p.idProducto as id, p.nombre as nombre, p.descripcion as descripcion, pr.nombre as proveedor, p.categoria.descripcion as categoria, p.precios as precio, p.stock as cantidad, p.estado as estado) " +
            "FROM Productos p JOIN p.provedor pr WHERE pr.estado = true AND p.categoria.descripcion ='Bebidas Alcohólicas'")
    List<Map<String, Object>> findProductoPersonalizadosLicores();

    @Query("SELECT p.nombre,p.descripcion, pr.nombre , p.categoria, p.precios, p.stock, p.estado " +
            "FROM Productos p  JOIN p.provedor pr WHERE pr.estado = true AND p.categoria.descripcion ='Comidas'" )
    List<Object[]> findProductoPersonalizadosComidas();

    @Query("SELECT p FROM Productos p WHERE p.categoria.descripcion = 'Bebidas Alcohólicas' AND p.estado = true")
    List<Productos> findAllLicores();

    @Query("SELECT p FROM Productos p WHERE p.categoria.descripcion = 'Comidas' AND p.estado = true")
    List<Productos> findAllComida();


    @Modifying
    @Transactional
    @Query("UPDATE Productos p SET p.stock = p.stock - :cantidad WHERE p.idProducto = :idProducto")
    void reducirStock(@Param("cantidad") Integer cantidad, @Param("idProducto") Integer idProducto);

    @Query("SELECT p FROM Productos p WHERE p.categoria.descripcion = 'Bebidas Alcohólicas' " +
            "AND p.estado = true AND LOWER(p.nombre) LIKE LOWER(CONCAT('%', :busqueda, '%'))")
    List<Productos> buscarLicoresPorNombre(@Param("busqueda") String busqueda);

    // Consulta personalizada para obtener productos de comidas por nombre y categoría
    @Query("SELECT p FROM Productos p WHERE p.categoria.descripcion = 'Comidas' " +
            "AND p.estado = true AND LOWER(p.nombre) LIKE LOWER(CONCAT('%', :busqueda, '%'))")
    List<Productos> buscarComidasPorNombre(@Param("busqueda") String busqueda);

    @Query("SELECT c FROM Categoria c WHERE c.descripcion = :descripcion")
    Categoria findCategoriaByDescripcion(@Param("descripcion") String descripcion);

}

/*
    @Query()
    List<Object[]> OrdenarPorCategoria();

    @Query("")
   List<Object[]> OrdenarPorPrecio();

    @Query()
    List<Object[]> OrdenarPorNombre();

*/

