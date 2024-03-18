package HW3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Worker extends Employee {
    private static List<Worker> workers = new ArrayList<>();
    private Manager manager;


    {
        workers.add(Worker.this);
    }

    //region constructor:
    public Worker(String[] fullName, int salary, byte workExperience, LocalDate birthDate, long telephoneNumber) {
        super(fullName, salary, workExperience, birthDate, telephoneNumber);
    }
    public Worker(Employee employee) {
        super(employee);
    }
    public Worker() {
    }

    public Worker(String[] fullName) {
        super(fullName);
    }

    public Worker(String[] fullName, int salary) {
        super(fullName, salary);
    }

    public Worker(String[] fullName, int salary, byte workExperience) {
        super(fullName, salary, workExperience);
    }

    public Worker(String[] fullName, int salary, byte workExperience, LocalDate birthDate) {
        super(fullName, salary, workExperience, birthDate);
    }
    //endregion constructors
    public static List<Worker> getWorkers() {return workers;}
    protected Manager getManager() {return manager;}
    protected void setManager (Manager manager) {this.manager = manager;}

    @Override
    public void setTask(Task task, Employee employee) {}

    @Override
    public void takeTask(Task task) {
        this.task = task;
    }

    public Task getTask() {return task;}
}
