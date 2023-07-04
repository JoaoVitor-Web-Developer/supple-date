package br.com.dld.suppledate.temporality;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class TemporalityHandler {

    private TemporalityHandler() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Altera o valor de um campo específico em uma data e hora.
     *
     * @param dateTime  a data e hora original.
     * @param field     o campo a ser alterado.
     * @param value     o novo valor para o campo.
     * @return a data e hora resultante após alterar o valor do campo especificado.
     */
    public static LocalDateTime change(LocalDateTime dateTime, ChronoField field, long value) {
        return dateTime.with(field, value);
    }

    /**
     * Adiciona ou subtrai um valor específico a uma data e hora com base na unidade de tempo fornecida.
     *
     * @param dateTime  a data e hora inicial.
     * @param unit      a unidade de tempo na qual o valor será adicionado ou subtraído.
     * @param value     o valor a ser adicionado ou subtraído.
     * @return a data e hora resultante após adicionar ou subtrair o valor na unidade de tempo especificada.
     *
     * @example
     * LocalDateTime dateTime = LocalDateTime.of(2023, 1, 1, 12, 0, 0);<br/>
     * LocalDateTime result1 = sum(dateTime, ChronoUnit.HOURS, 3L); // Adiciona 3 horas<br/>
     * LocalDateTime result2 = sum(dateTime, ChronoUnit.DAYS, -1L); // Subtrai 1 dia
     */
    public static LocalDateTime sum(LocalDateTime dateTime, ChronoUnit unit, long value) {
        return dateTime.plus(value, unit);
    }

    /**
     * Calcula a diferença entre duas datas e horas com base na unidade de tempo fornecida.
     *
     * @param dateTime  a data e hora inicial.
     * @param unit      a unidade de tempo na qual a diferença será calculada.
     * @param lastDate  a data e hora final.
     * @return a diferença entre as datas e horas em termos da unidade de tempo fornecida.
     *
     * @throws ArithmeticException se a unidade de tempo não suportar a diferença entre as datas e horas.
     *
     * @example
     * LocalDateTime startDate = LocalDateTime.of(2023, 1, 1, 0, 0, 0);<br/>
     * LocalDateTime endDate = LocalDateTime.of(2023, 1, 10, 0, 0, 0);<br/>
     * long daysBetween = between(startDate, ChronoUnit.DAYS, endDate); // Retorna 9<br/>
     * long hoursBetween = between(startDate, ChronoUnit.HOURS, endDate); // Retorna 216
     */
    public static long between(LocalDateTime dateTime, ChronoUnit unit, LocalDateTime lastDate) {
        return dateTime.until(lastDate, unit);
    }

    /**
     * Retorna o primeiro dia do mês correspondente à data e hora especificadas.
     *
     * @param dateTime a data e hora para a qual o primeiro dia do mês será retornado.
     * @return um objeto LocalDateTime representando o primeiro dia do mês correspondente à data e hora fornecidas.
     */
    public static LocalDateTime startOfMonth(LocalDateTime dateTime) {
        return change(dateTime, ChronoField.DAY_OF_MONTH, 1L);
    }

    /**
     * Retorna o último dia do mês correspondente à data e hora especificadas.
     *
     * @param dateTime a data e hora para a qual o último dia do mês será retornado.
     * @return um objeto LocalDateTime representando o último dia do mês correspondente à data e hora fornecidas.
     */
    public static LocalDateTime endOfMonth(LocalDateTime dateTime) {
        return dateTime.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * Retorna o início do dia correspondente à data especificada.
     *
     * @param dateTime a data para a qual o início do dia será retornado.
     * @return um objeto LocalDateTime representando o início do dia (00:00:00) correspondente à data fornecidas.
     */
    public static LocalDateTime startOfDay(LocalDateTime dateTime) {
        return dateTime.truncatedTo(ChronoUnit.DAYS);
    }

    /**
     * Retorna o final do dia correspondente à data especificada.
     *
     * @param dateTime a data para a qual o final do dia será retornado.
     * @return um objeto LocalDateTime representando o final do dia (23:59:59) correspondente à data fornecidas.
     */
    public static LocalDateTime endOfDay(LocalDateTime dateTime) {
        return LocalDateTime.of(dateTime.toLocalDate(), LocalTime.MAX);
    }

    /**
     * Ajusta a data e hora especificada para evitar fins de semana.
     * Se a data e hora fornecidas caírem em um sábado, serão adicionados 2 dias.
     * Se a data e hora fornecidas caírem em um domingo, será adicionado 1 dia.
     * Caso contrário, a data e hora serão mantidas inalteradas.
     *
     * @param dateTime a data e hora a ser ajustada.
     * @return a data e hora ajustada para evitar fins de semana.
     */
    public static LocalDateTime preventWeekend(LocalDateTime dateTime) {
        if (DayOfWeek.SATURDAY.equals(dateTime.getDayOfWeek())) {
            return dateTime.plusDays(2L);
        } else if (DayOfWeek.SUNDAY.equals(dateTime.getDayOfWeek())) {
            return dateTime.plusDays(1L);
        }

        return dateTime;
    }

    /**
     * Verifica se a data e hora especificada estão dentro do intervalo definido pelas datas inicial e final.
     *
     * @param dateTime    a data e hora a ser verificada.
     * @param initialDate a data e hora inicial do intervalo.
     * @param finalDate   a data e hora final do intervalo.
     * @return true se a data e hora estiverem dentro do intervalo, caso contrário, false.
     */
    public static boolean isWithinRange(LocalDateTime dateTime, LocalDateTime initialDate, LocalDateTime finalDate) {
        return !dateTime.isBefore(initialDate) && !dateTime.isAfter(finalDate);
    }
}
