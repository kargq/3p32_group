package main.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "main_weapon_instance", schema = "public", catalog = "c3p32g02")
public class MainWeaponInstanceModel {
    private Integer eqpId;
    private int mainWeaponInstanceId;

    @Basic
    @Column(name = "eqp_id")
    public Integer getEqpId() {
        return eqpId;
    }

    public void setEqpId(Integer eqpId) {
        this.eqpId = eqpId;
    }

    @Id
    @Column(name = "main_weapon_instance_id")
    public int getMainWeaponInstanceId() {
        return mainWeaponInstanceId;
    }

    public void setMainWeaponInstanceId(int mainWeaponInstanceId) {
        this.mainWeaponInstanceId = mainWeaponInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainWeaponInstanceModel that = (MainWeaponInstanceModel) o;
        return mainWeaponInstanceId == that.mainWeaponInstanceId &&
                Objects.equals(eqpId, that.eqpId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eqpId, mainWeaponInstanceId);
    }
}
