package com.qfedu.controller;

import com.qfedu.service.SubjectService;
import com.qfedu.vo.SectionCourse;
import com.qfedu.vo.SectionVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SectionController {

    @Autowired
    private SubjectService subjectService;

    @RequestMapping("/video/showVideo.do")
    public String section(Model model, Integer videoId, String subjectName) {
        model.addAttribute("subjectName", subjectName);
        SectionVideo video = subjectService.findVideo(videoId);
        SectionCourse course = subjectService.findCourse(videoId);
        model.addAttribute("video", video);
        model.addAttribute("course", course);
        return "before/section";
    }
}
