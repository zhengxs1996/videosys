package com.qfedu.service;

import com.qfedu.entity.Video;
import com.qfedu.vo.VideoList;

import java.util.List;

public interface VideoService {
    public List<VideoList> videoList(Integer page, Integer limit, String keyWord, Integer speakerId, Integer courseId);

    public int videoSum(String keyWord);

    public void addVideo(Video video);

    public void deleteVideo(Integer id);

    public Video findById(Integer id);

    public void modify(Video video);

    public void deleteAll(Integer[] id);
}
