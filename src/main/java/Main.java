import java.io.IOException;

import eva.Eva;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class to start the application.
 */
public class Main extends Application {
    private Eva eva = new Eva();

    /**
     * Main method to run the Eva program.
     *
     * @param stage Command line arguments.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setEva(eva);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
