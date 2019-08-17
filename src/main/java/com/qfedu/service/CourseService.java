package com.qfedu.service;

import com.qfedu.entity.Course;
import com.qfedu.vo.SubjectCourse;

import java.util.List;

public interface CourseService {
    public List<Course> findAllCourse();

    public Integer findCourseSum();

    public void addCourse(Course course);

    public Course findById(Integer id);

    public void update(Course course);

    public void delete(Integer id);


}
