package tasks;

import exceptions.TaskException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate endTime;

    private Deadline(String name, LocalDate endTime) {
        super(name);
        this.endTime = endTime;
    }

    public Deadline(String name, boolean isDone, LocalDate endTime) {
        super(name, isDone);
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        String fEnd = this.endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[D]%s (by: %s)", super.toString(), fEnd);
    }

    public LocalDate getEndTime() {
        return this.endTime;
    }

    public static Deadline createDeadline(String taskDesc) throws TaskException {
        if (!taskDesc.contains(" /by ")) {
            throw new TaskException("Invalid deadline format!");
        }

        String[] split = taskDesc.split(" /by ");
        LocalDate endTime = LocalDate.parse(split[1]);
        return new Deadline(split[0], endTime);
    }
}
