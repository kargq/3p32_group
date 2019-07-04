package main.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "legs_armour_instance", schema = "public", catalog = "c3p32g02")
public class LegsArmourInstanceModel {
    private Integer eqpId;
    private int legsArmourInstanceId;

    @Basic
    @Column(name = "eqp_id")
    public Integer getEqpId() {
        return eqpId;
    }

    public void setEqpId(Integer eqpId) {
        this.eqpId = eqpId;
    }

    @Id
    @Column(name = "legs_armour_instance_id")
    public int getLegsArmourInstanceId() {
        return legsArmourInstanceId;
    }

    public void setLegsArmourInstanceId(int legsArmourInstanceId) {
        this.legsArmourInstanceId = legsArmourInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LegsArmourInstanceModel that = (LegsArmourInstanceModel) o;
        return legsArmourInstanceId == that.legsArmourInstanceId &&
                Objects.equals(eqpId, that.eqpId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eqpId, legsArmourInstanceId);
    }
}
