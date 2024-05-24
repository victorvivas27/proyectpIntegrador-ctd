package com.musichouse.api.music.interfaces;

import com.musichouse.api.music.dto.dto_entrance.LoginDtoEntrance;
import com.musichouse.api.music.dto.dto_entrance.UserAdminDtoEntrance;
import com.musichouse.api.music.dto.dto_entrance.UserDtoEntrance;
import com.musichouse.api.music.dto.dto_exit.TokenDtoSalida;
import com.musichouse.api.music.dto.dto_exit.UserDtoExit;
import com.musichouse.api.music.dto.dto_modify.UserDtoModify;
import com.musichouse.api.music.exception.ResourceNotFoundException;
import jakarta.mail.MessagingException;

import java.util.List;

public interface UserInterface {
    UserDtoExit createUser(UserDtoEntrance userDtoEntrance) throws MessagingException;

    UserDtoExit createUserAdmin(UserAdminDtoEntrance userAdminDtoEntrance) throws MessagingException;

    List<UserDtoExit> getAllUser();

    UserDtoExit getUserById(Long idUser) throws ResourceNotFoundException;

    UserDtoExit updateUser(UserDtoModify userDtoModify) throws ResourceNotFoundException;

    void deleteUser(Long idUser) throws ResourceNotFoundException;

    TokenDtoSalida loginUserAndCheckEmail(LoginDtoEntrance loginDtoEntrance) throws ResourceNotFoundException;
}
