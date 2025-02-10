import java.io.IOException;

import eva.Eva;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for using Eva using FXML.
 */
public class Main extends Application {
    private final Eva eva = new Eva();

    /**
     * Starts the application.
     *
     * @param stage The stage to start the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setEva(eva);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Error starting Eva!");
        }
    }
}
