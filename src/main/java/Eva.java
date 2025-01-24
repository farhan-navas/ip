import java.util.ArrayList;
import java.util.Scanner;

public class Eva {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String intro = "Hello! I'm Eva \nWhat can I do for you? \n";
        String end = "Bye. Hope to see you again soon!";
        System.out.println(intro);

        ArrayList<Task> taskList = new ArrayList<>();
        while (true) {
            String currInput = scanner.nextLine().trim();
            if (currInput.equals("bye")) {
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

        System.out.println(end);
        scanner.close();
    }

    private static void printTaskList(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            return;
        }
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i).toString());
        }
    }

    private static void addTask(ArrayList<Task> taskList, String taskDesc) {
        Task task = new Task(taskDesc);
        taskList.add(task);
        System.out.println("added: " + task.toString());
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
