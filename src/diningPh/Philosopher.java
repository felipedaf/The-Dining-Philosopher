package diningPh;

public class Philosopher {
    private PhilosopherState state;
    private int number;
    private Fork usableFork1;
    private Fork usableFork2;

    public Philosopher(int number, Fork fork1, Fork fork2) {
        this.state = PhilosopherState.THINKING;
        this.number = number;
        this.usableFork1 = fork1;
        this.usableFork2 = fork2;
    }

    public PhilosopherState getState() {
        return state;
    }

    private void setState(PhilosopherState state) {
        this.state = state;
    }

    public void eat() {
        if(this.state.equals(PhilosopherState.HUNGRY)) {
            if(!this.usableFork1.isInUse() && !this.usableFork2.isInUse()) {
                this.usableFork2.setInUse(true);
                this.usableFork1.setInUse(true);
                this.usableFork1.setOwner(this);
                this.usableFork2.setOwner(this);
                this.setState(PhilosopherState.EATING);
            }
        }
    }

    public void finishMeal() {
        if(this.state.equals(PhilosopherState.EATING)) {
            this.usableFork2.setOwner(null);
            this.usableFork1.setOwner(null);
            this.usableFork2.setInUse(false);
            this.usableFork2.setInUse(false);
            this.setState(PhilosopherState.THINKING);
        }
    }

    @Override
    public String toString() {
        return String.format("Philosopher %d, State: %s, Forks: (%d - %d)",
                this.number, this.state.toString(), this.usableFork1.getForkId(),
                this.usableFork2.getForkId());
    }
}
