package com.study.mf.services;

import com.study.mf.exceptions.CustomNotFoundException;
import com.study.mf.model.Music;
import com.study.mf.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MusicService {
    @Autowired
    private MusicRepository repository;

    public List<Music> findAll(){
        return repository.findAll();

    }

    public Music findById(Long id){
        return repository.findById(id).orElseThrow(() -> new CustomNotFoundException("Not Found"));
    }

    public Music create(Music music){
        return repository.save(music);
    }

    public Music update(Long id, Music music){
        Music stored = repository.findById(id).orElseThrow(() -> new CustomNotFoundException("Not Found"));
        stored.setName(music.getName());
        stored.setArtist(music.getArtist());
        stored.setYear(music.getYear());
        repository.save(stored);
        return stored;
    }

    public void delete(Long id){
        Music music = repository.findById(id).orElseThrow(() -> new CustomNotFoundException("Not Found"));
        repository.delete(music);
    }
}
