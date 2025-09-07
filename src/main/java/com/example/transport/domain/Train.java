package com.example.transport.domain;

import java.util.Objects;

public class Train extends Transport {
    private String line;
    private int carriages;

    public Train() { this("Train", 200, "Main Line", 8); }

    public Train(String name, int maxSpeed, String line, int carriages) {
        super(name, maxSpeed);
        setLine(line);
        setCarriages(carriages);
    }

    public String getLine() { return line; }
    public final void setLine(String line) {
        if (line == null || line.isBlank()) throw new IllegalArgumentException("Line must be non-empty");
        this.line = line.strip();
    }

    public int getCarriages() { return carriages; }
    public final void setCarriages(int carriages) {
        if (carriages < 1 || carriages > 40) throw new IllegalArgumentException("carriages must be in range 1..40");
        this.carriages = carriages;
    }

    @Override public String type() { return "Train"; }

    @Override
    public String toString() { return "%s, line='%s', carriages=%d".formatted(super.toString(), line, carriages); }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Train train = (Train) o;
        return carriages == train.carriages && java.util.Objects.equals(line, train.line);
    }

    @Override
    public int hashCode() { return Objects.hash(super.hashCode(), line, carriages); }
}
