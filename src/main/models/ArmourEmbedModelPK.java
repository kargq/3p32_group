package main.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ArmourEmbedModelPK implements Serializable {
    private int armourInstanceId;
    private int gemId;

    @Column(name = "armour_instance_id")
    @Id
    public int getArmourInstanceId() {
        return armourInstanceId;
    }

    public void setArmourInstanceId(int armourInstanceId) {
        this.armourInstanceId = armourInstanceId;
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
        ArmourEmbedModelPK that = (ArmourEmbedModelPK) o;
        return armourInstanceId == that.armourInstanceId &&
                gemId == that.gemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(armourInstanceId, gemId);
    }
}
