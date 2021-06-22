package com.app.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class LocalDateFormatter implements Formatter<LocalDate> {
    private DateTimeFormatter dateTimeFormatter;
    private String datePattern;

    public LocalDateFormatter(String datePattern){
        this.datePattern = datePattern;
        dateTimeFormatter = DateTimeFormatter.ofPattern(this.datePattern);
    }

    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
       try{
           return LocalDate.parse(text,DateTimeFormatter.ofPattern(this.datePattern));
       }
       catch (DateTimeParseException e){
           throw new IllegalArgumentException("invalid date format "+datePattern);
       }
    }

    @Override
    public String print(LocalDate object, Locale locale) {
        return object.format(dateTimeFormatter);
    }
}
