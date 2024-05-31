package com.musichouse.api.music.repository;

import com.musichouse.api.music.entity.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AvailableDateRepository extends JpaRepository<AvailableDate, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM AvailableDate a WHERE a.instrument.idInstrument = :idInstrument AND a.idAvailableDate = :idAvailableDate")
    void deleteByIdInstrumentAndIdAvailableDate(Long idInstrument, Long idAvailableDate);

    List<AvailableDate> findByDateAvailableAndAvailableIsTrue(LocalDate dateAvailable);

    Optional<AvailableDate> findByInstrumentIdInstrumentAndDateAvailable(Long instrumentId, LocalDate dateAvailable);

    List<AvailableDate> findByDateAvailableBetween(LocalDate startDate, LocalDate endDate);

    List<AvailableDate> findByInstrumentIdInstrumentAndDateAvailableBetween(Long idInstrument, LocalDate startDate, LocalDate endDate);

}
