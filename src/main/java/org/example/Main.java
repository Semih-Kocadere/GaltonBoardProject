package org.example;

import org.apache.commons.cli.*;

public class Main {

    int numBins = 20; // Çivilerin sayısı
    static int numThreads = 1000; // Toplam bilye sayısı

    public static void main(String[] args) {

        // Define command-line options
        Options options = new Options();
        options.addOption("numThread", true, "Number of threads");
        options.addOption("numBins", true, "Number of bins");

        // Parse command-line arguments
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            int numThread = Integer.parseInt(cmd.getOptionValue("numThread", "1000"));
            int numBins = Integer.parseInt(cmd.getOptionValue("numBins", "20"));

            // Create bins array
            int[] bins = new int[numBins];

            // Create thread pool
            Thread[] threads = new Thread[numThread];
            for (int i = 0; i < numThread; i++) {
                threads[i] = new Thread(new GaltonBoard(bins));
                threads[i].start();
            }

            // Wait for all threads to finish
            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            // Display summary of results
            displaySummary(bins);
        } catch (ParseException e) {
            System.err.println("Error parsing command-line arguments: " + e.getMessage());
        }
    }

    private static void displaySummary(int[] bins) {
        int totalBalls = numThreads;

        System.out.println("Total balls: " + totalBalls);

        // Display bins with non-zero ball counts
        System.out.println("Bins with non-zero ball counts:");
        for (int i = 0; i < bins.length; i++) {
            System.out.println( i + ": " + bins[i]);
        }
    }
}
