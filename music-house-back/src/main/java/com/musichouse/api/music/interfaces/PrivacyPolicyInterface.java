package com.musichouse.api.music.interfaces;

import com.musichouse.api.music.dto.dto_entrance.PrivacyPolicyDtoEntrance;
import com.musichouse.api.music.dto.dto_exit.PrivacyPolicyDtoExit;
import com.musichouse.api.music.dto.dto_modify.PrivacyPolicyDtoModify;
import com.musichouse.api.music.exception.ResourceNotFoundException;

import java.util.List;

public interface PrivacyPolicyInterface {

    PrivacyPolicyDtoExit createPrivacyPolicy(PrivacyPolicyDtoEntrance privacyPolicyDtoEntrance);

    List<PrivacyPolicyDtoExit> getAllPrivacyPolicy();

    PrivacyPolicyDtoExit updatePrivacyPolicy(PrivacyPolicyDtoModify privacyPolicyDtoModify) throws ResourceNotFoundException;

    void deleteidPrivacyPolicy(Long idPrivacyPolicy) throws ResourceNotFoundException;
}
