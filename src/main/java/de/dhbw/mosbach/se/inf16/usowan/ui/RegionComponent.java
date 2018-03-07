package de.dhbw.mosbach.se.inf16.usowan.ui;

import de.dhbw.mosbach.se.inf16.usowan.game.BoardRegion;
import de.dhbw.mosbach.se.inf16.usowan.game.Vector2;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class RegionComponent extends GridPane {

    private TextField posXfield = new TextField();
    private TextField posYfield = new TextField();

    private TextField sizeXfield = new TextField();
    private TextField sizeYfield = new TextField();

    private RegionSetupView parent;

    public RegionComponent(RegionSetupView parent) {
        this.parent = parent;

        Button deleteButton = new Button("-");
        deleteButton.setOnAction(e -> this.parent.deleteRegionEntry(this));
        deleteButton.setFocusTraversable(false);

        this.add(new Label("Pos x, y"), 0, 1);
        this.add(new Label("Size x, y"), 0, 2);

        this.add(new Label("Region"), 0, 0);
        this.add(posXfield, 1,1);
        this.add(posYfield, 2, 1);
        this.add(sizeXfield, 1, 2);
        this.add(sizeYfield, 2, 2);
        this.add(deleteButton, 3, 1, 1, 2);

        this.setHgap(4);
        this.setVgap(4);
    }

    public BoardRegion getRegion() {
        return new BoardRegion(getPosition(), getSize());
    }

    private Vector2 getPosition() {
        return new Vector2(Integer.parseInt(posXfield.getText()), Integer.parseInt(posYfield.getText()));
    }

    private Vector2 getSize() {
        return new Vector2(Integer.parseInt(sizeXfield.getText()), Integer.parseInt(sizeYfield.getText()));
    }

    public void focus() {
        this.posXfield.requestFocus();
    }
}
