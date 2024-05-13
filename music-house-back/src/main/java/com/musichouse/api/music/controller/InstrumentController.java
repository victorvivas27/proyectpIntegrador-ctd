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
    public ResponseEntity<InstrumentDtoExit> searchInstrumentById(@PathVariable Long idInstrument) throws ResourceNotFoundException {
        InstrumentDtoExit foundInstrument = instrumentService.getInstrumentById(idInstrument);
        return new ResponseEntity<>(foundInstrument, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<InstrumentDtoExit> updateInstrument(@Valid @RequestBody InstrumentDtoModify instrumentDtoModify) throws ResourceNotFoundException {
        InstrumentDtoExit updatedInstrument = instrumentService.updateInstrument(instrumentDtoModify);
        return new ResponseEntity<>(updatedInstrument, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{idInstrument}")
    public ResponseEntity<ApiResponse<String>> deleteInstrument(@PathVariable Long idInstrument) {
        try {
            instrumentService.deleteInstrument(idInstrument);
            return ResponseEntity.ok(new ApiResponse<>("Instrumento eliminado exitosamente.", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("El instrumento con el ID proporcionado no se encontró.", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("Ocurrió un error al procesar la solicitud.", null));
        }
    }

}
