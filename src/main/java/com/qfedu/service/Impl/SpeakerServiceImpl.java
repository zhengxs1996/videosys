package com.qfedu.service.Impl;

import com.qfedu.dao.SpeakerDao;
import com.qfedu.entity.Speaker;
import com.qfedu.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeakerServiceImpl implements SpeakerService {

    @Autowired
    private SpeakerDao speakerDao;

    @Override
    public List<Speaker> findAllSpeaker() {
        return speakerDao.findAllSpraker();
    }

    @Override
    public Integer findSprakerSum() {
        return speakerDao.findSpeakerSum();
    }

    @Override
    public void addSpeaker(Speaker speaker) {
        speakerDao.add(speaker);
    }

    @Override
    public void deleteSpeaker(Integer id) {
        speakerDao.delete(id);
    }

    @Override
    public Speaker findSpeakerById(Integer id) {
        return speakerDao.findById(id);
    }

    @Override
    public void modify(Speaker speaker) {
        speakerDao.update(speaker);
    }
}
