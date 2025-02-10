package eva;


import java.util.ArrayList;

import eva.exceptions.TaskException;
import eva.storage.Handler;
import eva.tasks.Task;
import eva.ui.Ui;

/**
 * Represents the main class of the Eva program. Contains the main method to run the program.
 * The Eva program is a task manager that allows users to add, delete, mark as done, and list tasks.
 *
 * <p> The Eva program is able to handle three types of tasks:
 * <ul>
 *     <li> Todo: A task without any date/time attached to it.</li>
 *     <li> Deadline: A task that needs to be done before a specific date/time.</li>
 *     <li> Event: A task that starts at a specific time and ends at a specific time.</li>
 * </ul>
 * </p>
 *
 * @author Farhan Navas
 * @version 1.0
 * @since 2025-09-01
 */
public class Eva {
    private final ArrayList<Task> taskList;
    private final Ui ui;
    private String commandType;

    /**
     * Constructor for the Eva class. Initializes the Ui and Handler objects.
     */
    public Eva() {
        this.ui = new Ui();
        this.taskList = Handler.loadTasks();

        assert ui != null : "Ui object is null!";
        assert taskList != null : "Task list is null!";
    }

    /**
     * Gets the response from Eva based on the user input.
     *
     * @param input The user input.
     * @return The response from Eva.
     */
    public String getResponse(String input) {
        assert input != null : "User input is null!";

        try {
            String[] response = this.ui.handleInput(input, this.taskList);
            assert response != null : "Response is null!";

            this.commandType = response[1];
            assert commandType != null : "Command type is null!";

            return response[0];
        } catch (TaskException e) {
            return e.getMessage();
        } finally {
            Handler.saveTasks(this.taskList);
        }
    }

    public String getCommandType() {
        return commandType;
    }
}
