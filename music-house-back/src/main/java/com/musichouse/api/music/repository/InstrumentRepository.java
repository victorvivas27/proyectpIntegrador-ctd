package com.musichouse.api.music.repository;

import com.musichouse.api.music.entity.Category;
import com.musichouse.api.music.entity.Instruments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstrumentRepository extends JpaRepository<Instruments, Long> {
    List<Instruments> findByCategory(Category category);
}
