package com.creditmusic.credit_music_app_api.repos;

import com.creditmusic.credit_music_app_api.domain.Biography;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BiographyRepository extends JpaRepository<Biography, Long> {
}
