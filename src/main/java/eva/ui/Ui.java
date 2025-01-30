package eva.ui;

import eva.exceptions.TaskException;
import eva.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final String INTRO = "Hello! I'm eva.Eva \nWhat can I do for you? \n";
    private final String END = "Bye. Hope to see you again soon!";

    private ArrayList<Task> taskList;

    public void showWelcome() {
        System.out.println(INTRO);
    }

    public void showEnd() {
        System.out.println(END);
    }

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

    private void printTaskList() {
        if (this.taskList.isEmpty()) {
            System.out.println("You have no eva.tasks in your list!");
            return;
        }
        System.out.println("Here are the eva.tasks in your list:");
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println((i + 1) + ". " + this.taskList.get(i).toString());
        }
    }

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

    private void addTask(String taskDesc) throws TaskException {
        Task task = Task.createTask(taskDesc);
        this.taskList.add(task);
        System.out.println("Got it: I've added this task:\n" + task.toString());
        System.out.println(String.format("Now you have %d eva.tasks in the list.", this.taskList.size()));
    }

    private void deleteTask(String taskDesc) {
        int posToDelete = Integer.parseInt(taskDesc.split(" ")[1]) - 1;
        Task task = this.taskList.get(posToDelete);
        this.taskList.remove(posToDelete);
        System.out.println("Noted. I've removed this task: \n" + task.toString());
        System.out.println(String.format("Now you have %d eva.tasks in the list.", this.taskList.size()));
    }
}
