package com.evgeny13.basejava.model;

import java.util.List;
import java.util.Objects;

public class Organization extends AbstractSection {

    private final List<Experience> organizations;

    public Organization(List<Experience> organizations) {
        this.organizations = organizations;
    }

    public List<Experience> getOrganizations() {
        return organizations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(organizations, that.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizations);
    }

    @Override
    public String toString() {
        return organizations.toString();
    }
}
