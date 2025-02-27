package task_f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class EmployeeSort {
    static List<String> sortRecords(List<String> employee, List<Integer> salary) {
        if (employee == null || salary == null || employee.size() != salary.size()) {
            return null;
        }
        int n = salary.size();
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, Comparator.comparingInt(salary::get));

        List<String> sortedEmployee = new ArrayList<>();

        for (Integer index : indices) {
            sortedEmployee.add(employee.get(index));
        }
        return sortedEmployee;
    }

    public static void main(String[] args) {
        List<String> employees = Arrays.asList("Alice", "Bob", "Charlie", "David");
        List<Integer> salaries = Arrays.asList(5000, 3000, 7000, 4000);

        List<String> sortedEmployees = sortRecords(employees, salaries);
        System.out.println("Sorted Employees: " + sortedEmployees);
    }

}
