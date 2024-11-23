package Api_CavaDorada.CavaDorada.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "tb_cabecera_venta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCabeceraVenta")
    private Integer idPedido;
    @Column(name = "fechaVenta",nullable = false)
    private LocalDateTime fechaPedido;
    @Column(name = "total",nullable = false,precision = 10,scale = 2)
    private BigDecimal total;
    @Column(name = "valorPagar", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorPagar;
    @Column(name = "estado",nullable = false)
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "idCliente",nullable = false)
    private Clientes clientes;
}
