package com.creditmusic.credit_music_app_api.service;

import com.creditmusic.credit_music_app_api.domain.Biography;
import com.creditmusic.credit_music_app_api.domain.Entity;
import com.creditmusic.credit_music_app_api.model.BiographyDTO;
import com.creditmusic.credit_music_app_api.repos.BiographyRepository;
import com.creditmusic.credit_music_app_api.repos.EntityRepository;
import com.creditmusic.credit_music_app_api.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class BiographyService {

    private final BiographyRepository biographyRepository;
    private final EntityRepository entityRepository;

    public BiographyService(final BiographyRepository biographyRepository,
            final EntityRepository entityRepository) {
        this.biographyRepository = biographyRepository;
        this.entityRepository = entityRepository;
    }

    public List<BiographyDTO> findAll() {
        final List<Biography> biographys = biographyRepository.findAll(Sort.by("id"));
        return biographys.stream()
                .map((biography) -> mapToDTO(biography, new BiographyDTO()))
                .toList();
    }

    public BiographyDTO get(final Long id) {
        return biographyRepository.findById(id)
                .map(biography -> mapToDTO(biography, new BiographyDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final BiographyDTO biographyDTO) {
        final Biography biography = new Biography();
        mapToEntity(biographyDTO, biography);
        return biographyRepository.save(biography).getId();
    }

    public void update(final Long id, final BiographyDTO biographyDTO) {
        final Biography biography = biographyRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(biographyDTO, biography);
        biographyRepository.save(biography);
    }

    public void delete(final Long id) {
        biographyRepository.deleteById(id);
    }

    private BiographyDTO mapToDTO(final Biography biography, final BiographyDTO biographyDTO) {
        biographyDTO.setId(biography.getId());
        biographyDTO.setName(biography.getName());
        biographyDTO.setDetail(biography.getDetail());
        biographyDTO.setBiography(biography.getBiography() == null ? null : biography.getBiography().getId());
        return biographyDTO;
    }

    private Biography mapToEntity(final BiographyDTO biographyDTO, final Biography biography) {
        biography.setName(biographyDTO.getName());
        biography.setDetail(biographyDTO.getDetail());
        final Entity biography = biographyDTO.getBiography() == null ? null : entityRepository.findById(biographyDTO.getBiography())
                .orElseThrow(() -> new NotFoundException("biography not found"));
        biography.setBiography(biography);
        return biography;
    }

}
