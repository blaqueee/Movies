package com;

import com.Exceptions.ChoiceNotFoundException;
import com.Exceptions.EmptyChoiceException;

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
        System.out.print("Enter the full or part name of movie: ");
        return sc.nextLine();
    }

    public void sort(int choice){
        Comparator<Movie> cmp = null;
        switch (choice) {
            case 1:
                cmp = Comparator.comparingInt(Movie::getYear);
                break;
            case 2:
                cmp = Comparator.comparing(Movie::getName);
                break;
            case 3:
                cmp = Comparator.comparing(Movie::getDirectorName);
                break;
        }

        movies.sort(cmp);
        if (!askSortOrder())
            Collections.reverse(movies);
    }

    public int askOrderCriterion(){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\nChoose order's criterion:\n" +
                    "1 -> Sort by year\n" +
                    "2 -> Sort by name\n" +
                    "3 -> Sort by director's name\n" +
                    "Your choice: ");
            String str = sc.nextLine().replaceAll("\\s+", "");
            try {
                return checkOrderCriterion(str);
            } catch (EmptyChoiceException | NumberFormatException | ChoiceNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private boolean askSortOrder(){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\nChoose the order\n" +
                    "1 -> Ascending order (from down to up)\n" +
                    "2 -> Descending order (from up to down)\n" +
                    "---> ");
            String str = sc.nextLine().replaceAll("\\s+", "");
            try {
                return checkChoice(str) == 1;
            } catch (EmptyChoiceException | NumberFormatException | ChoiceNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int checkChoice(String str) throws EmptyChoiceException, ChoiceNotFoundException {
        if (str.equals(""))
            throw new EmptyChoiceException("Choice can't be empty!");
        int choice = Integer.parseInt(str);
        if (choice < 1 || choice > 2)
            throw new ChoiceNotFoundException("Incorrect choice!");
        return choice;
    }

    private int checkOrderCriterion(String str) throws EmptyChoiceException, ChoiceNotFoundException {
        if (str.equals(""))
            throw new EmptyChoiceException("Choice can't be empty!");
        int choice = Integer.parseInt(str);
        if (choice < 1 || choice > 3)
            throw new ChoiceNotFoundException("Incorrect choice!");
        return choice;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }
}