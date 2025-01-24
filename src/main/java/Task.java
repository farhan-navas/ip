public class Task {
    private String name;
    private boolean isDone;
    private enum TaskType { TODO, DEADLINE, EVENT };
    private TaskType taskType;
    private String startTime;
    private String endTime;

    public Task(String name, String taskType) {
        this.isDone = false;
        this.taskType = parseType(taskType);
        if (this.taskType == TaskType.DEADLINE) {
            String[] split = name.split(" /by ");
            this.name = split[0];
            this.endTime = split[1];
        } else if (this.taskType == TaskType.EVENT) {
            String[] split = name.split(" /from ");
            this.name = split[0];
            String[] split2 = split[1].split(" /to ");
            this.startTime = split2[0];
            this.endTime = split2[1];
        } else {
            this.name = name;
        }
    }

    private TaskType parseType(String taskType) {
        switch (taskType) {
            case "todo":
                return TaskType.TODO;
            case "deadline":
                return TaskType.DEADLINE;
            case "event":
                return TaskType.EVENT;
            default:
                return null;
        }
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String str = (taskType == TaskType.TODO ? "[T] " : taskType == TaskType.EVENT ? "[E] " : "[D] ")
                + (isDone ? "[X] " : "[ ] ") + name;
        if (taskType == TaskType.DEADLINE) {
            str += " (by: " + endTime + ")";
        } else if (taskType == TaskType.EVENT) {
            str += " (from: " + startTime + " to: " + endTime + ")";
        }
        return str;
    }
}
