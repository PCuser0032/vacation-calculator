package ru.user0032.calculator.helpers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Stream;

public class ChronologyHelper {

    public static long countWorkdaysBetweenDates(LocalDate vacationStartDate, LocalDate vacationEndDate, List<String> holidayList) {

        long workdays = ChronoUnit.DAYS.between(vacationStartDate, vacationEndDate.plusDays(1));

        long weekends = 0L;

        while (!vacationStartDate.isAfter(vacationEndDate)) {

            DayOfWeek day = vacationStartDate.getDayOfWeek();

            if (day.equals(DayOfWeek.SATURDAY) || day.equals(DayOfWeek.SUNDAY)) {

                weekends++;
            }
            vacationStartDate = vacationStartDate.plusDays(1);
        }

        LocalDate finalStart = vacationStartDate;

        Stream<String> streamString = holidayList.stream();
        Stream<LocalDate> streamLocalDate = streamString.map(LocalDate::parse);
        Stream<LocalDate> streamLocalDateFiltered = streamLocalDate.filter(date -> !date.isBefore(finalStart.minusDays(1)) && !date.isAfter(vacationEndDate));

        long holidaysCount = streamLocalDateFiltered.count();

        return workdays - weekends - holidaysCount;
    }
}