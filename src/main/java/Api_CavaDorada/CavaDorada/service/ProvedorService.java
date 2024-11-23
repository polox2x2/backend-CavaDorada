package Api_CavaDorada.CavaDorada.service;



import Api_CavaDorada.CavaDorada.entity.Provedor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import Api_CavaDorada.CavaDorada.repository.ProvedorRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProvedorService {

    private final ProvedorRepository provedorRepository;

    public List<Provedor> getProvedores (){
        return provedorRepository.findAll();
    }
    public Optional<Provedor> obtenerProvedorPorId(Integer id) {
        return provedorRepository.findById(id);
    }

    public Provedor agregarProvedor(Provedor provedor) {
        return provedorRepository.save(provedor);
    }

    public Provedor getProvedorById(Integer id) {
        return provedorRepository.findById(id).orElseThrow();
    }

}
