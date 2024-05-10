package com.musichouse.api.music.dto.dto_exit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDtoExit {

    private Long idCategory;
    private String categoryName;
    private String description;


}
