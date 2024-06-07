package com.musichouse.api.music.dto.dto_modify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrivacyPolicyDtoModify {
    @NotNull(message = "El id de la politica de privacidad deve estar presente ")
    private Long idPrivacyPolicy;

    @NotNull(message = "El titulo de la politica de privacidad deve estar presente ")
    private String title;

    @NotNull(message = "El contenido de la politica de privacidad deve estar presente ")
    private String content;
}
