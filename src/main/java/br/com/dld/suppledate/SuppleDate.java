package br.com.dld.suppledate;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;

/**
 * @author David Duarte
 * @created 16/08/2022
 * @project supple-date
 */
public class SuppleDate {

    private LocalDateTime date;

    public SuppleDate() {
        this.date = LocalDateTime.now();
    }

    public SuppleDate(@NotNull Date date) {
        this.date = LocalDateTime.ofInstant(
                date.toInstant(),
                ZoneId.systemDefault()
        );
    }

    public SuppleDate(@NotNull Calendar date) {
        this.date = LocalDateTime.ofInstant(
                date.toInstant(),
                ZoneId.systemDefault()
        );
    }

    public SuppleDate(@NotNull LocalDate date) {
        this.date = date.atStartOfDay();
    }

    public SuppleDate(@NotNull LocalDateTime date) {
        this.date = date;
    }

    public SuppleDate(@NotNull String date, @NotNull Shape shape) {
        this(date, shape.getPattern());
    }

    public SuppleDate(@NotNull String date, @NotNull String pattern) {

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern(pattern)
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .toFormatter();

        this.date = LocalDateTime.parse(
                date,
                formatter
        );
    }

    public SuppleDate(@NotNull Long date) {
        String sdate = date.toString().trim();
        DateTimeFormatter formatter;

        if (!sdate.isEmpty() && !sdate.equals("0")) {
            switch (sdate.length()) {
                case 8:
                    formatter = DateTimeFormatter.ofPattern(
                            Shape.BASIC_D4_ISO.getPattern());
                    this.date = LocalDate.parse(sdate, formatter).atStartOfDay();
                    break;
                case 6:
                    formatter = DateTimeFormatter.ofPattern(
                            Shape.BASIC_D2_ISO.getPattern());
                    this.date = LocalDate.parse(sdate, formatter).atStartOfDay();
                    break;
                default:
                    formatter = DateTimeFormatter.ofPattern(
                            Shape.BASIC_D2HMS_ISO.getPattern());
                    this.date = LocalDateTime.parse(sdate, formatter);
                    break;
            }
        }
    }

    public static @NotNull SuppleDate now() {
        return new SuppleDate();
    }

    public static @NotNull SuppleDate of(@NotNull Date date) {
        return new SuppleDate(date);
    }

    public static @NotNull SuppleDate of(@NotNull Calendar date) {
        return new SuppleDate(date);
    }

    public static @NotNull SuppleDate of(@NotNull LocalDate date) {
        return new SuppleDate(date);
    }

    public static @NotNull SuppleDate of(@NotNull LocalDateTime date) {
        return new SuppleDate(date);
    }

    public static @NotNull SuppleDate of(@NotNull String date, @NotNull Shape shape) {
        return new SuppleDate(date, shape);
    }

    public static @NotNull SuppleDate of(@NotNull String date, @NotNull String pattern) {
        return new SuppleDate(date, pattern);
    }

    public static @NotNull SuppleDate of(@NotNull Long date) {
        return new SuppleDate(date);
    }

    public @NotNull DateHandler handler() {
        return new DateHandler(this);
    }

    public @NotNull DateFormatter format() {
        return new DateFormatter(this);
    }

    public @NotNull DateFormatter format(Shape shape) {
        return new DateFormatter(this, shape);
    }

    public @NotNull DateFormatter format(String pattern) {
        return new DateFormatter(this, pattern);
    }

    public LocalDateTime get() {
        return date;
    }
}
