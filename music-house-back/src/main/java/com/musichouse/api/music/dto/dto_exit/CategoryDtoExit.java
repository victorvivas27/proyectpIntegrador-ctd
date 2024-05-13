package com.musichouse.api.music.dto.dto_exit;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDtoExit {

    private Long idCategory;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date registDate;
    private String categoryName;
    private String description;


}
