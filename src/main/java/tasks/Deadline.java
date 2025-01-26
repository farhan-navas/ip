package tasks;

import exceptions.TaskException;

public class Deadline extends Task {
    private String endTime;

    public Deadline(String name, String endTime) {
        super(name);
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }

    public static Deadline createDeadline(String taskDesc) throws TaskException {
        if (!taskDesc.contains(" /by ")) {
            throw new TaskException("Invalid deadline format!");
        }

        String[] split = taskDesc.split(" /by ");
        return new Deadline(split[0], split[1]);
    }
}
