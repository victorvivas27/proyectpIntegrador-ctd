package com.musichouse.api.music.interfaces;

import com.musichouse.api.music.dto.dto_entrance.ThemeDtoEntrance;
import com.musichouse.api.music.dto.dto_exit.ThemeDtoExit;
import com.musichouse.api.music.dto.dto_modify.ThemeDtoModify;
import com.musichouse.api.music.exception.ResourceNotFoundException;

import java.util.List;

public interface ThemeInterface {

    ThemeDtoExit createTheme(ThemeDtoEntrance themeDtoEntrance);

    List<ThemeDtoExit> getAllThemes();

    ThemeDtoExit getThemeById(Long idTheme) throws ResourceNotFoundException;

    ThemeDtoExit updateTheme(ThemeDtoModify themeDtoModify) throws ResourceNotFoundException;

    void deleteTheme(Long idTheme) throws ResourceNotFoundException;
}
