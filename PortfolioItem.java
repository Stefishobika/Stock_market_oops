package d;

public class PortfolioItem {

    private stock stock;
    private int quantity;
    private double avgCost;

    public PortfolioItem(stock stock,int quantity,double avgCost) {

        this.stock = stock;
        this.quantity = quantity;
        this.avgCost = avgCost;
    }

    public stock getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAvgCost() {
        return avgCost;
    }
}
