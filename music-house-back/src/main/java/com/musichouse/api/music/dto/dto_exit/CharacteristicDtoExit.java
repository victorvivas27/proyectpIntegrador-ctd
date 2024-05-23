package com.musichouse.api.music.dto.dto_exit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacteristicDtoExit {
    private Long idCharacteristics;
    private String instrumentCase;
    private String support;
    private String tuner;
    private String microphone;
    private String phoneHolder;
}
