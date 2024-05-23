package com.musichouse.api.music.controller;

import com.musichouse.api.music.dto.dto_entrance.InstrumentDtoEntrance;
import com.musichouse.api.music.dto.dto_exit.InstrumentDtoExit;
import com.musichouse.api.music.dto.dto_modify.InstrumentDtoModify;
import com.musichouse.api.music.exception.ResourceNotFoundException;
import com.musichouse.api.music.service.InstrumentService;
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
@RequestMapping("/api/instrument")
public class InstrumentController {

    private final InstrumentService instrumentService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<InstrumentDtoExit>> createInstrument(@Valid @RequestBody InstrumentDtoEntrance instrumentDtoEntrance) {
        try {
            InstrumentDtoExit instrumentDtoExit = instrumentService.createInstrument(instrumentDtoEntrance);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>("Instrumento creado exitosamente.", instrumentDtoExit));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("El idCategory o idTheme no se encuentra en la DB", null));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<InstrumentDtoExit>> allInstruments() {
        List<InstrumentDtoExit> allInstruments = instrumentService.getAllInstruments();
        return new ResponseEntity<>(allInstruments, HttpStatus.OK);
    }

    @GetMapping("/search/{idInstrument}")
    public ResponseEntity<?> searchInstrumentById(@PathVariable Long idInstrument) {
        try {
            InstrumentDtoExit foundInstrument = instrumentService.getInstrumentById(idInstrument);
            return ResponseEntity.ok(new ApiResponse<>("Instrumento encontrado.", foundInstrument));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("No se encontró el instrumento con el ID proporcionado.", null));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateInstrument(@Valid @RequestBody InstrumentDtoModify instrumentDtoModify) {
        try {
            InstrumentDtoExit instrumentDtoExit = instrumentService.updateInstrument(instrumentDtoModify);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>("Instrumento  actualizado con éxito.", instrumentDtoExit));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(e.getMessage(), null));
        }
    }

    @DeleteMapping("/delete/{idInstrument}")
    public ResponseEntity<ApiResponse<?>> deleteInstrument(@PathVariable Long idInstrument) {
        try {
            instrumentService.deleteInstrument(idInstrument);
            return ResponseEntity.ok(new ApiResponse<>("Instrumento con ID :" + idInstrument + " eliminado exitosamente.", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("El instrumento con el ID proporcionado no se encontró.", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("Ocurrió un error al procesar la solicitud.", null));
        }
    }
}


