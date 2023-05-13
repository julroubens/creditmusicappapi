package com.creditmusic.credit_music_app_api.repos;

import com.creditmusic.credit_music_app_api.domain.Lyric;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LyricRepository extends JpaRepository<Lyric, Long> {
}
