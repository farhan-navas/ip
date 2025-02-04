package eva;


import java.util.ArrayList;
import java.util.Scanner;

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

    /**
     * Constructor for the Eva class. Initializes the Ui and Handler objects.
     */
    public Eva() {
        this.ui = new Ui();
        this.taskList = Handler.loadTasks();
    }

    /**
     * Runs the Eva program. Loads tasks from the hard drive, handles user input, and saves tasks to the hard drive.
     *
     * @throws TaskException If there is an error loading tasks from the hard drive.
     */
    public void run() throws TaskException {
        this.ui.showWelcome();
        Scanner scanner = new Scanner(System.in);

        this.ui.handleInput(scanner, this.taskList);
        this.ui.showEnd();
        Handler.saveTasks(this.taskList);
    }

    /**
     * Returns a response to the user input.
     *
     * @param input The user input.
     * @return The response to the user input.
     */
    public String getResponse(String input) {
        return "Eva heard: " + input;
    }

    /**
     * Main method to run the Eva program.
     *
     * @param args Command line arguments.
     * @throws TaskException If there is an error loading tasks from the hard drive.
     */
    public static void main(String[] args) throws TaskException {
        new Eva().run();
    }
}
