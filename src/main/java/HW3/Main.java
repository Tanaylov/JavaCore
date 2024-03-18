package HW3;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

        EmployeeGenerator.generate(EmployeeStatus.WORKER, 5);
        EmployeeGenerator.generate(EmployeeStatus.MANAGER, 4);
        Employee.printEmployeeList();
        System.out.println("-".repeat(200));
//        Comparator nameComparator = new NameComparator();
//        Employee.getEmployeeList().sort(nameComparator.reversed());
//        Employee.printEmployeeList();
//        Employee.getAverageAge();
//        Employee.getAverageSalary();
//        Manager m1 = (Manager) Employee.getEmployeeList().get(Employee.getEmployeeNumber() - 1);
//        m1.changeSalary(10000);
//        Employee.printEmployeeList();
//        System.out.println("-".repeat(200));
//        m1.changeSalary((Worker) Employee.getEmployeeList().get(0), 1000);
//        Employee.printEmployeeList();
//
//        System.out.println("-".repeat(200));
//        m1.changeSalary(2, 1000);
//        Employee.printEmployeeList();
        Worker.getWorkers().forEach(System.out::println);
        System.out.println("-".repeat(200));
        Manager.getManagers().forEach(System.out::println);
        Manager.getManagers().forEach(el -> el.addSubordinates((short) 2));
        System.out.println("-".repeat(200));
        for (Manager manager : Manager.getManagers()) {
            if (manager.getSubordinates().isEmpty()) break;
            System.out.println(Arrays.toString(manager.getFullName()) + ": ");
            manager.getSubordinates().forEach(System.out::println);
            System.out.println("-".repeat(70));
        }

        Manager manager1 = Manager.getManagers().get(0);
        Worker worker1 = manager1.getSubordinates().get(0);
        manager1.addTask(new Task("Calculate"));
        worker1.takeTask(worker1.getManager().getTask(0));
//        manager1.setTask(manager1.getTask(0), worker1);

        System.out.println(worker1 + "\n" + worker1.getTask() + "\n" + worker1.getManager());
    }
}
