package ru.user0032.calculator.services;

import java.io.IOException;
import java.time.LocalDate;

public interface CalculatorService {
    double calculateSimple(double averageSalary, int vacationDaysCount);
    double calculateWithCalendar(double averageSalary, LocalDate vacationStartDate, LocalDate vacationEndDate) throws IOException;
}
