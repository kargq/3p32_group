package main.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "armour_embed", schema = "public", catalog = "c3p32g02")
@IdClass(ArmourEmbedModelPK.class)
public class ArmourEmbedModel {
    private int armourInstanceId;
    private int gemId;

    @Id
    @Column(name = "armour_instance_id")
    public int getArmourInstanceId() {
        return armourInstanceId;
    }

    public void setArmourInstanceId(int armourInstanceId) {
        this.armourInstanceId = armourInstanceId;
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
        ArmourEmbedModel that = (ArmourEmbedModel) o;
        return armourInstanceId == that.armourInstanceId &&
                gemId == that.gemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(armourInstanceId, gemId);
    }
}
