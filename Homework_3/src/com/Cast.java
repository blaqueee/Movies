package com;

import java.util.Objects;

public class Cast {
    private String fullName;
    private String role;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cast that = (Cast) o;
        return role.equals(that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role);
    }
}
