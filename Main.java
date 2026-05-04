import java.io.*;
import java.util.*;

public class EmployeeAnalyzer {

    Map<Integer, Employee> employeeMap = new HashMap<>();

    public void loadData(String filePath) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line = br.readLine(); // skip header

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");

            int id = Integer.parseInt(parts[0]);
            String firstName = parts[1];
            String lastName = parts[2];
            double salary = Double.parseDouble(parts[3]);
            Integer managerId = parts.length > 4 && !parts[4].isEmpty()
                    ? Integer.parseInt(parts[4])
                    : null;

            employeeMap.put(id, new Employee(id, firstName, lastName, salary, managerId));
        }
        br.close();

        buildHierarchy();
    }

    private void buildHierarchy() {
        for (Employee emp : employeeMap.values()) {
            if (emp.managerId != null) {
                Employee manager = employeeMap.get(emp.managerId);
                emp.manager = manager;
                manager.subordinates.add(emp);
            }
        }
    }

    // 🔹 Salary Validation
    public void analyzeSalaries() {
        for (Employee manager : employeeMap.values()) {
            if (manager.subordinates.isEmpty()) continue;

            double avg = manager.subordinates.stream()
                    .mapToDouble(e -> e.salary)
                    .average()
                    .orElse(0);

            double min = avg * 1.2;
            double max = avg * 1.5;

            if (manager.salary < min) {
                System.out.println(manager.getFullName() +
                        " earns LESS than expected by " +
                        (min - manager.salary));
            } else if (manager.salary > max) {
                System.out.println(manager.getFullName() +
                        " earns MORE than expected by " +
                        (manager.salary - max));
            }
        }
    }

    // 🔹 Reporting Line Validation
    public void analyzeReportingLines() {
        for (Employee emp : employeeMap.values()) {
            int depth = 0;
            Employee current = emp.manager;

            while (current != null) {
                depth++;
                current = current.manager;
            }

            if (depth > 4) {
                System.out.println(emp.getFullName() +
                        " has too long reporting line by " +
                        (depth - 4));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        EmployeeAnalyzer analyzer = new EmployeeAnalyzer();

        analyzer.loadData("employees.csv");

        System.out.println("---- Salary Issues ----");
        analyzer.analyzeSalaries();

        System.out.println("\n---- Reporting Line Issues ----");
        analyzer.analyzeReportingLines();
    }
}
