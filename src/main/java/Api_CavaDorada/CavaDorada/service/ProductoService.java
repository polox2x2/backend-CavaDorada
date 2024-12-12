package Api_CavaDorada.CavaDorada.service;

import Api_CavaDorada.CavaDorada.entity.Productos;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import Api_CavaDorada.CavaDorada.repository.ProductoRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    public List<Productos> getProductos() {
        return productoRepository.findAll();
    }

    public Optional<Productos> obtenerProductoPorId(Integer id) {
        return productoRepository.findById(id);
    }

    public List<Productos> obtenerLicores() {
        return productoRepository.findAllLicores();
    }

    public List<Productos> obtenerComidas() {
        return productoRepository.findAllComida();
    }

    public List<Map<String, Object>> obtenerProductosLicores() {
        return productoRepository.findProductoPersonalizadosLicores();
    }

    public List<Object[]> obtenerProductosComidas() {
        return productoRepository.findProductoPersonalizadosComidas();
    }

    public Productos reducirStock(Integer cantidad, Integer idProducto) {
        Productos producto = productoRepository.findById(idProducto)
                .orElseThrow();
        // Reducir el stock
        int nuevoStock = producto.getStock() - cantidad;
        if (nuevoStock < 0) {
            nuevoStock = 0;
        }
        producto.setStock(nuevoStock);
        if (nuevoStock == 0) {
            producto.setEstado(false);
        }productoRepository.save(producto);
        return producto;
    }
    public boolean eliminarProductoLogico(Integer id) {
        Productos producto = productoRepository.findById(id).orElse(null);
        if (producto != null) {
            producto.setEstado(false); // Cambio de estado a inactivo
            productoRepository.save(producto);
            return true;
        }
        return false;
    }

    public Productos actualizarProducto(Integer id, Productos productos) {
        Productos productoExistente = productoRepository.findById(id).orElse(null);
        if (productoExistente != null) {
            productoExistente.setNombre(productos.getNombre());
            productoExistente.setDescripcion(productos.getDescripcion());
            productoExistente.setPrecios(productos.getPrecios());
            productoExistente.setStock(productos.getStock());
            productoExistente.setCategoria(productos.getCategoria());
            productoExistente.setProvedor(productos.getProvedor());
            productoExistente.setPorcentajeIva(productos.getPorcentajeIva());
            productoExistente.setEstado(productos.getEstado()); // Si se desea cambiar el estado, hacerlo aquí
            return productoRepository.save(productoExistente);
        }
        return null;
    }

    public void actualizarProducto(Productos producto) {
        productoRepository.save(producto); // Guardar los cambios en el producto
    }

    // Buscar productos por nombre en la categoría "Licores"
    public List<Productos> buscarLicoresPorNombre(String busqueda) {
        return productoRepository.buscarLicoresPorNombre(busqueda);
    }

    // Buscar productos por nombre en la categoría "Comidas"
    public List<Productos> buscarComidasPorNombre(String busqueda) {
        return productoRepository.buscarComidasPorNombre(busqueda);
    }

    // Buscar productos por nombre y categoría
    public List<Productos> buscarProductos(String busqueda, String categoria) {
        if ("Bebidas Alcohólicas".equals(categoria)) {
            return buscarLicoresPorNombre(busqueda);
        } else if ("Comidas".equals(categoria)) {
            return buscarComidasPorNombre(busqueda);
        }
        return List.of();  // Si no se encuentra la categoría, retorna una lista vacía.
    }
}
