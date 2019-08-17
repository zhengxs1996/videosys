package com.qfedu.dao;

import com.qfedu.entity.Video;
import com.qfedu.vo.VideoList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoDao {
    public List<VideoList> videoList(@Param("keyWord") String keyWord,@Param("speakerId") Integer speakerId,
                                     @Param("courseId") Integer courseId);

    public void add(Video video);

    public void delete(Integer id);

    public int videoSum(@Param("keyWord") String keyWord);

    public Video findById(Integer id);

    public void update(Video video);

    public void deleteAll(Integer[] id);
}
