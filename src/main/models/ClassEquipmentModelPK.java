package main.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ClassEquipmentModelPK implements Serializable {
    private String clsName;
    private int eqpId;

    @Column(name = "cls_name")
    @Id
    public String getClsName() {
        return clsName;
    }

    public void setClsName(String clsName) {
        this.clsName = clsName;
    }

    @Column(name = "eqp_id")
    @Id
    public int getEqpId() {
        return eqpId;
    }

    public void setEqpId(int eqpId) {
        this.eqpId = eqpId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassEquipmentModelPK that = (ClassEquipmentModelPK) o;
        return eqpId == that.eqpId &&
                Objects.equals(clsName, that.clsName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clsName, eqpId);
    }
}
