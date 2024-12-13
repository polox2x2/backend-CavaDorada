package Api_CavaDorada.CavaDorada.controller;

import Api_CavaDorada.CavaDorada.entity.Categoria;
import Api_CavaDorada.CavaDorada.entity.Productos;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Api_CavaDorada.CavaDorada.service.ProductoService;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        if (productos.getNombre() == null || productos.getNombre().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
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
    @PutMapping("/activar/{id}")
    public ResponseEntity<String> activarProducto(@PathVariable Integer id) {
        boolean activado = productoService.activarProducto(id);
        if (activado) {
            return ResponseEntity.ok("Producto activado correctamente.");
        } else {
            return ResponseEntity.status(400).body("No se pudo activar el producto.");
        }
    }
    @PutMapping("/actualizar-stock/{id}")
    public ResponseEntity<Productos> actualizarStock(@PathVariable Integer id, @RequestParam Integer cantidad) {
        Optional<Productos> productoOpt = productoService.obtenerProductoPorId(id);
        if (productoOpt.isPresent()) {
            Productos producto = productoOpt.get();
            // Actualizar solo el stock
            producto.setStock(cantidad);
            productoService.actualizarProducto(producto); // Guardar cambios
            return ResponseEntity.ok(producto);
        }
        return ResponseEntity.status(404).body(null); // Si no existe el producto
    }
    @PostMapping("/crear")
    public ResponseEntity<Productos> crearProducto(@RequestBody Productos producto) {
        // Establecer categoría predeterminada si no se envía
        if (producto.getCategoria() == null) {
            // Obtener la categoría de "Bebidas Alcohólicas" (suponiendo que tienes una categoría con ese nombre)
            Categoria categoria = productoService.obtenerCategoriaPorDescripcion("Bebidas Alcohólicas");
            if (categoria == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Si no se encuentra la categoría
            }
            producto.setCategoria(categoria);
        }

        // Verificar que el proveedor esté presente
        if (producto.getProvedor() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Proveedor no puede ser nulo
        }

        // Crear el producto
        Productos productoCreado = productoService.crearProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoCreado);
    }

}
