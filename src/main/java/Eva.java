import exceptions.TaskException;
import tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Eva {

    public static void main(String[] args) throws TaskException {
        Scanner scanner = new Scanner(System.in);
        String intro = "Hello! I'm Eva \nWhat can I do for you? \n";
        String end = "Bye. Hope to see you again soon!";
        System.out.println(intro);

        ArrayList<Task> taskList = new ArrayList<>();
        while (true) {
            String currInput = scanner.nextLine().trim();
            if (currInput.equals("bye")) {
                System.out.println(end);
                break;
            } else if (currInput.equals("list")) {
                printTaskList(taskList);
            } else if (currInput.startsWith("mark")) {
                markTask(taskList, currInput, true);
            } else if (currInput.startsWith("unmark")) {
                markTask(taskList, currInput, false);
            } else {
                addTask(taskList, currInput);
            }
        }
        scanner.close();
    }

    private static void printTaskList(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i).toString());
        }
    }

    private static void addTask(ArrayList<Task> taskList, String taskDesc) throws TaskException {
        Task task = Task.createTask(taskDesc);
        taskList.add(task);
        System.out.println("Got it: I've added this task:\n" + task.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
    }

    private static void markTask(ArrayList<Task> taskList, String taskDesc, boolean isDone) {
        int posToChange = Integer.parseInt(taskDesc.split(" ")[1]) - 1;
        if (isDone) {
            taskList.get(posToChange).markAsDone();
            System.out.println("Nice! I've marked this task as done: \n" + taskList.get(posToChange).toString());
        } else {
            taskList.get(posToChange).markAsUndone();
            System.out.println("Ok! I've marked this task as not done yet: \n" + taskList.get(posToChange).toString());
        }
    }
}
