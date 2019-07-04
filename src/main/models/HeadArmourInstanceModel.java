package main.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "head_armour_instance", schema = "public", catalog = "c3p32g02")
public class HeadArmourInstanceModel {
    private Integer eqpId;
    private int headArmourInstanceId;

    @Basic
    @Column(name = "eqp_id")
    public Integer getEqpId() {
        return eqpId;
    }

    public void setEqpId(Integer eqpId) {
        this.eqpId = eqpId;
    }

    @Id
    @Column(name = "head_armour_instance_id")
    public int getHeadArmourInstanceId() {
        return headArmourInstanceId;
    }

    public void setHeadArmourInstanceId(int headArmourInstanceId) {
        this.headArmourInstanceId = headArmourInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeadArmourInstanceModel that = (HeadArmourInstanceModel) o;
        return headArmourInstanceId == that.headArmourInstanceId &&
                Objects.equals(eqpId, that.eqpId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eqpId, headArmourInstanceId);
    }
}
