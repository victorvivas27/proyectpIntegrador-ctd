package com.musichouse.api.music.service;

import com.musichouse.api.music.dto.dto_entrance.ThemeDtoEntrance;
import com.musichouse.api.music.dto.dto_exit.ThemeDtoExit;
import com.musichouse.api.music.dto.dto_modify.ThemeDtoModify;
import com.musichouse.api.music.entity.Instrument;
import com.musichouse.api.music.entity.Theme;
import com.musichouse.api.music.exception.CategoryAssociatedException;
import com.musichouse.api.music.exception.ResourceNotFoundException;
import com.musichouse.api.music.interfaces.ThemeInterface;
import com.musichouse.api.music.repository.InstrumentRepository;
import com.musichouse.api.music.repository.ThemeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ThemeService implements ThemeInterface {
    private final static Logger LOGGER = LoggerFactory.getLogger(ThemeService.class);
    private final ThemeRepository themeRepository;
    private final InstrumentRepository instrumentRepository;
    private final ModelMapper mapper;

    @Override
    public ThemeDtoExit createTheme(ThemeDtoEntrance themeDtoEntrance) {
        Theme theme = mapper.map(themeDtoEntrance, Theme.class);
        Theme themeSaved = themeRepository.save(theme);
        ThemeDtoExit themeDtoExit = mapper.map(themeSaved, ThemeDtoExit.class);
        return themeDtoExit;
    }

    @Override
    public List<ThemeDtoExit> getAllThemes() {
        List<ThemeDtoExit> themeDtoExits = themeRepository.findAll().stream()
                .map(theme -> mapper.map(theme, ThemeDtoExit.class)).toList();
        return themeDtoExits;
    }

    @Override
    public ThemeDtoExit getThemeById(Long idTheme) throws ResourceNotFoundException {
        Theme theme = themeRepository.findById(idTheme).orElse(null);
        ThemeDtoExit themeDtoExit = null;
        if (theme != null) {
            themeDtoExit = mapper.map(theme, ThemeDtoExit.class);

        } else {
            throw new ResourceNotFoundException("Theme with id " + idTheme + " not found");
        }
        return themeDtoExit;
    }

    @Override
    public ThemeDtoExit updateTheme(ThemeDtoModify themeDtoModify) throws ResourceNotFoundException {
        Theme themeToUpdate = themeRepository.findById(themeDtoModify.getIdTheme())
                .orElseThrow(() -> new ResourceNotFoundException("Theme with id " + themeDtoModify.getIdTheme() + " not found"));
        themeToUpdate.setThemeName(themeDtoModify.getThemeName());
        themeToUpdate.setDescription(themeDtoModify.getDescription());
        themeRepository.save(themeToUpdate);
        return mapper.map(themeToUpdate, ThemeDtoExit.class);
    }

    @Override
    public void deleteTheme(Long idTheme) throws ResourceNotFoundException {
        Theme themeToDelete = themeRepository.findById(idTheme)
                .orElseThrow(() -> new ResourceNotFoundException("Theme with id " + idTheme + " not found"));
        List<Instrument> instruments = instrumentRepository.findByTheme(themeToDelete);
        if (!instruments.isEmpty()) {
            throw new CategoryAssociatedException("Cannot delete theme with id " + idTheme +
                    " as it is associated with instruments");
        }

        themeRepository.deleteById(idTheme);
    }


}
