package com;

import java.util.*;

public class Catalog {
    private ArrayList<Movie> movies = new ArrayList<>();


    public void printFilm(ArrayList<Movie> movies){
        for (Movie movie : movies) {
            System.out.println(movie.getName());
            System.out.println(movie.getYear());
            System.out.println(movie.getDescription());
            System.out.println(movie.getDirector().getFullName());
            for (Cast cast : movie.getCast()) {
                System.out.println("    " + cast.getFullName());
                System.out.println("    " + cast.getRole() + "\n");
            }
        }
    }

    public ArrayList<Movie> searchFilm() {
        String inputName = askFilmName();
        ArrayList<Movie> films = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getName().contains(inputName))
                films.add(movie);
        }

        return films;
    }

    private String askFilmName() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }


    public ArrayList<Movie> getMovies() {
        return movies;
    }
}