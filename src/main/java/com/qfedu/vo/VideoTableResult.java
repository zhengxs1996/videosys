package com.qfedu.vo;

import java.util.List;

public class VideoTableResult {
    private int code;
    private String msg;
    private int count;
    private List<VideoList> data;

    public VideoTableResult() {
    }

    public VideoTableResult(int code, int count, List<VideoList> data) {
        this.code = code;
        this.count = count;
        this.data = data;
    }

    public VideoTableResult(int code, String msg, int count, List<VideoList> data) {
        this.code = code;
        this.msg = msg;
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

    public List<VideoList> getData() {
        return data;
    }

    public void setData(List<VideoList> data) {
        this.data = data;
    }
}
