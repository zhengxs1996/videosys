package com.qfedu.service;

import com.qfedu.entity.Speaker;

import java.util.List;

public interface SpeakerService {
    public List<Speaker> findAllSpeaker();

    public Integer findSprakerSum();

    public void addSpeaker(Speaker speaker);

    public void deleteSpeaker(Integer id);

    public Speaker findSpeakerById(Integer id);

    public void modify(Speaker speaker);
}
