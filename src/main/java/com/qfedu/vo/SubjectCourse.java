package com.qfedu.vo;

import com.qfedu.entity.Course;

import java.util.List;

public class SubjectCourse {
    private Integer subjectId;
    private String subjectName;
    private List<CourseVideo> courseList;

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public List<CourseVideo> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseVideo> courseList) {
        this.courseList = courseList;
    }
}
