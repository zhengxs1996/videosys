package com.qfedu.service;

import com.qfedu.entity.Subject;
import com.qfedu.vo.SectionCourse;
import com.qfedu.vo.SectionVideo;
import com.qfedu.vo.SubjectCourse;

import java.util.List;

public interface SubjectService {
    public List<Subject> findAll();

    public SubjectCourse findSubjectCourseList(Integer subjectId);

    public SectionVideo findVideo(Integer videoId);

    public SectionCourse findCourse(Integer videoId);
}
