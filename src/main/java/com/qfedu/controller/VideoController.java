package com.qfedu.controller;

import com.github.pagehelper.Page;
import com.qfedu.common.JsonResult;
import com.qfedu.entity.Video;
import com.qfedu.service.VideoService;
import com.qfedu.vo.VideoList;
import com.qfedu.vo.VideoTableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class VideoController {

    @Autowired
    private VideoService videoService;

    @RequestMapping("/videoList.do")
    @ResponseBody
    public Map<String, Object> videoList(Integer page, Integer limit, String keyWord, Integer speakerId, Integer courseId) {
        List<VideoList> videos = videoService.videoList(page, limit, keyWord, speakerId, courseId);
        long total = ((Page) videos).getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", total);
        map.put("data", videos);
        return map;
    }

    @RequestMapping("/addVideo.do")
    @ResponseBody
    public JsonResult addVideo(Video video) {
        try {
            videoService.addVideo(video);
            return new JsonResult(1, "添加成功");
        } catch (Exception e) {
            return new JsonResult(0, e.getMessage());
        }
    }

    @RequestMapping("/deleteVideo.do")
    @ResponseBody
    public JsonResult deleteVideo(Integer id) {
        try {
            videoService.deleteVideo(id);
            return new JsonResult(1, "删除成功");
        } catch (Exception e) {
            return new JsonResult(0, e.getMessage());
        }
    }

    @RequestMapping("/findVideo.do")
    @ResponseBody
    public JsonResult findVideo(Integer id) {
        try {
            Video video = videoService.findById(id);
            return new JsonResult(1, video);
        } catch (Exception e) {
            return new JsonResult(0, e.getMessage());
        }
    }

    @RequestMapping("/modifyVideo.do")
    @ResponseBody
    public JsonResult modifyVideo(Video video) {
        try {
            videoService.modify(video);
            return new JsonResult(1, "编辑成功");
        } catch (Exception e) {
            return new JsonResult(0, e.getMessage());
        }
    }

    @RequestMapping("/deleteAll.do")
    @ResponseBody
    public JsonResult deleteAll(@RequestParam(value="id[]") Integer[] id) {
        try {
            videoService.deleteAll(id);
            return new JsonResult(1, "删除成功");
        } catch (Exception e) {
            return new JsonResult(0, e.getMessage());
        }
    }


}
