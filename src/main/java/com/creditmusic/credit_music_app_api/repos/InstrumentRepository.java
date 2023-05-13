package com.creditmusic.credit_music_app_api.repos;

import com.creditmusic.credit_music_app_api.domain.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InstrumentRepository extends JpaRepository<Instrument, Long> {
}
