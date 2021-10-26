package me.nort3x.property;

public class DoubleProperty extends ObjectProperty<Double> {

    public DoubleProperty(double initial) {
        super(() -> initial);
    }

    public DoubleProperty() {
        this(0);
    }

    public DoubleProperty add(double d) {
        this.andThen(x -> x + d);
        return this;
    }

    public DoubleProperty add(DoubleProperty dp) {
        this.andThen((x, y) -> x + y, dp);
        return this;
    }

    public DoubleProperty sub(double d) {
        this.andThen(x -> x - d);
        return this;
    }

    public DoubleProperty sub(DoubleProperty dp) {
        this.andThen((x, y) -> x - y, dp);
        return this;
    }

    public DoubleProperty mul(double d) {
        this.andThen(x -> x * d);
        return this;
    }

    public DoubleProperty mul(DoubleProperty dp) {
        this.andThen((x, y) -> x * y, dp);
        return this;
    }

    public DoubleProperty div(double d) {
        this.andThen(x -> x / d);
        return this;
    }

    public DoubleProperty div(DoubleProperty dp) {
        this.andThen((x, y) -> x * y, dp);
        return this;
    }

    public DoubleProperty pow(double d) {
        this.andThen(x -> Math.pow(x, d));
        return this;
    }

    public DoubleProperty pow(DoubleProperty dp) {
        this.andThen((x, y) -> Math.pow(x, y), dp);
        return this;
    }

}
