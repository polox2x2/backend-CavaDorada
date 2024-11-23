package Api_CavaDorada.CavaDorada.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Entity
@Table(name = "tb_producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;
    @Column(name = "nombre",nullable=false, length = 100)
    private String  nombre;
    @Column(name="precio",nullable = false)
    private BigDecimal precios;
    @Column(name = "cantidad",nullable = false)
    private Integer stock;
    @Column (name = "descripcion",nullable = false,length = 230)
    private String descripcion ;
    @Column(name = "estado",nullable = false)
    private Boolean estado = true;
    @Column(name="porcentajeIva")
    private Integer porcentajeIva;

    @ManyToOne
    @JoinColumn(name = "categoria_id",nullable = false )
    private  Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "provedor_id",nullable = false )
    private Provedor provedor;
}
