package com.qfedu.service.Impl;

import com.qfedu.dao.CourseDao;
import com.qfedu.entity.Course;
import com.qfedu.service.CourseService;
import com.qfedu.vo.SubjectCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Override
    public List<Course> findAllCourse() {
        return courseDao.findAllCourse();
    }

    @Override
    public Integer findCourseSum() {
        return courseDao.findCourseSum();
    }

    @Override
    public void addCourse(Course course) {
        courseDao.add(course);
    }

    @Override
    public Course findById(Integer id) {
        return courseDao.findById(id);
    }

    @Override
    public void update(Course course) {
        courseDao.update(course);
    }

    @Override
    public void delete(Integer id) {
        courseDao.delete(id);
    }
}
