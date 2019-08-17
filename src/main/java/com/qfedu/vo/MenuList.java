package com.qfedu.vo;

import com.qfedu.entity.Cmenu;

import java.util.List;

public class MenuList {
    private String pname;
    private List<Cmenu> cmenu;

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public List<Cmenu> getCmenu() {
        return cmenu;
    }

    public void setCmenu(List<Cmenu> cmenu) {
        this.cmenu = cmenu;
    }

    @Override
    public String toString() {
        return "MenuList{" +
                "pname='" + pname + '\'' +
                ", cmenu=" + cmenu +
                '}';
    }
}
