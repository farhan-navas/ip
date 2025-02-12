package eva.storage;

import java.util.ArrayList;
import java.util.Comparator;

import eva.exceptions.TaskException;
import eva.tasks.Deadline;
import eva.tasks.Event;
import eva.tasks.Task;
import eva.tasks.Todo;

/**
 * Represents the sorter that sorts the tasks in the task list.
 */
public class Sorter {
    /**
     * Sorts the tasks in the task list based on the task name and whether the task is done.
     * Sorts the tasks in this order:
     * 1. Todos
     *      1.1. Done/Not Done
     *      1.2. Name (Alphabetical)
     * 2. Deadlines
     *     2.1. End Time (Earliest to Latest)
     *     2.2. Done/Not Done
     *     2.3. Name (Alphabetical)
     * 3. Events
     *    3.1. Start Time (Earliest to Latest)
     *    3.2. End Time (Earliest to Latest)
     *    3.3. Done/Not Done
     *    3.4. Name (Alphabetical)
     *
     *
     * @param tasks The list of tasks to be sorted.
     * @return The sorted list of tasks.
     */
    public static ArrayList<Task> sortTasks(ArrayList<Task> tasks) throws TaskException {
        assert tasks != null : "Task list is null!";

        tasks.sort(Comparator.comparingInt(task -> getTaskPriority((Task) task))
                .thenComparing(task -> ((Task) task).isDone() ? 1 : 0)
                .thenComparing(task -> (task instanceof Deadline)
                        ? ((Deadline) task).getEndTime()
                        : null, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(task -> (task instanceof Event)
                        ? ((Event) task).getStartTime()
                        : null, Comparator.nullsLast(Comparator.naturalOrder()))
        );

        return tasks;
    }

    /**
     * Returns the priority of the task based on the task type.
     *
     * @param task The task to be checked.
     * @return The priority of the task.
     */
    private static int getTaskPriority(Task task) {
        if (task instanceof Todo) {
            return 1;
        } else if (task instanceof Deadline) {
            return 2;
        } else if (task instanceof Event) {
            return 3;
        } else {
            return 0;
        }
    }
}
