package com.musichouse.api.music.dto.dto_exit;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDtoExit {
    private Long idAddress;
    private Long idUser;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date registDate;
    //private Long idUser;
    private String street;
    private Long number;
    private String city;
    private String state;
    private String country;
}
