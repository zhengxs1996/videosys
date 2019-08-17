package com.qfedu.dao;

import com.qfedu.entity.Course;

import java.util.List;

public interface CourseDao {
    public List<Course> findAllCourse();

    public Integer findCourseSum();

    public void add(Course course);

    public Course findById(Integer id);

    public void update(Course course);

    public void delete(Integer id);
}
