package com.qfedu.controller;

import com.qfedu.common.JsonResult;
import com.qfedu.entity.Speaker;
import com.qfedu.service.SpeakerService;
import com.qfedu.vo.SpeakerTableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SpeakerController {

    @Autowired
    private SpeakerService speakerService;

    @RequestMapping("/findAllSpeaker.do")
    @ResponseBody
    public JsonResult findAllSpeaker() {
        try {
            List<Speaker> allSpeaker = speakerService.findAllSpeaker();
            return new JsonResult(1, allSpeaker);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(0, e.getMessage());
        }
    }

    @RequestMapping("/speakerList.do")
    @ResponseBody
    public SpeakerTableResult speakerList() {
        List<Speaker> speakers = speakerService.findAllSpeaker();
        return new SpeakerTableResult(0, speakerService.findSprakerSum(), speakers);
    }

    @RequestMapping("/addSpeaker.do")
    @ResponseBody
    public JsonResult addSpeaker(Speaker speaker) {
        try {
            speakerService.addSpeaker(speaker);
            return new JsonResult(1, "添加成功");
        } catch (Exception e) {
            return new JsonResult(0, e.getMessage());
        }
    }

    @RequestMapping("/deleteSpeaker.do")
    @ResponseBody
    public JsonResult deleteSpeaker(Integer id) {
        try {
            speakerService.deleteSpeaker(id);
            return new JsonResult(1, "删除成功");
        } catch (Exception e) {
            return new JsonResult(0, e.getMessage());
        }
    }

    @RequestMapping("/findSpeaker.do")
    @ResponseBody
    public JsonResult findSpeaker(Integer id) {
        try {
            Speaker speaker = speakerService.findSpeakerById(id);
            return new JsonResult(1, speaker);
        } catch (Exception e) {
            return new JsonResult(0, e.getMessage());
        }
    }

    @RequestMapping("/modifySpeaker.do")
    @ResponseBody
    public JsonResult modifySpeaker(Speaker speaker) {
        try {
            speakerService.modify(speaker);
            return new JsonResult(1, "修改成功");
        } catch (Exception e) {
            return new JsonResult(0, e.getMessage());
        }
    }

}
