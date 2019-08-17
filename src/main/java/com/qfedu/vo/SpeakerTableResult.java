package com.qfedu.vo;

import com.qfedu.entity.Speaker;

import java.util.List;

public class SpeakerTableResult {
    private int code;
    private String msg;
    private int count;
    private List<Speaker> data;

    public SpeakerTableResult() {
    }

    public SpeakerTableResult(int code, int count, List<Speaker> data) {
        this.code = code;
        this.count = count;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Speaker> getData() {
        return data;
    }

    public void setData(List<Speaker> data) {
        this.data = data;
    }
}
