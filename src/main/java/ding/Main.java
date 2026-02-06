package ding;

import java.io.IOException;

import ding.exceptions.DingException;
import ding.ui.MainWindow;
import ding.ui.Messages;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Ding using FXML.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Ding");
            stage.getIcons().add(new Image(Main.class.getResourceAsStream("/images/pearl.png")));
            MainWindow controller = fxmlLoader.<MainWindow>getController();
            try {
                Ding ding = new Ding();
                controller.setDing(ding);
            } catch (DingException e) {
                controller.showStartupError(String.format(Messages.ERROR_LOAD_TASKS, e.getMessage()));
            }
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
