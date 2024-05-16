package com.musichouse.api.music.controller;

import com.musichouse.api.music.dto.dto_entrance.PhoneDtoEntrance;
import com.musichouse.api.music.dto.dto_exit.PhoneDtoExit;
import com.musichouse.api.music.dto.dto_modify.PhoneDtoModify;
import com.musichouse.api.music.exception.ResourceNotFoundException;
import com.musichouse.api.music.service.PhoneService;
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
@RequestMapping("/api/phone")
public class PhoneController {
    private final PhoneService phoneService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<PhoneDtoExit>> createPhone(@Valid @RequestBody PhoneDtoEntrance phoneDtoEntrance) {
        PhoneDtoExit phoneDtoExit = phoneService.createPhone(phoneDtoEntrance);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Telefono creado exitosamente.", phoneDtoExit));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PhoneDtoExit>> allPhone() {
        List<PhoneDtoExit> allPhone = phoneService.getAllPhone();
        return new ResponseEntity<>(allPhone, HttpStatus.OK);
    }

    @GetMapping("/search/{idPhone}")
    public ResponseEntity<PhoneDtoExit> searchPhoneById(@PathVariable Long idPhone) throws ResourceNotFoundException {
        PhoneDtoExit foundPhone = phoneService.getPhoneById(idPhone);
        return new ResponseEntity<>(foundPhone, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<PhoneDtoExit> updatepPhone(@Valid @RequestBody PhoneDtoModify phoneDtoModify) throws ResourceNotFoundException {
        PhoneDtoExit updatedPhone = phoneService.updatePhone(phoneDtoModify);
        return new ResponseEntity<>(updatedPhone, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{idPhone}")
    public ResponseEntity<ApiResponse<String>> deletePhone(@PathVariable Long idPhone) {
        try {
            phoneService.deletePhone(idPhone);
            return ResponseEntity.ok(new ApiResponse<>("Telefono eliminado exitosamente.", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("El  Telefono con el ID proporcionado no se encontró.", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("Ocurrió un error al procesar la solicitud.", null));
        }
    }
}
