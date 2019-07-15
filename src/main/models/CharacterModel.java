package main.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "character", schema = "public", catalog = "c3p32g02")
public class CharacterModel {
    private String charName;
    private Integer charExperience;
    private Integer charSpeed;
    private Integer charBlock;
    private Integer charPower;
    private Integer charLevel;
    private Integer charWill;
    private Integer charLife;
    private Integer charDefence;
    private Integer charStrength;
    private String hasClass;
    private Integer armourEquipped;
    private Integer mainEquipped;
    private Integer secondaryEquipped;

    @Id
    @Column(name = "char_name")
    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    @Override
    public String toString() {
        return charName;
    }

    @Basic
    @Column(name = "char_experience")
    public Integer getCharExperience() {
        return charExperience;
    }

    public void setCharExperience(Integer charExperience) {
        this.charExperience = charExperience;
    }

    @Basic
    @Column(name = "char_speed")
    public Integer getCharSpeed() {
        return charSpeed;
    }

    public void setCharSpeed(Integer charSpeed) {
        this.charSpeed = charSpeed;
    }

    @Basic
    @Column(name = "char_block")
    public Integer getCharBlock() {
        return charBlock;
    }

    public void setCharBlock(Integer charBlock) {
        this.charBlock = charBlock;
    }

    @Basic
    @Column(name = "char_power")
    public Integer getCharPower() {
        return charPower;
    }

    public void setCharPower(Integer charPower) {
        this.charPower = charPower;
    }

    @Basic
    @Column(name = "char_level")
    public Integer getCharLevel() {
        return charLevel;
    }

    public void setCharLevel(Integer charLevel) {
        this.charLevel = charLevel;
    }

    @Basic
    @Column(name = "char_will")
    public Integer getCharWill() {
        return charWill;
    }

    public void setCharWill(Integer charWill) {
        this.charWill = charWill;
    }

    @Basic
    @Column(name = "char_life")
    public Integer getCharLife() {
        return charLife;
    }

    public void setCharLife(Integer charLife) {
        this.charLife = charLife;
    }

    @Basic
    @Column(name = "char_defence")
    public Integer getCharDefence() {
        return charDefence;
    }

    public void setCharDefence(Integer charDefence) {
        this.charDefence = charDefence;
    }

    @Basic
    @Column(name = "char_strength")
    public Integer getCharStrength() {
        return charStrength;
    }

    public void setCharStrength(Integer charStrength) {
        this.charStrength = charStrength;
    }

    @Basic
    @Column(name = "has_class")
    public String getHasClass() {
        return hasClass;
    }

    public void setHasClass(String hasClass) {
        this.hasClass = hasClass;
    }

    @Basic
    @Column(name = "armour_equipped")
    public Integer getArmourEquipped() {
        return armourEquipped;
    }

    public void setArmourEquipped(Integer armourEquipped) {
        this.armourEquipped = armourEquipped;
    }

    @Basic
    @Column(name = "main_equipped")
    public Integer getMainEquipped() {
        return mainEquipped;
    }

    public void setMainEquipped(Integer mainEquipped) {
        this.mainEquipped = mainEquipped;
    }

    @Basic
    @Column(name = "secondary_equipped")
    public Integer getSecondaryEquipped() {
        return secondaryEquipped;
    }

    public void setSecondaryEquipped(Integer secondaryEquipped) {
        this.secondaryEquipped = secondaryEquipped;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterModel that = (CharacterModel) o;
        return Objects.equals(charName, that.charName) &&
                Objects.equals(charExperience, that.charExperience) &&
                Objects.equals(charSpeed, that.charSpeed) &&
                Objects.equals(charBlock, that.charBlock) &&
                Objects.equals(charPower, that.charPower) &&
                Objects.equals(charLevel, that.charLevel) &&
                Objects.equals(charWill, that.charWill) &&
                Objects.equals(charLife, that.charLife) &&
                Objects.equals(charDefence, that.charDefence) &&
                Objects.equals(charStrength, that.charStrength) &&
                Objects.equals(hasClass, that.hasClass) &&
                Objects.equals(armourEquipped, that.armourEquipped) &&
                Objects.equals(mainEquipped, that.mainEquipped) &&
                Objects.equals(secondaryEquipped, that.secondaryEquipped);
    }

    @Override
    public int hashCode() {
        return Objects.hash(charName, charExperience, charSpeed, charBlock, charPower, charLevel, charWill, charLife, charDefence, charStrength, hasClass, armourEquipped, mainEquipped, secondaryEquipped);
    }


}
