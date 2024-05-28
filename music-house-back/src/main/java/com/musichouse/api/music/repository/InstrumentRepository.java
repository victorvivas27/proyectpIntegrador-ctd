package com.musichouse.api.music.repository;

import com.musichouse.api.music.entity.Category;
import com.musichouse.api.music.entity.Instrument;
import com.musichouse.api.music.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface InstrumentRepository extends JpaRepository<Instrument, Long> {
    List<Instrument> findByCategory(Category category);
    List<Instrument> findByTheme(Theme theme);
    List<Instrument> findByNameContaining(String name);
    List<Instrument> findByRentalPriceLessThan(BigDecimal maxPrice);
    List<Instrument> findByRentalPriceGreaterThan(BigDecimal minPrice);
    List<Instrument> findByRentalPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}
