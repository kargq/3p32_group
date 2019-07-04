package main.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "secondary_equipment_instance", schema = "public", catalog = "c3p32g02")
public class SecondaryEquipmentInstanceModel {
    private Integer eqpId;
    private int secondaryWeaponInstanceId;

    @Basic
    @Column(name = "eqp_id")
    public Integer getEqpId() {
        return eqpId;
    }

    public void setEqpId(Integer eqpId) {
        this.eqpId = eqpId;
    }

    @Id
    @Column(name = "secondary_weapon_instance_id")
    public int getSecondaryWeaponInstanceId() {
        return secondaryWeaponInstanceId;
    }

    public void setSecondaryWeaponInstanceId(int secondaryWeaponInstanceId) {
        this.secondaryWeaponInstanceId = secondaryWeaponInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecondaryEquipmentInstanceModel that = (SecondaryEquipmentInstanceModel) o;
        return secondaryWeaponInstanceId == that.secondaryWeaponInstanceId &&
                Objects.equals(eqpId, that.eqpId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eqpId, secondaryWeaponInstanceId);
    }
}
