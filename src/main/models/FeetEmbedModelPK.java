package main.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class FeetEmbedModelPK implements Serializable {
    private int feetArmourInstanceId;
    private int gemId;

    @Column(name = "feet_armour_instance_id")
    @Id
    public int getFeetArmourInstanceId() {
        return feetArmourInstanceId;
    }

    public void setFeetArmourInstanceId(int feetArmourInstanceId) {
        this.feetArmourInstanceId = feetArmourInstanceId;
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
        FeetEmbedModelPK that = (FeetEmbedModelPK) o;
        return feetArmourInstanceId == that.feetArmourInstanceId &&
                gemId == that.gemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feetArmourInstanceId, gemId);
    }
}
