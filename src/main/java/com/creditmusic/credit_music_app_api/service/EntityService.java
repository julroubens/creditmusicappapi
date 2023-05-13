package com.creditmusic.credit_music_app_api.service;

import com.creditmusic.credit_music_app_api.domain.Entity;
import com.creditmusic.credit_music_app_api.model.EntityDTO;
import com.creditmusic.credit_music_app_api.repos.EntityRepository;
import com.creditmusic.credit_music_app_api.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class EntityService {

    private final EntityRepository entityRepository;

    public EntityService(final EntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    public List<EntityDTO> findAll() {
        final List<Entity> entitys = entityRepository.findAll(Sort.by("id"));
        return entitys.stream()
                .map((entity) -> mapToDTO(entity, new EntityDTO()))
                .toList();
    }

    public EntityDTO get(final Long id) {
        return entityRepository.findById(id)
                .map(entity -> mapToDTO(entity, new EntityDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final EntityDTO entityDTO) {
        final Entity entity = new Entity();
        mapToEntity(entityDTO, entity);
        return entityRepository.save(entity).getId();
    }

    public void update(final Long id, final EntityDTO entityDTO) {
        final Entity entity = entityRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(entityDTO, entity);
        entityRepository.save(entity);
    }

    public void delete(final Long id) {
        entityRepository.deleteById(id);
    }

    private EntityDTO mapToDTO(final Entity entity, final EntityDTO entityDTO) {
        entityDTO.setId(entity.getId());
        entityDTO.setName(entity.getName());
        return entityDTO;
    }

    private Entity mapToEntity(final EntityDTO entityDTO, final Entity entity) {
        entity.setName(entityDTO.getName());
        return entity;
    }

}
