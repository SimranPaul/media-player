package mymediaplayer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

public class Player extends JFrame{
    
    static final long serialVersionUID=0;
    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
    
    private int state=0;
    
    private JButton pauseButton;
    private JButton rewindButton;
    private JButton skipButton;
    
    public Player(String arg) {
        new NativeDiscovery().discover();
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        contentPane.add(mediaPlayerComponent, BorderLayout.CENTER);
        
        JPanel controlsPane = new JPanel();
        pauseButton = new JButton();
        rewindButton = new JButton();
        skipButton = new JButton();
        try {
            File inp = new File("src/main/java/mymediaplayer/Assets/pause.png");
            Image img = ImageIO.read(inp);
            pauseButton.setIcon(new ImageIcon(img.getScaledInstance(19, 19,Image.SCALE_DEFAULT)));
            inp = new File("src/main/java/mymediaplayer/Assets/rewind.png");
            img = ImageIO.read(inp);
            rewindButton.setIcon(new ImageIcon(img.getScaledInstance(15, 15,Image.SCALE_DEFAULT)));
            inp = new File("src/main/java/mymediaplayer/Assets/forward.png");
            img = ImageIO.read(inp);
            skipButton.setIcon(new ImageIcon(img.getScaledInstance(15, 15,Image.SCALE_DEFAULT)));
        } catch (Exception e) {
            ;
        }
        
        
        rewindButton.setOpaque(false);
        rewindButton.setFocusPainted(false);
        rewindButton.setBorderPainted(false);
        rewindButton.setContentAreaFilled(false);
        rewindButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        skipButton.setOpaque(false);
        skipButton.setFocusPainted(false);
        skipButton.setBorderPainted(false);
        skipButton.setContentAreaFilled(false);
        skipButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        pauseButton.setOpaque(false);
        pauseButton.setFocusPainted(false);
        pauseButton.setBorderPainted(false);
        pauseButton.setContentAreaFilled(false);
        pauseButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        
        controlsPane.add(rewindButton);
        controlsPane.add(pauseButton);
        controlsPane.add(skipButton);
        contentPane.add(controlsPane, BorderLayout.SOUTH);
        
        
        this.setContentPane(contentPane);
        this.setVisible(true);
        
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = 1-state;
                try {
                    if(state==0){
                        File inp = new File("src/main/java/mymediaplayer/Assets/pause.png");
                        Image img = ImageIO.read(inp);
                        pauseButton.setIcon(new ImageIcon(img.getScaledInstance(19, 19,Image.SCALE_DEFAULT)));
                        
                    }
                    else{
                        
                        File inp = new File("src/main/java/mymediaplayer/Assets/play.png");
                        Image img = ImageIO.read(inp);
                        Player.this.pauseButton.setIcon(new ImageIcon(img.getScaledInstance(19, 19,Image.SCALE_DEFAULT)));
                        
                    }
                } catch (Exception ex) {
                    ;
                }
                mediaPlayerComponent.getMediaPlayer().pause();
            }
        });
        
        rewindButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayerComponent.getMediaPlayer().skip(-10000);
            }
        });
        
        skipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayerComponent.getMediaPlayer().skip(10000);
            }
        });
        
        this.setBounds(100, 100, 600, 400);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        
        mediaPlayerComponent.getMediaPlayer().playMedia(arg);
    }
}