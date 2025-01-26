import exceptions.TaskException;
import tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Eva {
    public static ArrayList<Task> taskList  = new ArrayList<>();

    public static void main(String[] args) throws TaskException {
        Scanner scanner = new Scanner(System.in);
        String intro = "Hello! I'm Eva \nWhat can I do for you? \n";
        String end = "Bye. Hope to see you again soon!";
        System.out.println(intro);

        while (true) {
            String currInput = scanner.nextLine().trim();
            if (currInput.equals("bye")) {
                System.out.println(end);
                break;
            } else if (currInput.equals("list")) {
                printTaskList();
            } else if (currInput.startsWith("mark")) {
                markTask(currInput, true);
            } else if (currInput.startsWith("unmark")) {
                markTask(currInput, false);
            } else if (currInput.startsWith("delete")) {
                deleteTask(taskList, currInput);
            } else {
                addTask(currInput);
            }
        }
        scanner.close();
    }

    private static void printTaskList() {
        if (Eva.taskList.isEmpty()) {
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Eva.taskList.size(); i++) {
            System.out.println((i + 1) + ". " + Eva.taskList.get(i).toString());
        }
    }

    private static void addTask(String taskDesc) throws TaskException {
        Task task = Task.createTask(taskDesc);
        Eva.taskList.add(task);
        System.out.println("Got it: I've added this task:\n" + task.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", Eva.taskList.size()));
    }

    private static void markTask(String taskDesc, boolean isDone) {
        int posToChange = Integer.parseInt(taskDesc.split(" ")[1]) - 1;
        if (isDone) {
            Eva.taskList.get(posToChange).markAsDone();
            System.out.println("Nice! I've marked this task as done: \n" + Eva.taskList.get(posToChange).toString());
        } else {
            Eva.taskList.get(posToChange).markAsUndone();
            System.out.println("Ok! I've marked this task as not done yet: \n" + Eva.taskList.get(posToChange).toString());
        }
    }

    private static void deleteTask(ArrayList<Task> taskList, String taskDesc) {
        int posToDelete = Integer.parseInt(taskDesc.split(" ")[1]) - 1;
        Task task = taskList.get(posToDelete);
        taskList.remove(posToDelete);
        System.out.println("Noted. I've removed this task: \n" + task.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
    }
}
