package br.com.dld.suppledate;

import br.com.dld.suppledate.exceptions.FormatNotAcceptable;
import br.com.dld.suppledate.temporality.Temporality;
import br.com.dld.suppledate.temporality.TemporalityParser;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@Getter
@AllArgsConstructor
public enum Chronos {

    BASIC_D4("ddMMyyyy"),
    BASIC_D4HMS("ddMMyyyyHHmmss"),
    BASIC_D2("ddMMyy"),
    BASIC_D2HMS("ddMMyyHHmmss"),
    BASIC_D4_ISO("yyyyMMdd"),
    BASIC_D4HMS_ISO("yyyyMMddHHmmss"),
    BASIC_D2_ISO("yyMMdd"),
    BASIC_D2HMS_ISO("yyMMddHHmmss"),
    BASIC_HM("HHmm"),

    BAR_D4("dd/MM/yyyy"),
    BAR_D4HM("dd/MM/yyyy HH:mm"),
    BAR_D4HMS("dd/MM/yyyy HH:mm:ss"),
    BAR_D2("dd/MM/yy"),
    BAR_D2HM("dd/MM/yy HH:mm"),
    BAR_D2HMS("dd/MM/yy HH:mm:ss"),
    BAR_D4_ISO("yyyy/MM/dd"),
    BAR_D4HM_ISO("yyyy/MM/dd HH:mm"),
    BAR_D4HMS_ISO("yyyy/MM/dd HH:mm:ss"),
    BAR_D2_ISO("yy/MM/dd"),

    TRACE_D4("dd-MM-yyyy"),
    TRACE_D4HM("dd-MM-yyyy HH:mm"),
    TRACE_D4HMS("dd-MM-yyyy HH:mm:ss"),
    TRACE_D2("dd-MM-yy"),
    TRACE_D2HM("dd-MM-yy HH:mm"),
    TRACE_D2HMS("dd-MM-yy HH:mm:ss"),
    TRACE_D4_ISO("yyyy-MM-dd"),
    TRACE_D4HM_ISO("yyyy-MM-dd HH:mm"),
    TRACE_D4HMS_ISO("yyyy-MM-dd HH:mm:ss"),
    TRACE_D2_ISO("yy-MM-dd"),

    FILE_D2HM("yy-MM-dd HH mm"),
    FILE_D4HM("yyyy-MM-dd HH mm");

    private final String pattern;

    public Temporality now() {
        return of(LocalDateTime.now());
    }

    public Temporality now(ZoneId zoneId) {
        return of(LocalDateTime.now(), zoneId);
    }

    public Temporality of(String date) throws FormatNotAcceptable {
        return Temporality.of(pattern, date);
    }

    public Temporality of(long date) throws FormatNotAcceptable {
        return Temporality.of(pattern, date);
    }

    public Temporality of(Calendar date) {
        return Temporality.of(pattern, date);
    }

    public Temporality of(Date date) {
        return Temporality.of(pattern, date);
    }

    public Temporality of(LocalDate date) {
        return Temporality.of(pattern, date);
    }

    public Temporality of(LocalDateTime date) {
        return Temporality.of(pattern, date);
    }

    public Temporality of(String date, ZoneId zoneId) throws FormatNotAcceptable {
        return Temporality.of(pattern, date, zoneId);
    }

    public Temporality of(long date, ZoneId zoneId) throws FormatNotAcceptable {
        return Temporality.of(pattern, date, zoneId);
    }

    public Temporality of(Calendar date, ZoneId zoneId) {
        return Temporality.of(pattern, date, zoneId);
    }

    public Temporality of(Date date, ZoneId zoneId) {
        return Temporality.of(pattern, date, zoneId);
    }

    public Temporality of(LocalDate date, ZoneId zoneId) {
        return Temporality.of(pattern, date, zoneId);
    }

    public Temporality of(LocalDateTime date, ZoneId zoneId) {
        return Temporality.of(pattern, date, zoneId);
    }

    public LocalDateTime ldt(String date) {
        return TemporalityParser.toLocalDateTime(date, pattern);
    }

    public LocalDate ld(String date) {
        return TemporalityParser.toLocalDate(date, pattern);
    }

    public Date dt(String date) {
        return TemporalityParser.toDate(date, pattern);
    }

    public Calendar cl(String date) {
        return TemporalityParser.toCalendar(date, pattern);
    }
}