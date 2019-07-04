package main.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "earned_skill", schema = "public", catalog = "c3p32g02")
@IdClass(EarnedSkillModelPK.class)
public class EarnedSkillModel {
    private int skillId;
    private String clsName;

    @Id
    @Column(name = "skill_id")
    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
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
        EarnedSkillModel that = (EarnedSkillModel) o;
        return skillId == that.skillId &&
                Objects.equals(clsName, that.clsName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillId, clsName);
    }
}
