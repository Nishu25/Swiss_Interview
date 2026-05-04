import java.util.*;

public class Employee {
    int id;
    String firstName;
    String lastName;
    double salary;
    Integer managerId;

    Employee manager;
    List<Employee> subordinates = new ArrayList<>();

    public Employee(int id, String firstName, String lastName, double salary, Integer managerId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.managerId = managerId;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
