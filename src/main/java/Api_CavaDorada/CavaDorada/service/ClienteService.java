package Api_CavaDorada.CavaDorada.service;


import Api_CavaDorada.CavaDorada.entity.Clientes;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import Api_CavaDorada.CavaDorada.repository.ClienteRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Clientes CrearCliente(Clientes clientes) { // crear un nuevo cliente
        return clienteRepository.save(clientes);
    }

    public Clientes getClienteById( Integer idCliente) { //obtener por id de cliente
        return clienteRepository.findById(idCliente).orElseThrow();
    }

    public List<Clientes> getClientes() { //obtener todos los clientes
        return clienteRepository.findAll();
    }

    public Clientes findByNombre(String Nombre) { //obtener por nombre
        return  clienteRepository.findByNombre(Nombre).orElse(null);
    }
    public Clientes findByEmailAndClave(String email, String clave) {
        return clienteRepository.findByEmailAndClave(email, clave);
    }
    public Clientes updateCliente(Integer idCliente, Clientes clientes) {
        if (!clienteRepository.existsById(idCliente)) {
            return null;
        }
        clientes.setIdCliente(idCliente);
        return clienteRepository.save(clientes);
    }
    public List<Object[]> obtenerClientesPersonalizados() { //listado personalizado
        return clienteRepository.findClientesPersonalizados();
    }


    public void deleteLogicoCliente( Integer idCliente){
        Clientes clientes = clienteRepository.findById(idCliente).orElseThrow(null);
        clientes.setEstado(false);
        clienteRepository.save(clientes);
    }
    public void reactivarCliente( Integer idCliente){
        Clientes clientes = clienteRepository.findById(idCliente).orElseThrow(null);
        clientes.setEstado(true);
        clienteRepository.save(clientes);
    }

}

