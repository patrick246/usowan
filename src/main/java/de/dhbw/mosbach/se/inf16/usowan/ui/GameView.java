package de.dhbw.mosbach.se.inf16.usowan.ui;

import de.dhbw.mosbach.se.inf16.usowan.game.Board;
import de.dhbw.mosbach.se.inf16.usowan.game.BoardRegion;
import de.dhbw.mosbach.se.inf16.usowan.game.Field;
import de.dhbw.mosbach.se.inf16.usowan.game.Vector2;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameView {

    private Canvas canvas;
    private Board board;

    private final int PADDING_OUTSIDE = 8;
    private final int PADDING_FIELD = 6;
    private final int FIELD_SIZE = 40;
    private final Vector2 TEXT_OFFSET = new Vector2(12, FIELD_SIZE / 2 + 8);

    public GameView(Board board) {
        this.board = board;
        Vector2 size = board.getSize();
        canvas = new Canvas(
                2 * PADDING_OUTSIDE + (2 * PADDING_FIELD + FIELD_SIZE) * size.getX(),
                2 * PADDING_OUTSIDE + (2 * PADDING_FIELD + FIELD_SIZE) * size.getY()
        );

        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent t) -> {
            if (t.getButton() == MouseButton.PRIMARY && t.getClickCount() == 1) {
                handleClick(new Vector2((int) t.getX(), (int) t.getY()));
            }
        });
        draw();
    }

    private void draw() {
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        context.setFill(Color.BLACK);
        context.setStroke(Color.GRAY);
        context.setFont(new Font(context.getFont().getName(), 28));

        Field[] fields = board.getFields();

        for (int y = 0; y < board.getSize().getY(); y++) {
            for (int x = 0; x < board.getSize().getX(); x++) {
                context.strokeRect(
                        PADDING_OUTSIDE + (2 * PADDING_FIELD + FIELD_SIZE) * x + PADDING_FIELD,
                        PADDING_OUTSIDE + (2 * PADDING_FIELD + FIELD_SIZE) * y + PADDING_FIELD,
                        FIELD_SIZE, FIELD_SIZE);

                switch (fields[y * board.getSize().getX() + x].getType()) {
                    case EmptyField:
                        context.fillText("?",
                                PADDING_OUTSIDE + (2 * PADDING_FIELD + FIELD_SIZE) * x + PADDING_FIELD + TEXT_OFFSET.getX(),
                                PADDING_OUTSIDE + (2 * PADDING_FIELD + FIELD_SIZE) * y + PADDING_FIELD + TEXT_OFFSET.getY());
                        break;
                    case NumberField:
                        context.fillText(fields[y * board.getSize().getX() + x].getNumber().toString(),
                                PADDING_OUTSIDE + (2 * PADDING_FIELD + FIELD_SIZE) * x + PADDING_FIELD + TEXT_OFFSET.getX(),
                                PADDING_OUTSIDE + (2 * PADDING_FIELD + FIELD_SIZE) * y + PADDING_FIELD + TEXT_OFFSET.getY());
                        break;
                    case ToggleField: {
                        boolean isSet = fields[y * board.getSize().getX() + x].isSet();
                        if (isSet) {
                            context.fillRect(
                                    PADDING_OUTSIDE + (2 * PADDING_FIELD + FIELD_SIZE) * x + 2 * PADDING_FIELD,
                                    PADDING_OUTSIDE + (2 * PADDING_FIELD + FIELD_SIZE) * y + 2 * PADDING_FIELD,
                                    FIELD_SIZE - 2 * PADDING_FIELD,
                                    FIELD_SIZE - 2 * PADDING_FIELD
                            );
                        } else {
                            context.strokeRect(
                                    PADDING_OUTSIDE + (2 * PADDING_FIELD + FIELD_SIZE) * x + 2 * PADDING_FIELD,
                                    PADDING_OUTSIDE + (2 * PADDING_FIELD + FIELD_SIZE) * y + 2 * PADDING_FIELD,
                                    FIELD_SIZE - 2 * PADDING_FIELD,
                                    FIELD_SIZE - 2 * PADDING_FIELD
                            );
                        }
                    }
                }
            }
        }

        context.setStroke(Color.BLACK);
        for (BoardRegion region : board.getRegions()) {
            context.strokeRect(
                    PADDING_OUTSIDE + region.getLocation().getX() * (2 * PADDING_FIELD + FIELD_SIZE),
                    PADDING_OUTSIDE + region.getLocation().getY() * (2 * PADDING_FIELD + FIELD_SIZE),
                    region.getSize().getX() * (2 * PADDING_FIELD + FIELD_SIZE),
                    region.getSize().getY() * (2 * PADDING_FIELD + FIELD_SIZE)
            );
        }

    }

    private void handleClick(Vector2 target) {
        Vector2 coords = toBoardCoords(target);
        Field field = board.getField(coords);
        if(field.isToggleField()) {
            field.toggle();
            draw();
        }
    }

    private Vector2 toBoardCoords(Vector2 coords) {
        int x = (coords.getX() - 2 * PADDING_OUTSIDE) / (2 * PADDING_FIELD + FIELD_SIZE);
        int y = (coords.getY() - 2 * PADDING_OUTSIDE) / (2 * PADDING_FIELD + FIELD_SIZE);
        return new Vector2(x, y);
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
