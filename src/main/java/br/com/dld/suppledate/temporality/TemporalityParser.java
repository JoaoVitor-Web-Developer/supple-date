package br.com.dld.suppledate.temporality;

import br.com.dld.suppledate.Chronos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;

public class TemporalityParser {

    private TemporalityParser() {
        throw new IllegalStateException("Utility class");
    }

    //region To LocalDateTime

    /**
     * Converte uma string de data em um objeto LocalDateTime utilizando o padrão de formato de data fornecido pelo objeto Chronos.
     *
     * @param date    a string de data a ser convertida.
     * @param chronos o objeto Chronos contendo o padrão de formato de data desejado.
     * @return um objeto LocalDateTime representando a data e hora correspondentes à string de data fornecida e ao padrão de formato especificado.
     */
    public static LocalDateTime toLocalDateTime(String date, Chronos chronos) {
        return toLocalDateTime(date, chronos.getPattern());
    }

    /**
     * Converte uma string de data em um objeto LocalDateTime utilizando o padrão de formato fornecido.
     *
     * @param date    a string de data a ser convertida.
     * @param pattern o padrão de formato de data desejado.
     * @return um objeto LocalDateTime representando a data e hora correspondentes à string de data fornecida e ao padrão de formato especificado.
     */
    public static LocalDateTime toLocalDateTime(String date, String pattern) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern(pattern)
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .toFormatter();

        return LocalDateTime.parse(date, formatter);
    }

    /**
     * Converte um valor numérico de data em um objeto LocalDateTime utilizando o padrão de formato de data correspondente.
     *
     * @param date    o valor numérico de data a ser convertido.
     * @param chronos o objeto Chronos contendo o padrão de formato de data desejado.
     * @return um objeto LocalDateTime representando a data e hora correspondentes ao valor numérico de data fornecido e ao padrão de formato correspondente.
     */
    public static LocalDateTime toLocalDateTime(long date, Chronos chronos) {
        String sDate = String.valueOf(date);
        return toLocalDateTime(sDate, chronos.getPattern());
    }

    /**
     * Converte um valor numérico de data em um objeto LocalDateTime utilizando o padrão de formato de data correspondente.
     *
     * @param date    o valor numérico de data a ser convertido.
     * @param pattern o padrão de formato de data desejado.
     * @return um objeto LocalDateTime representando a data e hora correspondentes ao valor numérico de data fornecido e ao padrão de formato correspondente.
     */
    public static LocalDateTime toLocalDateTime(long date, String pattern) {
        String sDate = String.valueOf(date);
        return toLocalDateTime(sDate, pattern);
    }

    /**
     * Converte um objeto Calendar em um objeto LocalDateTime utilizando a zona de fuso horário padrão do sistema.
     *
     * @param date o objeto Calendar a ser convertido.
     * @return um objeto LocalDateTime representando a data e hora correspondentes ao objeto Calendar fornecido.
     */
    public static LocalDateTime toLocalDateTime(Calendar date) {
        return toLocalDateTime(date, ZoneId.systemDefault());
    }

    /**
     * Converte um objeto Calendar em um objeto LocalDateTime utilizando a zona de fuso horário especificada.
     *
     * @param date   o objeto Calendar a ser convertido.
     * @param zoneId a zona de fuso horário desejada.
     * @return um objeto LocalDateTime representando a data e hora correspondentes ao objeto Calendar fornecido e à zona de fuso horário especificada.
     */
    public static LocalDateTime toLocalDateTime(Calendar date, ZoneId zoneId) {
        return LocalDateTime.ofInstant(date.toInstant(), zoneId);
    }

    /**
     * Converte um objeto Date em um objeto LocalDateTime utilizando a zona de fuso horário padrão do sistema.
     *
     * @param date o objeto Date a ser convertido.
     * @return um objeto LocalDateTime representando a data e hora correspondentes ao objeto Date fornecido.
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return toLocalDateTime(date, ZoneId.systemDefault());
    }

    /**
     * Converte um objeto Date em um objeto LocalDateTime utilizando a zona de fuso horário especificada.
     *
     * @param date   o objeto Date a ser convertido.
     * @param zoneId a zona de fuso horário desejada.
     * @return um objeto LocalDateTime representando a data e hora correspondentes ao objeto Date fornecido e à zona de fuso horário especificada.
     */
    public static LocalDateTime toLocalDateTime(Date date, ZoneId zoneId) {
        return LocalDateTime.ofInstant(date.toInstant(), zoneId);
    }

    /**
     * Converte um objeto LocalDate em um objeto LocalDateTime representando o início do dia.
     *
     * @param date o objeto LocalDate a ser convertido.
     * @return um objeto LocalDateTime representando o início do dia correspondente ao objeto LocalDate fornecido.
     */
    public static LocalDateTime toLocalDateTime(LocalDate date) {
        return date.atStartOfDay();
    }
    //endregion

    //region To LocalDate

    /**
     * Converte uma data no formato de string fornecido e um objeto Chronos em um objeto LocalDate.
     *
     * @param date    a data no formato de string a ser convertida.
     * @param chronos o objeto Chronos contendo o padrão de formatação da data.
     * @return um objeto LocalDate representando a data correspondente à string fornecida e ao padrão de formatação do objeto Chronos.
     */
    public static LocalDate toLocalDate(String date, Chronos chronos) {
        return toLocalDate(date, chronos.getPattern());
    }

    /**
     * Converte uma data no formato de string fornecido e um padrão de formatação em um objeto LocalDate.
     *
     * @param date    a data no formato de string a ser convertida.
     * @param pattern o padrão de formatação da data.
     * @return um objeto LocalDate representando a data correspondente à string fornecida e ao padrão de formatação.
     */
    public static LocalDate toLocalDate(String date, String pattern) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern(pattern)
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .toFormatter();

        return LocalDate.parse(date, formatter);
    }

    /**
     * Converte um valor long em um objeto LocalDate.
     *
     * @param date o valor long a ser convertido.
     * @return um objeto LocalDate representando a data correspondente ao valor long fornecido.
     */
    public static LocalDate toLocalDate(long date, String pattern) {
        return toLocalDate(String.valueOf(date), pattern);
    }

    /**
     * Converte um objeto Calendar em um objeto LocalDate utilizando a ZoneId padrão do sistema.
     *
     * @param date o objeto Calendar a ser convertido.
     * @return um objeto LocalDate representando a data correspondente ao objeto Calendar fornecido, utilizando a ZoneId padrão do sistema.
     */
    public static LocalDate toLocalDate(Calendar date) {
        return toLocalDate(date, ZoneId.systemDefault());
    }

    /**
     * Converte um objeto Calendar em um objeto LocalDate utilizando a ZoneId fornecida.
     *
     * @param date   o objeto Calendar a ser convertido.
     * @param zoneId a ZoneId representando o fuso horário desejado para a conversão.
     * @return um objeto LocalDate representando a data correspondente ao objeto Calendar fornecido, utilizando a ZoneId fornecida.
     */
    public static LocalDate toLocalDate(Calendar date, ZoneId zoneId) {
        return date.toInstant().atZone(zoneId).toLocalDate();
    }

    /**
     * Converte um objeto Date em um objeto LocalDate utilizando a ZoneId padrão do sistema.
     *
     * @param date o objeto Date a ser convertido.
     * @return um objeto LocalDate representando a parte da data correspondente ao objeto Date fornecido, utilizando a ZoneId padrão do sistema.
     */
    public static LocalDate toLocalDate(Date date) {
        return toLocalDate(date, ZoneId.systemDefault());
    }

    /**
     * Converte um objeto Date em um objeto LocalDate utilizando a ZoneId fornecida.
     *
     * @param date   o objeto Date a ser convertido.
     * @param zoneId a ZoneId representando o fuso horário desejado para a conversão.
     * @return um objeto LocalDate representando a data correspondente ao objeto Date fornecido, utilizando a ZoneId fornecida.
     */
    public static LocalDate toLocalDate(Date date, ZoneId zoneId) {
        return date.toInstant().atZone(zoneId).toLocalDate();
    }

    /**
     * Converte um objeto LocalDateTime em um objeto LocalDate.
     *
     * @param date o objeto LocalDateTime a ser convertido.
     * @return um objeto LocalDate representando a parte da data correspondente ao objeto LocalDateTime fornecido.
     */
    public static LocalDate toLocalDate(LocalDateTime date) {
        return date.toLocalDate();
    }
    //endregion

    //region To Calendar

    /**
     * Converte uma data no formato de string fornecido e um objeto Chronos em um objeto Calendar.
     *
     * @param date    a data no formato de string a ser convertida.
     * @param chronos o objeto Chronos contendo o padrão de formatação da data.
     * @return um objeto Calendar representando a data e hora correspondentes à string fornecida e ao padrão de formatação do objeto Chronos.
     * @throws DateTimeParseException se a string da data não puder ser analisada usando o padrão fornecido pelo objeto Chronos.
     */
    public static Calendar toCalendar(String date, Chronos chronos) {
        return toCalendar(date, chronos.getPattern());
    }

    /**
     * Converte uma data no formato de string fornecido e um padrão de formatação em um objeto Calendar.
     *
     * @param date    a data no formato de string a ser convertida.
     * @param pattern o padrão de formatação da data.
     * @return um objeto Calendar representando a data e hora correspondentes à string fornecida e ao padrão de formatação.
     * @throws DateTimeParseException se a string da data não puder ser analisada usando o padrão fornecido.
     */
    public static Calendar toCalendar(String date, String pattern) {
        LocalDateTime localDate = toLocalDateTime(date, pattern);
        return toCalendar(localDate);
    }

    /**
     * Converte um valor long em um objeto Calendar.
     *
     * @param date o valor long a ser convertido.
     * @return um objeto Calendar representando a data e hora correspondentes ao valor long fornecido.
     */
    public static Calendar toCalendar(long date, String pattern) {
        return toCalendar(String.valueOf(date), pattern);
    }

    /**
     * Converte um objeto Date em um objeto Calendar.
     *
     * @param date o objeto Date a ser convertido.
     * @return um objeto Calendar representando a mesma data e hora que o objeto Date fornecido.
     */
    public static Calendar toCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar;
    }

    /**
     * Converte um objeto LocalDateTime em um objeto Calendar.
     *
     * @param date o objeto LocalDateTime a ser convertido.
     * @return um objeto Calendar representando a mesma data e hora que o objeto LocalDateTime fornecido.
     */
    public static Calendar toCalendar(LocalDateTime date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth(), date.getHour(), date.getMinute(), date.getSecond());

        return calendar;
    }

    /**
     * Converte um objeto LocalDate em um objeto Calendar.
     *
     * @param date o objeto LocalDate a ser convertido.
     * @return um objeto Calendar representando a mesma data que o objeto LocalDate fornecido.
     */
    public static Calendar toCalendar(LocalDate date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());

        return calendar;
    }
    //endregion

    //region To Date

    /**
     * Converte uma data no formato de string fornecido, um objeto Chronos e usa a ZoneId padrão do sistema em um objeto Date.
     *
     * @param date    a data no formato de string a ser convertida.
     * @param chronos o objeto Chronos contendo o padrão de formatação da data.
     * @return um objeto Date representando a data e hora correspondentes à string fornecida, ao padrão de formatação do objeto Chronos e à ZoneId padrão do sistema.
     * @throws DateTimeParseException se a string da data não puder ser analisada usando o padrão fornecido pelo objeto Chronos.
     */
    public static Date toDate(String date, Chronos chronos) {
        return toDate(date, chronos, ZoneId.systemDefault());
    }

    /**
     * Converte uma data no formato de string fornecido, um objeto Chronos e uma ZoneId em um objeto Date.
     *
     * @param date    a data no formato de string a ser convertida.
     * @param chronos o objeto Chronos contendo o padrão de formatação da data.
     * @param zoneId  a ZoneId representando o fuso horário desejado para a conversão.
     * @return um objeto Date representando a data e hora correspondentes à string fornecida, ao padrão de formatação do objeto Chronos e à ZoneId fornecida.
     * @throws DateTimeParseException se a string da data não puder ser analisada usando o padrão fornecido pelo objeto Chronos.
     */
    public static Date toDate(String date, Chronos chronos, ZoneId zoneId) {
        return toDate(date, chronos.getPattern(), zoneId);
    }

    /**
     * Converte uma data no formato de string fornecido, um padrão de formatação e usa a ZoneId padrão do sistema em um objeto Date.
     *
     * @param date    a data no formato de string a ser convertida.
     * @param pattern o padrão de formatação da data.
     * @return um objeto Date representando a data e hora correspondentes à string fornecida e ao padrão de formatação, utilizando a ZoneId padrão do sistema.
     * @throws DateTimeParseException se a string da data não puder ser analisada usando o padrão fornecido.
     */
    public static Date toDate(String date, String pattern) {
        return toDate(date, pattern, ZoneId.systemDefault());
    }

    /**
     * Converte uma data no formato de string fornecido, um padrão de formatação e uma ZoneId em um objeto Date.
     *
     * @param date    a data no formato de string a ser convertida.
     * @param pattern o padrão de formatação da data.
     * @param zoneId  a ZoneId representando o fuso horário desejado para a conversão.
     * @return um objeto Date representando a data e hora correspondentes à string fornecida, ao padrão de formatação e à ZoneId fornecida.
     * @throws DateTimeParseException se a string da data não puder ser analisada usando o padrão fornecido.
     */
    public static Date toDate(String date, String pattern, ZoneId zoneId) {
        LocalDateTime localDate = toLocalDateTime(date, pattern);
        return toDate(localDate, zoneId);
    }

    /**
     * Converte um valor long em um objeto Date utilizando a ZoneId padrão do sistema.
     *
     * @param date o valor long a ser convertido.
     * @return um objeto Date representando a data e hora correspondentes ao valor long fornecido, utilizando a ZoneId padrão do sistema.
     */
    public static Date toDate(long date, String pattern) {
        return toDate(date, pattern, ZoneId.systemDefault());
    }

    /**
     * Converte um valor long em um objeto Date utilizando a ZoneId fornecida.
     *
     * @param date   o valor long a ser convertido.
     * @param zoneId a ZoneId representando o fuso horário desejado para a conversão.
     * @return um objeto Date representando a data e hora correspondentes ao valor long fornecido, utilizando a ZoneId fornecida.
     */
    public static Date toDate(long date, String pattern, ZoneId zoneId) {
        return toDate(String.valueOf(date), pattern, zoneId);
    }

    /**
     * Converte um objeto Calendar em um objeto Date.
     *
     * @param date o objeto Calendar a ser convertido.
     * @return um objeto Date representando a data e hora correspondentes ao objeto Calendar fornecido.
     */
    public static Date toDate(Calendar date) {
        return Date.from(date.toInstant());
    }

    /**
     * Converte um objeto LocalDateTime em um objeto Date utilizando a ZoneId padrão do sistema.
     *
     * @param date o objeto LocalDateTime a ser convertido.
     * @return um objeto Date representando a data e hora correspondentes ao objeto LocalDateTime fornecido, utilizando a ZoneId padrão do sistema.
     */
    public static Date toDate(LocalDateTime date) {
        return toDate(date, ZoneId.systemDefault());
    }

    /**
     * Converte um objeto LocalDateTime em um objeto Date utilizando a ZoneId fornecida.
     *
     * @param date   o objeto LocalDateTime a ser convertido.
     * @param zoneId a ZoneId representando o fuso horário desejado para a conversão.
     * @return um objeto Date representando a data e hora correspondentes ao objeto LocalDateTime fornecido, utilizando a ZoneId fornecida.
     */
    public static Date toDate(LocalDateTime date, ZoneId zoneId) {
        return Date.from(date.atZone(zoneId).toInstant());
    }

    /**
     * Converte um objeto LocalDate em um objeto Date utilizando a ZoneId padrão do sistema.
     *
     * @param date o objeto LocalDate a ser convertido.
     * @return um objeto Date representando a data correspondente ao objeto LocalDate fornecido, utilizando a ZoneId padrão do sistema.
     */
    public static Date toDate(LocalDate date) {
        return toDate(date, ZoneId.systemDefault());
    }

    /**
     * Converte um objeto LocalDate em um objeto Date utilizando a ZoneId fornecida.
     *
     * @param date   o objeto LocalDate a ser convertido.
     * @param zoneId a ZoneId representando o fuso horário desejado para a conversão.
     * @return um objeto Date representando a data correspondente ao objeto LocalDate fornecido, utilizando a ZoneId fornecida.
     */
    public static Date toDate(LocalDate date, ZoneId zoneId) {
        return Date.from(date.atStartOfDay(zoneId).toInstant());
    }
    //endregion
}
