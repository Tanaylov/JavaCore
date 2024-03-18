package HW3;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class Employee implements TaskManager {
    //region variables:
    private static int idCounter = 0;
    protected String id = Employee.this.getClass().getSimpleName() + "_";
    protected String[] fullName;
    protected LocalDate birthDate;
    protected long telephoneNumber;
    protected int salary;
    protected byte workExperience;
    protected Task task;

    private static ArrayList<Employee> employeeList = new ArrayList<>(100);
    //endregion variables

    //region constructors:
    public Employee(){}
    public Employee(String[] fullName) {
        this.fullName = fullName;
    }
    public Employee(String[] fullName, int salary) {
        this(fullName);
        this.salary = salary;
    }
    public Employee(String[] fullName, int salary, byte workExperience) {
        this(fullName, salary);
        this.workExperience = workExperience;
    }
    public Employee(String[] fullName, int salary, byte workExperience, LocalDate birthDate) {
        this(fullName, salary, workExperience);
        this.birthDate = birthDate;
        if (checkAge(birthDate)) {
            System.out.println("Employee too young");
            System.out.println(Employee.this + "\n");
            employeeList.remove(Employee.this);
        }
    }
    public Employee(String[] fullName, int salary, byte workExperience, LocalDate birthDate, long telephoneNumber) {
        this(fullName, salary, workExperience, birthDate);
        this.telephoneNumber = telephoneNumber;
    }
    public Employee(Employee employee) {
        this(employee.fullName, employee.salary, employee.workExperience, employee.birthDate, employee.telephoneNumber);
    }
    //endregion constructors
    {
        id += idCounter++;
        employeeList.add(Employee.this);
    }
    //region setters:
    public void setBirthDate(LocalDate birthDate) {
        if (!checkAge(birthDate)) {
            System.out.println("Employee is too young");
        } else this.birthDate = birthDate;
    }

    //endregion setters

    //region getters:
    public String[] getFullName() {
        return fullName;
    }
    public int getSalary() {
        return salary;
    }
    public static float getAverageAge() {
        float ageSum = 0f;
        for (Employee employee : employeeList) {
            ageSum += employee.getAge();
        }
        System.out.println(String.format("Average age is %.2f", ageSum / employeeList.size()));
        return ageSum / employeeList.size();
    }
    public static float getAverageSalary() {
        float salarySum = 0f;
        for (Employee employee : employeeList) {
            salarySum += employee.getSalary();
        }
        System.out.println(String.format("Average salary is %.2f", salarySum / employeeList.size()));
        return salarySum / employeeList.size();
    }
    public int getAge() {
        if (birthDate == null) return 0;
        return (int) LocalDate.from(birthDate).until(LocalDate.now(), ChronoUnit.YEARS);
    }

    public String getEmployeeStatus() {
        return this.getClass().getSimpleName();
    }
    public static List<Employee> getEmployeeList() {return employeeList;}

    public static int getEmployeeNumber() {return employeeList.size();}
    public LocalDate getBirthDate() {return birthDate;}

    //endregion getters:

    //region checking:

    private boolean checkAge(LocalDate birthDate) {
        return birthDate.isAfter(LocalDate.now().minusYears(14));
    }

    //endregion checking

    //region equals and hashCode:

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return telephoneNumber == employee.telephoneNumber && salary == employee.salary
                && workExperience == employee.workExperience && Objects.equals(id, employee.id)
                && Arrays.equals(fullName, employee.fullName) && Objects.equals(birthDate, employee.birthDate);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, birthDate, telephoneNumber, salary, workExperience);
        result = 31 * result + Arrays.hashCode(fullName);
        return result;
    }


    //endregion equals and hashCode

    //region print:
    public static void printEmployeeList() {
        employeeList.forEach(System.out::println);
    }
    @Override
    public String toString() {
        return this.id + "{" +
                "fullName= " + Arrays.toString(fullName) +
                ", birth Date= " + birthDate +
                ", age= " + this.getAge() +
                ", telephoneNumber= " + telephoneNumber +
                ", salary= " + salary +
                ", workExperience= " + workExperience +
                ", employeeStatus= " + this.getEmployeeStatus() +
                '}';
    }

    //endregion print:


}
