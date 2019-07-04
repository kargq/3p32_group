package main.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class SecondaryEmbedModelPK implements Serializable {
    private int secondaryWeaponInstanceId;
    private int gemId;

    @Column(name = "secondary_weapon_instance_id")
    @Id
    public int getSecondaryWeaponInstanceId() {
        return secondaryWeaponInstanceId;
    }

    public void setSecondaryWeaponInstanceId(int secondaryWeaponInstanceId) {
        this.secondaryWeaponInstanceId = secondaryWeaponInstanceId;
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
        SecondaryEmbedModelPK that = (SecondaryEmbedModelPK) o;
        return secondaryWeaponInstanceId == that.secondaryWeaponInstanceId &&
                gemId == that.gemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(secondaryWeaponInstanceId, gemId);
    }
}
