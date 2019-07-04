package main.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "clan_member", schema = "public", catalog = "c3p32g02")
public class ClanMemberModel {
    private String clnName;
    private String charName;

    @Basic
    @Column(name = "cln_name")
    public String getClnName() {
        return clnName;
    }

    public void setClnName(String clnName) {
        this.clnName = clnName;
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
        ClanMemberModel that = (ClanMemberModel) o;
        return Objects.equals(clnName, that.clnName) &&
                Objects.equals(charName, that.charName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clnName, charName);
    }
}
