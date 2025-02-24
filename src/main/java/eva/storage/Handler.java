package eva.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import eva.exceptions.TaskException;
import eva.tasks.Task;

/**
 * Handles the loading and saving of tasks from the hard drive.
 */
public class Handler {
    private static final String FILE_PATH = "data/eva.txt";

    /**
     * Loads tasks from the hard drive, which is stored in a text file.
     *
     * @return An ArrayList of tasks loaded from the hard drive.
     */
    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (Scanner taskScanner = new Scanner(new File(FILE_PATH))) {
            while (taskScanner.hasNextLine()) {
                String currTaskString = taskScanner.nextLine();
                assert currTaskString != null && !currTaskString.isEmpty() : "Task string is empty!";
                Task currTask = Task.loadTask(currTaskString);
                tasks.add(currTask);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("File not found!");
        } catch (TaskException e) {
            System.out.println(e.getMessage());
            System.out.println("Task format incorrect! Please refresh/check hard drive for errors");
        }

        return tasks;
    }

    /**
     * Saves tasks to the hard drive, which is stored in a text file.
     *
     * @param tasks The ArrayList of tasks to be saved to the hard drive.
     */
    public static void saveTasks(ArrayList<Task> tasks) {
        assert tasks != null : "Task list is null!";

        try {
            File taskFile = new File(FILE_PATH);
            if (!taskFile.exists()) {
                taskFile.getParentFile().mkdirs();
                taskFile.createNewFile();
            }

            try (FileWriter taskFileWriter = new FileWriter(FILE_PATH, false)) {
                for (Task currTask : tasks) {
                    if (currTask instanceof eva.tasks.Event) {
                        taskFileWriter.write("E | " + (currTask.isDone() ? "1 " : "0 ") + "| " + currTask.getName()
                                + " | " + ((eva.tasks.Event) currTask).getStartTime() + "| "
                                + ((eva.tasks.Event) currTask).getEndTime() + "\n");
                    } else if (currTask instanceof eva.tasks.Deadline) {
                        taskFileWriter.write("D | " + (currTask.isDone() ? "1 " : "0 ") + "| "
                                + currTask.getName() + " | " + ((eva.tasks.Deadline) currTask).getEndTime() + "\n");
                    } else if (currTask instanceof eva.tasks.Todo) {
                        taskFileWriter.write("T | " + (currTask.isDone() ? "1" : "0") + " | "
                                + currTask.getName() + "\n");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("IO Error while getting messages: " + e.getMessage());
        }
    }

}
