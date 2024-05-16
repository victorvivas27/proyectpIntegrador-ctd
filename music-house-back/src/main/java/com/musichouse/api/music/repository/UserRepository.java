package com.musichouse.api.music.repository;

import com.musichouse.api.music.entity.Address;
import com.musichouse.api.music.entity.Instrument;
import com.musichouse.api.music.entity.Theme;
import com.musichouse.api.music.entity.User;
import com.mysql.cj.log.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  boolean existsByEmail(String email);

  Optional<User> findByRoles_Rol(String rol);
  }
