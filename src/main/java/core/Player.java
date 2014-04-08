package core;

public class Player {

    private static final int WALLET_START_VALUE = 10000;
    private Wallet wallet;

    public Player() {
        this.wallet = new Wallet(WALLET_START_VALUE);
    }

    // Methods
    public Integer showWallet() {
        return wallet.getMoney().intValue();

    }

    public void removeMoney(Double price) {
        wallet.removeMoney(price);
    }

    public void removeMoney(int price) {
        wallet.removeMoney(Double.valueOf(price));
    }

    public void addMoney(int price) {
        wallet.addMoney(Double.valueOf(price));
    }

    // Accessor
    public Wallet getWallet() {
        return wallet;
    }

}
