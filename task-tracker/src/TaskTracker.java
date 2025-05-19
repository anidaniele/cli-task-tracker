import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class TaskTracker {
    Map<Integer, Task> tasks;

    public TaskTracker(Map<Integer, Task> loadedTasks) {
        this.tasks = loadedTasks != null ? loadedTasks : new HashMap<>();
    }

    public void createTask(String description) {
        Task task = new Task(description);
        tasks.put(task.getId(), task);
        System.out.printf("Task added successfully (ID: %d)", task.getId());
    }


    public void updateTask(int id, String description){
        Task task = tasks.get(id);
        if (task != null) {
            task.setDescription(description);
            task.setUpdatedAt(LocalDateTime.now());
        } else {
            System.out.println("Task with id " + id + " not found.");
        }
    }

    public void deleteTask(int id) {
        if (tasks.get(id) != null) {
            tasks.remove(id);
        } else {
            System.out.println("Task with id " + id + " not found.");
        }
    }

    public void markInProgress(int id) {
        if (tasks.get(id) != null) {
            tasks.get(id).setStatus(Status.IN_PROGRESS);
            tasks.get(id).setUpdatedAt(LocalDateTime.now());
        }
        else {
            System.out.println("Task with id " + id + " not found.");
        }
    }

    public void markDone(int id) {
        if (tasks.get(id) != null) {
            tasks.get(id).setStatus(Status.DONE);
            tasks.get(id).setUpdatedAt(LocalDateTime.now());
        }
        else {
            System.out.println("Task with id " + id + " not found.");
        }
    }

    public void listAllTasks() {
        if (!tasks.isEmpty()) {
            for (Map.Entry<Integer, Task> mapEntry : tasks.entrySet()) {
                System.out.println(mapEntry.getValue());
            }
        }
        else System.out.println("No tasks were created");
    }

    public void listSpecificTasks(String status) {
        if (!tasks.isEmpty()) {
            tasks.entrySet().stream()
                    .filter(x -> Status.valueOf(status.toUpperCase()).equals(x.getValue().getStatus()))
                    .forEach(x -> System.out.println(x.getValue()));
        }
        else System.out.println("No tasks are present for this status");
    }

    public void saveTasksToFile() throws IOException {
        try (BufferedWriter bf = new BufferedWriter(new FileWriter("tasks.json"))) {
            bf.write("[\n");
            int count = 0;
            int size = tasks.size();
            for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
                bf.write(entry.getValue().toString());
                if (++count < size) {
                    bf.write(",");
                }
                bf.newLine();
            }
            bf.write("]");
        }
    }
}
