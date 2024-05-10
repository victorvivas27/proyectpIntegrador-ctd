package com.musichouse.api.music.interfaces;


import com.musichouse.api.music.dto.dto_entrance.ImageUrlsDtoEntrance;
import com.musichouse.api.music.dto.dto_exit.ImagesUrlsDtoExit;
import com.musichouse.api.music.dto.dto_modify.ImageUrlsDtoModify;
import com.musichouse.api.music.exception.ResourceNotFoundException;

import java.util.List;

public interface ImageUrlsInterface {
    ImagesUrlsDtoExit addImageUrls(ImageUrlsDtoEntrance imageUrlsDtoEntrance) throws ResourceNotFoundException;

    List<ImagesUrlsDtoExit> getAllImageUrls();

    ImagesUrlsDtoExit getImageUrlsById(Long idImage) throws ResourceNotFoundException;

    ImagesUrlsDtoExit updateImageUrls(ImageUrlsDtoModify imageUrlsDtoModify) throws ResourceNotFoundException;

    void deleteImageUrls(Long idImage,Long idInstrument) throws ResourceNotFoundException;

}
