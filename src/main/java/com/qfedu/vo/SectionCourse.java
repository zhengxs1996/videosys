package com.qfedu.vo;

import com.qfedu.entity.Video;

import java.util.List;

public class SectionCourse {
    private String courseDesc;
    private List<VideoList> videoList;

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public List<VideoList> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<VideoList> videoList) {
        this.videoList = videoList;
    }
}
