package com.musichouse.api.music.interfaces;

import com.musichouse.api.music.dto.dto_exit.CharacteristicDtoExit;
import com.musichouse.api.music.dto.dto_modify.CharacteristicDtoModify;
import com.musichouse.api.music.exception.ResourceNotFoundException;

import java.util.List;


public interface CharacteristicInterface {


    List<CharacteristicDtoExit> getAllCharacteristic();

    CharacteristicDtoExit getCharacteristicById(Long idCharacteristic) throws ResourceNotFoundException;

    CharacteristicDtoExit updateCharacteristic(CharacteristicDtoModify characteristicDtoModify) throws ResourceNotFoundException;


}

