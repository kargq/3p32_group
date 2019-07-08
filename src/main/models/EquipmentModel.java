package main.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "equipment", schema = "public", catalog = "c3p32g02")
public class EquipmentModel {
    private int eqpId;
    private String eqpName;
    private Integer ewill;
    private Integer espeed;
    private Integer edefence;
    private Integer estrength;
    private Integer eblcok;
    private Integer epower;
    private Integer elife;
    private Integer elevel;
    private Integer gemLimit;

    @Override
    public String toString(){
        return this.eqpName;
    }

    @Id
    @Column(name = "eqp_id")
    public int getEqpId() {
        return eqpId;
    }

    public void setEqpId(int eqpId) {
        this.eqpId = eqpId;
    }

    @Basic
    @Column(name = "eqp_name")
    public String getEqpName() {
        return eqpName;
    }

    public void setEqpName(String eqpName) {
        this.eqpName = eqpName;
    }

    @Basic
    @Column(name = "ewill")
    public Integer getEwill() {
        return ewill;
    }

    public void setEwill(Integer ewill) {
        this.ewill = ewill;
    }

    @Basic
    @Column(name = "espeed")
    public Integer getEspeed() {
        return espeed;
    }

    public void setEspeed(Integer espeed) {
        this.espeed = espeed;
    }

    @Basic
    @Column(name = "edefence")
    public Integer getEdefence() {
        return edefence;
    }

    public void setEdefence(Integer edefence) {
        this.edefence = edefence;
    }

    @Basic
    @Column(name = "estrength")
    public Integer getEstrength() {
        return estrength;
    }

    public void setEstrength(Integer estrength) {
        this.estrength = estrength;
    }

    @Basic
    @Column(name = "eblcok")
    public Integer getEblcok() {
        return eblcok;
    }

    public void setEblcok(Integer eblcok) {
        this.eblcok = eblcok;
    }

    @Basic
    @Column(name = "epower")
    public Integer getEpower() {
        return epower;
    }

    public void setEpower(Integer epower) {
        this.epower = epower;
    }

    @Basic
    @Column(name = "elife")
    public Integer getElife() {
        return elife;
    }

    public void setElife(Integer elife) {
        this.elife = elife;
    }

    @Basic
    @Column(name = "elevel")
    public Integer getElevel() {
        return elevel;
    }

    public void setElevel(Integer elevel) {
        this.elevel = elevel;
    }

    @Basic
    @Column(name = "gem_limit")
    public Integer getGemLimit() {
        return gemLimit;
    }

    public void setGemLimit(Integer gemLimit) {
        this.gemLimit = gemLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentModel that = (EquipmentModel) o;
        return eqpId == that.eqpId &&
                Objects.equals(eqpName, that.eqpName) &&
                Objects.equals(ewill, that.ewill) &&
                Objects.equals(espeed, that.espeed) &&
                Objects.equals(edefence, that.edefence) &&
                Objects.equals(estrength, that.estrength) &&
                Objects.equals(eblcok, that.eblcok) &&
                Objects.equals(epower, that.epower) &&
                Objects.equals(elife, that.elife) &&
                Objects.equals(elevel, that.elevel) &&
                Objects.equals(gemLimit, that.gemLimit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eqpId, eqpName, ewill, espeed, edefence, estrength, eblcok, epower, elife, elevel, gemLimit);
    }
}
