package diningPh;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int philosophersNumber = 5;
    public static void main(String[] args) {
	// write your code here
        List<Philosopher> philosophers = new ArrayList();
        List<Fork> forks = new ArrayList();

        for(int f = 0; f < philosophersNumber; f++) {
            forks.add(new Fork(f));
        }

        for(int p = 0; p < philosophersNumber; p++) {
            Fork fork1 = forks.get(p % philosophersNumber);
            Fork fork2 = forks.get((p + 1) % philosophersNumber);
            philosophers.add(new Philosopher(p + 1, fork1, fork2));
        }

    }
}
