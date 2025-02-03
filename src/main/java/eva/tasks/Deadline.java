package eva.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import eva.exceptions.TaskException;

/**
 * Represents a task with a deadline. Extends the <code>Task</code> class.
 * A <code>Deadline</code> object has a name, a status of whether it is done and a deadline.
 */
public class Deadline extends Task {
    private final LocalDate endTime;

    /**
     * Creates a new <code>Deadline</code> object with the specified name and deadline.
     *
     * @param name name of the deadline.
     * @param endTime deadline of the deadline.
     */
    private Deadline(String name, LocalDate endTime) {
        super(name);
        this.endTime = endTime;
    }

    /**
     * Creates a new <code>Deadline</code> object with the specified name, status and deadline.
     *
     * @param name name of the deadline.
     * @param isDone status of the deadline.
     * @param endTime deadline of the deadline.
     */
    public Deadline(String name, boolean isDone, LocalDate endTime) {
        super(name, isDone);
        this.endTime = endTime;
    }

    /**
     * Returns the string representation of the deadline.
     *
     * @return string representation of the deadline.
     */
    @Override
    public String toString() {
        String fEnd = this.endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[D]%s (by: %s)", super.toString(), fEnd);
    }

    /**
     * Returns the deadline of the Deadline object.
     *
     * @return deadline of the Deadline object.
     */
    public LocalDate getEndTime() {
        return this.endTime;
    }

    /**
     * Factory method to create a new <code>Deadline</code> object based on the task description.
     *
     * @param taskDesc task description of the deadline.
     *
     * @return new <code>Deadline</code> object.
     * @throws TaskException if the task description is in an invalid format.
     */
    public static Deadline createDeadline(String taskDesc) throws TaskException {
        if (!taskDesc.contains(" /by ")) {
            throw new TaskException("Invalid deadline format!");
        }

        String[] split = taskDesc.split(" /by ");
        LocalDate endTime = LocalDate.parse(split[1]);
        return new Deadline(split[0], endTime);
    }
}
