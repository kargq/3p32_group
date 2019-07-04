package main.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "secondary_embed", schema = "public", catalog = "c3p32g02")
@IdClass(SecondaryEmbedModelPK.class)
public class SecondaryEmbedModel {
    private int secondaryWeaponInstanceId;
    private int gemId;

    @Id
    @Column(name = "secondary_weapon_instance_id")
    public int getSecondaryWeaponInstanceId() {
        return secondaryWeaponInstanceId;
    }

    public void setSecondaryWeaponInstanceId(int secondaryWeaponInstanceId) {
        this.secondaryWeaponInstanceId = secondaryWeaponInstanceId;
    }

    @Id
    @Column(name = "gem_id")
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
        SecondaryEmbedModel that = (SecondaryEmbedModel) o;
        return secondaryWeaponInstanceId == that.secondaryWeaponInstanceId &&
                gemId == that.gemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(secondaryWeaponInstanceId, gemId);
    }
}
