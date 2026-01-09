package vendingmachine.domain;

public class Balance {
    private int balance;

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void minusBalance(int amount) {
        this.balance -= amount;
    }

    public int getBalance() {
        return balance;
    }
}
