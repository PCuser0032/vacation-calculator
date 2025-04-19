package ru.user0032.calculator.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.user0032.calculator.services.CalculatorServiceImpl;

import java.time.LocalDate;

@RestController
public class CalculatorController {

    private final CalculatorServiceImpl calculatorService;

    public CalculatorController(CalculatorServiceImpl calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/calculate")
    public ResponseEntity<String> calculate(
            @RequestParam("averageSalary") double averageSalary,
            @RequestParam(value = "vacationDaysCount", required = false) Integer vacationDaysCount,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate vacationStartDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate vacationEndDate
    ) throws Exception {
        if (vacationStartDate != null && vacationEndDate != null && vacationStartDate.isBefore(vacationEndDate)) {

            double calculatedValue = calculatorService.calculateWithCalendar(averageSalary, vacationStartDate, vacationEndDate);

            String calculatedValueToString = Double.toString(calculatedValue);

            return new ResponseEntity<>(calculatedValueToString, HttpStatus.OK);
        } else if (vacationDaysCount != null && vacationDaysCount > 0) {

            double calculatedValue = calculatorService.calculateSimple(averageSalary, vacationDaysCount);

            String calculatedValueToString = Double.toString(calculatedValue);

            return new ResponseEntity<>(calculatedValueToString, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}