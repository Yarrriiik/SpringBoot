package com.example.transport.domain;

import java.util.Objects;

public abstract class Transport {
    private String name;    // текстовое
    private int maxSpeed;   // числовое (км/ч)

    protected Transport() { this("Unnamed", 0); }

    protected Transport(String name, int maxSpeed) {
        setName(name);
        setMaxSpeed(maxSpeed);
    }

    public String getName() { return name; }
    public final void setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name must be non-empty");
        this.name = name.strip();
    }

    public int getMaxSpeed() { return maxSpeed; }
    public final void setMaxSpeed(int maxSpeed) {
        if (maxSpeed < 0 || maxSpeed > 1200) throw new IllegalArgumentException("maxSpeed must be in range 0..1200");
        this.maxSpeed = maxSpeed;
    }

    public String type() { return "Transport"; }

    @Override
    public String toString() { return "%s{name='%s', maxSpeed=%d}".formatted(type(), name, maxSpeed); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transport that)) return false;
        return maxSpeed == that.maxSpeed &&
                Objects.equals(name, that.name) &&
                Objects.equals(this.getClass(), that.getClass());
    }

    @Override
    public int hashCode() { return Objects.hash(name, maxSpeed, this.getClass()); }
}
