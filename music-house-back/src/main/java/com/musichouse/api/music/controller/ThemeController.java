package com.musichouse.api.music.controller;

import com.musichouse.api.music.dto.dto_entrance.ThemeDtoEntrance;
import com.musichouse.api.music.dto.dto_exit.ThemeDtoExit;
import com.musichouse.api.music.dto.dto_modify.ThemeDtoModify;
import com.musichouse.api.music.exception.CategoryAssociatedException;
import com.musichouse.api.music.exception.ResourceNotFoundException;
import com.musichouse.api.music.service.ThemeService;
import com.musichouse.api.music.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/theme")
public class ThemeController {
    private final ThemeService themeService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ThemeDtoExit>> createTheme(@RequestBody @Valid ThemeDtoEntrance themeDtoEntrance) {
        try {
            ThemeDtoExit themeDtoExit = themeService.createTheme(themeDtoEntrance);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>("Tematica creada exitosamente.", themeDtoExit));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>("La tematica ya existe en la base de datos.", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("Ocurrió un error al procesar la solicitud.", null));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ThemeDtoExit>> allTheme() {
        List<ThemeDtoExit> themeDtoExits = themeService.getAllThemes();
        return new ResponseEntity<>(themeDtoExits, HttpStatus.OK);
    }

    @GetMapping("/search/{idTheme}")
    public ResponseEntity<ThemeDtoExit> searchThemeById(@PathVariable Long idTheme) throws ResourceNotFoundException {
        ThemeDtoExit themeDtoExit = themeService.getThemeById(idTheme);
        return ResponseEntity.ok(themeDtoExit);
    }

    @PutMapping("/update")
    public ResponseEntity<ThemeDtoExit> updateTheme(@RequestBody @Valid ThemeDtoModify themeDtoModify) throws ResourceNotFoundException {
        ThemeDtoExit themeDtoExit = themeService.updateTheme(themeDtoModify);
        return ResponseEntity.ok(themeDtoExit);
    }

    @DeleteMapping("/delete/{idTheme}")
    public ResponseEntity<String> deleteTheme(@PathVariable Long idTheme) {
        try {
            themeService.deleteTheme(idTheme);
            return ResponseEntity.ok("Theme deleted successfully");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Theme not found");
        } catch (CategoryAssociatedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cannot delete theme as it is associated with instruments");
        }
    }
}
