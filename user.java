package d;

public class user {

    private String username;
    private double balance;

    public user(String username,double balance) {

        this.username = username;
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance;
    }

    public void addFunds(double amount) {
        balance += amount;
    }

    public boolean deductFunds(double amount) {

        if(balance >= amount) {

            balance -= amount;
            return true;
        }

        return false;
    }
}
