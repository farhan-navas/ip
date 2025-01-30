package tasks;

import exceptions.TaskException;

public class Event extends Task {
    private String startTime;
    private String endTime;

    private Event(String name, String startTime, String endTime) {
       super(name);
       this.startTime = startTime;
       this.endTime = endTime;
    }

    public Event(String name, boolean isDone, String startTime, String endTime) {
        super(name, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.startTime, this.endTime);
    }

    public static Event createEvent(String taskDesc) throws TaskException {
        if (!taskDesc.contains(" /from ") || !taskDesc.contains(" /to ")) {
            throw new TaskException("Invalid event format!");
        }

        String[] split = taskDesc.split(" /from ");
        String name = split[0];
        String[] split2 = split[1].split(" /to ");
        String startTime = split2[0];
        String endTime = split2[1];
        return new Event(name, startTime, endTime);
    }

}

