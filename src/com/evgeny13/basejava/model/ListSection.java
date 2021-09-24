package com.evgeny13.basejava.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {

    private final List<String> elements;

    public ListSection(List<String> elements) {
        Objects.requireNonNull(elements, "Elements must not be null");
        this.elements = elements;
    }

    public List<String> getElements() {
        return elements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elements);
    }

    @Override
    public String toString() {
        return elements.toString();
    }
}
