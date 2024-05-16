package com.musichouse.api.music.interfaces;

import com.musichouse.api.music.dto.dto_entrance.AddressAddDtoEntrance;
import com.musichouse.api.music.dto.dto_entrance.AddressDtoEntrance;
import com.musichouse.api.music.dto.dto_exit.AddressDtoExit;
import com.musichouse.api.music.dto.dto_modify.AddressDtoModify;
import com.musichouse.api.music.exception.ResourceNotFoundException;

import java.util.List;

public interface AddressInterface {
   /* AddressDtoExit addAddress(AddressAddDtoEntrance addressAddDtoEntrance) throws ResourceNotFoundException;*/

    List<AddressDtoExit> getAllAddress();

    AddressDtoExit getAddressById(Long idAddress) throws ResourceNotFoundException;

    AddressDtoExit updateAddress(AddressDtoModify addressDtoModify) throws ResourceNotFoundException;

    void deleteAddress(Long idAddress) throws ResourceNotFoundException;
}
