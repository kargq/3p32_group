package main.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class MainEmbedModelPK implements Serializable {
    private int mainWeaponInstanceId;
    private int gemId;

    @Column(name = "main_weapon_instance_id")
    @Id
    public int getMainWeaponInstanceId() {
        return mainWeaponInstanceId;
    }

    public void setMainWeaponInstanceId(int mainWeaponInstanceId) {
        this.mainWeaponInstanceId = mainWeaponInstanceId;
    }

    @Column(name = "gem_id")
    @Id
    public int getGemId() {
        return gemId;
    }

    public void setGemId(int gemId) {
        this.gemId = gemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainEmbedModelPK that = (MainEmbedModelPK) o;
        return mainWeaponInstanceId == that.mainWeaponInstanceId &&
                gemId == that.gemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mainWeaponInstanceId, gemId);
    }
}
