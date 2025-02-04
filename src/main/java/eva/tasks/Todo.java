package eva.tasks;

import eva.exceptions.TaskException;

/**
 * Represents a Todo object. A <code>Todo</code> has a name.
 * This class extends from the <code>Task</code> class.
 */
public class Todo extends Task {

    /**
     * Creates a new <code>Todo</code> object with the specified name.
     *
     * @param name name of the todo.
     */
    private Todo(String name) {
        super(name);
    }

    /**
     * Creates a new <code>Todo</code> object with the specified name and status.
     *
     * @param name name of the todo.
     * @param isDone status of the todo.
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Returns the string representation of the todo.
     *
     * @return string representation of the todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Factory method to create a new <code>Todo</code> object based on the task description.
     *
     * @param taskDesc description of the todo.
     *
     * @return new <code>Todo</code> object
     * @throws TaskException if the description of the todo is empty
     */
    public static Todo createTodo(String taskDesc) throws TaskException {
        if (taskDesc.isBlank()) {
            throw new TaskException("Invalid todo format!");
        }

        return new Todo(taskDesc);
    }
}
