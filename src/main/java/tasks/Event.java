package tasks;

import exceptions.TaskException;

public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event(String name, String startTime, String endTime) {
       super(name);
       this.startTime = startTime;
       this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
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

