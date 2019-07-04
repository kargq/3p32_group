package main.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "leg_embed", schema = "public", catalog = "c3p32g02")
@IdClass(LegEmbedModelPK.class)
public class LegEmbedModel {
    private int legsArmourInstanceId;
    private int gemId;

    @Id
    @Column(name = "legs_armour_instance_id")
    public int getLegsArmourInstanceId() {
        return legsArmourInstanceId;
    }

    public void setLegsArmourInstanceId(int legsArmourInstanceId) {
        this.legsArmourInstanceId = legsArmourInstanceId;
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
        LegEmbedModel that = (LegEmbedModel) o;
        return legsArmourInstanceId == that.legsArmourInstanceId &&
                gemId == that.gemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(legsArmourInstanceId, gemId);
    }
}
