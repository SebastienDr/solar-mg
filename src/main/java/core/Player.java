package core;


import java.util.List;

public class Player {

    private static final int START_VALUE = 10000;

    private int wallet;
    private List<Transport> transports;

    public Player() {
        this.wallet = START_VALUE;
    }

    // Accessor
    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public List<Transport> getTransports() {
        return transports;
    }

    public void setTransports(List<Transport> transports) {
        this.transports = transports;
    }
}
