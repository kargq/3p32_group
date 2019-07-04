package main.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "class_equipment", schema = "public", catalog = "c3p32g02")
@IdClass(ClassEquipmentModelPK.class)
public class ClassEquipmentModel {
    private String clsName;
    private int eqpId;

    @Id
    @Column(name = "cls_name")
    public String getClsName() {
        return clsName;
    }

    public void setClsName(String clsName) {
        this.clsName = clsName;
    }

    @Id
    @Column(name = "eqp_id")
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
        ClassEquipmentModel that = (ClassEquipmentModel) o;
        return eqpId == that.eqpId &&
                Objects.equals(clsName, that.clsName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clsName, eqpId);
    }
}
