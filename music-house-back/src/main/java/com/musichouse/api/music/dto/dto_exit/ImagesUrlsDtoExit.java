package com.musichouse.api.music.dto.dto_exit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImagesUrlsDtoExit {
    private Long idImage;
    private LocalDate creationDate;
    private String imageUrl;


}
