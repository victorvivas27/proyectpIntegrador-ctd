package com.musichouse.api.music.repository;

import com.musichouse.api.music.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
