package com.example.transport.service;

import com.example.transport.domain.Transport;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransportService {
    private final List<Transport> items = new ArrayList<>();

    public void add(Transport t) {
        if (t == null) throw new IllegalArgumentException("Transport must not be null");
        items.add(t);
    }

    public Transport removeAt(int index) {
        checkIndex(index);
        return items.remove(index);
    }

    public List<Transport> listAll() {
        return List.copyOf(items);
    }

    public boolean equalsByIndex(int i1, int i2) {
        checkIndex(i1);
        checkIndex(i2);
        return items.get(i1).equals(items.get(i2));
    }

    public int size() { return items.size(); }

    private void checkIndex(int index) {
        if (index < 0 || index >= items.size()) throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    }
}
