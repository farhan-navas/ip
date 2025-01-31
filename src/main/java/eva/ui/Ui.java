package eva.ui;

import java.util.ArrayList;
import java.util.Scanner;

import eva.exceptions.TaskException;
import eva.tasks.Task;

/**
 * Represents the user interface of the program. Handles user input and prints messages to the user.
 */
public class Ui {
    private final String INTRO = "Hello! I'm Eva. \nWhat can I do for you? \n";
    private final String END = "Bye. Hope to see you again soon!";

    private ArrayList<Task> taskList;

    /**
     * Prints the default Eva welcome message.
     */
    public void showWelcome() {
        System.out.println(INTRO);
    }

    /**
     * Prints the default Eva goodbye message.
     */
    public void showEnd() {
        System.out.println(END);
    }

    /**
     * Handles the user input and performs the necessary actions. Actions include adding tasks, marking tasks,
     * deleting tasks, listing tasks and exiting the program.
     *
     * @param scanner the scanner object to read user input.
     * @param taskList the list of tasks to be updated.
     * @throws TaskException if the task description is invalid.
     */
    public void handleInput(Scanner scanner, ArrayList<Task> taskList) throws TaskException {
        this.taskList = taskList;
        while (true) {
            String currInput = scanner.nextLine().trim();
            if (currInput.equals("bye")) {
                break;
            } else if (currInput.equals("list")) {
                this.printTaskList();
            } else if (currInput.startsWith("mark")) {
                this.markTask(currInput, true);
            } else if (currInput.startsWith("unmark")) {
                this.markTask(currInput, false);
            } else if (currInput.startsWith("delete")) {
                deleteTask(currInput);
            } else {
                addTask(currInput);
            }
        }

        scanner.close();
    }

    /**
     * Prints the list of tasks in the task list, if it is not empty.
     * If the task list is empty, prints a message to inform the user.
     */
    private void printTaskList() {
        if (this.taskList.isEmpty()) {
            System.out.println("You have no tasks in your list!");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println((i + 1) + ". " + this.taskList.get(i).toString());
        }
    }

    /**
     * Marks the task as done or undone based on the task description.
     *
     * @param taskDesc the task description.
     * @param isDone true if the task is to be marked as done, false otherwise.
     */
    private void markTask(String taskDesc, boolean isDone) {
        int posToChange = Integer.parseInt(taskDesc.split(" ")[1]) - 1;
        if (isDone) {
            this.taskList.get(posToChange).markAsDone();
            System.out.println("Nice! I've marked this task as done: \n" + this.taskList.get(posToChange).toString());
        } else {
            this.taskList.get(posToChange).markAsUndone();
            System.out.println("Ok! I've marked this task as not done yet: \n" + this.taskList.get(posToChange).toString());
        }
    }

    /**
     * Creates a task  based on the task description and adds it to the task list.
     *
     * @param taskDesc the task description.
     * @throws TaskException if the task description is invalid.
     */
    private void addTask(String taskDesc) throws TaskException {
        Task task = Task.createTask(taskDesc);
        this.taskList.add(task);
        System.out.println("Got it: I've added this task:\n" + task.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", this.taskList.size()));
    }

    /**
     * Deletes a task from the task list based on the task description.
     *
     * @param taskDesc the task description.
     */
    private void deleteTask(String taskDesc) {
        int posToDelete = Integer.parseInt(taskDesc.split(" ")[1]) - 1;
        Task task = this.taskList.get(posToDelete);
        this.taskList.remove(posToDelete);
        System.out.println("Noted. I've removed this task: \n" + task.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", this.taskList.size()));
    }
}
