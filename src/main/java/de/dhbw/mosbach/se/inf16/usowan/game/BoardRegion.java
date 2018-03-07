package de.dhbw.mosbach.se.inf16.usowan.game;

import java.util.stream.Stream;

public class BoardRegion {
    private final Vector2 location;
    private final Vector2 size;
    private Field[] fields;
    private Board parent;

    public BoardRegion(Vector2 location, Vector2 size) {
        this.location = location;
        this.size = size;
        fields = new Field[this.size.getX() * this.size.getY()];
        for(int y = 0; y < size.getY(); y++) {
            for(int x = 0; x < size.getX(); x++) {
                fields[y * size.getX() + x] = new Field(new Vector2(this.location.getX() + x, this.location.getY() + y));
            }
        }
    }

    public boolean intersects(BoardRegion other) {
        boolean xOverlap = valueInRange(this.location.getX(), other.location.getX(), other.location.getX() + other.size.getX()) ||
                valueInRange(other.location.getX(), this.location.getX(), this.location.getX() + this.size.getX());
        boolean yOverlap = valueInRange(this.location.getY(), other.location.getY(), other.location.getY() + other.size.getY()) ||
                valueInRange(other.location.getY(), this.location.getY(), this.location.getY() + this.size.getY());
        return xOverlap && yOverlap;
    }

    public boolean pointInRegion(Vector2 point) {
        return valueInRange(point.getX(), this.location.getX(), this.location.getX() + this.size.getX()) &&
                valueInRange(point.getY(), this.location.getY(), this.location.getY() + this.size.getY());
    }

    public void toggle(Vector2 location) {
        int x = location.getX() - this.location.getX();
        int y = location.getY() - this.location.getY();
        this.fields[y * this.size.getX() + x].toggle();
    }

    public void setParent(Board board) {
        this.parent = board;
    }

    public Field getField(Vector2 location) {
        if (pointInRegion(location)) {
            int x = location.getX() - this.location.getX();
            int y = location.getY() - this.location.getY();

            return this.fields[y * this.size.getX() + x];
        }
        throw new IllegalArgumentException("Point is not in this Region: " + location);
    }

    public Vector2 getLocation() {
        return location;
    }

    public Vector2 getSize() {
        return size;
    }

    /**
     * Tests if there is exactly one liar in the region
     * @return whether the field is valid
     */
    public boolean isRegionValidLiars() {
        long numberOfLiars =  Stream.of(fields)
                .filter(Field::isNumberField)
                .filter(f -> !this.validateNumberField(f))
                .count();

        System.out.println(numberOfLiars);
        return numberOfLiars == 1;
    }

    /**
     * Tests if there are no adjacent black cells
     * @return true if there are no adjacent black cells
     */
    public boolean isRegionValidBlacks() {
        return Stream.of(fields)
                .filter(Field::isToggleField)
                .filter(Field::isSet)
                .allMatch(this::validateBlackField);
    }

    /**
     * Tests if the field is valid, whether it has exactly the amount of black fields around it as it says
     * @param field The field to test if it is valid
     * @return the validity status of the field
     */
    private boolean validateNumberField(Field field) {

        long blackFields = getNeighbourStream(field)
            .filter(Field::isToggleField)
            .filter(Field::isSet)
            .count();
        System.out.println(field.getPosition().toString() + "(" + field.getNumber() + "): " + blackFields);
        return blackFields == field.getNumber();
    }

    private boolean validateBlackField(Field blackField) {
        return getNeighbourStream(blackField)
                .filter(Field::isToggleField)
                .noneMatch(Field::isSet);
    }

    private Stream<Field> getNeighbourStream(Field field) {
        return Stream.of(
                new Vector2(field.getPosition().getX() + 1, field.getPosition().getY()),
                new Vector2(field.getPosition().getX() - 1, field.getPosition().getY()),
                new Vector2(field.getPosition().getX(), field.getPosition().getY() + 1),
                new Vector2(field.getPosition().getX(), field.getPosition().getY() - 1)
        )
                .filter(p -> p.getX() >= 0 && p.getX() < parent.getSize().getX())
                .filter(p -> p.getY() >= 0 && p.getY() < parent.getSize().getY())
                .map(parent::getField);
    }

    private boolean valueInRange(int value, int minIncl, int maxExcl) {
        return value >= minIncl && value < maxExcl;
    }


}
