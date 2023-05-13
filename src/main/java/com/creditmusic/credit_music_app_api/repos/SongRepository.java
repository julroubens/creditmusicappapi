package com.creditmusic.credit_music_app_api.repos;

import com.creditmusic.credit_music_app_api.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SongRepository extends JpaRepository<Song, Long> {
}
