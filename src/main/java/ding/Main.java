package ding;

import java.io.IOException;

import ding.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Ding using FXML.
 */
public class Main extends Application {

    private Ding ding = new Ding();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDing(ding);  // inject the Ding instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
