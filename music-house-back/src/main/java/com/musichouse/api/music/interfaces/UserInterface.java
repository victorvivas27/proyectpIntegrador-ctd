package com.musichouse.api.music.interfaces;

import com.musichouse.api.music.dto.dto_entrance.UserDtoEntrance;
import com.musichouse.api.music.dto.dto_exit.UserDtoExit;
import com.musichouse.api.music.dto.dto_modify.UserDtoModify;
import com.musichouse.api.music.exception.ResourceNotFoundException;

import java.util.List;

public interface UserInterface {
    UserDtoExit createUserWithRole(UserDtoEntrance userDtoEntrance, String rol);

    List<UserDtoExit> getAllUser();

    UserDtoExit getUserById(Long idUser) throws ResourceNotFoundException;

    UserDtoExit updateUser(UserDtoModify userDtoModify) throws ResourceNotFoundException;

    void deleteUser(Long idUser) throws ResourceNotFoundException;
}
