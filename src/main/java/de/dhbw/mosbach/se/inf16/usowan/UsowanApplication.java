package de.dhbw.mosbach.se.inf16.usowan;

import de.dhbw.mosbach.se.inf16.usowan.game.Board;
import de.dhbw.mosbach.se.inf16.usowan.game.BoardRegion;
import de.dhbw.mosbach.se.inf16.usowan.game.Field;
import de.dhbw.mosbach.se.inf16.usowan.game.Vector2;
import de.dhbw.mosbach.se.inf16.usowan.ui.GameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UsowanApplication extends Application {

    private GameView game;

    public UsowanApplication() {
        BoardRegion[] regions = new BoardRegion[2];
        regions[0] = new BoardRegion(new Vector2(0, 0), new Vector2(2, 4));
        regions[1] = new BoardRegion(new Vector2(2, 0), new Vector2(2, 4));
        Board board = new Board(new Vector2(4,4), regions);
        board.getField(new Vector2(0, 0)).setType(Field.Type.NumberField);
        board.getField(new Vector2(0, 0)).setNumber(3);
        board.getField(new Vector2(0, 1)).setType(Field.Type.ToggleField);
        game = new GameView(board);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new AnchorPane(game.getCanvas()), 800, 600);
        stage.setScene(scene);
        stage.show();
    }
}
