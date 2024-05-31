package com.musichouse.api.music.dto.dto_exit;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateDtoExit {
    private Long idAvailableDate;
    private Long idInstrument;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date registDate;
    private LocalDate dateAvailable;
    private boolean available;
}
