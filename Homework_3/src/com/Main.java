package com;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Catalog catalog = Reader.readFile();

//        for (Movie movie : catalog.getMovies()){
//            System.out.println("Name: " + movie.getName());
//            System.out.println("Year: " + movie.getYear());
//            System.out.println("Description: " + movie.getDescription());
//            System.out.println("Director: ");
//            System.out.println("   Full name: " + movie.getDirector().getFullName());
//            System.out.println("Cast: ");
//            for (Cast cast : movie.getCast()){
//                System.out.println("    Full name: " + cast.getFullName());
//                System.out.println("    Role: " + cast.getRole());
//            }
//            System.out.println();
//        }

        ArrayList<Movie> movies = catalog.searchFilm();
        if (movies != null)
            catalog.printFilm(movies);
    }
}
