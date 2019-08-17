package com.qfedu.entity;

import java.util.List;

public class Menu {
    private int id;
    private String name;
    private List<Cmenu> cmenu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Cmenu> getCmenu() {
        return cmenu;
    }

    public void setCmenu(List<Cmenu> cmenu) {
        this.cmenu = cmenu;
    }
}
