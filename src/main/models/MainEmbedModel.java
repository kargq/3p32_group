package main.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "main_embed", schema = "public", catalog = "c3p32g02")
@IdClass(MainEmbedModelPK.class)
public class MainEmbedModel {
    private int mainWeaponInstanceId;
    private int gemId;

    @Id
    @Column(name = "main_weapon_instance_id")
    public int getMainWeaponInstanceId() {
        return mainWeaponInstanceId;
    }

    public void setMainWeaponInstanceId(int mainWeaponInstanceId) {
        this.mainWeaponInstanceId = mainWeaponInstanceId;
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
        MainEmbedModel that = (MainEmbedModel) o;
        return mainWeaponInstanceId == that.mainWeaponInstanceId &&
                gemId == that.gemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mainWeaponInstanceId, gemId);
    }
}
