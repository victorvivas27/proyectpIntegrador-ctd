package com.musichouse.api.music.dto.dto_exit;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.musichouse.api.music.entity.Instrument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteDtoExit {
    private Long idFavorite;
    private Long idUser;
    private Boolean isFavorite;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date registDate;
    private Instrument instrument;
    private String imageUrl;

}