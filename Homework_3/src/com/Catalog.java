package com;

import com.Exceptions.ChoiceNotFoundException;
import com.Exceptions.EmptyChoiceException;

import java.util.*;

public class Catalog {
    private ArrayList<Movie> movies = new ArrayList<>();

    public void printMovie(ArrayList<Movie> movies) {
        for (Movie movie : movies) {
            System.out.println("+-----------------------------------------------+");
            System.out.printf("| %-45s |%n", movie.getName());
            System.out.println("+-----------------+-----------------------------+");
            System.out.printf("| YEAR            | %-27s |%n", movie.getYear());
            System.out.println("+-----------------+-----------------------------+");
            System.out.printf("| DESCRIPTION     | %-27s |%n", movie.getDescription());
            System.out.println("+-----------------+-----------------------------+");
            System.out.printf("| DIRECTOR        | %-27s |%n", movie.getDirector().getFullName());
            System.out.println("+-----------------------------------------------+");
            System.out.println("|                      CAST                     |");
            System.out.println("+-----------------------------------------------+");
            System.out.println("|          NAME         |           ROLE        |");
                    System.out.println("+-----------------------+-----------------------+");
            for (Cast cast : movie.getCast()) {
                System.out.printf("| %-21s | %-21s |%n", cast.getFullName(), cast.getRole());
                System.out.println("+-----------------------+-----------------------+");
            }
            System.out.println();
        }
    }

    public void printActorMovies(ArrayList<Movie> movies, String name){
        System.out.println("+------------------------------------------------------------------------+");
        System.out.printf("| %-70s |%n", name);
        System.out.println("+------------------------------------------------------------------------+");
        System.out.println("|                    MOVIES                     |           ROLE         |");
        System.out.println("+-----------------------------------------------+------------------------+");
        for (Movie movie : movies){
            for (Cast cast : movie.getCast()){
                if (cast.getFullName().equals(name)) {
                    System.out.printf("| %-45s | %-22s |%n", movie.getName(), cast.getRole());
                    System.out.println("+-----------------------------------------------+------------------------+");
                }
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


    public void sort(int choice) {
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

    public int askOrderCriterion() {
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

    public void printMoviesWithCastsAndRoles(ArrayList<Movie> movies){
        Set<Cast> casts = new HashSet<>();
        Comparator<Cast> cmp = Comparator.comparing(Cast::getFullName);
        List<Cast> castList = new ArrayList<>();

        for (Movie movie : movies){
            castList.addAll(Arrays.asList(movie.getCast()));
        }

        for (Cast cast : castList){
            casts.add(cast);
        }

        castList = new ArrayList<>();
        for (Cast cast : casts){
            castList.add(cast);
        }

        castList.sort(cmp);

        System.out.println("+-----------------------+-----------------------+");
        System.out.println("|          NAME         |           ROLE        |");
        System.out.println("+-----------------------+-----------------------+");
        for (Cast cast : castList){
            System.out.printf("| %-21s | %-21s |%n", cast.getFullName(), cast.getRole());
            System.out.println("+-----------------------+-----------------------+");
        }
    }

    public ArrayList<Movie> searchByYear() {
        int year = askFilmYear();

        return parseListToYearMap().get(year);
    }

    public ArrayList<Movie> searchByCastName() {
        Scanner sc = new Scanner(System.in);
        String name = "";
        while (true) {
            System.out.print("Enter cast's name: ");
            name = sc.nextLine().trim();
            try {
                checkLine(name);
                break;
            } catch (EmptyChoiceException e) {
                System.out.println(e.getMessage());
            }
        }

        return parseListToCastNameMap().get(name);
    }

    public ArrayList<Movie> searchByCastNameAndPrintRoles() {
        Scanner sc = new Scanner(System.in);
        String name = "";
        while (true) {
            System.out.print("Enter cast's name: ");
            name = sc.nextLine().trim();
            try {
                checkLine(name);
                break;
            } catch (EmptyChoiceException e) {
                System.out.println(e.getMessage());
            }
        }

        if (parseListToCastNameMap().get(name) != null)
            printActorMovies(parseListToCastNameMap().get(name), name);
        else
            System.out.println("Can't find films with this cast!");
        return parseListToCastNameMap().get(name);
    }

    public ArrayList<Movie> searchByDirectorName() {
        Scanner sc = new Scanner(System.in);
        String name = "";
        while (true) {
            System.out.print("Enter director's name: ");
            name = sc.nextLine().trim();
            try {
                checkLine(name);
                break;
            } catch (EmptyChoiceException e) {
                System.out.println(e.getMessage());
            }
        }

        return parseListToDirectorNameMap().get(name);
    }

    private HashMap<String, ArrayList<Movie>> parseListToCastNameMap() {
        HashMap<String, ArrayList<Movie>> castMap = new HashMap<>();

        for (Movie m : movies) {
            for (Cast cast : m.getCast()) {
                String name = cast.getFullName();
                if (!castMap.containsKey(name)) {
                    castMap.put(name, new ArrayList<>());
                    castMap.get(name).add(m);
                } else {
                    castMap.get(name).add(m);
                }
            }
        }
        return castMap;
    }

    private HashMap<String, ArrayList<Movie>> parseListToDirectorNameMap() {
        HashMap<String, ArrayList<Movie>> directorMap = new HashMap<>();

        for (Movie m : movies) {
            String name = m.getDirectorName();
            if (!directorMap.containsKey(name)) {
                directorMap.put(name, new ArrayList<>());
                directorMap.get(name).add(m);
            } else {
                directorMap.get(name).add(m);
            }
        }
        return directorMap;
    }

    private HashMap<Integer, ArrayList<Movie>> parseListToYearMap() {
        HashMap<Integer, ArrayList<Movie>> yearMap = new HashMap<>();

        for (Movie m : movies) {
            int year = m.getYear();
            if (!yearMap.containsKey(year)) {
                yearMap.put(m.getYear(), new ArrayList<>());
                yearMap.get(year).add(m);
            } else {
                yearMap.get(year).add(m);
            }
        }
        return yearMap;
    }

    private String askFilmName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the full or part name of movie: ");
        return sc.nextLine();
    }

    private int askFilmYear() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the year of the movie: ");
            String str = sc.nextLine().replaceAll("\\+s", "");
            try {
                return checkYear(str);
            } catch (EmptyChoiceException | NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int checkYear(String str) throws EmptyChoiceException {
        if (str.equals(""))
            throw new EmptyChoiceException("Input year can't be empty!");
        return Integer.parseInt(str);
    }

    private boolean askSortOrder() {
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

    private String checkLine(String str){
        if (str.replaceAll("\\s+", "").equals(""))
            throw new EmptyChoiceException("You can't enter empty name!");
        return str;
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