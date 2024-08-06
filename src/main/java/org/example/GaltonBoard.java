package org.example;

import java.util.Random;

public class GaltonBoard implements Runnable {
    public final int[] bins;
    public final Random random;

    public GaltonBoard(int[] bins) {
        this.bins = bins;
        this.random = new Random();
    }

    @Override
    public void run() {
        int position = bins.length / 2; // Start at the middle bin
        while (true) {
            synchronized (bins) {
                bins[position]++;
            }

            if (position == 0 || position == bins.length - 1) {
                break; // Stop when reaching the edge bins
            }

            // Randomly move left or right
            if (random.nextBoolean()) {
                position--;
            } else {
                position++;
            }
        }
    }
}

