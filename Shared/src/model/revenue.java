package model;

public final class revenue {

    private static revenue INSTANCE;
    private int total;

    private revenue() {}

    public synchronized static revenue getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new revenue();
        }
        return INSTANCE;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
