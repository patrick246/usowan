package de.dhbw.mosbach.se.inf16.usowan.game;

public class Field {
    public enum Type {
        EmptyField,
        NumberField,
        ToggleField
    }

    private Type type = Type.EmptyField;
    private final Vector2 position;
    private Integer number;
    private boolean state;

    public Field(Vector2 position) {
        this.state = false;
        this.number = null;
        this.position = position;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        if(type == Type.NumberField)
            this.number = number;
    }

    public void toggle() {
        if(type == Type.ToggleField) {
            this.state = !this.state;
        }
    }

    public boolean isSet() {
        return state;
    }

    public boolean isNumberField() {
        return type == Type.NumberField;
    }

    public boolean isToggleField() {
        return type == Type.ToggleField;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
