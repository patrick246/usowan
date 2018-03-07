package de.dhbw.mosbach.se.inf16.usowan.ui;

import de.dhbw.mosbach.se.inf16.usowan.UsowanApplication;
import de.dhbw.mosbach.se.inf16.usowan.game.Board;
import de.dhbw.mosbach.se.inf16.usowan.game.Vector2;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class FieldSetupView {
    private GridPane grid = new GridPane();
    private Button doneButton = new Button("Done");
    public FieldSetupView(Board board, UsowanApplication application) {
        grid.setHgap(8);
        grid.setVgap(8);

        Vector2 size = board.getSize();

        for(int y = 0; y < size.getY(); y++) {
            for(int x = 0; x < size.getX(); x++) {
                FieldChoiceBox box = new FieldChoiceBox(board.getField(new Vector2(x, y)));
                this.grid.add(box, x, y);
            }
        }
        doneButton.setOnAction(e -> application.startGame());
    }

    public Scene getScene() {
        return new Scene(new VBox(grid, doneButton), 800, 600);
    }
}
