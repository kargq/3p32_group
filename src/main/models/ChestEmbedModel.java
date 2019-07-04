package main.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "chest_embed", schema = "public", catalog = "c3p32g02")
@IdClass(ChestEmbedModelPK.class)
public class ChestEmbedModel {
    private int chestArmourInstanceId;
    private int gemId;

    @Id
    @Column(name = "chest_armour_instance_id")
    public int getChestArmourInstanceId() {
        return chestArmourInstanceId;
    }

    public void setChestArmourInstanceId(int chestArmourInstanceId) {
        this.chestArmourInstanceId = chestArmourInstanceId;
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
        ChestEmbedModel that = (ChestEmbedModel) o;
        return chestArmourInstanceId == that.chestArmourInstanceId &&
                gemId == that.gemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(chestArmourInstanceId, gemId);
    }
}
