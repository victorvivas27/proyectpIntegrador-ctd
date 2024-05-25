package com.musichouse.api.music.service;

import com.musichouse.api.music.entity.Role;
import com.musichouse.api.music.entity.User;
import com.musichouse.api.music.exception.ResourceNotFoundException;
import com.musichouse.api.music.interfaces.RoleInterface;
import com.musichouse.api.music.repository.RolRepository;
import com.musichouse.api.music.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RoleService implements RoleInterface {
    private final static Logger LOGGER = LoggerFactory.getLogger(RoleService.class);
    private final RolRepository rolRepository;
    private final ModelMapper mapper;
    private final UserRepository userRepository;

    @Transactional
    public void addRoleToUser(Long userId, String rol) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        Role role = rolRepository.findByRol(rol)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with name: " + rol));
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Transactional
    public void removeRoleFromUser(Long userId, String rol) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        Role role = rolRepository.findByRol(rol)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with name: " + rol));
        user.getRoles().remove(role);
        userRepository.save(user);
    }

}
