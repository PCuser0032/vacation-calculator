package ru.user0032.calculator.services;

import java.time.LocalDate;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import ru.user0032.calculator.helpers.ChronologyHelper;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    static final double DAYS_IN_MONTH = 29.3;

    @Override
    public double calculateSimple(double averageSalary, int vacationDaysCount) {

        return Math.round((averageSalary / DAYS_IN_MONTH) * vacationDaysCount * 100.0) / 100.0;
    }

    @Override
    public double calculateWithCalendar(double averageSalary, LocalDate vacationStartDate, LocalDate vacationEndDate) throws IOException {

        List<String> holidays = loadHolidays();

        long totalDays = ChronologyHelper.countWorkdaysBetweenDates(vacationStartDate, vacationEndDate, holidays);

        return Math.round((averageSalary / DAYS_IN_MONTH) * totalDays * 100.0) / 100.0;
    }

    private List<String> loadHolidays() throws IOException {

        return Files.readAllLines(Paths.get("holidays.txt"));
    }
}