package Api_CavaDorada.CavaDorada.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "tb_usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Integer idUsuario;
    @Column(name = "usuario", nullable = false,length = 20)
    private String nombreUsuario;
    @Column(name = "password",nullable = false,length = 16)
    private String clave;
    @Column(name = "nombre",nullable = false,length = 20)
    private String nombre;
    @Column(name = "apellido",nullable = false,length = 20)
    private String apellido;
    @Column(name = "telefono",length = 15)
    private String telefono;
    @Column(name = "estado")
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = " idRol",nullable = false )
    private  Rol rol;
}
