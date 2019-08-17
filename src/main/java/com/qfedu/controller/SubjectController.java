package com.qfedu.controller;

import com.qfedu.common.JsonResult;
import com.qfedu.entity.Subject;
import com.qfedu.service.SubjectService;
import com.qfedu.vo.SubjectCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @RequestMapping("/findAllSubject.do")
    @ResponseBody
    public JsonResult findAllSubject() {
        try {
            List<Subject> subjects = subjectService.findAll();
            return new JsonResult(1, subjects);
        } catch (Exception e) {
            return new JsonResult(0, e.getMessage());
        }
    }

    @RequestMapping("/course/list.do")
    public String courseList(Integer subjectId, Model model) {
        SubjectCourse subject = subjectService.findSubjectCourseList(subjectId);
        model.addAttribute("subject", subject);
        return "before/course";
    }
}
