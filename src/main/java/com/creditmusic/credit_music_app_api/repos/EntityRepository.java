package com.creditmusic.credit_music_app_api.repos;

import com.creditmusic.credit_music_app_api.domain.Entity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EntityRepository extends JpaRepository<Entity, Long> {
}
