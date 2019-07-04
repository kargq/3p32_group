package main.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class AutoSkillModelPK implements Serializable {
    private int skillId;
    private String clsName;

    @Column(name = "skill_id")
    @Id
    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @Column(name = "cls_name")
    @Id
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
        AutoSkillModelPK that = (AutoSkillModelPK) o;
        return skillId == that.skillId &&
                Objects.equals(clsName, that.clsName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillId, clsName);
    }
}
