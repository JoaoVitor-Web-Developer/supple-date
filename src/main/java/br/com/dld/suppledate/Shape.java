package br.com.dld.suppledate;

import lombok.Getter;

/**
 * @author David Duarte
 * @created 15/08/2022
 * @project supple-date
 */
@Getter
public enum Shape {

    BASIC_D4("ddMMyyyy", Type.BASIC),
    BASIC_D4HMS("ddMMyyyyHHmmss", Type.BASIC),
    BASIC_D2("ddMMyy", Type.BASIC),
    BASIC_D2HMS("ddMMyyHHmmss", Type.BASIC),
    BASIC_D4_ISO("yyyyMMdd", Type.BASIC),
    BASIC_D4HMS_ISO("yyyyMMddHHmmss", Type.BASIC),
    BASIC_D2_ISO("yyMMdd", Type.BASIC),
    BASIC_D2HMS_ISO("yyMMddHHmmss", Type.BASIC),
    BASIC_HM("HHmm", Type.BASIC),

    BAR_D4("dd/MM/yyyy", Type.BAR),
    BAR_D4HM("dd/MM/yyyy HH:mm", Type.BAR),
    BAR_D4HMS("dd/MM/yyyy HH:mm:ss", Type.BAR),
    BAR_D2("dd/MM/yy", Type.BAR),
    BAR_D2HM("dd/MM/yy HH:mm", Type.BAR),
    BAR_D2HMS("dd/MM/yy HH:mm:ss", Type.BAR),
    BAR_D4_ISO("yyyy/MM/dd", Type.BAR),
    BAR_D4HM_ISO("yyyy/MM/dd HH:mm", Type.BAR),
    BAR_D4HMS_ISO("yyyy/MM/dd HH:mm:ss", Type.BAR),
    BAR_D2_ISO("yy/MM/dd", Type.BAR),

    TRACE_D4("dd-MM-yyyy", Type.TRACE),
    TRACE_D4HM("dd-MM-yyyy HH:mm", Type.TRACE),
    TRACE_D4HMS("dd-MM-yyyy HH:mm:ss", Type.TRACE),
    TRACE_D2("dd-MM-yy", Type.TRACE),
    TRACE_D2HM("dd-MM-yy HH:mm", Type.TRACE),
    TRACE_D2HMS("dd-MM-yy HH:mm:ss", Type.TRACE),
    TRACE_D4_ISO("yyyy-MM-dd", Type.TRACE),
    TRACE_D4HM_ISO("yyyy-MM-dd HH:mm", Type.TRACE),
    TRACE_D4HMS_ISO("yyyy-MM-dd HH:mm:ss", Type.TRACE),
    TRACE_D2_ISO("yy-MM-dd", Type.TRACE),

    FILE_D2HM("yy-MM-dd HH mm", Type.FILE),
    FILE_D4HM("yyyy-MM-dd HH mm", Type.FILE);

    private final String pattern;
    private final Type type;

    Shape(String pattern, Type type) {
        this.pattern = pattern;
        this.type = type;
    }

    public enum Type {
        BASIC,
        BAR,
        TRACE,
        FILE;
    }
}
