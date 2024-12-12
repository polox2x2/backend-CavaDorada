package Api_CavaDorada.CavaDorada.controller;


import Api_CavaDorada.CavaDorada.entity.Categoria;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import Api_CavaDorada.CavaDorada.service.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
@AllArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService ;

    @PostMapping()
    public Categoria agregarCategoria (@RequestBody Categoria categoria){
        return categoriaService.agregarCategoria(categoria);
    }
    @GetMapping()
    public List<Categoria>obtenerCategorias (){
        return categoriaService.obtenerCategorias();
    }
}
