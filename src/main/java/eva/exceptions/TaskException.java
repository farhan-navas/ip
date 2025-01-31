package eva.exceptions;

public class TaskException extends Exception {
    public TaskException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "TaskException: " + getMessage();
    }
}
