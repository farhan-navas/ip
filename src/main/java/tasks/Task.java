package tasks;

import exceptions.TaskException;

public abstract  class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.isDone = false;
        this.name = name;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
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
}
