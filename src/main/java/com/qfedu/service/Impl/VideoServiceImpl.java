package com.qfedu.service.Impl;

import com.github.pagehelper.PageHelper;
import com.qfedu.dao.VideoDao;
import com.qfedu.entity.Video;
import com.qfedu.service.VideoService;
import com.qfedu.vo.VideoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoDao videoDao;

    @Override
    public List<VideoList> videoList(Integer page, Integer limit, String keyWord, Integer speakerId, Integer courseId) {
        PageHelper.startPage(page, limit);
        List<VideoList> videoLists = videoDao.videoList(keyWord, speakerId, courseId);
        while(true){
            if (videoLists.size() != 0) {
                break;
            }
            PageHelper.startPage(--page, limit);
            videoLists = videoDao.videoList(keyWord, speakerId, courseId);
        }
        return videoLists;
    }

    @Override
    public int videoSum(String keyWord) {
        return videoDao.videoSum(keyWord);
    }

    @Override
    public void addVideo(Video video) {
        videoDao.add(video);
    }

    @Override
    public void deleteVideo(Integer id) {
        videoDao.delete(id);
    }

    @Override
    public Video findById(Integer id) {
        return videoDao.findById(id);
    }

    @Override
    public void modify(Video video) {
        videoDao.update(video);
    }

    @Override
    public void deleteAll(Integer[] id) {
        videoDao.deleteAll(id);
    }
}
