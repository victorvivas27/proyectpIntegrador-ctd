package com.musichouse.api.music.service;

import com.musichouse.api.music.dto.dto_entrance.ImageUrlsDtoEntrance;
import com.musichouse.api.music.dto.dto_exit.ImagesUrlsDtoExit;
import com.musichouse.api.music.dto.dto_modify.ImageUrlsDtoModify;
import com.musichouse.api.music.entity.ImageUrls;
import com.musichouse.api.music.entity.Instruments;
import com.musichouse.api.music.exception.ResourceNotFoundException;
import com.musichouse.api.music.interfaces.ImageUrlsInterface;
import com.musichouse.api.music.repository.ImageUrlsRepository;
import com.musichouse.api.music.repository.InstrumentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ImageUrlsService implements ImageUrlsInterface {
    private final static Logger LOGGER = LoggerFactory.getLogger(ImageUrlsService.class);
    private final ImageUrlsRepository imageUrlsRepository;
    private final ModelMapper mapper;
    private final InstrumentRepository instrumentRepository;

    @Override
    public ImagesUrlsDtoExit addImageUrls(ImageUrlsDtoEntrance imageUrlsDtoEntrance) throws ResourceNotFoundException {
        Long instrumentId = imageUrlsDtoEntrance.getIdInstrument();
        if (instrumentId == null) {
            throw new IllegalArgumentException("El ID del instrumento no puede ser nulo");
        }
        Instruments instrument = instrumentRepository.findById(instrumentId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el instrumento con el ID proporcionado"));
        ImageUrls image = new ImageUrls();
        image.setImageUrl(imageUrlsDtoEntrance.getImageUrl());
        image.setInstrument(instrument);
        ImageUrls savedImage = imageUrlsRepository.save(image);
        Long idImage = savedImage.getIdImage();
        ImagesUrlsDtoExit imageDtoExit = new ImagesUrlsDtoExit();
        imageDtoExit.setIdImage(idImage);
        imageDtoExit.setImageUrl(savedImage.getImageUrl());
        return imageDtoExit;
    }

    @Override
    public List<ImagesUrlsDtoExit> getAllImageUrls() {
        List<ImagesUrlsDtoExit> imagesUrlsDtoExits = imageUrlsRepository.findAll().stream()
                .map(imageUrls -> mapper.map(imageUrls, ImagesUrlsDtoExit.class)).toList();
        return imagesUrlsDtoExits;
    }

    @Override
    public ImagesUrlsDtoExit getImageUrlsById(Long idImage) throws ResourceNotFoundException {
        ImageUrls imageUrls = imageUrlsRepository.findById(idImage).orElse(null);
        if (imageUrls == null) {
            throw new ResourceNotFoundException("Imagen no encontrada con ID " + idImage);
        }
        return mapper.map(imageUrls, ImagesUrlsDtoExit.class);
    }

    @Override
    public ImagesUrlsDtoExit updateImageUrls(ImageUrlsDtoModify imageUrlsDtoModify) throws ResourceNotFoundException {
        ImageUrls imageUrlsToUpdate = imageUrlsRepository.findById(imageUrlsDtoModify.getIdImage())
                .orElseThrow(() -> new ResourceNotFoundException("Imagen no encontrada con ID " + imageUrlsDtoModify.getIdImage()));
        imageUrlsToUpdate.setImageUrl(imageUrlsDtoModify.getImageUrl());
        imageUrlsRepository.save(imageUrlsToUpdate);
        return mapper.map(imageUrlsToUpdate, ImagesUrlsDtoExit.class);
    }

    @Override
    public void deleteImageUrls(Long idInstrument, Long idImage) throws ResourceNotFoundException {
        Instruments instrument = instrumentRepository.findById(idInstrument)
                .orElseThrow(() -> new ResourceNotFoundException("Instrumento no encontrado con ID " + idInstrument));
        imageUrlsRepository.deleteImageByIdAndInstrumentId(idImage, idInstrument);

    }
}
