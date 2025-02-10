package eva.tasks;

import java.time.LocalDate;

import eva.exceptions.TaskException;

/**
 * Represents a Task object. A <code>Task</code> has a name and a status of whether it is done.
 * This is an abstract class and cannot be instantiated directly, use of its subclasses is recommended.
 */
public abstract class Task {
    private final String name;
    private boolean isDone;

    /**
     * Constructor for Task object.
     *
     * @param name name of the task.
     */
    public Task(String name) {
        assert name != null : "Task name cannot be null!";
        this.name = name;
        this.isDone = false;
    }

    /**
     * Constructor for Task object.
     *
     * @param name name of the task.
     * @param isDone status of the task.
     */
    public Task(String name, boolean isDone) {
        assert name != null : "Task name cannot be null!";
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        assert !this.isDone : "Task is already done!";
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        assert this.isDone : "Task is already undone!";
        this.isDone = false;
    }

    /**
     * Returns the status of the task.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the name of the task.
     *
     * @return name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return string representation of the task.
     */
    @Override
    public String toString() {
        return (this.isDone ? "[X] " : "[ ] ") + name;
    }

    /**
     * Factory method to create a task based on the task description.
     *
     * @param taskDesc description of the task.
     * @return Task object created.
     * @throws TaskException if the task description is invalid.
     * @see Todo
     * @see Deadline
     * @see Event
     */
    public static Task createTask(String taskDesc) throws TaskException {
        assert taskDesc != null : "Task description cannot be null!";
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

    /**
     * Loads a task from a string representation, from the hard drive.
     *
     * @param taskDesc string representation of the task.
     * @return Task object created.
     * @throws TaskException if the task description is invalid.
     */
    public static Task loadTask(String taskDesc) throws TaskException {
        assert taskDesc != null : "Task description cannot be null!";
        String[] split = taskDesc.split(" \\| ");
        assert split.length >= 3 : "Invalid task format!";
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
