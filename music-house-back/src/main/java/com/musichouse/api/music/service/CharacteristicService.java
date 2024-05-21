package com.musichouse.api.music.service;

import com.musichouse.api.music.dto.dto_exit.CharacteristicDtoExit;
import com.musichouse.api.music.dto.dto_modify.CharacteristicDtoModify;
import com.musichouse.api.music.entity.Characteristics;
import com.musichouse.api.music.exception.ResourceNotFoundException;
import com.musichouse.api.music.interfaces.CharacteristicInterface;
import com.musichouse.api.music.repository.CharacteristicRepository;
import com.musichouse.api.music.repository.InstrumentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CharacteristicService implements CharacteristicInterface {
    private final static Logger LOGGER = LoggerFactory.getLogger(CharacteristicService.class);
    private final CharacteristicRepository characteristicRepository;
    private final InstrumentRepository instrumentRepository;
    private final ModelMapper mapper;


    @Override
    public List<CharacteristicDtoExit> getAllCharacteristic() {
        List<CharacteristicDtoExit> characteristicDtoExits = characteristicRepository.findAll().stream()
                .map(user -> mapper.map(user, CharacteristicDtoExit.class)).toList();
        return characteristicDtoExits;
    }

    @Override
    public CharacteristicDtoExit getCharacteristicById(Long idCharacteristic) throws ResourceNotFoundException {
        Characteristics characteristics = characteristicRepository.findById(idCharacteristic).orElse(null);
        CharacteristicDtoExit characteristicDtoExit = null;
        if (characteristics != null) {
            characteristicDtoExit = mapper.map(characteristics, CharacteristicDtoExit.class);
        } else {
            throw new ResourceNotFoundException(" Characteristic not found with id: " + idCharacteristic);
        }

        return characteristicDtoExit;
    }

    @Override
    public CharacteristicDtoExit updateCharacteristic(CharacteristicDtoModify characteristicDtoModify) throws ResourceNotFoundException {
        Characteristics characteristicsToUpdate = characteristicRepository.findById(characteristicDtoModify.getIdCharacteristic())
                .orElseThrow(() -> new ResourceNotFoundException("Characteristic not found with id: " + characteristicDtoModify.getIdCharacteristic()));
        characteristicsToUpdate.setMaterial(characteristicDtoModify.getMaterial());
        characteristicsToUpdate.setFrets(characteristicDtoModify.getFrets());
        characteristicsToUpdate.setScaleLength(characteristicDtoModify.getScaleLength());
        characteristicsToUpdate.setNumberOfStrings(characteristicDtoModify.getNumberOfStrings());
        characteristicsToUpdate.setTypeOfStrings(characteristicDtoModify.getTypeOfStrings());
        characteristicsToUpdate.setOriginCountry(characteristicDtoModify.getOriginCountry());
        characteristicRepository.save(characteristicsToUpdate);
        return mapper.map(characteristicsToUpdate, CharacteristicDtoExit.class);

    }
}
