package d;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class form {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> showLoginPage());
    }

    public static void showLoginPage() {

        JFrame frame = new JFrame("Login - Stock Simulator");
        frame.setSize(380, 270);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        JLabel title = new JLabel("Stock Simulator Login", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setBounds(50, 10, 270, 25);
        frame.add(title);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(30, 50, 100, 25);
        frame.add(userLabel);

        JTextField userText = new JTextField();
        userText.setBounds(130, 50, 200, 25);
        frame.add(userText);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(30, 90, 100, 25);
        frame.add(passLabel);

        JPasswordField passText = new JPasswordField();
        passText.setBounds(130, 90, 200, 25);
        frame.add(passText);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(120, 130, 120, 30);
        frame.add(loginBtn);

        JLabel status = new JLabel("", SwingConstants.CENTER);
        status.setBounds(40, 170, 300, 25);
        frame.add(status);

        loginBtn.addActionListener(e -> {

            String username = userText.getText();
            String password = new String(passText.getPassword());

            if(validateLogin(username,password)) {

                JOptionPane.showMessageDialog(frame,"Login Successful");
                frame.dispose();

                new dashboard(new user(username,0));

            } else {
                status.setText("Invalid Username or Password");
            }
        });

        frame.setVisible(true);
    }

    private static boolean validateLogin(String username,String password) {

        try {

            Connection con = DatabaseConnect.getConnection();

            String sql = "SELECT * FROM user_data WHERE username=? AND password=?";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1,username);
            pst.setString(2,password);

            ResultSet rs = pst.executeQuery();

            return rs.next();

        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
