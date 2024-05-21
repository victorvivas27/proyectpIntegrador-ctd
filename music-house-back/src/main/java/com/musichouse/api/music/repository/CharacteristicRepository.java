package com.musichouse.api.music.repository;

import com.musichouse.api.music.entity.Characteristics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacteristicRepository extends JpaRepository<Characteristics, Long> {
}
