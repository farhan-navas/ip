package storage;

import exceptions.TaskException;
import java.io.FileNotFoundException;

import tasks.Task;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Handler {
    private static final String FILE_PATH = "data/eva.txt";

    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File taskFile = new File(FILE_PATH);
            Scanner taskScanner = new Scanner(taskFile);

            while (taskScanner.hasNextLine()) {
               String currTaskString = taskScanner.nextLine();
               Task currTask = Task.loadTask(currTaskString);
               tasks.add(currTask);
            }
            taskScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("File not found!");
        } catch (TaskException e) {
            System.out.println(e.getMessage());
            System.out.println("Task format incorrect! Please refresh/check hard drive for errors");
        } finally {
            return tasks;
        }
    }

    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            File taskFile = new File(FILE_PATH);
            if (taskFile.delete()) {
                taskFile.createNewFile();
            } else {
                throw new FileNotFoundException();
            }

            FileWriter taskFileWriter = new FileWriter(FILE_PATH);
            for (int i = 0; i < tasks.size(); i++) {
                Task currTask = tasks.get(i);
                if (currTask instanceof tasks.Event) {
                    taskFileWriter.write("E | " + (currTask.isDone() ? "1" : "0") + " | " + currTask.getName() + " | " + ((tasks.Event) currTask).getStartTime() + "-" + ((tasks.Event) currTask).getEndTime() + "\n");
                } else if (currTask instanceof tasks.Deadline) {
                    taskFileWriter.write("D | " + (currTask.isDone() ? "1" : "0") + " | " + currTask.getName() + " | " + ((tasks.Deadline) currTask).getEndTime() + "\n");
                } else if (currTask instanceof tasks.Todo) {
                    taskFileWriter.write("T | " + (currTask.isDone() ? "1" : "0") + " | " + currTask.getName() + "\n");
                }
            }
            taskFileWriter.close();
        } catch (FileNotFoundException e) {
           System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("IO Exception has occurred!");
        }


    }

}
