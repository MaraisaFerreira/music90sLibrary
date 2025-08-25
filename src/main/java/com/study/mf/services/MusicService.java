package com.study.mf.services;

import com.study.mf.data.dto.MusicDTO;
import com.study.mf.exceptions.CustomNotFoundException;
import com.study.mf.model.Music;
import com.study.mf.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.study.mf.mappers.ObjectsMapper.parseObject;
import static com.study.mf.mappers.ObjectsMapper.parseListObject;

import java.util.ArrayList;
import java.util.List;

@Service
public class MusicService {
    @Autowired
    private MusicRepository repository;

    public List<MusicDTO> findAll(){
        return parseListObject(repository.findAll(), MusicDTO.class);
    }

    public MusicDTO findById(Long id){
        return parseObject(repository.findById(id).orElseThrow(() -> new CustomNotFoundException("Not Found")), MusicDTO.class);
    }

    public MusicDTO create(Music music){
        return parseObject(repository.save(music), MusicDTO.class);
    }

    public MusicDTO update(Long id, Music music){
        Music stored = repository.findById(id).orElseThrow(() -> new CustomNotFoundException("Not Found"));
        stored.setName(music.getName());
        stored.setArtist(music.getArtist());
        stored.setYear(music.getYear());
        repository.save(stored);
        return parseObject(stored, MusicDTO.class);
    }

    public void delete(Long id){
        Music music = repository.findById(id).orElseThrow(() -> new CustomNotFoundException("Not Found"));
        repository.delete(music);
    }
}
