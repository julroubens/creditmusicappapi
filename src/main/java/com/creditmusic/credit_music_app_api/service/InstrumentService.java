package com.creditmusic.credit_music_app_api.service;

import com.creditmusic.credit_music_app_api.domain.Instrument;
import com.creditmusic.credit_music_app_api.domain.Musician;
import com.creditmusic.credit_music_app_api.model.InstrumentDTO;
import com.creditmusic.credit_music_app_api.repos.InstrumentRepository;
import com.creditmusic.credit_music_app_api.repos.MusicianRepository;
import com.creditmusic.credit_music_app_api.util.NotFoundException;
import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Transactional
@Service
public class InstrumentService {

    private final InstrumentRepository instrumentRepository;
    private final MusicianRepository musicianRepository;

    public InstrumentService(final InstrumentRepository instrumentRepository,
            final MusicianRepository musicianRepository) {
        this.instrumentRepository = instrumentRepository;
        this.musicianRepository = musicianRepository;
    }

    public List<InstrumentDTO> findAll() {
        final List<Instrument> instruments = instrumentRepository.findAll(Sort.by("id"));
        return instruments.stream()
                .map((instrument) -> mapToDTO(instrument, new InstrumentDTO()))
                .toList();
    }

    public InstrumentDTO get(final Long id) {
        return instrumentRepository.findById(id)
                .map(instrument -> mapToDTO(instrument, new InstrumentDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final InstrumentDTO instrumentDTO) {
        final Instrument instrument = new Instrument();
        mapToEntity(instrumentDTO, instrument);
        return instrumentRepository.save(instrument).getId();
    }

    public void update(final Long id, final InstrumentDTO instrumentDTO) {
        final Instrument instrument = instrumentRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(instrumentDTO, instrument);
        instrumentRepository.save(instrument);
    }

    public void delete(final Long id) {
        instrumentRepository.deleteById(id);
    }

    private InstrumentDTO mapToDTO(final Instrument instrument, final InstrumentDTO instrumentDTO) {
        instrumentDTO.setId(instrument.getId());
        instrumentDTO.setName(instrument.getName());
        instrumentDTO.setImg(instrument.getImg());
        instrumentDTO.setMusician(instrument.getMusician() == null ? null : instrument.getMusician().stream()
                .map(musician -> musician.getId())
                .toList());
        return instrumentDTO;
    }

    private Instrument mapToEntity(final InstrumentDTO instrumentDTO, final Instrument instrument) {
        instrument.setName(instrumentDTO.getName());
        instrument.setImg(instrumentDTO.getImg());
        final List<Musician> musician = musicianRepository.findAllById(
                instrumentDTO.getMusician() == null ? Collections.emptyList() : instrumentDTO.getMusician());
        if (musician.size() != (instrumentDTO.getMusician() == null ? 0 : instrumentDTO.getMusician().size())) {
            throw new NotFoundException("one of musician not found");
        }
        instrument.setMusician(musician.stream().collect(Collectors.toSet()));
        return instrument;
    }

}
