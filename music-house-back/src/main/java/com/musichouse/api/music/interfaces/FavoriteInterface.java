package com.musichouse.api.music.interfaces;

import com.musichouse.api.music.dto.dto_entrance.FavoriteDtoEntrance;
import com.musichouse.api.music.dto.dto_exit.FavoriteDtoExit;
import com.musichouse.api.music.exception.ResourceNotFoundException;

import java.util.List;

public interface FavoriteInterface {

    FavoriteDtoExit addFavorite(FavoriteDtoEntrance favoriteDtoEntrance) throws ResourceNotFoundException;

    List<FavoriteDtoExit> getAllFavorite();

    public void deleteFavorite(Long idInstrument, Long idUser, Long idFavorite) throws ResourceNotFoundException;
}