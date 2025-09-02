/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tvseriesapp;

/**
 *
 * @author lab_services_student
 */
import java.util.Scanner;

public class SeriesApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Series series = new Series();

        System.out.println("LATEST SERIES - 2025");
        System.out.println("********************************");
        System.out.print("Enter (1) to launch menu or any other key to exit: ");
        String start = sc.nextLine().trim();
        if (!"1".equals(start)) {
            series.ExitSeriesApplication();
            return;
        }

        boolean running = true;
        while (running) {
            System.out.println("\nPlease select one of the following menu items:");
            System.out.println("(1) Capture a new series.");
            System.out.println("(2) Search for a series.");
            System.out.println("(3) Update series age restriction (and other details).");
            System.out.println("(4) Delete a series.");
            System.out.println("(5) Print series report - 2025.");
            System.out.println("(6) Exit Application.");
            System.out.print("Your choice: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1": series.CaptureSeries(); break;
                case "2": series.SearchSeries(); break;
                case "3": series.UpdateSeries(); break;
                case "4": series.DeleteSeries(); break;
                case "5": series.SeriesReport(); break;
                case "6": series.ExitSeriesApplication(); running = false; break;
                default:  System.out.println("Invalid choice. Please select 1-6.");
            }

            if (running) {
                System.out.print("\nEnter (1) to launch menu or any other key to exit: ");
                String again = sc.nextLine().trim();
                if (!"1".equals(again)) {
                    series.ExitSeriesApplication();
                    running = false;
                }
            }
        }
        sc.close();
    }
}
