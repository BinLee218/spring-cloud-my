package com.company.mybatis.commons.utils;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/**
 * @author bin.li
 * @date 2020/4/23
 */
public class DateUtils {
    public static void main(String[] args) {
        System.out.println(DateUtils.getNowLocalDateTime().toString());
        System.out.println(DateUtils.date2LocalDateTimeStringBypattern(DateUtils.getNowLocalDateTime(), "yyyyMMddHHmmss"));
        System.out.println(DateUtils.date2LocalDateTimeStringBypattern(DateUtils.getNowLocalDateTime(), "MMyyyy"));
        System.out.println(DateUtils.date2Patterntring(DateUtils.getNowDate(), "dd/MM/yyyy HH:mm:ss"));
        System.out.println(DateUtils.date2Patterntring(DateUtils.getNowDate(), "ddMMyyyy"));
        System.out.println(DateUtils.date2Patterntring(DateUtils.getNowDate(), "MMyyyy"));
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        LocalDate nowLocalDate = DateUtils.date2LocalDate(DateUtils.getNowDate());
        LocalDate endLocalDate = LocalDate.of(2020, 7, 31);
        for (int i = 1; i < 12; i++) {
            System.out.println(endLocalDate.plusMonths(i));
        }
        LocalDate beforeLocalDate = LocalDate.of(2020, 10 , 16);
        LocalDate afterLocalDate = LocalDate.of(2020, 10 , 20);
        System.out.println(beforeLocalDate.isBefore(afterLocalDate));
        int i = DateUtils.betweenDay(beforeLocalDate, afterLocalDate);
        System.out.println(i);

        LocalDate localDate = DateUtils.date2LocalDate(DateUtils.getNowDate());
        LocalDate localDate1 = localDate.minusMonths(1);
        String localDateString = DateUtils.date2LocalDateStringBypattern(localDate1, "yyyy-MM");
        System.out.println(localDateString);


        LocalDate yearMonth = DateUtils.string2YearMonthPattern(localDateString);
        LocalDate startAtMonthDay = yearMonth.withDayOfMonth(1);
        LocalDate endAtMonthDay = startAtMonthDay.plusMonths(1);
        Date startDate = DateUtils.localDateTime2Date(startAtMonthDay.atStartOfDay());
        Date endDate = DateUtils.localDateTime2Date(endAtMonthDay.atStartOfDay());
        System.out.println(startDate);
        System.out.println(endDate);
        System.out.println(DateUtils.date2LocalDateTimeString(startDate));
        System.out.println(DateUtils.date2LocalDateTimeString(endDate));

        System.out.println("------------------------------------------------------------------------------------------------------------------");

        LocalDateTime nowLocalDateTime = DateUtils.getNowLocalDateTime();
        ZonedDateTime zdt = nowLocalDateTime.atZone(ZoneId.systemDefault());
        long l = nowLocalDateTime.toInstant(zdt.getOffset()).toEpochMilli();
        System.out.println(l);
        System.out.println(new Date().getTime());
        System.out.println(new Date(l));

        System.out.println(DateUtils.getNowDate().getTime());
    }

    private static Long offset;
    private static LocalDateTime localDateTime = LocalDateTime.now();

    public static Date getNowDate() {
        localDateTime = LocalDateTime.now();
        if (offset != null) {
            localDateTime = localDateTime.plusSeconds(offset);
        }
        return localDateTime2Date(localDateTime);
    }

    public static LocalDateTime getNowLocalDateTime() {
        localDateTime = LocalDateTime.now();
        if (offset != null) {
            localDateTime = localDateTime.plusSeconds(offset);
        }
        return localDateTime;
    }

    public static void resetTime(){
        offset = null;
    }

    public static void setOffset(String dateTime) {
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime zdt = now.atZone(ZoneId.systemDefault());
        LocalDateTime throughLocalDateTime = string2LocalDateTime(dateTime);
        long l = throughLocalDateTime.toEpochSecond(zdt.getOffset());
        long l1 = now.toEpochSecond(zdt.getOffset());
        offset = l - l1;
    }

    public static LocalDateTime date2LocalDateTime(Date date){
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    public static String date2LocalDateTimeString(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTimeFormatter.format(localDateTime);
    }



    public static String date2LocalDateString(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dateTimeFormatter.format(localDate);
    }

    public static LocalDate date2LocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        return localDate;
    }

    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        Date date = Date.from(zdt.toInstant());
        return date;
    }

    public static Date localDate2Date(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    public static LocalDateTime string2LocalDateTime(String time) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(time, dateTimeFormatter);
    }


    public static LocalDate string2LocalDateByPattern(String time, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(time, dateTimeFormatter);
    }

    public static String date2LocalDateTimeStringBypattern(LocalDateTime nowLocalDateTime, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTimeFormatter.format(nowLocalDateTime);
    }

    public static String date2LocalDateStringBypattern(LocalDate time, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTimeFormatter.format(time);
    }

    public static String date2Patterntring(Date date, String pattern) {
        if(Objects.nonNull(date)) {
            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
            return dateTimeFormatter.format(localDateTime);
        }
        return null;
    }

    public static boolean localDateCompare(LocalDate localDate1, LocalDate localDate2) {
        return localDate1.isAfter(localDate2);
    }

    public static LocalDate string2YearMonthPattern(String year_month){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM");
        YearMonth ym = YearMonth.parse(year_month, fmt);
        return ym.atEndOfMonth();
    }

    /**
     * 两个时间对比，返回相减的秒
     *
     * @param before
     * @param after
     * @return
     */
    public static long between(LocalDateTime before, LocalDateTime after) {
        Duration d = Duration.between(before, after);
        return d.getSeconds();
    }

    /**
     * 两个时间对比，返回相减的年
     *
     * @param before
     * @param after
     * @return
     */
    public static int betweenPeriod(LocalDate before, LocalDate after) {
        Period period = Period.between(before, after);
        return period.getYears();
    }

    /**
     * 两个时间对比，返回相减的天
     * Period.between 有坑，只能计算天的差，会忽略月份，
     * @param before
     * @param after
     * @return
     */
    public static int betweenDay(LocalDate before, LocalDate after) {
//        Period period = Period.between(before, after);
        return Long.valueOf(after.toEpochDay() - before.toEpochDay()).intValue();
    }

    public static Date datePlusMinute(Date date, int minute){
        LocalDateTime localDateTime = date2LocalDateTime(date);
        LocalDateTime localDateTime1 = localDateTime.plusMinutes(minute);
        return localDateTime2Date(localDateTime1);
    }

}
