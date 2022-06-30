package com;

import com.Exceptions.ChoiceNotFoundException;
import com.Exceptions.EmptyChoiceException;
import com.Reader.Reader;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Catalog catalog = Reader.readFile();

        catalog = doAction(catalog);
    }

    private static Catalog doAction(Catalog catalog){
        while (true) {
            switch (askAction()) {
                case 1:
                    print(catalog);
                    break;
                case 2:
                    ArrayList<Movie> movies = catalog.searchFilm();
                    if (movies != null)
                        catalog.printFilm(movies);
                    else
                        System.out.println("Can't find movies with this name!");
                    break;
                case 3:
                    catalog.sort(catalog.askOrderCriterion());
                    break;
            }
            return catalog;
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
        if (choice < 1 || choice > 3)
            throw new ChoiceNotFoundException("Incorrect choice!");
        return choice;
    }
    private static void printActions(){
        System.out.println("""
                Actions:
                1 -> Print all movies
                2 -> Find movies by name
                3 -> Sort movies""");
    }

    private static void print(Catalog catalog){
        for (Movie movie : catalog.getMovies()){
            System.out.println("Name: " + movie.getName());
            System.out.println("Year: " + movie.getYear());
            System.out.println("Description: " + movie.getDescription());
            System.out.println("Director: ");
            System.out.println("   Full name: " + movie.getDirector().getFullName());
            System.out.println("Cast: ");
            for (Cast cast : movie.getCast()){
                System.out.println("    Full name: " + cast.getFullName());
                System.out.println("    Role: " + cast.getRole());
            }
            System.out.println();
        }
    }
}
