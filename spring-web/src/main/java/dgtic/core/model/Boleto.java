package dgtic.core.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "BOLETO")
@Getter
@Setter
public class Boleto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BOLETO")
    private Integer idBoleto;

    @OneToOne
    @JoinColumn(name = "ID_ASIENTO", unique = true, nullable = false)
    private Asiento asiento;

    @ManyToOne
    @JoinColumn(name = "ID_EVENTO", nullable = false)
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "ID_COMPRA", nullable = false)
    private Compra compra;

    @Column(name = "ESTADO", nullable = false)
    private String estado; // DISPONIBLE, OCUPADO, CANCELADO

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ID_DEVOLUCION")
    private Devolucion devolucion;




}
