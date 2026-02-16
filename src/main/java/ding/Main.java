package ding;

import java.io.IOException;
import java.util.Objects;

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
    private static final String TITLE = "Ding";
    private static final String ICON_PATH = "/images/pearl.png";
    private static final String MAIN_WINDOW_FXML_PATH = "/view/MainWindow.fxml";
    private static final double MIN_WIDTH = 400.0;
    private static final double MIN_HEIGHT = 600.0;

    @Override
    public void start(Stage stage) {
        try {
            MainWindow controller = initStage(stage);
            initDing(controller);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MainWindow initStage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(MAIN_WINDOW_FXML_PATH));
        AnchorPane ap = fxmlLoader.load();
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);
        stage.getIcons().add(new Image(Objects.requireNonNull(
                Main.class.getResourceAsStream(ICON_PATH))));
        return fxmlLoader.<MainWindow>getController();
    }
    
    private void initDing(MainWindow controller) {
        try {
            Ding ding = new Ding();
            controller.setDing(ding);
            controller.showStartupWarnings(ding.getStartupWarnings());
        } catch (DingException e) {
            controller.showStartupError(String.format(Messages.ERROR_LOAD_TASKS, e.getMessage()));
        }
    }
}
