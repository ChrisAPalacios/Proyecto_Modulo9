// Asiento.java
package dgtic.core.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "ASIENTO")
@Getter
@Setter
public class Asiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ASIENTO")
    private Integer idAsiento;

    @Pattern(regexp = "^[A-Z]-\\d{3}$", message = "Formato inválido. Ejemplo: A-001")
    @Column(name = "NUMERO_ASIENTO", unique = true)
    private String numeroAsiento;

    @ManyToOne
    @JoinColumn(name = "ID_ZONA", nullable = false)
    private Zona zona;

    @OneToOne(mappedBy = "asiento", cascade = CascadeType.ALL)
    private Boleto boleto;

    @OneToMany(mappedBy = "asiento")
    private List<Boleto> boletos;

}
