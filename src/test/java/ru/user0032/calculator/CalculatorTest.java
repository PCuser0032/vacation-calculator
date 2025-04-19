package ru.user0032.calculator;

import org.junit.jupiter.api.Test;
import ru.user0032.calculator.services.CalculatorServiceImpl;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    private final CalculatorServiceImpl calculatorService = new CalculatorServiceImpl();

    @Test
    void testCalculateSimple() {
        assertEquals(23890.78, calculatorService.calculateSimple(50_000.00, 14), 0.01);
    }

    @Test
    void testCalculateWithCalendar() throws Exception {
        double result = calculatorService.calculateWithCalendar(50_000.00, LocalDate.parse("2025-06-10"), LocalDate.parse("2025-06-26"));
        assertEquals(22184.3, result, 0.01); // пример значения для тестирования
    }
}