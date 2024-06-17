package com.musichouse.api.music.repository;

import com.musichouse.api.music.entity.Favorite;
import com.musichouse.api.music.entity.Instrument;
import com.musichouse.api.music.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Favorite findByUserAndInstrument(User user, Instrument instrument);

    @Query("SELECT f FROM Favorite f WHERE f.user.id = :userId")
    List<Favorite> findByUserId(@Param("userId") Long userId);

    @Transactional
    void deleteByInstrumentIdInstrument(Long idInstrument);
}
