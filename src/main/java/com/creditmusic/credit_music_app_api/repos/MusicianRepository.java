package com.creditmusic.credit_music_app_api.repos;

import com.creditmusic.credit_music_app_api.domain.Musician;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MusicianRepository extends JpaRepository<Musician, Long> {
}
