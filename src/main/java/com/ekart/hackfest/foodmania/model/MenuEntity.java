package com.ekart.hackfest.foodmania.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by rishabh.sood on 23/02/16.
 */
@Entity
@Table(name = "Menu", schema = "", catalog = "foodmania")
public class MenuEntity {
    private String menuid;
    private Timestamp starttime;
    private Timestamp endtime;

    @Id
    @Column(name = "MENUID")
    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    @Basic
    @Column(name = "STARTTIME")
    public Timestamp getStarttime() {
        return starttime;
    }

    public void setStarttime(Timestamp starttime) {
        this.starttime = starttime;
    }

    @Basic
    @Column(name = "ENDTIME")
    public Timestamp getEndtime() {
        return endtime;
    }

    public void setEndtime(Timestamp endtime) {
        this.endtime = endtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuEntity that = (MenuEntity) o;

        if (menuid != null ? !menuid.equals(that.menuid) : that.menuid != null) return false;
        if (starttime != null ? !starttime.equals(that.starttime) : that.starttime != null) return false;
        if (endtime != null ? !endtime.equals(that.endtime) : that.endtime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = menuid != null ? menuid.hashCode() : 0;
        result = 31 * result + (starttime != null ? starttime.hashCode() : 0);
        result = 31 * result + (endtime != null ? endtime.hashCode() : 0);
        return result;
    }
}
