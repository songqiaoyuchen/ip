package ding.ui;

import java.util.List;
import java.util.Objects;

import ding.Ding;
import ding.exceptions.DingException;
import javafx.application.Platform;
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
            this.getClass().getResourceAsStream("/images/user.jpg")));
    private final Image dingImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/ding.jpg")), 
                    112, 112, true, true);

    /** Injects the Ding instance */
    public void setDing(Ding d) {
        ding = d;
    }

    @FXML
    public void initialize() {
        sendButton.disableProperty().bind(userInput.textProperty().isEmpty());
    }

    /** Shows startup errors when Ding fails to initialize. */
    public void showStartupError(String message) {
        dialogContainer.getChildren().add(
                DialogBox.getErrorDialog(message, dingImage)
        );
    }

    /** Shows non-fatal startup warnings after initialization. */
    public void showStartupWarnings(List<String> warnings) {
        if (warnings == null || warnings.isEmpty()) {
            return;
        }
        
        for (String warning : warnings) {
            dialogContainer.getChildren().add(
                    DialogBox.getDingDialog(warning, dingImage)
            );
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Ding's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input == null || input.trim().isEmpty()) {
            dialogContainer.getChildren().add(
                DialogBox.getErrorDialog(Messages.ERROR_EMPTY_INPUT, dingImage)
            );
            userInput.clear();
            return;
        }

        try {
            String response = ding.getResponse(input);
            dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDingDialog(response, dingImage)
            );
        } catch (DingException e) {
            dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getErrorDialog(e.getMessage(), dingImage)
            );
        }
        userInput.clear();

        // fix mouse scrolling issue with auto scroll
        // @author https://github.com/NUS-CS2103-AY2526-S2/forum/issues/157
        Platform.runLater(() -> scrollPane.setVvalue(1.0));
    }
}

