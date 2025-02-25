package eva.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import eva.exceptions.TaskException;

public class TaskTest {
    @Test
    public void testLoadTask_todo() throws TaskException {
        Task task = Task.loadTask("T | 0 | read book");
        assertInstanceOf(Todo.class, task);
        assertEquals("read book", task.getName());
        assertEquals(false, task.isDone());
    }

    @Test
    public void testLoadTask_deadline() throws TaskException {
        Task task = Task.loadTask("D | 1 | submit assignment | 2025-02-20");
        assertInstanceOf(Deadline.class, task);
        assertEquals("submit assignment", task.getName());
        assertEquals(true, task.isDone());
        assertEquals(LocalDate.parse("2025-02-20"), ((Deadline) task).getEndTime());
    }

    @Test
    public void testLoadTask_invalidTask_throwsException() {
        assertThrows(TaskException.class, () -> Task.loadTask("X | 0 | unknown task"));
    }
}
