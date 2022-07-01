package com;

import com.Exceptions.ChoiceNotFoundException;
import com.Exceptions.EmptyChoiceException;
import com.Reader.Reader;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Catalog catalog = null;

        catalog = doAction(catalog);
    }

    private static Catalog doAction(Catalog catalog){
        while (true) {
            switch (askAction()) {
                case 0:
                    catalog = Reader.readFile();
                    break;
                case 1:
                    if (catalog != null)
                        catalog.printMovie(catalog.getMovies());
                    else
                        System.out.println("Read JSON File!!!");
                    break;
                case 2:
                    if (catalog != null) {
                        ArrayList<Movie> movies = catalog.searchFilm();
                        if (movies != null)
                            catalog.printMovie(movies);
                        else
                            System.out.println("Can't find movies with this name!");
                    } else
                        System.out.println("Read JSON File!!!");
                    break;
                case 3:
                    if (catalog != null)
                        catalog.sort(catalog.askOrderCriterion());
                    else
                        System.out.println("Read JSON File!!!");
                    break;
                case 4:
                    if (catalog != null) {
                        ArrayList<Movie> movies = catalog.searchByCastName();
                        if (movies != null)
                            catalog.printMovie(movies);
                        else
                            System.out.println("Can't find films of this cast!");
                    } else
                        System.out.println("Read JSON File!!!");
                    break;
                case 5:
                    if (catalog != null) {
                        ArrayList<Movie> movies = catalog.searchByDirectorName();
                        if (movies != null)
                            catalog.printMovie(movies);
                        else
                            System.out.println("Can't find films of this director!");
                    } else
                        System.out.println("Read JSON File!!!");
                    break;
                case 6:
                    if (catalog != null) {
                        ArrayList<Movie> movies = catalog.searchByYear();
                        if (movies != null)
                            catalog.printMovie(movies);
                        else
                            System.out.println("Can't find films of this year!");
                    } else
                        System.out.println("Read JSON File!!");
                    break;
                case 7:
                    if (catalog != null)
                        catalog.searchByCastNameAndPrintRoles();
                    else
                        System.out.println("Read JSON File!!!");
                    break;
                case 8:
                    if (catalog != null)
                        catalog.printMoviesWithCastsAndRoles(catalog.getMovies());
                    else
                        System.out.println("Read JSON File!!!");
                    break;
            }
        }
    }

    private static int askAction(){
        Scanner sc = new Scanner(System.in);
        while (true){
            printActions();
            System.out.print("Enter your choice: ");
            String str = sc.nextLine().replaceAll("\\s+", "");
            try {
                return checkAction(str);
            } catch (EmptyChoiceException | NumberFormatException | ChoiceNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static int checkAction(String str) throws EmptyChoiceException, ChoiceNotFoundException {
        if (str.equals(""))
            throw new EmptyChoiceException("Choice can't be empty!");
        int choice = Integer.parseInt(str);
        if (choice < 0 || choice > 8)
            throw new ChoiceNotFoundException("Incorrect choice!");
        return choice;
    }
    private static void printActions(){
        System.out.println("""
                Actions:
                0 -> Read JSON file
                1 -> Print all movies
                2 -> Find movies by name
                3 -> Sort movies
                4 -> Search movies by cast's full name
                5 -> Search movies by director's full name
                6 -> Search movies by year
                7 -> Search movies by cast's full name and print roles
                8 -> Print all casts and their roles
                """);
    }
}
