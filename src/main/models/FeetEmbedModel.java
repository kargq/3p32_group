package main.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "feet_embed", schema = "public", catalog = "c3p32g02")
@IdClass(FeetEmbedModelPK.class)
public class FeetEmbedModel {
    private int feetArmourInstanceId;
    private int gemId;

    @Id
    @Column(name = "feet_armour_instance_id")
    public int getFeetArmourInstanceId() {
        return feetArmourInstanceId;
    }

    public void setFeetArmourInstanceId(int feetArmourInstanceId) {
        this.feetArmourInstanceId = feetArmourInstanceId;
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
        FeetEmbedModel that = (FeetEmbedModel) o;
        return feetArmourInstanceId == that.feetArmourInstanceId &&
                gemId == that.gemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feetArmourInstanceId, gemId);
    }
}
