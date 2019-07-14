package main.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "class", schema = "public", catalog = "c3p32g02")
public class ClazzModel {
    private String clsName;
    private Integer baseLife;
    private Integer basePower;
    private Integer baseStrength;
    private Integer baseDefence;
    private Integer baseWill;
    private Integer baseSpeed;

    @Override
    public String toString(){
        return this.clsName;
    }

    @Id
    @Column(name = "cls_name")
    public String getClsName() {
        return clsName;
    }

    public void setClsName(String clsName) {
        this.clsName = clsName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClazzModel that = (ClazzModel) o;
        return Objects.equals(clsName, that.clsName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clsName);
    }

    @Basic
    @Column(name = "base_life")
    public Integer getBaseLife() {
        return baseLife;
    }

    public void setBaseLife(Integer baseLife) {
        this.baseLife = baseLife;
    }

    @Basic
    @Column(name = "base_power")
    public Integer getBasePower() {
        return basePower;
    }

    public void setBasePower(Integer basePower) {
        this.basePower = basePower;
    }

    @Basic
    @Column(name = "base_strength")
    public Integer getBaseStrength() {
        return baseStrength;
    }

    public void setBaseStrength(Integer baseStrength) {
        this.baseStrength = baseStrength;
    }

    @Basic
    @Column(name = "base_defence")
    public Integer getBaseDefence() {
        return baseDefence;
    }

    public void setBaseDefence(Integer baseDefence) {
        this.baseDefence = baseDefence;
    }

    @Basic
    @Column(name = "base_will")
    public Integer getBaseWill() {
        return baseWill;
    }

    public void setBaseWill(Integer baseWill) {
        this.baseWill = baseWill;
    }

    @Basic
    @Column(name = "base_speed")
    public Integer getBaseSpeed() {
        return baseSpeed;
    }

    public void setBaseSpeed(Integer baseSpeed) {
        this.baseSpeed = baseSpeed;
    }
}
