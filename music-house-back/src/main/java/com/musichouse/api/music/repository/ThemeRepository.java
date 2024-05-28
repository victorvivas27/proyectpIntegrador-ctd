package com.musichouse.api.music.repository;

import com.musichouse.api.music.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {
    List<Theme> findBythemeNameContaining(String themeName);
}
