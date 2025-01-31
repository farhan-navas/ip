package eva.tasks;

import eva.exceptions.TaskException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskTest {
    private Task task;

    @BeforeEach
    public void setUp() throws TaskException {
        task = Task.createTask("todo read book");
    }

    // test for createTask, todo
    @Test
    public void testCreateTask_todo() throws TaskException {
        Task todo = this.task;
        assert todo instanceof Todo;
        assert todo.getName().equals(" read book");
    }

}
