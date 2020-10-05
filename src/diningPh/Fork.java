package diningPh;

public class Fork {
    private int forkId;
    private boolean inUse;
    private Philosopher owner;

    public Fork(int id) {
        this.forkId = id;
        this.inUse = false;
        this.owner = null;
    }

    public Philosopher getOwner() {
        return owner;
    }

    public void setOwner(Philosopher owner) {
        this.owner = owner;
    }

    public int getForkId() {
        return forkId;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public boolean isInUse() {
        return inUse;
    }
}
