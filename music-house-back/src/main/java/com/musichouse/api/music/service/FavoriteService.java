package com.musichouse.api.music.service;

import com.musichouse.api.music.dto.dto_entrance.FavoriteDtoEntrance;
import com.musichouse.api.music.dto.dto_exit.FavoriteDtoExit;
import com.musichouse.api.music.dto.dto_exit.IsFavoriteExit;
import com.musichouse.api.music.entity.Favorite;
import com.musichouse.api.music.entity.ImageUrls;
import com.musichouse.api.music.entity.Instrument;
import com.musichouse.api.music.entity.User;
import com.musichouse.api.music.exception.FavoriteAlreadyExistsException;
import com.musichouse.api.music.exception.ResourceNotFoundException;
import com.musichouse.api.music.interfaces.FavoriteInterface;
import com.musichouse.api.music.repository.FavoriteRepository;
import com.musichouse.api.music.repository.InstrumentRepository;
import com.musichouse.api.music.repository.UserRepository;
import com.musichouse.api.music.util.ApiResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FavoriteService implements FavoriteInterface {
    private final static Logger LOGGER = LoggerFactory.getLogger(FavoriteService.class);
    private final ModelMapper mapper;
    private final FavoriteRepository favoriteRepository;
    private final InstrumentRepository instrumentRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public FavoriteDtoExit addFavorite(FavoriteDtoEntrance favoriteDtoEntrance) throws ResourceNotFoundException {
        Long instrumentId = favoriteDtoEntrance.getIdInstrument();
        Long userId = favoriteDtoEntrance.getIdUser();

        Instrument instrument = instrumentRepository.findById(instrumentId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el instrumento con el ID proporcionado"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el usuario con el ID proporcionado"));

        Favorite existingFavorite = favoriteRepository.findByUserAndInstrument(user, instrument);
        if (existingFavorite != null) {
            throw new FavoriteAlreadyExistsException("El instrumento ya está agregado como favorito para este usuario");
        }

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setInstrument(instrument);
        favorite.setIsFavorite(true);
        Favorite favoriteSaved = favoriteRepository.save(favorite);

        String imageUrl = "";
        List<ImageUrls> imageUrls = instrument.getImageUrls();
        if (imageUrls != null && !imageUrls.isEmpty()) {
            imageUrl = imageUrls.get(0).getImageUrl();
        }

        FavoriteDtoExit favoriteDtoExit = new FavoriteDtoExit();
        favoriteDtoExit.setIdFavorite(favoriteSaved.getIdFavorite());
        favoriteDtoExit.setInstrument(favoriteSaved.getInstrument());
        favoriteDtoExit.setImageUrl(imageUrl);
        favoriteDtoExit.setIdUser(favoriteSaved.getUser().getIdUser());
        favoriteDtoExit.setRegistDate(favoriteSaved.getRegistDate());
        favoriteDtoExit.setIsFavorite(favoriteSaved.getIsFavorite());

        return favoriteDtoExit;
    }

    @Override
    public List<FavoriteDtoExit> getAllFavorite() {
        return favoriteRepository.findAll().stream()
                .map(favorite -> {
                    FavoriteDtoExit favoriteDtoExit = mapper.map(favorite, FavoriteDtoExit.class);
                    String imageUrl = "";
                    Instrument instrument = favorite.getInstrument();
                    List<ImageUrls> imageUrls = instrument.getImageUrls();
                    if (imageUrls != null && !imageUrls.isEmpty()) {
                        imageUrl = imageUrls.get(0).getImageUrl();
                    }
                    favoriteDtoExit.setImageUrl(imageUrl);
                    favoriteDtoExit.setIsFavorite(favorite.getIsFavorite());
                    return favoriteDtoExit;
                })
                .collect(Collectors.toList());
    }


    @Override
    public ApiResponse deleteFavorite(Long idInstrument, Long idUser, Long idFavorite) throws ResourceNotFoundException {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID " + idUser));
        Instrument instrument = instrumentRepository.findById(idInstrument)
                .orElseThrow(() -> new ResourceNotFoundException("Instrumento no encontrado con ID " + idInstrument));
        Favorite favorite = favoriteRepository.findById(idFavorite)
                .orElseThrow(() -> new ResourceNotFoundException("Favorito no encontrado con ID " + idFavorite));
        if (!favorite.getUser().getIdUser().equals(idUser) || !favorite.getInstrument().getIdInstrument().equals(idInstrument)) {
            throw new ResourceNotFoundException("Favorito no encontrado para el usuario con ID " + idUser +
                    " y el instrumento con ID " + idInstrument);
        }
        IsFavoriteExit isFavoriteExit = new IsFavoriteExit();
        isFavoriteExit.setIsFavorite(false);
        favoriteRepository.delete(favorite);
        ApiResponse<IsFavoriteExit> response = new ApiResponse<>("Favorito eliminado exitosamente.", isFavoriteExit);

        return response;
    }
}
