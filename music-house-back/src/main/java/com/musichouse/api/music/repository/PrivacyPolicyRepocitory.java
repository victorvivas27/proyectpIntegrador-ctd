package com.musichouse.api.music.repository;

import com.musichouse.api.music.entity.PrivacyPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivacyPolicyRepocitory extends JpaRepository<PrivacyPolicy, Long> {
}

