package eva.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eva.exceptions.TaskException;
import eva.tasks.Task;
import eva.tasks.Todo;

/**
 * Test class for Ui class.
 */
public class UiTest {
    private Ui ui;
    private ArrayList<Task> taskList;

    @BeforeEach
    public void setUp() {
        this.ui = new Ui();
        this.taskList = new ArrayList<>();
    }

    @Test
    public void testShowWelcome() {
        String expected = "Hello! I'm Eva. \nWhat can I do for you? \n";
        assertEquals(expected, ui.showWelcome());
    }

    @Test
    public void testShowEnd() {
        String expected = "Bye. Hope to see you again soon!";
        assertEquals(expected, ui.showEnd());
    }

    @Test
    public void testPrintTaskList_emptyList() throws TaskException {
        assertEquals("You have no tasks in your list!", ui.handleInput("list", taskList)[0]);
    }

    @Test
    public void testPrintTaskList_withTasks() throws TaskException {
        taskList.add(Todo.createTodo("read book"));
        taskList.add(Todo.createTodo("write essay"));

        String expected = "Here are the tasks in your list: \n"
                + "1. [T][ ] read book\n"
                + "2. [T][ ] write essay\n";

        assertEquals(expected, ui.handleInput("list", taskList)[0]);
    }

    @Test
    public void testMarkTask() throws Exception {
        taskList.add(Todo.createTodo("finish project"));

        // Mark task
        String markResult = ui.handleInput("mark 1", taskList)[0];
        assertEquals("Nice! I've marked this task as done: \n[T][X] finish project", markResult);

        // Unmark task
        String unmarkResult = ui.handleInput("unmark 1", taskList)[0];
        assertEquals("Ok! I've marked this task as not done yet: \n[T][ ] finish project", unmarkResult);
    }


}
