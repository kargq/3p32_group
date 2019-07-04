package main.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "skill", schema = "public", catalog = "c3p32g02")
public class SkillModel {
    private int skillId;
    private Integer minLevel;
    private Integer powerCost;
    private String sdescription;
    private String sname;

    @Id
    @Column(name = "skill_id")
    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @Basic
    @Column(name = "min_level")
    public Integer getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(Integer minLevel) {
        this.minLevel = minLevel;
    }

    @Basic
    @Column(name = "power_cost")
    public Integer getPowerCost() {
        return powerCost;
    }

    public void setPowerCost(Integer powerCost) {
        this.powerCost = powerCost;
    }

    @Basic
    @Column(name = "sdescription")
    public String getSdescription() {
        return sdescription;
    }

    public void setSdescription(String sdescription) {
        this.sdescription = sdescription;
    }

    @Basic
    @Column(name = "sname")
    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillModel that = (SkillModel) o;
        return skillId == that.skillId &&
                Objects.equals(minLevel, that.minLevel) &&
                Objects.equals(powerCost, that.powerCost) &&
                Objects.equals(sdescription, that.sdescription) &&
                Objects.equals(sname, that.sname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillId, minLevel, powerCost, sdescription, sname);
    }
}
