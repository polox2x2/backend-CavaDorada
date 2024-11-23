package Api_CavaDorada.CavaDorada.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_cliente")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Clientes {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idCliente")
        private Integer idCliente;

        @Column(name = "nombre", nullable = false, length = 20)

        private String nombre;

        @Column(name = "apellido", nullable = false, length = 30)
        private String apellido;

        @Column(name = "direccion", nullable = false, length = 60)
        private String direccion;

        @Column(name = "telefono", nullable = false, length = 9)
        private String telefono;

        @Column(name = "cedula", nullable = false, length = 20)
        private String documentoIdentidad;

        @Column(name = "fecha_Registro")
        private LocalDateTime fechaRegistro;

        @Column(name = "fecha_Eliminacion")
        private LocalDateTime fechaEliminacion;

        @Column(name = "email", nullable = false, length = 100)
        private String email;

        @Column(name = "clave", length = 16)
        private String clave;

        @Column(name = "estado", nullable = false)
        private Boolean estado = true;

}
