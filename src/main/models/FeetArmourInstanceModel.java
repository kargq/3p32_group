package main.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "feet_armour_instance", schema = "public", catalog = "c3p32g02")
public class FeetArmourInstanceModel {
    private Integer eqpId;
    private int feetArmourInstanceId;

    @Basic
    @Column(name = "eqp_id")
    public Integer getEqpId() {
        return eqpId;
    }

    public void setEqpId(Integer eqpId) {
        this.eqpId = eqpId;
    }

    @Id
    @Column(name = "feet_armour_instance_id")
    public int getFeetArmourInstanceId() {
        return feetArmourInstanceId;
    }

    public void setFeetArmourInstanceId(int feetArmourInstanceId) {
        this.feetArmourInstanceId = feetArmourInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeetArmourInstanceModel that = (FeetArmourInstanceModel) o;
        return feetArmourInstanceId == that.feetArmourInstanceId &&
                Objects.equals(eqpId, that.eqpId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eqpId, feetArmourInstanceId);
    }
}
