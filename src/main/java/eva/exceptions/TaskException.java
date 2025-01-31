package eva.exceptions;

/**
 * Represents an exception that occurs when handling tasks.
 */
public class TaskException extends Exception {

    /**
     * Constructs a TaskException with the specified detail message.
     *
     * @param message The detail message.
     */
    public TaskException(String message) {
        super(message);
    }

    /**
     * Returns a string representation of the TaskException.
     *
     * @return A string representation of the TaskException.
     */
    @Override
    public String toString() {
        return "TaskException: " + getMessage();
    }
}
