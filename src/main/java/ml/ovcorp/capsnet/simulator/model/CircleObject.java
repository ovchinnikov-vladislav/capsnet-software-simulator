package ml.ovcorp.capsnet.simulator.model;

import javafx.scene.paint.Color;

import java.util.Objects;

public class CircleObject extends DrawnObject {

    private double width;
    private double height;
    private double radius;
    private double square;

    public CircleObject(double x, double y, Color color, double radius) {
        super(x, y, color);
        this.radius = radius;
        calcAttrs();
    }

    public void setRadius(double radius) {
        this.radius = radius;
        calcAttrs();
    }

    private void calcAttrs() {
        this.width = radius * 2;
        this.height = radius * 2;
        this.square = Math.PI * Math.pow(radius, 2);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getRadius() {
        return radius;
    }

    public double getSquare() {
        return square;
    }

    @Override
    public boolean coordinatesBelong(double x, double y) {
        return x > this.getX() && y > this.getY() && x < (this.getX() + radius * 2) && y < (this.getY() + radius * 2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CircleObject that = (CircleObject) o;
        return Double.compare(that.width, width) == 0 &&
                Double.compare(that.height, height) == 0 &&
                Double.compare(that.radius, radius) == 0 &&
                Double.compare(that.square, square) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), width, height, radius, square);
    }
}
