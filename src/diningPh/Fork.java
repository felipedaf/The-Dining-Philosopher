package diningPh;

import java.util.ArrayList;
import java.util.List;

public class Fork {
    private int forkId;
    private boolean inUse;
    private Philosopher owner;
    public static List<Fork> available = new ArrayList();

    public Fork(int id) {
        this.forkId = id;
        this.inUse = false;
        this.owner = null;
        available.add(this);
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

    @Override
    public String toString() {
        return "Fork: " + this.forkId + " - inUse: " + this.inUse;
    }
}
