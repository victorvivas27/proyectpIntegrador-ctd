package com.musichouse.api.music.controller;

import com.musichouse.api.music.dto.dto_entrance.UserAdminDtoEntrance;
import com.musichouse.api.music.dto.dto_entrance.UserDtoEntrance;
import com.musichouse.api.music.dto.dto_exit.UserAdminDtoExit;
import com.musichouse.api.music.dto.dto_exit.UserDtoExit;
import com.musichouse.api.music.service.UserService;
import com.musichouse.api.music.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/create/admin")
    public ResponseEntity<?> createUserAdmin(@RequestBody @Valid UserAdminDtoEntrance userAdminDtoEntrance) {
        try {
            UserAdminDtoExit createdUser = userService.createUserAdmin(userAdminDtoEntrance);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Usuario  admin creado con éxito.", createdUser));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>("El correo electrónico ingresado ya está en uso. Por favor, elija otro correo electrónico.", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("Ocurrió un error al procesar la solicitud.", null));
        }
    }
    @PostMapping("/create/user")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDtoEntrance userDtoEntrance) {
        try {
            UserDtoExit createdUser = userService.createUser(userDtoEntrance);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Usuario creado con éxito.", createdUser));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>("El correo electrónico ingresado ya está en uso. Por favor, elija otro correo electrónico.", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("Ocurrió un error al procesar la solicitud.", null));
        }
    }
}
