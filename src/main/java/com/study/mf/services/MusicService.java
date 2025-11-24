package com.study.mf.services;

import com.study.mf.data.dto.MusicDTO;
import com.study.mf.exceptions.CustomBadRequestException;
import com.study.mf.exceptions.CustomNotFoundException;
import com.study.mf.model.Music;
import com.study.mf.repository.MusicRepository;
import jakarta.transaction.Transactional;
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
        return parseObject(repository.findById(id).orElseThrow(
            () -> new CustomNotFoundException("Not Found")), MusicDTO.class);
    }

    public MusicDTO create(MusicDTO musicDTO){
        if (musicDTO.getName() == null || musicDTO.getYear() == null) {
            throw new CustomBadRequestException("Fields mame and year cannot be null...");
        }

        Music music = repository.save(parseObject(musicDTO, Music.class));
        return parseObject(music, MusicDTO.class);
    }

    @Transactional
    public MusicDTO update(Long id, MusicDTO musicDTO){
        Music stored = repository.findById(id).orElseThrow(
            () -> new CustomNotFoundException("Not Found"));

        if (musicDTO.getName() == null || musicDTO.getYear() == null) {
            throw new CustomBadRequestException("Fields mame and year cannot be null...");
        }

        stored.setName(musicDTO.getName());
        stored.setArtist(musicDTO.getArtist());
        stored.setYear(musicDTO.getYear());

        return parseObject(stored, MusicDTO.class);
    }

    public void delete(Long id){
        Music music = repository.findById(id).orElseThrow(() -> new CustomNotFoundException("Not Found"));
        repository.delete(music);
    }
}
