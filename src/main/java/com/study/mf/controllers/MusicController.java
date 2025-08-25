package com.study.mf.controllers;

import com.study.mf.data.dto.MusicDTO;
import com.study.mf.model.Music;
import com.study.mf.services.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/musics")
public class MusicController {
    @Autowired
    private MusicService service;

    @GetMapping
    public ResponseEntity<List<MusicDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MusicDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<MusicDTO> create(@RequestBody Music music) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(music));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MusicDTO> updated(
            @PathVariable Long id,
            @RequestBody Music music
    ) {
        return ResponseEntity.ok(service.update(id, music));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
