package com.musichouse.api.music.dto.dto_entrance;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrivacyPolicyDtoEntrance {

    @NotNull(message = "El titulo de la politica de privacidad deve estar presente ")
    private String title;

    @NotNull(message = "El contenido de la politica de privacidad deve estar presente ")
    private String content;
}
