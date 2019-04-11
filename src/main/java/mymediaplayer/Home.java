package mymediaplayer;

import java.io.File;


import javax.swing.*;
import javax.swing.JButton;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.SimpleAttributeSet;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class Home extends JFrame {

    private File listOfFiles[];

    Home() {
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(3,1));

        JPanel videosPane = new JPanel();
        videosPane.setLayout(new GridBagLayout());
        videosPane.setBackground(new java.awt.Color(179, 128, 255));
        GridBagConstraints c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        File folder = new File("./src/main/java/mymediaplayer/Assets/");
        listOfFiles = folder.listFiles();
        Font font = new  Font("", Font.BOLD, 18);
        JButton button;
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() && getFileExtension(listOfFiles[i]).equals("mp4")) {
              button = new JButton(listOfFiles[i].getName());
              button.setFocusPainted(false);
              button.setBorderPainted(false);
              button.setContentAreaFilled(false);
              button.setFont(font);
              button.setSize(1,3);
              videosPane.add(button,c);
              c.gridy+=1;
              button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(int i=0; i < Home.this.listOfFiles.length; i++){
                        if(((JButton)e.getSource()).getText().equals(Home.this.listOfFiles[i].getName())){
                            new Player(Home.this.listOfFiles[i].getAbsolutePath());
                        }
                    }
                    Home.this.setVisible(false);
                }
            });
            } 
          }
        JTextPane textPane = new JTextPane();
        Font textFont = new Font(Font.SANS_SERIF, Font.BOLD, 20);
        textPane.setText("Pick A Video");
        textPane.setFont(textFont);
        textPane.setSize(40,40);
        textPane.setEditable(false);
        textPane.setBackground(new java.awt.Color(179, 128, 255));
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        contentPane.setBackground(new java.awt.Color(179, 128, 255));
        contentPane.add(textPane,BorderLayout.CENTER);
        contentPane.add(videosPane, BorderLayout.SOUTH);
        this.add(contentPane);
        this.setBounds(100, 100, 600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
}

