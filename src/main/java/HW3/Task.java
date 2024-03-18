package HW3;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Task {
    private String taskName;
    List<Task> taskList = new ArrayList<>();
    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskName='" + taskName + '\'' +
                '}';
    }
}
