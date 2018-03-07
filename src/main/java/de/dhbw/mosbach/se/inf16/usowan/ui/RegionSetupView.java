package de.dhbw.mosbach.se.inf16.usowan.ui;

import de.dhbw.mosbach.se.inf16.usowan.UsowanApplication;
import de.dhbw.mosbach.se.inf16.usowan.game.BoardRegion;
import de.dhbw.mosbach.se.inf16.usowan.game.Vector2;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RegionSetupView {
    private TextField sizeX = new TextField();
    private TextField sizeY = new TextField();
    private HBox sizeHbox = new HBox(new Label("Board size x, y"), sizeX, sizeY);

    private Button addRegionBtn = new Button("Add Region");
    private List<RegionComponent> regions = new ArrayList<>();
    private VBox regionVbox = new VBox();

    private Button doneBtn = new Button("Done");

    public RegionSetupView(UsowanApplication application) {
        sizeHbox.setSpacing(4);
        regionVbox.setSpacing(8);
        doneBtn.setOnAction(e -> {application.regionSetupResult(getSize(), getRegions());});
        addRegionBtn.setOnAction((e) -> this.addRegionEntry());
        addRegionEntry();
    }

    public Scene getScene() {
        VBox vBox = new VBox(sizeHbox, regionVbox, addRegionBtn, doneBtn);
        vBox.setSpacing(8);
        return new Scene(vBox, 800, 600);
    }

    private void addRegionEntry() {
        RegionComponent component = new RegionComponent(this);
        regions.add(component);
        regionVbox.getChildren().add(component);
        component.focus();
    }

    public void deleteRegionEntry(RegionComponent component) {
        regions.remove(component);
        regionVbox.getChildren().remove(component);
    }

    private List<BoardRegion> getRegions() {
        return regions.stream().map(RegionComponent::getRegion).collect(Collectors.toList());
    }

    private Vector2 getSize() {
        return new Vector2(Integer.parseInt(sizeX.getText()), Integer.parseInt(sizeY.getText()));
    }
}
