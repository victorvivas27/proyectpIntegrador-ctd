package com.musichouse.api.music.service;

import com.musichouse.api.music.dto.dto_entrance.PhoneDtoEntrance;
import com.musichouse.api.music.dto.dto_exit.PhoneDtoExit;
import com.musichouse.api.music.dto.dto_modify.PhoneDtoModify;
import com.musichouse.api.music.exception.ResourceNotFoundException;
import com.musichouse.api.music.interfaces.PhoneInterface;
import com.musichouse.api.music.repository.PhoneRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PhoneService implements PhoneInterface {
    private final static Logger LOGGER = LoggerFactory.getLogger(PhoneService.class);
    private final PhoneRepository repository;
    private final ModelMapper mapper;

    @Override
    public PhoneDtoExit createPhone(PhoneDtoEntrance phoneDtoEntrance) {
        return null;
    }

    @Override
    public List<PhoneDtoExit> getAllPhone() {
        return List.of();
    }

    @Override
    public PhoneDtoExit getPhoneById(Long idPhone) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public PhoneDtoExit updatePhone(PhoneDtoModify phoneDtoModify) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public void deletePhone(Long idPhone) throws ResourceNotFoundException {

    }
}
