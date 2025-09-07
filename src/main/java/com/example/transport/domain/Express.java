package com.example.transport.domain;

import java.util.Objects;

public class Express extends Train {
    private String serviceClass;
    private int priorityLevel;

    public Express() { this("Express Train", 300, "Express Line", 6, "Express", 3); }

    public Express(String name, int maxSpeed, String line, int carriages, String serviceClass, int priorityLevel) {
        super(name, maxSpeed, line, carriages);
        setServiceClass(serviceClass);
        setPriorityLevel(priorityLevel);
    }

    public String getServiceClass() { return serviceClass; }
    public final void setServiceClass(String serviceClass) {
        if (serviceClass == null || serviceClass.isBlank()) throw new IllegalArgumentException("serviceClass must be non-empty");
        this.serviceClass = serviceClass.strip();
    }

    public int getPriorityLevel() { return priorityLevel; }
    public final void setPriorityLevel(int priorityLevel) {
        if (priorityLevel < 1 || priorityLevel > 5) throw new IllegalArgumentException("priorityLevel must be in range 1..5");
        this.priorityLevel = priorityLevel;
    }

    @Override public String type() { return "Express"; }

    @Override
    public String toString() { return "%s, serviceClass='%s', priority=%d".formatted(super.toString(), serviceClass, priorityLevel); }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Express that = (Express) o;
        return priorityLevel == that.priorityLevel && Objects.equals(serviceClass, that.serviceClass);
    }

    @Override
    public int hashCode() { return Objects.hash(super.hashCode(), serviceClass, priorityLevel); }
}
