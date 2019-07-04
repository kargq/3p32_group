package main.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "chest_armour_instance", schema = "public", catalog = "c3p32g02")
public class ChestArmourInstanceModel {
    private Integer eqpId;
    private int chestArmourInstanceId;

    @Basic
    @Column(name = "eqp_id")
    public Integer getEqpId() {
        return eqpId;
    }

    public void setEqpId(Integer eqpId) {
        this.eqpId = eqpId;
    }

    @Id
    @Column(name = "chest_armour_instance_id")
    public int getChestArmourInstanceId() {
        return chestArmourInstanceId;
    }

    public void setChestArmourInstanceId(int chestArmourInstanceId) {
        this.chestArmourInstanceId = chestArmourInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChestArmourInstanceModel that = (ChestArmourInstanceModel) o;
        return chestArmourInstanceId == that.chestArmourInstanceId &&
                Objects.equals(eqpId, that.eqpId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eqpId, chestArmourInstanceId);
    }
}
