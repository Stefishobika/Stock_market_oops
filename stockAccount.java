package d;

import java.util.ArrayList;
import java.util.List;

public abstract class stockAccount {

    protected user user;
    protected List<PortfolioItem> portfolio;

    public stockAccount(user user) {

        this.user = user;
        portfolio = new ArrayList<>();
    }

    public abstract void buyStock(stock stock,int qty);

    public abstract void sellStock(stock stock,int qty);

    public abstract void addFunds(double amount);

    public abstract double getPortfolioValue();
}
