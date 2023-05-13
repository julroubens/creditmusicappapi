package com.creditmusic.credit_music_app_api.service;

import com.creditmusic.credit_music_app_api.domain.Musician;
import com.creditmusic.credit_music_app_api.model.MusicianDTO;
import com.creditmusic.credit_music_app_api.repos.MusicianRepository;
import com.creditmusic.credit_music_app_api.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class MusicianService {

    private final MusicianRepository musicianRepository;

    public MusicianService(final MusicianRepository musicianRepository) {
        this.musicianRepository = musicianRepository;
    }

    public List<MusicianDTO> findAll() {
        final List<Musician> musicians = musicianRepository.findAll(Sort.by("id"));
        return musicians.stream()
                .map((musician) -> mapToDTO(musician, new MusicianDTO()))
                .toList();
    }

    public MusicianDTO get(final Long id) {
        return musicianRepository.findById(id)
                .map(musician -> mapToDTO(musician, new MusicianDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final MusicianDTO musicianDTO) {
        final Musician musician = new Musician();
        mapToEntity(musicianDTO, musician);
        return musicianRepository.save(musician).getId();
    }

    public void update(final Long id, final MusicianDTO musicianDTO) {
        final Musician musician = musicianRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(musicianDTO, musician);
        musicianRepository.save(musician);
    }

    public void delete(final Long id) {
        musicianRepository.deleteById(id);
    }

    private MusicianDTO mapToDTO(final Musician musician, final MusicianDTO musicianDTO) {
        musicianDTO.setId(musician.getId());
        musicianDTO.setName(musician.getName());
        musicianDTO.setCountryOfOrigin(musician.getCountryOfOrigin());
        musicianDTO.setImg(musician.getImg());
        return musicianDTO;
    }

    private Musician mapToEntity(final MusicianDTO musicianDTO, final Musician musician) {
        musician.setName(musicianDTO.getName());
        musician.setCountryOfOrigin(musicianDTO.getCountryOfOrigin());
        musician.setImg(musicianDTO.getImg());
        return musician;
    }

}
