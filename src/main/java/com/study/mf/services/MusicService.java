package com.study.mf.services;

import com.study.mf.controllers.MusicController;
import com.study.mf.data.dto.MusicDTO;
import com.study.mf.exceptions.CustomBadRequestException;
import com.study.mf.exceptions.CustomNotFoundException;
import com.study.mf.model.Music;
import com.study.mf.repository.MusicRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.study.mf.mappers.ObjectsMapper.parseListObject;
import static com.study.mf.mappers.ObjectsMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class MusicService {

    private final MusicRepository repository;
    private final PagedResourcesAssembler<MusicDTO> assembler;

    public MusicService(MusicRepository repository, PagedResourcesAssembler<MusicDTO> assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    public PagedModel<EntityModel<MusicDTO>> findAll(Pageable pageable) {
        Page<Music> musicsPage = repository.findAll(pageable);
        Page<MusicDTO> musicDTOPage = musicsPage.map(music -> {
            MusicDTO dto = parseObject(music, MusicDTO.class);
            return dto;
        });

        Link link = linkTo(methodOn(MusicController.class).findAll(
            null, null, null, null
        )).withSelfRel().withType("GET");

        return assembler.toModel(musicDTOPage, link);
    }

    public MusicDTO findById(Long id) {
        MusicDTO musicDTO = parseObject(repository.findById(id).orElseThrow(
            () -> new CustomNotFoundException("Not Found")), MusicDTO.class);
        addHateoasLinks(musicDTO);
        return musicDTO;
    }

    public MusicDTO create(MusicDTO musicDTO) {
        if (musicDTO.getName() == null || musicDTO.getYear() == null) {
            throw new CustomBadRequestException("Fields mame and year cannot be null...");
        }

        Music music = repository.save(parseObject(musicDTO, Music.class));
        MusicDTO dto = parseObject(music, MusicDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    @Transactional
    public MusicDTO update(Long id, MusicDTO musicDTO) {
        Music stored = repository.findById(id).orElseThrow(
            () -> new CustomNotFoundException("Not Found"));

        if (musicDTO.getName() == null || musicDTO.getYear() == null) {
            throw new CustomBadRequestException("Fields mame and year cannot be null...");
        }

        stored.setName(musicDTO.getName());
        stored.setArtist(musicDTO.getArtist());
        stored.setYear(musicDTO.getYear());

        MusicDTO dto = parseObject(stored, MusicDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id) {
        Music music = repository.findById(id).orElseThrow(() -> new CustomNotFoundException("Not Found"));
        repository.delete(music);
    }

    private void addHateoasLinks(MusicDTO dto) {
        dto.add(linkTo(methodOn(MusicController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(MusicController.class).findAll(0, 10, "asc", "name")).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(MusicController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(MusicController.class).updated(dto.getId(), dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(MusicController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}
