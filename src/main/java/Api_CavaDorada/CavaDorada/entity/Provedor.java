package Api_CavaDorada.CavaDorada.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "tb_proveedor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Provedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProveedor")
    private Integer idProvedor;
    @Column(name = "nombre",nullable = false,length = 100)
    private String nombre;
    @Column(name = "telefono" , nullable = false,length = 9)
    private String telefono;
    @Column(name = "direccion", nullable = false , length = 100)
    private String direccion;
    @Column(name = "estado",nullable = false)
    private boolean estado = true;
}
