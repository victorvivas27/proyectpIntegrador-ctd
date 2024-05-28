package com.musichouse.api.music.service;

import com.musichouse.api.music.dto.dto_entrance.CharacteristicDtoEntrance;
import com.musichouse.api.music.dto.dto_entrance.InstrumentDtoEntrance;
import com.musichouse.api.music.dto.dto_exit.InstrumentDtoExit;
import com.musichouse.api.music.dto.dto_modify.InstrumentDtoModify;
import com.musichouse.api.music.entity.*;
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

import java.math.BigDecimal;
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
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la temática con el ID proporcionado"));
        CharacteristicDtoEntrance characteristicsDtoEntrance = instrumentsDtoEntrance.getCharacteristic();
        Characteristics characteristics = new Characteristics();
        characteristics.setInstrumentCase(characteristicsDtoEntrance.getInstrumentCase());
        characteristics.setSupport(characteristicsDtoEntrance.getSupport());
        characteristics.setTuner(characteristicsDtoEntrance.getTuner());
        characteristics.setMicrophone(characteristicsDtoEntrance.getMicrophone());
        characteristics.setPhoneHolder(characteristicsDtoEntrance.getPhoneHolder());

        Instrument instrument = new Instrument();
        instrument.setName(instrumentsDtoEntrance.getName());
        instrument.setDescription(instrumentsDtoEntrance.getDescription());
        instrument.setRentalPrice(instrumentsDtoEntrance.getRentalPrice());
        instrument.setWeight(instrumentsDtoEntrance.getWeight());
        instrument.setMeasures(instrumentsDtoEntrance.getMeasures());
        instrument.setCategory(category);
        instrument.setTheme(theme);
        instrument.setCharacteristics(characteristics);

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
                .map(instrument -> {
                    InstrumentDtoExit dto = mapper.map(instrument, InstrumentDtoExit.class);
                    return dto;
                }).toList();
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
        Category category = categoryRepository.findById(instrumentDtoModify.getIdCategory())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la categoría con el ID proporcionado"));
        Theme theme = themeRepository.findById(instrumentDtoModify.getIdTheme())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la temática con el ID proporcionado"));
        Instrument instrumentToUpdate = instrumentRepository.findById(instrumentDtoModify.getIdInstrument())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el instrumento con el ID proporcionado"));
        instrumentToUpdate.setName(instrumentDtoModify.getName());
        instrumentToUpdate.setDescription(instrumentDtoModify.getDescription());
        instrumentToUpdate.setWeight(instrumentDtoModify.getWeight());
        instrumentToUpdate.setMeasures(instrumentDtoModify.getMeasures());
        instrumentToUpdate.setRentalPrice(instrumentDtoModify.getRentalPrice());
        instrumentToUpdate.setCategory(category);
        instrumentToUpdate.setTheme(theme);
        Characteristics characteristics = instrumentToUpdate.getCharacteristics();
        if (characteristics == null) {
            characteristics = new Characteristics();
            instrumentToUpdate.setCharacteristics(characteristics);
        }
        CharacteristicDtoEntrance characteristicsDtoEntrance = instrumentDtoModify.getCharacteristic();
        characteristics.setInstrumentCase(characteristicsDtoEntrance.getInstrumentCase());
        characteristics.setSupport(characteristicsDtoEntrance.getSupport());
        characteristics.setTuner(characteristicsDtoEntrance.getTuner());
        characteristics.setMicrophone(characteristicsDtoEntrance.getMicrophone());
        characteristics.setPhoneHolder(characteristicsDtoEntrance.getPhoneHolder());
        instrumentRepository.save(instrumentToUpdate);
        InstrumentDtoExit instrumentDtoExit = mapper.map(instrumentToUpdate, InstrumentDtoExit.class);
        return instrumentDtoExit;
    }

    @Override
    public void deleteInstrument(Long idInstrument) throws ResourceNotFoundException {
        if (instrumentRepository.findById(idInstrument).orElse(null) != null) {
            instrumentRepository.deleteById(idInstrument);
        } else {
            throw new ResourceNotFoundException("No se encontró el instrumento con el ID proporcionado");
        }
    }

    public List<Instrument> searchInstruments(String name) throws IllegalArgumentException {
        if (name != null) {
            return instrumentRepository.findByNameContaining(name);
        } else {
            throw new IllegalArgumentException("Parámetro de búsqueda inválido");
        }
    }

    public List<Instrument> findInstrumentsByRentalPriceLessThan(BigDecimal maxPrice) {
        if (maxPrice == null || maxPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio máximo debe ser un valor positivo.");
        }
        return instrumentRepository.findByRentalPriceLessThan(maxPrice.add(BigDecimal.ONE));
    }

    public List<Instrument> findInstrumentsByRentalPriceGreaterThan(BigDecimal minPrice) {
        if (minPrice == null || minPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio mínimo debe ser un valor positivo.");
        }
        return instrumentRepository.findByRentalPriceGreaterThan(minPrice);
    }

    public List<Instrument> findInstrumentsByRentalPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        if (minPrice == null || maxPrice == null || minPrice.compareTo(maxPrice) > 0 ||
                minPrice.compareTo(BigDecimal.ZERO) < 0 || maxPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El rango de precios es inválido.");
        }
        return instrumentRepository.findByRentalPriceBetween(minPrice, maxPrice);
    }

}
