package main.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class HeadEmbedModelPK implements Serializable {
    private int headArmourInstanceId;
    private int gemId;

    @Column(name = "head_armour_instance_id")
    @Id
    public int getHeadArmourInstanceId() {
        return headArmourInstanceId;
    }

    public void setHeadArmourInstanceId(int headArmourInstanceId) {
        this.headArmourInstanceId = headArmourInstanceId;
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
        HeadEmbedModelPK that = (HeadEmbedModelPK) o;
        return headArmourInstanceId == that.headArmourInstanceId &&
                gemId == that.gemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(headArmourInstanceId, gemId);
    }
}
