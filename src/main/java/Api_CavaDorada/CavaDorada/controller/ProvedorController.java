package Api_CavaDorada.CavaDorada.controller;



import Api_CavaDorada.CavaDorada.entity.Provedor;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import Api_CavaDorada.CavaDorada.service.ProvedorService;

import java.util.List;

@RestController
@RequestMapping ("/api/provedor")
@AllArgsConstructor
public class ProvedorController {

    private final ProvedorService provedorService;

    @GetMapping()
    public List<Provedor> obtenerProvedores (Provedor provedor){
        return provedorService.getProvedores();
    }
    @PostMapping()
    public Provedor agregarProvedor (@RequestBody Provedor provedor){
        return provedorService.agregarProvedor(provedor);
    }
}
