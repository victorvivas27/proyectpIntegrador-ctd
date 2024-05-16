package com.musichouse.api.music;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MusicHouseApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(MusicHouseApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MusicHouseApplication.class, args);
        LOGGER.info("🎹 Let's hit the keys and start the musical journey with MusicHouseApplication! 🎵🌟");

    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
