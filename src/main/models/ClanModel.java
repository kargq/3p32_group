package main.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "clan", schema = "public", catalog = "c3p32g02")
public class ClanModel {
    private String clanname;
    private String chief;

    @Id
    @Column(name = "clanname")
    public String getClanname() {
        return clanname;
    }

    public void setClanname(String clanname) {
        this.clanname = clanname;
    }

    @Basic
    @Column(name = "chief")
    public String getChief() {
        return chief;
    }

    public void setChief(String chief) {
        this.chief = chief;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClanModel clanModel = (ClanModel) o;
        return Objects.equals(clanname, clanModel.clanname) &&
                Objects.equals(chief, clanModel.chief);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clanname, chief);
    }
}
