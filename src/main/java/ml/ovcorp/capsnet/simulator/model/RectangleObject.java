package ml.ovcorp.capsnet.simulator.model;

import javafx.scene.paint.Color;

import java.util.Objects;

public class RectangleObject extends DrawnObject {

    private double height;
    private double width;
    private double square;

    public RectangleObject(double x, double y, Color color, double height, double width) {
        super(x, y, color);
        this.height = height;
        this.width = width;
        calcAttrs();
    }

    private void calcAttrs() {
        this.square = height * width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
        calcAttrs();
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
        calcAttrs();
    }

    public double getSquare() {
        return square;
    }

    // TODO:
    @Override
    public boolean coordinatesBelong(double x, double y) {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RectangleObject that = (RectangleObject) o;
        return Double.compare(that.height, height) == 0 &&
                Double.compare(that.width, width) == 0 &&
                Double.compare(that.square, square) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), height, width, square);
    }
}
