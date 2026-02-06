package ding.ui;

import java.util.Objects;

import ding.Ding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
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

    private Ding ding;

    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/User.jpg")));
    private final Image dingImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/Ding.jpg")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Ding instance */
    public void setDing(Ding d) {
        ding = d;
    }

    /** Shows startup errors when Ding fails to initialize. */
    public void showStartupError(String message) {
        dialogContainer.getChildren().add(
                DialogBox.getDingDialog(message, dingImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Ding's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ding.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDingDialog(response, dingImage)
        );
        userInput.clear();
    }
}

