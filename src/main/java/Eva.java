import java.util.Scanner;

public class Eva {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String intro = "Hello! I'm Eva \nWhat can I do for you? \n";
        String end = "Bye. Hope to see you again soon!";
        System.out.println(intro);

        String userInput;
        while (true) {
            userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            System.out.println(userInput);
        }

        System.out.println(end);
        scanner.close();
    }
}
