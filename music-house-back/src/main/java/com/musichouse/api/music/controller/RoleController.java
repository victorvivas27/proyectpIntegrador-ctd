package com.musichouse.api.music.controller;

import com.musichouse.api.music.exception.ResourceNotFoundException;
import com.musichouse.api.music.service.RoleService;
import com.musichouse.api.music.util.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/roles")
public class RoleController {
    private final RoleService roleService;


    @PostMapping("/users/{userId}/rol/add/{rol}")
    public ResponseEntity<ApiResponse<Void>> addRoleToUser(@PathVariable Long userId, @PathVariable String rol) {
        try {
            roleService.addRoleToUser(userId, rol);
            return ResponseEntity.ok(new ApiResponse<>("Rol " + rol + " agregado al usuario con ID " + userId + " exitosamente.", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(e.getMessage(), null));
        }
    }

    @DeleteMapping("/users/{userId}/rol/delete/{rol}")
    public ResponseEntity<ApiResponse<Void>> removeRoleFromUser(@PathVariable Long userId, @PathVariable String rol) {
        try {
            roleService.removeRoleFromUser(userId, rol);
            return ResponseEntity.ok(new ApiResponse<>("Rol " + rol + " eliminado del usuario con ID " + userId + " exitosamente.", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(e.getMessage(), null));
        }
    }
}
