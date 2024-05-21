package com.musichouse.api.music.dto.dto_exit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacteristicDtoExit {
    private Long idCharacteristics;
    private String material;
    private Long frets;
    private String scaleLength;
    private Long numberOfStrings;
    private String typeOfStrings;
    private String originCountry;
}
