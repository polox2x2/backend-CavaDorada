package Api_CavaDorada.CavaDorada.service;


import Api_CavaDorada.CavaDorada.entity.Categoria;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import Api_CavaDorada.CavaDorada.repository.CategoriaRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public Categoria agregarCategoria (Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public List<Categoria>obtenerCategorias (){
        return  categoriaRepository.findAll();
    }
    public Categoria getCategoriaById(Integer id) {
        return categoriaRepository.findById(id).orElseThrow();
    }

}
