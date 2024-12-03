package Api_CavaDorada.CavaDorada.controller;

import Api_CavaDorada.CavaDorada.entity.Productos;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Api_CavaDorada.CavaDorada.service.ProductoService;
import java.util.List;

@RestController
@RequestMapping ("/api/cart")
@AllArgsConstructor
public class ProductosController {

    private final ProductoService productoService;

    @GetMapping("/{id}")
    public ResponseEntity<Productos> obtenerProductoPorId(@PathVariable Integer id) {
        return productoService.obtenerProductoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/lista")
    public List<Productos> obtenerTodosLosProductos() {
        return productoService.getProductos();
    }

    @PutMapping("/{id}")
    public Productos actualizarProducto(@PathVariable Integer id, @RequestBody Productos productos) {
        return productoService.actualizarProducto(id, productos);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Integer id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/licores")
    public List<Productos> obtenerLicores() {
        return productoService.obtenerLicores();
    }
    @GetMapping("/comidas")
    public List<Productos> obtenerComidas() {
        return productoService.obtenerComidas();
    }
    @GetMapping("/productos/licores")
    public List<Object[]>obtenerProductosLicores(){
        return productoService.obtenerProductosLicores();
    }
    @GetMapping("/productos/comidas")
    public List<Object[]>obtenerProductosComidas(){
        return productoService.obtenerProductosComidas();
    }
    @PutMapping("/reducir-stock/{idProducto}")
    public ResponseEntity<String> reducirStock(@PathVariable Integer idProducto, @RequestParam Integer cantidad) {
        productoService.reducirStock(cantidad, idProducto);
        return ResponseEntity.ok("Stock reducido exitosamente");
    }


}
