package Api_CavaDorada.CavaDorada.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_historial_venta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Historialventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHistorial_venta")
    private Integer idHistorialVentas;
    @Column(name = "fecha",nullable = false)
    private LocalDateTime fecha;
    @Column(name = "total",nullable = false,precision = 10,scale = 2)
    private BigDecimal total;
    @Column(name = "estado",nullable = false)
    private boolean estado;
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedidos pedidos;

}
