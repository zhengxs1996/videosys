package com.qfedu.service.Impl;

import com.qfedu.dao.SubjectDao;
import com.qfedu.entity.Subject;
import com.qfedu.service.SubjectService;
import com.qfedu.vo.SectionCourse;
import com.qfedu.vo.SectionVideo;
import com.qfedu.vo.SubjectCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectDao subjectDao;

    @Override
    public List<Subject> findAll() {
        return subjectDao.findAll();
    }

    @Override
    public SubjectCourse findSubjectCourseList(Integer subjectId) {
        return subjectDao.findSubjectCourseList(subjectId);
    }

    @Override
    public SectionVideo findVideo(Integer videoId) {
        return subjectDao.findVideo(videoId);
    }

    @Override
    public SectionCourse findCourse(Integer videoId) {
        return subjectDao.findCourse(videoId);
    }
}
