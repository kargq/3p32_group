package main.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class HasEarnedModelPK implements Serializable {
    private int skillId;
    private String charName;

    @Column(name = "skill_id")
    @Id
    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @Column(name = "char_name")
    @Id
    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HasEarnedModelPK that = (HasEarnedModelPK) o;
        return skillId == that.skillId &&
                Objects.equals(charName, that.charName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillId, charName);
    }
}
