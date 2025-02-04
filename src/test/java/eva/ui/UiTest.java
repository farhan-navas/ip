package eva.ui;

import eva.exceptions.TaskException;
import eva.tasks.Task;
import eva.tasks.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UiTest {
    private Ui ui;
    private ArrayList<Task> taskList;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        ui = new Ui();
        taskList = new ArrayList<>();
        System.setOut(new PrintStream(outContent));
    }

    // Test for handleInput addTask
    @Test
    public void testHandleInput_addTask() throws TaskException {
        String simInput = "todo read book\nbye\n";

        ui.handleInput(simInput, taskList);

        assertEquals(1, taskList.size());
        assertTrue(this.taskList.get(0) instanceof Todo);
        assertEquals(" read book", this.taskList.get(0).getName());

        String consoleOutput = outContent.toString();
        assert consoleOutput.contains("Got it: I've added this task:");
        assert consoleOutput.contains("[T][ ]  read book");
        assert consoleOutput.contains("Now you have 1 tasks in the list.");
    }
}
