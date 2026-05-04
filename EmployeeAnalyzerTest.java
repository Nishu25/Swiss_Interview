package com.company;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeAnalyzerTest {

    @Test
    void testSalaryBelowRange() throws Exception {
        EmployeeAnalyzer analyzer = new EmployeeAnalyzer();
        analyzer.loadData("employees.csv");

        // Just ensure method runs without crash
        assertDoesNotThrow(analyzer::analyzeSalaries);
    }

    @Test
    void testReportingLine() throws Exception {
        EmployeeAnalyzer analyzer = new EmployeeAnalyzer();
        analyzer.loadData("employees.csv");

        assertDoesNotThrow(analyzer::analyzeReportingLines);
    }

    @Test
    void testHierarchyBuild() throws Exception {
        EmployeeAnalyzer analyzer = new EmployeeAnalyzer();
        analyzer.loadData("employees.csv");

        assertFalse(analyzer.employeeMap.isEmpty());
    }
}
