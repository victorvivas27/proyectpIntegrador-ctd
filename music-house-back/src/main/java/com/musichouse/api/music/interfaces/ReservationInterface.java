package com.musichouse.api.music.interfaces;

import com.musichouse.api.music.dto.dto_entrance.ReservationDtoEntrance;
import com.musichouse.api.music.dto.dto_exit.ReservationDtoExit;
import com.musichouse.api.music.dto.dto_modify.ReservationDtoModify;
import com.musichouse.api.music.exception.ResourceNotFoundException;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.List;

public interface ReservationInterface {
    ReservationDtoExit createReservation(ReservationDtoEntrance reservationDtoEntrance) throws ResourceNotFoundException, MessagingException, IOException;

    List<ReservationDtoExit> getAllReservation();

    ReservationDtoExit getReservationById(Long idReservation) throws ResourceNotFoundException;

    public List<ReservationDtoExit> getReservationByUserId(Long userId) throws ResourceNotFoundException;

    ReservationDtoExit updateReservation(ReservationDtoModify reservationDtoModify) throws ResourceNotFoundException;

    void deleteReservation(Long idInstrument, Long idUser, Long idReservation) throws ResourceNotFoundException;
}
