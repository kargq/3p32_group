package main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "class", schema = "public", catalog = "c3p32g02")
public class ClazzModel {
    private String clsName;

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
        ClazzModel that = (ClazzModel) o;
        return Objects.equals(clsName, that.clsName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clsName);
    }
}
