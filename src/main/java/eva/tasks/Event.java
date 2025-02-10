package eva.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import eva.exceptions.TaskException;

/**
 * Represents an Event object. An <code>Event</code> has a name, a start time and an end time.
 * This class extends from the <code>Task</code> class.
 */
public class Event extends Task {
    private final LocalDate startTime;
    private final LocalDate endTime;

    /**
     * Creates an Event object with the specified name, start time and end time.
     *
     * @param name name of the event.
     * @param startTime start time of the event.
     * @param endTime end time of the event.
     */
    private Event(String name, LocalDate startTime, LocalDate endTime) {
        super(name);
        assert startTime != null : "Start time of event cannot be null!";
        assert endTime != null : "End time of event cannot be null!";
        assert startTime.isBefore(endTime) : "Start time of event cannot be after end time!";

        this.startTime = startTime;
        this.endTime = endTime;
    }
    /**
     * Creates an Event object with the specified name, status, start time and end time.
     *
     * @param name name of the event.
     * @param isDone status of the event.
     * @param startTime start time of the event.
     * @param endTime end time of the event.
     */
    public Event(String name, boolean isDone, LocalDate startTime, LocalDate endTime) {
        super(name, isDone);
        assert startTime != null : "Start time of event cannot be null!";
        assert endTime != null : "End time of event cannot be null!";
        assert startTime.isBefore(endTime) : "Start time of event cannot be after end time!";

        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns the start time of the event.
     *
     * @return start time of the event.
     */
    public LocalDate getStartTime() {
        return this.startTime;
    }

    /**
     * Returns the end time of the event.
     *
     * @return end time of the event.
     */
    public LocalDate getEndTime() {
        return this.endTime;
    }

    /**
     * Returns the string representation of the event.
     *
     * @return string representation of the event.
     */
    @Override
    public String toString() {
        String fStart = this.startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String fEnd = this.endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[E]%s (from: %s to: %s)", super.toString(), fStart, fEnd);
    }

    /**
     * Factory method to create a new <code>Event</code> object based on the task description.
     *
     * @param taskDesc description of the event.
     *
     * @return Event object created.
     * @throws TaskException if the task description is in an invalid format.
     */
    public static Event createEvent(String taskDesc) throws TaskException {
        assert taskDesc != null : "Task description cannot be null!";

        if (!taskDesc.contains(" /from ") || !taskDesc.contains(" /to ")) {
            throw new TaskException("Invalid event format!");
        }

        String[] split = taskDesc.split(" /from ");
        assert split.length == 2 : "Invalid event format!";

        String name = split[0];
        String[] split2 = split[1].split(" /to ");
        assert split2.length == 2 : "Invalid event format! Event name should contain /from and /to!";

        LocalDate startTime = LocalDate.parse(split2[0]);
        LocalDate endTime = LocalDate.parse(split2[1]);
        assert startTime != null : "Start time of event cannot be null!";
        assert endTime != null : "End time of event cannot be null!";
        assert startTime.isBefore(endTime) : "Start time of event cannot be after end time!";

        Event newEvent = new Event(name, startTime, endTime);
        assert newEvent != null : "Event object cannot be null!";
        return newEvent;
    }
}

