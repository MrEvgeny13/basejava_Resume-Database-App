package com.evgeny13.basejava.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

public class Experience {

    private final Link page;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String jobPosition;
    private final String description;

    public Experience(String name, String url, LocalDate startDate, LocalDate endDate, String jobPosition, String description) {
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        Objects.requireNonNull(jobPosition, "jobPosition must not be null");
        this.page = new Link(name, url);
        this.startDate = startDate;
        this.endDate = endDate;
        this.jobPosition = jobPosition;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return Objects.equals(page, that.page)
                && Objects.equals(startDate, that.startDate)
                && Objects.equals(endDate, that.endDate)
                && Objects.equals(jobPosition, that.jobPosition)
                && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, startDate, endDate, jobPosition, description);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Experience.class.getSimpleName() + "[", "]")
                .add("page=" + page)
                .add("startDate=" + startDate)
                .add("endDate=" + endDate)
                .add("jobPosition='" + jobPosition + "'")
                .add("description='" + description + "'")
                .toString();
    }
}
