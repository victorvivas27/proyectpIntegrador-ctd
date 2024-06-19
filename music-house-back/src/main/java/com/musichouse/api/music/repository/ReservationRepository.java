package com.musichouse.api.music.repository;

import com.musichouse.api.music.entity.Instrument;
import com.musichouse.api.music.entity.Reservation;
import com.musichouse.api.music.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Reservation findByUserAndInstrument(User user, Instrument instrument);

    @Query("SELECT f FROM Reservation f WHERE f.user.id = :userId")
    List<Reservation> findByUserId(@Param("userId") Long userId);

    @Query("SELECT r FROM Reservation r WHERE r.instrument.id = :instrumentId AND " +
            "((r.startDate <= :endDate AND r.startDate >= :startDate) OR " +
            "(r.endDate >= :startDate AND r.endDate <= :endDate) OR " +
            "(r.startDate <= :startDate AND r.endDate >= :endDate))")
    List<Reservation> findByInstrumentIdAndDateRange(@Param("instrumentId") Long instrumentId,
                                                     @Param("startDate") LocalDate startDate,
                                                     @Param("endDate") LocalDate endDate);
}
