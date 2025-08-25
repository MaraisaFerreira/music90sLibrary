package com.study.mf.services;

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
        return new ArrayList<>(List.of(
                new Music(null,"Viva Forever", "Spice Girls", 1998),
                new Music(null,"Stop", "Spice Girls", 1998)
        ));
    }

    public Music findById(Long id){
        return new Music(id, "Malandragem", "Cassia Eller", 1994);
    }

    public Music create(Music music){
        music.setId(2L);
        return music;
    }

    public Music update(Long id, Music music){
        music.setName(music.getName() + " updated");
        return music;
    }

    public void delete(Long id){}
}
