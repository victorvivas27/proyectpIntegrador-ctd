package com.musichouse.api.music.service;

import com.musichouse.api.music.dto.dto_entrance.LoginDtoEntrance;
import com.musichouse.api.music.dto.dto_entrance.UserAdminDtoEntrance;
import com.musichouse.api.music.dto.dto_entrance.UserDtoEntrance;
import com.musichouse.api.music.dto.dto_exit.TokenDtoSalida;
import com.musichouse.api.music.dto.dto_exit.UserDtoExit;
import com.musichouse.api.music.dto.dto_modify.UserDtoModify;
import com.musichouse.api.music.entity.Role;
import com.musichouse.api.music.entity.User;
import com.musichouse.api.music.exception.ResourceNotFoundException;
import com.musichouse.api.music.infra.MailManager;
import com.musichouse.api.music.interfaces.UserInterface;
import com.musichouse.api.music.repository.AddressRepository;
import com.musichouse.api.music.repository.PhoneRepository;
import com.musichouse.api.music.repository.RolRepository;
import com.musichouse.api.music.repository.UserRepository;
import com.musichouse.api.music.security.JwtService;
import com.musichouse.api.music.util.RoleConstants;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@AllArgsConstructor
public class UserService implements UserInterface {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RolRepository rolRepository;
    private final AddressRepository addressRepository;
    private final PhoneRepository phoneRepository;
    @Autowired
    private  final MailManager mailManager;


    @Transactional
    @Override
    public UserDtoExit createUser(UserDtoEntrance userDtoEntrance) throws DataIntegrityViolationException {
        User user = mapper.map(userDtoEntrance, User.class);
        String contraseñaEncriptada = passwordEncoder.encode(user.getPassword());
        user.setPassword(contraseñaEncriptada);
        Role role = rolRepository.findByRol(RoleConstants.USER)
                .orElseGet(() -> rolRepository.save(new Role(RoleConstants.USER)));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        user.getAddresses().forEach(address -> address.setUser(user));
        user.getPhones().forEach(phone -> phone.setUser(user));
        String jwt = jwtService.generateToken(user);
        User userSaved = userRepository.save(user);
        UserDtoExit userDtoExit = mapper.map(userSaved, UserDtoExit.class);
        userDtoExit.setToken(new TokenDtoSalida(jwt));

        try {
            sendMessageUser(user.getEmail(), user.getName(),user.getLastName());
        } catch (MessagingException e) {
            LOGGER.error("Error al enviar el correo electrónico: ", e);
            throw new RuntimeException("Error al enviar el correo electrónico", e);
        }
        return userDtoExit;
    }

    @Transactional
    @Override
    public UserDtoExit createUserAdmin(UserAdminDtoEntrance userAdminDtoEntrance) throws DataIntegrityViolationException {
        User user = mapper.map(userAdminDtoEntrance, User.class);
        String contraseñaEncriptada = passwordEncoder.encode(user.getPassword());
        user.setPassword(contraseñaEncriptada);
        Role role = rolRepository.findByRol(RoleConstants.ADMIN)
                .orElseGet(() -> rolRepository.save(new Role(RoleConstants.ADMIN)));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        String jwt = jwtService.generateToken(user);
        User userSaved = userRepository.save(user);
        UserDtoExit userDtoExit = mapper.map(userSaved, UserDtoExit.class);
        userDtoExit.setToken(new TokenDtoSalida(jwt));
        return userDtoExit;
    }

    @Override
    public TokenDtoSalida loginUserAndCheckEmail(LoginDtoEntrance loginDtoEntrance) throws ResourceNotFoundException, AuthenticationException {
        Optional<User> userOptional = userRepository.findByEmail(loginDtoEntrance.getEmail());
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("Usuario no encontrado con el correo electrónico proporcionado.");
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDtoEntrance.getEmail(), loginDtoEntrance.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);
        User user = userOptional.get();
        TokenDtoSalida tokenDtoSalida = new TokenDtoSalida(token, new ArrayList<>(user.getRoles()));
        return tokenDtoSalida;
    }

    @Override
    public List<UserDtoExit> getAllUser() {
        List<UserDtoExit> userDtoExits = userRepository.findAll().stream()
                .map(user -> mapper.map(user, UserDtoExit.class)).toList();
        return userDtoExits;
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
        if (!userToUpdate.getPassword().isEmpty()) {
            String contraseñaEncriptada = passwordEncoder.encode(userToUpdate.getPassword());
            userToUpdate.setPassword(contraseñaEncriptada);
        }
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

    public  void sendMessageUser(String email,String name,String lastName) throws MessagingException {
        mailManager.sendMessage(email,name,lastName);

    }


}
