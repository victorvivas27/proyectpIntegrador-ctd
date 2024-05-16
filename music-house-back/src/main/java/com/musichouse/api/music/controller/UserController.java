package com.musichouse.api.music.controller;

import com.musichouse.api.music.dto.dto_entrance.UserAdminDtoEntrance;
import com.musichouse.api.music.dto.dto_entrance.UserDtoEntrance;
import com.musichouse.api.music.dto.dto_exit.UserDtoExit;
import com.musichouse.api.music.dto.dto_modify.UserDtoModify;
import com.musichouse.api.music.exception.ResourceNotFoundException;
import com.musichouse.api.music.service.UserService;
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
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;



    @GetMapping("/all")
    public ResponseEntity<?> allUser() {
        List<UserDtoExit> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

    @GetMapping("/search/{idUser}")
    public ResponseEntity<ApiResponse<UserDtoExit>> searchUserById(@PathVariable Long idUser) {
        try {
            UserDtoExit foundUser = userService.getUserById(idUser);
            return ResponseEntity.ok(new ApiResponse<>("Usuario encontrado.", foundUser));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("No se encontró el usuario con el ID proporcionado.", null));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<UserDtoExit>> updateUser(@Valid @RequestBody UserDtoModify userDtoModify) {
        try {
            UserDtoExit userDtoExit = userService.updateUser(userDtoModify);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>("Usuario actualizado con éxito.", userDtoExit));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("No se encontró el usuario con el ID proporcionado.", null));
        }
    }

    @DeleteMapping("/delete/{idUser}")
    public ResponseEntity<?> deleteUser(@PathVariable Long idUser) {
        try {
            userService.deleteUser(idUser);
            return ResponseEntity.ok(new ApiResponse<>("Usuario con ID " + idUser + " eliminado exitosamente.", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("El Usuario con el ID " + idUser + " no se encontró.", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("Ocurrió un error al procesar la solicitud.", null));
        }
    }
}