package com.qfedu.entity;

public class Cmenu {
    private String cname;
    private String url;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Cmenu{" +
                "cname='" + cname + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
