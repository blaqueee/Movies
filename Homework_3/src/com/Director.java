package com;

public class Director implements Comparable<Movie>{
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public int compareTo(Movie o) {
        return this.fullName.compareTo(o.getDirector().getFullName());
    }
}