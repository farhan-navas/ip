package eva.tasks;

import java.time.LocalDate;

import eva.exceptions.TaskException;

public abstract  class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
       return (this.isDone ? "[X] " : "[ ] ") + name;
    }

    // Factory method
    public static Task createTask(String taskDesc) throws TaskException {
        if (taskDesc.startsWith("todo")) {
            return Todo.createTodo(taskDesc.substring(4));
        } else if (taskDesc.startsWith("deadline")) {
            return Deadline.createDeadline(taskDesc.substring(8));
        } else if (taskDesc.startsWith("event")) {
            return Event.createEvent(taskDesc.substring(5));
        } else {
            throw new TaskException("Invalid task type!");
        }
    }

    // Factory method to load task from hard drive
    public static Task loadTask(String taskDesc) throws TaskException {
        String[] split = taskDesc.split(" \\| ");
        String taskType = split[0].trim();
        boolean isDone = split[1].trim().equals("1");
        String taskName = split[2].trim();
        if (taskType.equals("E")) {
            LocalDate startTime = LocalDate.parse(split[3].split("-")[0].trim());
            LocalDate endTime = LocalDate.parse(split[3].split("-")[1].trim());
            return new Event(taskName, isDone, startTime, endTime);
        } else if (taskType.equals("D")) {
            LocalDate endTime = LocalDate.parse(split[3].trim());
            return new Deadline(taskName, isDone, endTime);
        } else if (taskType.equals("T")) {
            return new Todo(taskName, isDone);
        }

        throw new TaskException("Invalid task type!");
    }

}
