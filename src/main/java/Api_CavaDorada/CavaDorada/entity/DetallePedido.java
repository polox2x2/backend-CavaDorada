package Api_CavaDorada.CavaDorada.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_detalle_venta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetalleVenta")
    private Integer idDetallePedido;
    @Column(name = "cantidad",nullable = false)
    private Integer cantidad;
    @Column(name = "precioUnitario",nullable = false,precision = 10,scale = 2)
    private BigDecimal precioUnitario;
    @Column(name = "subtotal",nullable = false,precision = 10,scale = 2)
    private BigDecimal subTotal;
    @Column(name = "descuento",nullable = false,precision = 10,scale = 2)
    private BigDecimal descuento;
    @Column(name = "iva",nullable = false,precision = 10,scale = 2)
    private BigDecimal iva;
    @Column(name = "totalPagar",nullable = false,precision = 10,scale = 2)
    private BigDecimal total;

    @Column(name = "estado",nullable = false)
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "idCabeceraVenta")
    private Pedidos pedidos;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Productos productos;

}
