package main.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ChestEmbedModelPK implements Serializable {
    private int chestArmourInstanceId;
    private int gemId;

    @Column(name = "chest_armour_instance_id")
    @Id
    public int getChestArmourInstanceId() {
        return chestArmourInstanceId;
    }

    public void setChestArmourInstanceId(int chestArmourInstanceId) {
        this.chestArmourInstanceId = chestArmourInstanceId;
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
        ChestEmbedModelPK that = (ChestEmbedModelPK) o;
        return chestArmourInstanceId == that.chestArmourInstanceId &&
                gemId == that.gemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(chestArmourInstanceId, gemId);
    }
}
