import java.util.ArrayList;
import java.util.Scanner;

public class Eva {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String intro = "Hello! I'm Eva \nWhat can I do for you? \n";
        String end = "Bye. Hope to see you again soon!";
        System.out.println(intro);

        ArrayList<String> userInput = new ArrayList<>();
        while (true) {
            String currInput = scanner.nextLine();
            if (currInput.equals("list")) {
                for (int i = 0; i < userInput.size(); i++) {
                    System.out.println((i + 1) + ". " + userInput.get(i));
                }
            } else if (currInput.equals("bye")) {
                break;
            } else {
                userInput.add(currInput);
                System.out.println("added: " + currInput);
            }
        }

        System.out.println(end);
        scanner.close();
    }
}
