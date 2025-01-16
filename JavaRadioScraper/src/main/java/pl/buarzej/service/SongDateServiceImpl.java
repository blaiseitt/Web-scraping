package pl.buarzej.service;

import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;

@Service
public class SongDateServiceImpl implements SongDateService {

    @Override
    public String getSongPlayedDate(String songPlayedTime) {
        Instant currentInstant = Instant.now();
        ZoneId systemZone = ZoneId.systemDefault();
        ZonedDateTime currentZonedDateTime = currentInstant.atZone(systemZone);
        LocalDate currentDate = currentZonedDateTime.toLocalDate();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime playedTime = LocalTime.parse(songPlayedTime, timeFormatter);
        ZonedDateTime playedDateTime = currentDate.atTime(playedTime).atZone(systemZone);
        if (playedDateTime.isAfter(currentZonedDateTime)) {
            playedDateTime = playedDateTime.minusDays(1);
        }
        return playedDateTime.toLocalDate().toString();
    }
}
