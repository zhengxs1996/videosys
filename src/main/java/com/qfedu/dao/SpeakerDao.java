package com.qfedu.dao;

import com.qfedu.entity.Speaker;

import java.util.List;

public interface SpeakerDao {
    public List<Speaker> findAllSpraker();

    public Integer findSpeakerSum();

    public void add(Speaker speaker);

    public void delete(Integer id);

    public Speaker findById(Integer id);

    public void update(Speaker speaker);
}
