package fa.training.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateStringConverter {
    public String convertStringToLocalDateTime(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.valueOf(LocalDateTime.parse(date, dateTimeFormatter));
    }

    public String convertLocalDateTimeToString(String localDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.valueOf(dateTimeFormatter);
    }
}
