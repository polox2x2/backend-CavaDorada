package Api_CavaDorada.CavaDorada.controller;

import Api_CavaDorada.CavaDorada.entity.Productos;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Api_CavaDorada.CavaDorada.service.ProductoService;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping ("/api/cart")
@AllArgsConstructor
public class ProductosController {

    private final ProductoService productoService;

    @GetMapping("/productos/{id}")
    public ResponseEntity<Productos> obtenerProductoPorId(@PathVariable Integer id) {
        return productoService.obtenerProductoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/lista")
    public List<Productos> obtenerTodosLosProductos() {
        return productoService.getProductos();
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
    public List<Map<String, Object>> obtenerProductosLicores() {
        return productoService.obtenerProductosLicores();
    }

    @GetMapping("/productos/comidas")
    public List<Object[]> obtenerProductosComidas() {
        return productoService.obtenerProductosComidas();
    }

    @PutMapping("/reducir-stock/{idProducto}")
    public ResponseEntity<String> reducirStock(@PathVariable Integer idProducto, @RequestParam Integer cantidad) {

        // Reducir el stock del producto
        Productos producto = productoService.reducirStock(cantidad, idProducto);

        // Verificar si el stock llegó a 0, en cuyo caso se cambia el estado a inactivo
        if (producto.getStock() == 0) {
            producto.setEstado(false); // Cambiar el estado a "inactivo"
            productoService.actualizarProducto(producto); // Actualizar el producto en la base de datos
        }

        return ResponseEntity.ok("Stock reducido exitosamente");
    }

    @PutMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Integer id) {
        boolean eliminado = productoService.eliminarProductoLogico(id);
        if (eliminado) {
            return ResponseEntity.ok("Producto eliminado correctamente.");
        } else {
            return ResponseEntity.status(400).body("No se pudo eliminar el producto.");
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Productos> actualizarProducto(@PathVariable Integer id, @RequestBody Productos productos) {
        // Verifica que el nombre no esté vacío o nulo
        if (productos.getNombre() == null || productos.getNombre().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);  // O un mensaje de error más específico
        }

        Productos productoActualizado = productoService.actualizarProducto(id, productos);
        if (productoActualizado != null) {
            return ResponseEntity.ok(productoActualizado);
        }
        return ResponseEntity.status(404).body(null);
    }
    @GetMapping("/productos/buscar")
    public ResponseEntity<List<Productos>> buscarProductos(@RequestParam String busqueda, @RequestParam String categoria) {
        List<Productos> productos = productoService.buscarProductos(busqueda, categoria);
        return productos.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(productos);
    }






}
