package com.qfedu.controller;

import com.qfedu.common.JsonResult;
import com.qfedu.entity.Course;
import com.qfedu.service.CourseService;
import com.qfedu.vo.CourseTableResult;
import com.qfedu.vo.SubjectCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/findAllCourse.do")
    @ResponseBody
    public JsonResult findAllCourse() {
        try {
            List<Course> allCourse = courseService.findAllCourse();
            return new JsonResult(1, allCourse);
        } catch (Exception e) {
            return new JsonResult(0, e.getMessage());
        }
    }

    @RequestMapping("/courseList.do")
    @ResponseBody
    public CourseTableResult courseList() {
        List<Course> allCourse = courseService.findAllCourse();
        return new CourseTableResult(0, courseService.findCourseSum(), allCourse);
    }

    @RequestMapping("/addCourse.do")
    @ResponseBody
    public JsonResult addCourse(Course course) {
        try {
            courseService.addCourse(course);
            return new JsonResult(1, "添加成功");
        } catch (Exception e) {
            return new JsonResult(0, e.getMessage());
        }
    }

    @RequestMapping("/findCourseById.do")
    @ResponseBody
    public JsonResult findCourseById(Integer id) {
        try {
            Course course = courseService.findById(id);
            return new JsonResult(1, course);
        } catch (Exception e) {
            return new JsonResult(0, e.getMessage());
        }
    }

    @RequestMapping("/modifyCourse.do")
    @ResponseBody
    public JsonResult modifyCourse(Course course) {
        try {
            courseService.update(course);
            return new JsonResult(1, "修改成功");
        } catch (Exception e) {
            return new JsonResult(0, e.getMessage());
        }
    }

    @RequestMapping("/deleteCourse.do")
    @ResponseBody
    public JsonResult deleteCourse(Integer id) {
        try {
            courseService.delete(id);
            return new JsonResult(1, "删除成功");
        } catch (Exception e) {
            return new JsonResult(0, e.getMessage());
        }
    }
}
