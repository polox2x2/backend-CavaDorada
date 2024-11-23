package Api_CavaDorada.CavaDorada.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_categoria")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategoria")
    private Integer idCategoria;
    @Column(name = "descripcion", nullable = false, length = 300)
    private String descripcion;
    @Column(name = "estado",nullable = false)
    private Boolean estado =true;

}
