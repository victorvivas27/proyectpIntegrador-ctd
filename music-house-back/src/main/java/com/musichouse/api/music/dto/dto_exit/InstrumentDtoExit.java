package com.musichouse.api.music.dto.dto_exit;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.musichouse.api.music.entity.Characteristics;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstrumentDtoExit {
    private Long idInstrument;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date registDate;
    private String name;
    private String description;
    private String measures;
    private BigDecimal weight;
    private BigDecimal rentalPrice;
    private CategoryDtoExit category;
    private ThemeDtoExit theme;
    private List<ImagesUrlsDtoExit> imageUrls;
    private Characteristics characteristics;
}
