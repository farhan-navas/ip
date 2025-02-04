import eva.Eva;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Represents the main window of the Eva program. Contains the user interface for the program.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Eva eva;

    private Image manImage = new Image(this.getClass().getResourceAsStream("/images/male.png"));
    private Image womanImage = new Image(this.getClass().getResourceAsStream("/images/female.png"));

    /**
     * Initializes the MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Eva object for the MainWindow.
     *
     * @param e The Eva object to be set.
     */
    public void setEva(Eva e) {
        this.eva = e;
    }

    /**
     * Handles the user input and generates a response from Eva.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = eva.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, manImage),
                DialogBox.getEvaDialog(response, womanImage)
        );
        userInput.clear();
    }

}
