package com.example.transport.domain;

import java.util.Objects;

public class Car extends Transport {
    private String model;
    private int seats;

    public Car() { this("Car", 120, "Generic", 4); }

    public Car(String name, int maxSpeed, String model, int seats) {
        super(name, maxSpeed);
        setModel(model);
        setSeats(seats);
    }

    public String getModel() { return model; }
    public final void setModel(String model) {
        if (model == null || model.isBlank()) throw new IllegalArgumentException("Model must be non-empty");
        this.model = model.strip();
    }

    public int getSeats() { return seats; }
    public final void setSeats(int seats) {
        if (seats < 1 || seats > 9) throw new IllegalArgumentException("seats must be in range 1..9");
        this.seats = seats;
    }

    @Override public String type() { return "Car"; }

    @Override
    public String toString() { return "%s, model='%s', seats=%d".formatted(super.toString(), model, seats); }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Car car = (Car) o;
        return seats == car.seats && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() { return Objects.hash(super.hashCode(), model, seats); }
}
