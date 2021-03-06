package main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "armour", schema = "public", catalog = "c3p32g02")
public class ArmourModel {
    private int eqpId;

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
        ArmourModel that = (ArmourModel) o;
        return eqpId == that.eqpId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eqpId);
    }
}
