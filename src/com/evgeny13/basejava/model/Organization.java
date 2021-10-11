package com.evgeny13.basejava.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

public class Organization {

    private final Link homePage;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String title;
    private final String description;

    public Organization(String name, String url, LocalDate startDate, LocalDate endDate, String title, String description) {
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        Objects.requireNonNull(title, "title must not be null");
        this.homePage = new Link(name, url);
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(homePage, that.homePage)
                && Objects.equals(startDate, that.startDate)
                && Objects.equals(endDate, that.endDate)
                && Objects.equals(title, that.title)
                && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, startDate, endDate, title, description);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Organization.class.getSimpleName() + "[", "]")
                .add("page=" + homePage)
                .add("startDate=" + startDate)
                .add("endDate=" + endDate)
                .add("title='" + title + "'")
                .add("description='" + description + "'")
                .toString();
    }
}
