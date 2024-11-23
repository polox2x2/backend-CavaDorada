package Api_CavaDorada.CavaDorada.service;


import Api_CavaDorada.CavaDorada.entity.Productos;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import Api_CavaDorada.CavaDorada.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;



    public List<Productos> getProductos (){
        return productoRepository.findAll();

    }
    public Optional<Productos> obtenerProductoPorId(Integer id) {
        return productoRepository.findById(id);
    }



    public void eliminarProducto(Integer id) {
        productoRepository.deleteById(id);
    }
    public Productos actualizarProducto(Integer id, Productos productoActualizado) {
        productoActualizado.setIdProducto(id);
        return productoRepository.save(productoActualizado);
    }

    public List<Productos> obtenerLicores() {
        return productoRepository.findAllLicores();
    }
    public  List<Productos>obtenerComidas(){
        return  productoRepository.findAllComida();
    }

    public List<Object[]> obtenerProductosLicores(){
        return productoRepository.findProductoPersonalizadosLicores();
    }
    public List<Object[]> obtenerProductosComidas(){
        return productoRepository.findProductoPersonalizadosComidas();
    }
    public void reducirStock(Integer cantidad , Integer idProducto){
        productoRepository.reducirStock(cantidad,idProducto);
    }

    /*public List<Object[]> PrecioOrdenar(){
        return productoRepository.OrdenarPorPrecio();
    }
        */

}
