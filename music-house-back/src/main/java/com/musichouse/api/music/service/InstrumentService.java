package com.musichouse.api.music.service;

import com.musichouse.api.music.dto.dto_entrance.InstrumentDtoEntrance;
import com.musichouse.api.music.dto.dto_exit.InstrumentDtoExit;
import com.musichouse.api.music.dto.dto_modify.InstrumentDtoModify;
import com.musichouse.api.music.entity.Category;
import com.musichouse.api.music.entity.ImageUrls;
import com.musichouse.api.music.entity.Instrument;
import com.musichouse.api.music.entity.Theme;
import com.musichouse.api.music.exception.ResourceNotFoundException;
import com.musichouse.api.music.interfaces.InstrumentInterface;
import com.musichouse.api.music.repository.CategoryRepository;
import com.musichouse.api.music.repository.InstrumentRepository;
import com.musichouse.api.music.repository.ThemeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InstrumentService implements InstrumentInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(InstrumentService.class);
    private final InstrumentRepository instrumentRepository;
    private final ModelMapper mapper;
    private final CategoryRepository categoryRepository;
    private final ThemeRepository themeRepository;

    @Override
    public InstrumentDtoExit createInstrument(InstrumentDtoEntrance instrumentsDtoEntrance) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(instrumentsDtoEntrance.getIdCategory())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la categoría con el ID proporcionado"));
        Theme theme = themeRepository.findById(instrumentsDtoEntrance.getIdTheme())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la tematica con el ID proporcionado"));
        Instrument instrument = new Instrument();
        instrument.setName(instrumentsDtoEntrance.getName());
        instrument.setDescription(instrumentsDtoEntrance.getDescription());
        instrument.setRentalPrice(instrumentsDtoEntrance.getRentalPrice());
        instrument.setWeight(instrumentsDtoEntrance.getWeight());
        instrument.setMeasures(instrumentsDtoEntrance.getMeasures());
        instrument.setCategory(category);
        instrument.setTheme(theme);
        List<ImageUrls> imageUrls = instrumentsDtoEntrance.getImageUrls().stream()
                .map(url -> {
                    ImageUrls imageUrl = new ImageUrls();
                    imageUrl.setImageUrl(url);
                    imageUrl.setInstrument(instrument);
                    return imageUrl;
                }).toList();
        instrument.setImageUrls(imageUrls);
        Instrument instrumentSave = instrumentRepository.save(instrument);
        InstrumentDtoExit instrumentDtoExit = mapper.map(instrumentSave, InstrumentDtoExit.class);
        return instrumentDtoExit;
    }


    @Override
    public List<InstrumentDtoExit> getAllInstruments() {
        List<InstrumentDtoExit> instrumentDtoExits = instrumentRepository.findAll().stream()
                .map(instrument -> mapper.map(instrument, InstrumentDtoExit.class)).toList();
        return instrumentDtoExits;
    }

    @Override
    public InstrumentDtoExit getInstrumentById(Long idInstrument) throws ResourceNotFoundException {
        Instrument instrument = instrumentRepository.findById(idInstrument).orElse(null);
        InstrumentDtoExit instrumentDtoExit = null;
        if (instrument != null) {
            instrumentDtoExit = mapper.map(instrument, InstrumentDtoExit.class);
        } else {
            throw new ResourceNotFoundException("No se encontró el instrumento con el ID proporcionado");
        }
        return instrumentDtoExit;
    }

    @Override
    public InstrumentDtoExit updateInstrument(InstrumentDtoModify instrumentDtoModify) throws ResourceNotFoundException {
        Instrument instrumentsToUpdate = instrumentRepository.findById(instrumentDtoModify.getIdInstrument())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el instrumento con el ID proporcionado"));
        instrumentsToUpdate.setName(instrumentDtoModify.getName());
        instrumentsToUpdate.setDescription(instrumentDtoModify.getDescription());
        instrumentsToUpdate.setRentalPrice(instrumentDtoModify.getRentalPrice());
        instrumentsToUpdate.setWeight(instrumentDtoModify.getWeight());
        instrumentsToUpdate.setMeasures(instrumentDtoModify.getMeasures());
        instrumentRepository.save(instrumentsToUpdate);
        return mapper.map(instrumentsToUpdate, InstrumentDtoExit.class);
    }

    @Override
    public void deleteInstrument(Long idInstrument) throws ResourceNotFoundException {
        if (instrumentRepository.findById(idInstrument).orElse(null) != null) {
            instrumentRepository.deleteById(idInstrument);
        } else {
            throw new ResourceNotFoundException("No se encontró el instrumento con el ID proporcionado");
        }
    }

}
