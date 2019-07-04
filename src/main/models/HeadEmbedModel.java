package main.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "head_embed", schema = "public", catalog = "c3p32g02")
@IdClass(HeadEmbedModelPK.class)
public class HeadEmbedModel {
    private int headArmourInstanceId;
    private int gemId;

    @Id
    @Column(name = "head_armour_instance_id")
    public int getHeadArmourInstanceId() {
        return headArmourInstanceId;
    }

    public void setHeadArmourInstanceId(int headArmourInstanceId) {
        this.headArmourInstanceId = headArmourInstanceId;
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
        HeadEmbedModel that = (HeadEmbedModel) o;
        return headArmourInstanceId == that.headArmourInstanceId &&
                gemId == that.gemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(headArmourInstanceId, gemId);
    }
}
