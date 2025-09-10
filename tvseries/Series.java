package com.mycompany.tvseries;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Series: holds application logic and in-memory storage.
 */
public class Series {
    private final List<SeriesModel> seriesList = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);

    // ------------ helpers (used by app and tests) ------------
    public SeriesModel findSeriesById(String id) {
        for (SeriesModel s : seriesList) {
            if (s.getSeriesId().equals(id)) return s;
        }
        return null;
    }

    public boolean isValidAge(int age) { return age >= 2 && age <= 18; }

    public void addSeries(SeriesModel s) { seriesList.add(s); }

    public boolean updateSeries(String id, String name, int age, int episodes) {
        SeriesModel s = findSeriesById(id);
        if (s == null) return false;
        s.setSeriesName(name);
        s.setSeriesAge(age);
        s.setSeriesNumberOfEpisodes(episodes);
        return true;
    }

    public boolean deleteSeries(String id) {
        SeriesModel s = findSeriesById(id);
        if (s == null) return false;
        return seriesList.remove(s);
    }

    // ------------ interactive features ------------
    public void CaptureSeries() {
        System.out.println("\nCAPTURE A NEW SERIES");
        System.out.println("******************************");

        System.out.print("Enter the series id: ");
        String id = sc.nextLine().trim();

        System.out.print("Enter the series name: ");
        String name = sc.nextLine().trim();

        int age = promptValidAge();
        int eps = promptPositiveInt("Enter the number of episodes for " + name + ": ");

        seriesList.add(new SeriesModel(id, name, age, eps));
        System.out.println("Series processed successfully!!!");
    }

    public void SearchSeries() {
        System.out.print("\nEnter the series id to search: ");
        String id = sc.nextLine().trim();
        SeriesModel s = findSeriesById(id);

        if (s == null) {
            System.out.println("\n----------------------------------------");
            System.out.println("Series with Series Id: " + id + " was not found!");
            System.out.println("----------------------------------------");
        } else {
            printSeriesRecord(s);
        }
    }

    public void UpdateSeries() {
        System.out.print("\nEnter the series id to update: ");
        String id = sc.nextLine().trim();
        SeriesModel s = findSeriesById(id);

        if (s == null) {
            System.out.println("Series with Series Id: " + id + " was not found!");
            return;
        }

        System.out.print("Enter the series name: ");
        String name = sc.nextLine().trim();
        int age = promptValidAge();
        int eps = promptPositiveInt("Enter the number of episodes: ");

        s.setSeriesName(name);
        s.setSeriesAge(age);
        s.setSeriesNumberOfEpisodes(eps);
        System.out.println("Series updated successfully!");
    }

    public void DeleteSeries() {
        System.out.print("\nEnter the series id to delete: ");
        String id = sc.nextLine().trim();
        SeriesModel s = findSeriesById(id);

        if (s == null) {
            System.out.println("Series with Series Id: " + id + " was not found!");
            return;
        }

        System.out.print("Are you sure you want to delete series " + id + " from the system? Yes (y) to delete: ");
        String ans = sc.nextLine().trim();
        if (ans.equalsIgnoreCase("y")) {
            seriesList.remove(s);
            System.out.println("\n----------------------------------------");
            System.out.println("Series with Series Id: " + id + " WAS deleted!");
            System.out.println("----------------------------------------");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    public void SeriesReport() {
        if (seriesList.isEmpty()) {
            System.out.println("\nNo series captured yet.");
            return;
        }
        int count = 1;
        for (SeriesModel s : seriesList) {
            System.out.println("\nSeries " + count++);
            System.out.println("========================================");
            System.out.println("SERIES ID: " + s.getSeriesId());
            System.out.println("SERIES NAME: " + s.getSeriesName());
            System.out.println("SERIES AGE RESTRICTION: " + s.getSeriesAge());
            System.out.println("NUMBER OF EPISODES: " + s.getSeriesNumberOfEpisodes());
            System.out.println("========================================");
        }
    }

    public void ExitSeriesApplication() {
        System.out.println("Exiting application... Goodbye!");
    }

    // ------------ private input helpers ------------
    private int promptValidAge() {
        while (true) {
            System.out.print("Enter the series age restriction: ");
            String input = sc.nextLine().trim();
            try {
                int age = Integer.parseInt(input);
                if (isValidAge(age)) return age;
                System.out.println("You have entered a incorrect series age!!!");
            } catch (NumberFormatException e) {
                System.out.println("You have entered a incorrect series age!!!");
            }
            System.out.print("Please re-enter the series age >> ");
        }
    }

    private int promptPositiveInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String in = sc.nextLine().trim();
            try {
                int n = Integer.parseInt(in);
                if (n > 0) return n;
                System.out.println("Please enter a number greater than 0.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private void printSeriesRecord(SeriesModel s) {
        System.out.println("\n----------------------------------------");
        System.out.println("SERIES ID: " + s.getSeriesId());
        System.out.println("SERIES NAME: " + s.getSeriesName());
        System.out.println("SERIES AGE RESTRICTION: " + s.getSeriesAge());
        System.out.println("SERIES NUMBER OF EPISODES: " + s.getSeriesNumberOfEpisodes());
        System.out.println("----------------------------------------");
    }
}


