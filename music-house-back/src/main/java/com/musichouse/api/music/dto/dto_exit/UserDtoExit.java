package com.musichouse.api.music.dto.dto_exit;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.musichouse.api.music.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoExit {
    private Long idUser;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date registDate;
    private String name;
    private String lastName;
    private String email;
    private Long telegramChatId;
    private List<Role> roles;
    private List<AddressDtoExit> addresses;
    private List<PhoneDtoExit> phones;

}
