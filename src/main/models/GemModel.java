package main.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "gem", schema = "public", catalog = "c3p32g02")
public class GemModel {
    private int gemId;
    private Integer glife;
    private Integer gpower;
    private String gname;
    private Integer gspeed;
    private Integer gdefence;
    private Integer gwill;
    private Integer gstrength;
    private Integer glevel;
    private Integer gblock;

    @Override
    public String toString(){
        return this.gname;
    }

    @Id
    @Column(name = "gem_id")
    public int getGemId() {
        return gemId;
    }

    public void setGemId(int gemId) {
        this.gemId = gemId;
    }

    @Basic
    @Column(name = "glife")
    public Integer getGlife() {
        return glife;
    }

    public void setGlife(Integer glife) {
        this.glife = glife;
    }

    @Basic
    @Column(name = "gpower")
    public Integer getGpower() {
        return gpower;
    }

    public void setGpower(Integer gpower) {
        this.gpower = gpower;
    }

    @Basic
    @Column(name = "gname")
    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    @Basic
    @Column(name = "gspeed")
    public Integer getGspeed() {
        return gspeed;
    }

    public void setGspeed(Integer gspeed) {
        this.gspeed = gspeed;
    }

    @Basic
    @Column(name = "gdefence")
    public Integer getGdefence() {
        return gdefence;
    }

    public void setGdefence(Integer gdefence) {
        this.gdefence = gdefence;
    }

    @Basic
    @Column(name = "gwill")
    public Integer getGwill() {
        return gwill;
    }

    public void setGwill(Integer gwill) {
        this.gwill = gwill;
    }

    @Basic
    @Column(name = "gstrength")
    public Integer getGstrength() {
        return gstrength;
    }

    public void setGstrength(Integer gstrength) {
        this.gstrength = gstrength;
    }

    @Basic
    @Column(name = "glevel")
    public Integer getGlevel() {
        return glevel;
    }

    public void setGlevel(Integer glevel) {
        this.glevel = glevel;
    }

    @Basic
    @Column(name = "gblock")
    public Integer getGblock() {
        return gblock;
    }

    public void setGblock(Integer gblock) {
        this.gblock = gblock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GemModel gemModel = (GemModel) o;
        return gemId == gemModel.gemId &&
                Objects.equals(glife, gemModel.glife) &&
                Objects.equals(gpower, gemModel.gpower) &&
                Objects.equals(gname, gemModel.gname) &&
                Objects.equals(gspeed, gemModel.gspeed) &&
                Objects.equals(gdefence, gemModel.gdefence) &&
                Objects.equals(gwill, gemModel.gwill) &&
                Objects.equals(gstrength, gemModel.gstrength) &&
                Objects.equals(glevel, gemModel.glevel) &&
                Objects.equals(gblock, gemModel.gblock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gemId, glife, gpower, gname, gspeed, gdefence, gwill, gstrength, glevel, gblock);
    }
}
