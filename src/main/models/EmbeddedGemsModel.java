package main.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "embedded_gems", schema = "public", catalog = "c3p32g02")
@IdClass(EmbeddedGemsModelPK.class)
public class EmbeddedGemsModel {
    private int gemId;
    private int eqpId;

    @Id
    @Column(name = "gem_id")
    public int getGemId() {
        return gemId;
    }

    public void setGemId(int gemId) {
        this.gemId = gemId;
    }

    @Id
    @Column(name = "eqp_id")
    public int getEqpId() {
        return eqpId;
    }

    public void setEqpId(int eqpId) {
        this.eqpId = eqpId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmbeddedGemsModel that = (EmbeddedGemsModel) o;
        return gemId == that.gemId &&
                eqpId == that.eqpId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gemId, eqpId);
    }
}
