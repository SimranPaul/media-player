package mymediaplayer;

import javax.swing.*;

public class App {
    
    
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new existingUser().setVisible(true);
            }
        });
    }
}
