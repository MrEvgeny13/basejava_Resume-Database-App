package com.evgeny13.basejava.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    private static final long serialVersionUID = 1L;

    private final List<String> items;

    public ListSection(String... items) {
        this(Arrays.asList(items));
    }

    public ListSection(List<String> elements) {
        Objects.requireNonNull(elements, "Elements must not be null");
        this.items = elements;
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }

    @Override
    public String toString() {
        StringBuilder content = new StringBuilder();
        for (String items : items) {
            content.append("\n" + items);
        }
        return content.toString();
    }
}
