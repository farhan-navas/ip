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
        assert stage != null : "Stage is null!";

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            assert fxmlLoader != null : "FXML loader is null!";

            AnchorPane ap = fxmlLoader.load();
            assert ap != null : "AnchorPane is null!";

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setEva(eva);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            assert false : "IOException occurred while loading FXML!" + e.getMessage();
        }
    }
}
