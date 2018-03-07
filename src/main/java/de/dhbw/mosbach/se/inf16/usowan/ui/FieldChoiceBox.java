package de.dhbw.mosbach.se.inf16.usowan.ui;

import de.dhbw.mosbach.se.inf16.usowan.game.Field;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;

public class FieldChoiceBox extends ChoiceBox<String> {

    private final static ObservableList<String> options = FXCollections.observableArrayList(
            "", "0", "1", "2", "3" , "4"
    );

    public FieldChoiceBox(Field field) {
        super(options);
        this.getSelectionModel().selectFirst();

        this.getSelectionModel().selectedItemProperty().addListener((o, old, n) -> {
            switch (n) {
                case "":
                    field.setType(Field.Type.ToggleField);
                break;
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                    field.setType(Field.Type.NumberField);
                    field.setNumber(Integer.parseInt(n));
            }
        });
    }
}
