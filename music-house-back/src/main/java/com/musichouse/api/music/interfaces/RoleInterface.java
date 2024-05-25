package com.musichouse.api.music.interfaces;

import com.musichouse.api.music.exception.ResourceNotFoundException;

public interface RoleInterface {


    public void addRoleToUser(Long userId, String roleName) throws ResourceNotFoundException;

    public void removeRoleFromUser(Long userId, String roleName) throws ResourceNotFoundException;
}
