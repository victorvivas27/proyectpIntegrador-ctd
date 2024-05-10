package com.musichouse.api.music.controller;

import com.musichouse.api.music.dto.dto_entrance.ImageUrlsDtoEntrance;
import com.musichouse.api.music.dto.dto_exit.ImagesUrlsDtoExit;
import com.musichouse.api.music.dto.dto_modify.ImageUrlsDtoModify;
import com.musichouse.api.music.exception.ResourceNotFoundException;
import com.musichouse.api.music.service.ImageUrlsService;
import com.musichouse.api.music.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/imageurls")
public class ImageUrlsController {

    private final ImageUrlsService imageUrlsService;

    @PostMapping("/addImage")
    public ResponseEntity<ApiResponse<ImagesUrlsDtoExit>> createImageUrls(@RequestBody @Valid ImageUrlsDtoEntrance imageUrlsDtoEntrance) {
        try {
            ImagesUrlsDtoExit imagesUrlsDtoExit = imageUrlsService.addImageUrls(imageUrlsDtoEntrance);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>("Imágenes agregadas exitosamente.", imagesUrlsDtoExit));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("No se encontró la categoría o el instrumento asociado.", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("Ocurrió un error al procesar la solicitud.", null));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ImagesUrlsDtoExit>> allImageUrls() {
        List<ImagesUrlsDtoExit> imagesUrlsDtoExits = imageUrlsService.getAllImageUrls();
        return new ResponseEntity<>(imagesUrlsDtoExits, HttpStatus.OK);
    }

    @GetMapping("/search/{idImage}")
    public ResponseEntity<ImagesUrlsDtoExit> searchImageUrlsById(@PathVariable Long idImage) throws ResourceNotFoundException {
        ImagesUrlsDtoExit imagesUrlsDtoExit = imageUrlsService.getImageUrlsById(idImage);
        return ResponseEntity.ok(imagesUrlsDtoExit);
    }

    @PutMapping("/update")
    public ResponseEntity<ImagesUrlsDtoExit> updateImageUrls(@RequestBody @Valid ImageUrlsDtoModify imageUrlsDtoModify) throws ResourceNotFoundException {
        ImagesUrlsDtoExit imagesUrlsDtoExit = imageUrlsService.updateImageUrls(imageUrlsDtoModify);
        return ResponseEntity.ok(imagesUrlsDtoExit);
    }

    @DeleteMapping("/delete/{idInstrument}/{idImage}")
    public ResponseEntity<ApiResponse<String>> deleteImageUrls(@PathVariable Long idInstrument, @PathVariable Long idImage) {
        try {
            imageUrlsService.deleteImageUrls(idInstrument, idImage);
            return ResponseEntity.ok(new ApiResponse<>("Urls de imagen eliminadas exitosamente.", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("Ocurrió un error al procesar la solicitud.", null));
        }
    }
}
