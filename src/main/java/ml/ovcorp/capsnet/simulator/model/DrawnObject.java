package ml.ovcorp.capsnet.simulator.model;

import javafx.scene.paint.Color;

import java.util.Objects;

public abstract class DrawnObject {

    private double x;
    private double y;
    private Color color;

    public DrawnObject(double x, double y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public abstract boolean coordinatesBelong(double x, double y);

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DrawnObject that = (DrawnObject) o;
        return Double.compare(that.x, x) == 0 &&
                Double.compare(that.y, y) == 0 &&
                Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, color);
    }
}
