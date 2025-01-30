package eva.tasks;

import eva.exceptions.TaskException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate startTime;
    private LocalDate endTime;

    private Event(String name, LocalDate startTime, LocalDate endTime) {
       super(name);
       this.startTime = startTime;
       this.endTime = endTime;
    }

    public Event(String name, boolean isDone, LocalDate startTime, LocalDate endTime) {
        super(name, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDate getStartTime() {
        return this.startTime;
    }

    public LocalDate getEndTime() {
        return this.endTime;
    }

    @Override
    public String toString() {
        String fStart = this.startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String fEnd = this.endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[E]%s (from: %s to: %s)", super.toString(), fStart, fEnd);
    }

    public static Event createEvent(String taskDesc) throws TaskException {
        if (!taskDesc.contains(" /from ") || !taskDesc.contains(" /to ")) {
            throw new TaskException("Invalid event format!");
        }

        String[] split = taskDesc.split(" /from ");
        String name = split[0];
        String[] split2 = split[1].split(" /to ");
        LocalDate startTime = LocalDate.parse(split2[0]);
        LocalDate endTime = LocalDate.parse(split2[1]);
        return new Event(name, startTime, endTime);
    }

}

