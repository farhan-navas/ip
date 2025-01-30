import exceptions.TaskException;
import tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;
import storage.Handler;
import ui.Ui;

public class Eva {
    public static ArrayList<Task> taskList  = new ArrayList<>();
    private Ui ui;
    private Handler handler;

    public Eva() {
        this.ui = new Ui();
        this.handler = new Handler();
    }

    public void run() throws TaskException {
        Eva.taskList = this.handler.loadTasks();
        this.ui.showWelcome();
        Scanner scanner = new Scanner(System.in);

        this.ui.handleInput(scanner, this.taskList);
        this.ui.showEnd();
        Handler.saveTasks(this.taskList);
    }

    public static void main(String[] args) throws TaskException {
        new Eva().run();
    }
}
