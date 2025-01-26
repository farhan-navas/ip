package tasks;

import exceptions.TaskException;

public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public static Todo createTodo(String taskDesc) throws TaskException {
        if (taskDesc.isBlank()) {
            throw new TaskException("The description of a todo cannot be empty.");
        }

        return new Todo(taskDesc);
    }
}
