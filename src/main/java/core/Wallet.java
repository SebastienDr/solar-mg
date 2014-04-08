package core;

public class Wallet {

    private Double money;

    public Wallet(double walletStartValue) {
        this.money = walletStartValue;
    }

    // Methods
    public void addMoney(Double value) {
        this.money = this.money + value;
    }

    public void removeMoney(Double value) {
        this.money = this.money - value;
    }

    public void emptyWallet() {
        this.money = 0.0;
    }

    // Accessors
    public Double getMoney() {
        return money;
    }

}
