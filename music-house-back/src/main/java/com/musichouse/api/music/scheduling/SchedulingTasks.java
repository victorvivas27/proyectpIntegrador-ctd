package com.musichouse.api.music.scheduling;

import com.musichouse.api.music.service.AvailableDateService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SchedulingTasks {
    private final AvailableDateService availableDateService;
    @Scheduled(cron = "0 0 0 * * ?") // Ejecuta a medianoche todos los días
    public void deletePastAvailableDates() {
        availableDateService.deletePastAvailableDates();
    }
}
