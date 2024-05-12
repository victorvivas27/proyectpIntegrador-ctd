package com.musichouse.api.music.dto.dto_exit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDtoExit {
    private Long idCategory;
    private LocalDate creationDate;
    private String categoryName;
    private String description;


}
