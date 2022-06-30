package com;

public class Movie {
    private String name;
    private int year;
    private String description;
    private Director director;
    private Cast[] cast;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Director getDirector() {
        return director;
    }

    public String getDirectorName(){
        return director.getFullName();
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Cast[] getCast() {
        return cast;
    }

    public void setCast(Cast[] cast) {
        this.cast = cast;
    }

}
