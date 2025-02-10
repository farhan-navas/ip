package eva.ui;

import java.util.ArrayList;

import eva.exceptions.TaskException;
import eva.tasks.Task;

/**
 * Represents the user interface of the program. Handles user input and prints messages to the user.
 */
public class Ui {
    private static final String INTRO_MESSAGE = "Hello! I'm Eva. \nWhat can I do for you? \n";
    private static final String END_MESSAGE = "Bye. Hope to see you again soon!";

    private ArrayList<Task> taskList;

    /**
     * Prints the default Eva welcome message.
     */
    public String showWelcome() {
        return INTRO_MESSAGE;
    }

    /**
     * Prints the default Eva goodbye message.
     */
    public String showEnd() {
        return END_MESSAGE;
    }

    /**
     * Handles the user input and processes the tasks based on the input.
     *
     * @param currInput The current user input.
     * @param taskList The list of tasks to be processed.
     * @throws TaskException If there is an error processing the tasks.
     */
    public String[] handleInput(String currInput, ArrayList<Task> taskList) throws TaskException {
        this.taskList = taskList;
        if (currInput.equals("list")) {
            return new String[] {this.printTaskList(), "default"};
        } else if (currInput.startsWith("mark")) {
            return new String[] {this.markTask(currInput, true), "ChangeMarkCommand"};
        } else if (currInput.startsWith("unmark")) {
            return new String[] {this.markTask(currInput, false), "ChangeMarkCommand"};
        } else if (currInput.startsWith("delete")) {
            return new String[] {this.deleteTask(currInput), "DeleteCommand"};
        } else if (currInput.startsWith("find")) {
            return new String[] {this.findTask(currInput), "default"};
        } else {
            return new String[] {this.addTask(currInput), "AddCommand"};
        }
    }

    /**
     * Prints the list of tasks in the task list, if it is not empty.
     * If the task list is empty, prints a message to inform the user.
     */
    private String printTaskList() {
        if (this.taskList.isEmpty()) {
            return "You have no tasks in your list!";
        }

        return this.taskList.stream()
                .map(task -> (this.taskList.indexOf(task) + 1) + ". " + task)
                .reduce("Here are the tasks in your list: \n", (acc, task) -> acc + task + "\n");
    }

    /**
     * Marks the task as done or undone based on the task description.
     *
     * @param taskDesc the task description.
     * @param isDone true if the task is to be marked as done, false otherwise.
     */
    private String markTask(String taskDesc, boolean isDone) {
        int posToChange = Integer.parseInt(taskDesc.split(" ")[1]) - 1;
        if (isDone) {
            this.taskList.get(posToChange).markAsDone();
            return "Nice! I've marked this task as done: \n" + this.taskList.get(posToChange).toString();
        } else {
            this.taskList.get(posToChange).markAsUndone();
            return "Ok! I've marked this task as not done yet: \n"
                    + this.taskList.get(posToChange).toString();
        }
    }

    /**
     * Creates a task  based on the task description and adds it to the task list.
     *
     * @param taskDesc the task description.s
     * @throws TaskException if the task description is invalid.
     */
    private String addTask(String taskDesc) throws TaskException {
        Task task = Task.createTask(taskDesc);
        this.taskList.add(task);
        String result = "Got it: I've added this task:\n" + task.toString() + "\n";
        result += String.format("Now you have %d tasks in the list.", this.taskList.size());
        return result;
    }

    /**
     * Deletes a task from the task list based on the task description.
     *
     * @param taskDesc the task description.
     */
    private String deleteTask(String taskDesc) {
        int posToDelete = Integer.parseInt(taskDesc.split(" ")[1]) - 1;
        Task task = this.taskList.get(posToDelete);
        this.taskList.remove(posToDelete);
        String result = "Noted. I've removed this task: \n" + task.toString() + "\n";
        result += String.format("Now you have %d tasks in the list.%n", this.taskList.size());
        return result;
    }

    /**
     * Finds a task from the task list based on the task description.
     *
     * @param taskDesc the task description.
     */
    private String findTask(String taskDesc) {
        String keyword = taskDesc.split(" ")[1];
        String result = this.taskList.stream()
                .filter(task -> task.getName().contains(keyword))
                .map(task -> (this.taskList.indexOf(task) + 1) + ". " + task)
                .reduce("Here are the matching tasks in your list: \n", (acc, task) -> acc + task + "\n");

        return result.equals("Here are the matching tasks in your list: \n")
                ? "No tasks found with the keyword: " + keyword
                : result;
    }
}
