package ma.najid.mementodp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TestMementoFX extends Application {

    private TextArea theArticle = new TextArea();
    private Button saveButton = new Button("Save");
    private Button undoButton = new Button("Undo");
    private Button redoButton = new Button("Redo");

    private CareTaker careTaker = new CareTaker();
    private Originator originator = new Originator();

    private int savedFile = 0;
    private int currentFile = -1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        theArticle.setPrefRowCount(20);
        theArticle.setPrefColumnCount(40);

        saveButton.setOnAction(e -> save());
        undoButton.setOnAction(e -> undo());
        redoButton.setOnAction(e -> redo());

        undoButton.setDisable(true);
        redoButton.setDisable(true);

        VBox layout = new VBox(10, new Label("Article:"), theArticle, saveButton, undoButton, redoButton);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Memento Design Pattern - JavaFX");
        primaryStage.show();
    }

    private void save() {
        if (!theArticle.getText().isEmpty()) {
            String text = theArticle.getText();
            originator.set(text);
            careTaker.addMemento(originator.storeInMemento());

            savedFile = careTaker.size();
            currentFile = savedFile - 1;

            undoButton.setDisable(currentFile <= 0);
            redoButton.setDisable(true);
        }
    }

    private void undo() {
        if (currentFile > 0) {
            currentFile--;
            String text = originator.restoreFromMemento(careTaker.getMemento(currentFile));
            theArticle.setText(text);
            redoButton.setDisable(false);
            undoButton.setDisable(currentFile <= 0);
        }
    }

    private void redo() {
        if (currentFile < savedFile - 1) {
            currentFile++;
            String text = originator.restoreFromMemento(careTaker.getMemento(currentFile));
            theArticle.setText(text);
            undoButton.setDisable(false);
            redoButton.setDisable(currentFile >= savedFile - 1);
        }
    }
}
