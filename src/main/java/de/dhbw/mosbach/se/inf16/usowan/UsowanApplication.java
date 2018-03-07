package de.dhbw.mosbach.se.inf16.usowan;

import de.dhbw.mosbach.se.inf16.usowan.game.Board;
import de.dhbw.mosbach.se.inf16.usowan.game.BoardRegion;
import de.dhbw.mosbach.se.inf16.usowan.game.Vector2;
import de.dhbw.mosbach.se.inf16.usowan.ui.FieldSetupView;
import de.dhbw.mosbach.se.inf16.usowan.ui.GameView;
import de.dhbw.mosbach.se.inf16.usowan.ui.RegionSetupView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.List;

public class UsowanApplication extends Application {

    private Stage stage;

    private List<BoardRegion> regions;
    private Vector2 size;

    private Board board;

    public UsowanApplication() {
        /*BoardRegion[] regions = new BoardRegion[2];
        regions[0] = new BoardRegion(new Vector2(0, 0), new Vector2(2, 4));
        regions[1] = new BoardRegion(new Vector2(2, 0), new Vector2(2, 4));
        Board board = new Board(new Vector2(4,4), regions);
        board.getField(new Vector2(0, 0)).setType(Field.Type.NumberField);
        board.getField(new Vector2(0, 0)).setNumber(3);
        board.getField(new Vector2(0, 1)).setType(Field.Type.ToggleField);
        game = new GameView(board);*/
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        RegionSetupView setupView = new RegionSetupView(this);
        Scene scene = setupView.getScene();
        stage.setScene(scene);
        stage.show();
    }

    public void regionSetupResult(Vector2 size, List<BoardRegion> regions) {
        this.regions = regions;
        this.size = size;


        board = new Board(this.size, this.regions.toArray(new BoardRegion[0]));
        FieldSetupView fieldSetupView = new FieldSetupView(board, this);
        this.stage.setScene(fieldSetupView.getScene());
    }

    public void startGame() {
        GameView gameView = new GameView(board);
        Scene scene = new Scene(new AnchorPane(gameView.getCanvas()), 800, 600);
        this.stage.setScene(scene);
    }
}
