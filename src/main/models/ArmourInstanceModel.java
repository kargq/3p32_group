package main.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "armour_instance", schema = "public", catalog = "c3p32g02")
public class ArmourInstanceModel {
    private Integer eqpId;
    private int armourInstanceId;

    @Basic
    @Column(name = "eqp_id")
    public Integer getEqpId() {
        return eqpId;
    }

    public void setEqpId(Integer eqpId) {
        this.eqpId = eqpId;
    }

    @Id
    @Column(name = "armour_instance_id")
    public int getArmourInstanceId() {
        return armourInstanceId;
    }

    public void setArmourInstanceId(int armourInstanceId) {
        this.armourInstanceId = armourInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArmourInstanceModel that = (ArmourInstanceModel) o;
        return armourInstanceId == that.armourInstanceId &&
                Objects.equals(eqpId, that.eqpId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eqpId, armourInstanceId);
    }
}
