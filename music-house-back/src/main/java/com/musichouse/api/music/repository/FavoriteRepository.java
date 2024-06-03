package com.musichouse.api.music.repository;

import com.musichouse.api.music.entity.Favorite;
import com.musichouse.api.music.entity.Instrument;
import com.musichouse.api.music.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Favorite findByUserAndInstrument(User user, Instrument instrument);
}
