package main.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "has_earned", schema = "public", catalog = "c3p32g02")
@IdClass(HasEarnedModelPK.class)
public class HasEarnedModel {
    private int skillId;
    private String charName;

    @Override
    public String toString(){
        return this.charName;
    }

    @Id
    @Column(name = "skill_id")
    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @Id
    @Column(name = "char_name")
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
        HasEarnedModel that = (HasEarnedModel) o;
        return skillId == that.skillId &&
                Objects.equals(charName, that.charName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillId, charName);
    }
}
