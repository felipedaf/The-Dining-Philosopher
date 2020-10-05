package diningPh;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Philosopher implements Runnable{
    private PhilosopherState state;
    private int number;
    private Fork usableFork1;
    private Fork usableFork2;
    private final int SECOND = 1000;
    public static Semaphore mutex1 = new Semaphore(1);
    public static Semaphore mutex2 = new Semaphore(1);

    public Philosopher(int number, Fork fork1, Fork fork2) {
        this.state = PhilosopherState.THINKING;
        this.number = number;
        this.usableFork1 = fork1;
        this.usableFork2 = fork2;

        new Thread((Runnable) this, String.format("Philosopher %d", this.number)).start();
        System.out.println(String.format("Philosopher %d is thinking.", this.number));
    }

    public PhilosopherState getState() {
        return state;
    }

    private void setState(PhilosopherState state) {
        this.state = state;
    }

    public void eat() {
        if(this.state.equals(PhilosopherState.HUNGRY)) {
            try {
                mutex1.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!this.usableFork1.isInUse() && !this.usableFork2.isInUse()) {
                this.usableFork2.setInUse(true);
                this.usableFork1.setInUse(true);
                this.usableFork1.setOwner(this);
                this.usableFork2.setOwner(this);
                this.setState(PhilosopherState.EATING);
                System.out.println(String.format(String.format("Philosopher %d is eating. fork %d fork %d",
                        this.number, this.usableFork1.getForkId(), this.usableFork2.getForkId())));
                this.wait(5);
            }
            mutex1.release();
        }
    }

    public void beHungry() {
        if(this.state.equals(PhilosopherState.THINKING)) {
            this.setState(PhilosopherState.HUNGRY);
            System.out.println(String.format("Philosopher %d is hungry.", this.number));

        }
    }

    public void finishMeal() {
        try {
            mutex2.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(this.state.equals(PhilosopherState.EATING)) {
            this.usableFork2.setOwner(null);
            this.usableFork1.setOwner(null);
            this.usableFork1.setInUse(false);
            this.usableFork2.setInUse(false);
            this.setState(PhilosopherState.THINKING);
            System.out.println(String.format("Philosopher %d is thinking.", this.number));
            this.wait(5);

        }
        mutex2.release();
    }

    private void wait(int limitTime) {
        try {
            int waitTime = new Random().ints(SECOND, limitTime * SECOND)
                    .findFirst().getAsInt();
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
           e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return String.format("Philosopher %d, State: %s, Forks: (%d - %d)",
                this.number, this.state.toString(), this.usableFork1.getForkId(),
                this.usableFork2.getForkId());
    }

    @Override
    public void run() {
        while(true) {
            this.beHungry();
            this.eat();
            this.finishMeal();
        }
    }
}
