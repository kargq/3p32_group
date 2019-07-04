package main.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class LegEmbedModelPK implements Serializable {
    private int legsArmourInstanceId;
    private int gemId;

    @Column(name = "legs_armour_instance_id")
    @Id
    public int getLegsArmourInstanceId() {
        return legsArmourInstanceId;
    }

    public void setLegsArmourInstanceId(int legsArmourInstanceId) {
        this.legsArmourInstanceId = legsArmourInstanceId;
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
        LegEmbedModelPK that = (LegEmbedModelPK) o;
        return legsArmourInstanceId == that.legsArmourInstanceId &&
                gemId == that.gemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(legsArmourInstanceId, gemId);
    }
}
