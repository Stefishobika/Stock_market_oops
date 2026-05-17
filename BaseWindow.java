package d;

import javax.swing.*;

public abstract class BaseWindow extends JFrame {

    protected String username;

    public BaseWindow(String username) {
        this.username = username;
    }
}
