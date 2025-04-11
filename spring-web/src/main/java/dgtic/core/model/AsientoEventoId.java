package dgtic.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class AsientoEventoId implements Serializable {

    @Column(name = "ID_EVENTO")
    private Integer idEvento;

    @Column(name = "ID_ASIENTO")
    private Integer idAsiento;

    public AsientoEventoId() {
    }

    public AsientoEventoId(Integer idEvento, Integer idAsiento) {
        this.idEvento = idEvento;
        this.idAsiento = idAsiento;
    }
}
