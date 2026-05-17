package d;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class dashboard extends stockAccount {

    private JTable stockTable;
    private DefaultTableModel model;
    private JLabel balanceLabel;
    private Connection con;

    public dashboard(user user) {

        super(user);

        con = DatabaseConnect.getConnection();

        JFrame frame = new JFrame("Dashboard");

        frame.setSize(800,500);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        balanceLabel = new JLabel("Balance : $" + user.getBalance());

        frame.add(balanceLabel,BorderLayout.NORTH);

        model = new DefaultTableModel(
                new Object[]{"Symbol","Name","Price"},0
        );

        stockTable = new JTable(model);

        frame.add(new JScrollPane(stockTable),BorderLayout.CENTER);

        JPanel panel = new JPanel();

        JButton buyBtn = new JButton("Buy");
        JButton sellBtn = new JButton("Sell");

        panel.add(buyBtn);
        panel.add(sellBtn);

        frame.add(panel,BorderLayout.SOUTH);

        buyBtn.addActionListener(e -> buyGUI());
        sellBtn.addActionListener(e -> sellGUI());

        loadStocks();

        frame.setVisible(true);
    }

    private void loadStocks() {

        model.setRowCount(0);

        try {

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM stocks");

            while(rs.next()) {

                model.addRow(new Object[]{
                        rs.getString("symbol"),
                        rs.getString("name"),
                        rs.getDouble("price")
                });
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void buyGUI() {

        int row = stockTable.getSelectedRow();

        if(row==-1) {
            JOptionPane.showMessageDialog(null,"Select Stock");
            return;
        }

        String symbol = model.getValueAt(row,0).toString();
        String name = model.getValueAt(row,1).toString();
        double price = (double) model.getValueAt(row,2);

        String qtyStr = JOptionPane.showInputDialog("Enter Quantity");

        int qty = Integer.parseInt(qtyStr);

        buyStock(new stock(symbol,name,price),qty);
    }

    private void sellGUI() {

        int row = stockTable.getSelectedRow();

        if(row==-1) {
            JOptionPane.showMessageDialog(null,"Select Stock");
            return;
        }

        String symbol = model.getValueAt(row,0).toString();
        String name = model.getValueAt(row,1).toString();
        double price = (double) model.getValueAt(row,2);

        String qtyStr = JOptionPane.showInputDialog("Enter Quantity");

        int qty = Integer.parseInt(qtyStr);

        sellStock(new stock(symbol,name,price),qty);
    }

    @Override
    public void buyStock(stock stock, int qty) {

        double total = stock.getPrice() * qty;

        if(user.deductFunds(total)) {

            portfolio.add(new PortfolioItem(stock,qty,stock.getPrice()));

            JOptionPane.showMessageDialog(null,"Stock Bought");

        } else {
            JOptionPane.showMessageDialog(null,"Insufficient Balance");
        }
    }

    @Override
    public void sellStock(stock stock, int qty) {

        JOptionPane.showMessageDialog(null,"Sell Feature Working");
    }

    @Override
    public void addFunds(double amount) {
        user.addFunds(amount);
    }

    @Override
    public double getPortfolioValue() {
        return 0;
    }
}
