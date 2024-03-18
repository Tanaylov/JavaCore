package HW3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Manager extends Employee {
    private short subordinatesCount = 1;
    private List<Worker> subordinates = new ArrayList<>();
    private List<Task> taskList = new ArrayList<>();
    private static List<Manager> managers = new ArrayList<>();

    {
        managers.add(Manager.this);
    }
    //region constructors:

    public Manager() {
    }

    public Manager(String[] fullName) {
        super(fullName);
    }

    public Manager(String[] fullName, int salary) {
        super(fullName, salary);
    }

    public Manager(String[] fullName, int salary, byte workExperience) {
        super(fullName, salary, workExperience);
    }

    public Manager(String[] fullName, int salary, byte workExperience, LocalDate birthDate) {
        super(fullName, salary, workExperience, birthDate);
    }

    public Manager(String[] fullName, int salary, byte workExperience, LocalDate birthDate, long telephoneNumber) {
        super(fullName, salary, workExperience, birthDate, telephoneNumber);
    }
    public Manager(String[] fullName, int salary, byte workExperience,
                   LocalDate birthDate, long telephoneNumber, short subordinatesCount) {
        super(fullName, salary, workExperience, birthDate, telephoneNumber);
        if (subordinatesCount > 1) this.subordinatesCount = subordinatesCount;
    }
    public Manager(Employee employee) {
        super(employee);
    }
    //endregion constructors

    public short getSubordinatesCount() {
        return subordinatesCount;
    }
    public static List<Manager> getManagers() {return managers;}
    public void setSubordinatesCount(short subordinatesCount) {
        if (subordinatesCount > 1) this.subordinatesCount = subordinatesCount;
        else System.out.println("A manager must have at least one subordinate.");
    }

    public List<Worker> getSubordinates() {
        return subordinates;
    }

    private void setSubordinates(List<Worker> subordinates) {
        this.subordinates = subordinates;
    }

    //region changeSalary:
    public void changeSalary(int valueChange) {
        for (int i = 0; i < Employee.getEmployeeNumber(); i++) {
            if (Employee.getEmployeeList().get(i) instanceof Worker)
                Employee.getEmployeeList().get(i).salary += valueChange;
        }
    }
    public void changeSalary(Worker worker, int valueChange) {
        if (Employee.getEmployeeList().contains(worker))
            worker.salary += valueChange; //В контексту моего варианта достаточно только этой строчки,
                                          // потому что при создании экземпляра Worker он автоматом попадает в список работников.
        else System.out.println("This worker doesn't exist");
    }

    public void changeSalary(int workerID, int valueChange) {
        for (int i = 0; i < Employee.getEmployeeNumber(); i++) {
            if (Employee.getEmployeeList().get(i) instanceof Worker
                    && Integer.parseInt(Employee.getEmployeeList().get(i).id.split("_")[1]) == workerID)
                Employee.getEmployeeList().get(i).salary += valueChange;
        }
    }
    //endregion changeSalary

    public void addSubordinates(short subordinatesCount) {
        for (Worker worker : Worker.getWorkers()) {
            if (worker.getManager() == null) {
                worker.setManager(this);
                subordinates.add(worker);
                subordinatesCount--;
                if (subordinatesCount == 0) break;
            }
        }
        if (subordinates.isEmpty()) System.out.println("Manager " + Arrays.toString(this.fullName) + " haven't subordinates");
    }
    public void addTask(Task task) {taskList.add(task);}
    public void addTask(Task[] task) {
        taskList = List.of(task);
    }
    public Task getTask(Task task) {
        for (Task task1 : taskList) {
            if (task1.equals(task)) return task1;
        }
        return null;
    }
    public Task getTask(int taskIndex) {
        return taskList.get(taskIndex);
    }

    @Override
    public void setTask(Task task, Employee employee) {
        for (Worker subordinate : this.subordinates) {
            if (subordinate.equals(employee)) subordinate.takeTask(task);
        }
    }

    @Override
    public void takeTask(Task task) {
        this.task = task;
    }
}
