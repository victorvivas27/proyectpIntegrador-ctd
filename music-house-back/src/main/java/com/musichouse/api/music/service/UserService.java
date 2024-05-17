package com.musichouse.api.music.service;

import com.musichouse.api.music.dto.dto_entrance.UserAdminDtoEntrance;
import com.musichouse.api.music.dto.dto_entrance.UserDtoEntrance;
import com.musichouse.api.music.dto.dto_exit.UserDtoExit;
import com.musichouse.api.music.dto.dto_modify.UserDtoModify;
import com.musichouse.api.music.entity.Role;
import com.musichouse.api.music.entity.User;
import com.musichouse.api.music.exception.ResourceNotFoundException;
import com.musichouse.api.music.interfaces.UserInterface;
import com.musichouse.api.music.repository.AddressRepository;
import com.musichouse.api.music.repository.PhoneRepository;
import com.musichouse.api.music.repository.RolRepository;
import com.musichouse.api.music.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@AllArgsConstructor
public class UserService implements UserInterface {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final RolRepository rolRepository;
    private final AddressRepository addressRepository;
    private final PhoneRepository phoneRepository;


    @Transactional
    @Override
    public UserDtoExit createUser(UserDtoEntrance userDtoEntrance) throws DataIntegrityViolationException {
        User user = mapper.map(userDtoEntrance, User.class);
        Role role = rolRepository.findByRol("USER")
                .orElseGet(() -> rolRepository.save(new Role("USER")));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        user.getAddresses().forEach(address -> address.setUser(user));
        user.getPhones().forEach(phone -> phone.setUser(user));
        User userSaved = userRepository.save(user);
        return mapper.map(userSaved, UserDtoExit.class);
    }

    @Transactional
    @Override
    public UserDtoExit createUserAdmin(UserAdminDtoEntrance userAdminDtoEntrance)throws DataIntegrityViolationException {
        User user = mapper.map(userAdminDtoEntrance, User.class);
        Role role = rolRepository.findByRol("ADMIN")
                .orElseGet(() -> rolRepository.save(new Role("ADMIN")));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        User userSaved = userRepository.save(user);
        return mapper.map(userSaved, UserDtoExit.class);
    }

    @Override
    public List<UserDtoExit> getAllUser() {
        List<UserDtoExit> userDtoExits = userRepository.findAll().stream()
                .map(user -> mapper.map(user, UserDtoExit.class)).toList();
        return userDtoExits ;
    }

    @Override
    public UserDtoExit getUserById(Long idUser) throws ResourceNotFoundException {
        User user = userRepository.findById(idUser).orElse(null);
        UserDtoExit userDtoExit = null;
        if (user != null) {
            userDtoExit = mapper.map(user, UserDtoExit.class);
        } else {
            throw new ResourceNotFoundException("User not found with id: " + idUser);
        }

        return userDtoExit;
    }

    @Override
    public UserDtoExit updateUser(UserDtoModify userDtoModify) throws ResourceNotFoundException {
        User userToUpdate = userRepository.findById(userDtoModify.getIdUser())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userDtoModify.getIdUser()));
        userToUpdate.setName(userDtoModify.getName());
        userToUpdate.setLastName(userDtoModify.getLastName());
        userToUpdate.setEmail(userDtoModify.getEmail());
        userToUpdate.setPassword(userDtoModify.getPassword());
        userRepository.save(userToUpdate);
        return mapper.map(userToUpdate, UserDtoExit.class);

    }

    @Override
    public void deleteUser(Long idUser) throws ResourceNotFoundException {
        Optional<User> usuarioOptional = userRepository.findById(idUser);
        if (usuarioOptional.isPresent()) {
            User usuario = usuarioOptional.get();
            usuario.getRoles().clear();
            userRepository.save(usuario);
            userRepository.deleteById(idUser);
        } else {
            throw new ResourceNotFoundException("User not found with id: " + idUser);
        }
    }

}
