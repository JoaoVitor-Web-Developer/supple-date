package br.com.dld.suppledate.temporality;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class TemporalityFormatter {

    private TemporalityFormatter() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Converte uma data e hora em uma representação de string formatada com base no fuso horário e padrão fornecidos.
     *
     * @param dateTime a data e hora a ser convertida.
     * @param zoneId   o fuso horário para a conversão.
     * @param pattern  o padrão de formatação da string.
     * @return a representação da data e hora como uma string formatada.
     */
    public static String toString(LocalDateTime dateTime, ZoneId zoneId, String pattern) {
        return ZonedDateTime.of(dateTime, zoneId).format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Converte uma data e hora em um valor long com base no fuso horário e padrão fornecidos.
     *
     * @param dateTime a data e hora a ser convertida.
     * @param zoneId   o fuso horário para a conversão.
     * @param pattern  o padrão de formatação da string.
     * @return o valor long resultante da conversão da data e hora.
     */
    public static Long toLong(LocalDateTime dateTime, ZoneId zoneId, String pattern) {
        return Long.parseLong(toString(dateTime, zoneId, pattern).replaceAll("\\D", ""));
    }

    /**
     * Converte um objeto LocalDateTime em um objeto Date utilizando a ZoneId fornecida.
     *
     * @param dateTime o objeto LocalDateTime a ser convertido.
     * @param zoneId   a ZoneId representando o fuso horário desejado para a conversão.
     * @return um objeto Date representando a data e hora correspondentes ao objeto LocalDateTime fornecido, utilizando a ZoneId fornecida.
     */
    public static Date toDate(LocalDateTime dateTime, ZoneId zoneId) {
        return TemporalityParser.toDate(dateTime, zoneId);
    }

    /**
     * Converte um objeto LocalDateTime em um objeto Calendar com base no ZoneId fornecido.
     *
     * @param dateTime o LocalDateTime a ser convertido.
     * @param zoneId   o ZoneId para a conversão.
     * @return o objeto Calendar resultante da conversão.
     */
    public static Calendar toCalendar(LocalDateTime dateTime, ZoneId zoneId) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(toDate(dateTime, zoneId));

        return calendar;
    }

    /**
     * Converte um objeto LocalDateTime em um objeto LocalDate.
     *
     * @param dateTime o LocalDateTime a ser convertido.
     * @return o objeto LocalDate resultante da conversão.
     */
    public static LocalDate toLocalDate(LocalDateTime dateTime) {
        return dateTime.toLocalDate();
    }
}
