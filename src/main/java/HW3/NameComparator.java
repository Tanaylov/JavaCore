package HW3;

import java.util.Comparator;

public class NameComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee employee1, Employee employee2) {
        return employee1.getFullName()[0].compareTo(employee2.getFullName()[0]);
    }
}
