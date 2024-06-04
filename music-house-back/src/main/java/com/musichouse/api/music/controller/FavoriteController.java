package com.musichouse.api.music.controller;

import com.musichouse.api.music.dto.dto_entrance.FavoriteDtoEntrance;
import com.musichouse.api.music.dto.dto_exit.FavoriteDtoExit;
import com.musichouse.api.music.exception.ResourceNotFoundException;
import com.musichouse.api.music.service.FavoriteService;
import com.musichouse.api.music.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/favorite")
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<?>> addAvailableDate(@RequestBody @Valid FavoriteDtoEntrance favoriteDtoEntrance) {
        try {
            FavoriteDtoExit favoriteDtoExit = favoriteService.addFavorite(favoriteDtoEntrance);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>("Instrumento Agregado a favoritos  exitosamente.", favoriteDtoExit));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(e.getMessage(), null));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<FavoriteDtoExit>>> getAllFavorite() {
        List<FavoriteDtoExit> favoriteDtoExits = favoriteService.getAllFavorite();
        ApiResponse<List<FavoriteDtoExit>> response =
                new ApiResponse<>("Lista de Favoritos disponibles exitosa.", favoriteDtoExits);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{idInstrument}/{idUser}/{idFavorite}")
    public ResponseEntity<ApiResponse<String>> deleteAvailableDate(
            @PathVariable Long idInstrument, @PathVariable Long idUser, @PathVariable Long idFavorite) {
        try {
            favoriteService.deleteFavorite(idInstrument, idUser, idFavorite);
            return ResponseEntity.ok(new ApiResponse<>(" Favorito disponible eliminada exitosamente.", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("Ocurrió un error al procesar la solicitud.", null));
        }
    }
}
