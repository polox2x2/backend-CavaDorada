package Api_CavaDorada.CavaDorada.controller;

import Api_CavaDorada.CavaDorada.entity.Clientes;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Api_CavaDorada.CavaDorada.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@AllArgsConstructor
public class ClientesController {

        private final ClienteService clienteService;
    @PostMapping ("/crear")
    public Clientes CrearCliente (@RequestBody Clientes clientes){
        return clienteService.CrearCliente(clientes);
    }

    @GetMapping ("/lista")
    public List <Clientes> getClientes () {
        return clienteService.getClientes();
    }
    @GetMapping ("/cliente/{id}")
    public Clientes getClienteById(@PathVariable Integer id){
        return clienteService.getClienteById(id);

    }


    @PostMapping("/login")
    public ResponseEntity<Clientes> loginCliente(@RequestBody Clientes loginCliente) {
        System.out.println("Email: " + loginCliente.getEmail());
        System.out.println("Clave: " + loginCliente.getClave());

        // Buscar el cliente por email y clave
        Clientes cliente = clienteService.findByEmailAndClave(loginCliente.getEmail(), loginCliente.getClave());

        if (cliente != null) {
            // Si el cliente se encuentra, devolverlo como respuesta (estatus 200)
            return ResponseEntity.ok(cliente);
        } else {
            // Si las credenciales son incorrectas, devolver un error de autorización (estatus 401)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }


}
