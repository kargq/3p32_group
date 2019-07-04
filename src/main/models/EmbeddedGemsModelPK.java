package main.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class EmbeddedGemsModelPK implements Serializable {
    private int gemId;
    private int eqpId;

    @Column(name = "gem_id")
    @Id
    public int getGemId() {
        return gemId;
    }

    public void setGemId(int gemId) {
        this.gemId = gemId;
    }

    @Column(name = "eqp_id")
    @Id
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
        EmbeddedGemsModelPK that = (EmbeddedGemsModelPK) o;
        return gemId == that.gemId &&
                eqpId == that.eqpId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gemId, eqpId);
    }
}
