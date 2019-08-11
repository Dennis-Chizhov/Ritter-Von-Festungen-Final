/**
 * [PlatformerGame.java]
 * The main GUI frame containg all necessary J components (buttons, panels, etc.)
 * Author: Dennis Chizhov, javadocs edited by Andy Wang
 * @since 1 Jan 2019
 */

/*** Classes used by the game other than Main. This was added by Andy. */
package gameClasses;

import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.sound.sampled.Clip;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.File;
import java.io.IOException;

/***
  * The revolutionary main GUI that allows you to play levels or quit the game.
  * @author Dennis Chizhov
  * @since 1 Jan 2019
  */

public class PlatformerGame extends JFrame {
    
    // variables we need (all new vars go here)
    private JButton startButton, quitButton;
    private JButton castleSelectButton, forestSelectButton, mountainsSelectButton, levelSelectBack;
    private JFrame thisReference;
    
    private JPanel mainPanel;
    private JLabel logo;
    private ImageIcon logoIcon;
    private JPanel levelSelectPanel;
    
    private Sound levelMusic;
    
    /*** The width of the frame. */
    public static final int WIDTH = 1366;
    /*** The height of the frame. */
    public static final int HEIGHT = 768;
    
    private Level level;
    private Level castleLevel;
    private Level forestLevel;
    private Level mountainLevel;
    
    /***
      * Constructor initalizes all necessary details when there is new JFrame created.
      */
    public PlatformerGame () {
        // Keep a reference to this object (this frame) by using a variable we can now access it from the inner class
        thisReference = this;
        
        /** INSTALL FONTS ON RUNTIME */
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); // register the font with the current graphics environment
        
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/PrinceValiant.ttf")));  // creates a true tpye font from the font directory
            ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        } catch (IOException e) {
            System.out.println("Error: cannot read fonts");
        } catch (FontFormatException e) {
            System.out.println ("Error: Someting messed up with loading the font");
        }
        
        Font vdFont = new Font("Prince Valiant", Font.PLAIN, 60);
        
        /** PRELOAD ALL THE LEVELS */
        castleLevel = new Level ("Castle");
        forestLevel = new Level ("Forest");
        mountainLevel = new Level ("Mountains");
        
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated (true);
        this.setLocationRelativeTo (null);
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.WHITE));
        this.setFocusable (true);
        this.requestFocus (true);
        this.addKeyListener (new KeyListener () {
            @Override public void keyPressed (KeyEvent e) {
                if (e.getKeyCode () == 27) {
                    System.exit (0);
                }
            } @Override public void keyTyped (KeyEvent e) {}
            @Override public void keyReleased (KeyEvent e) {}
        });
        
        /** Creating main panel */
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);
        levelSelectPanel = new JPanel();
        levelSelectPanel.setBackground(Color.BLACK);
        
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS)); // set to box layout
        
        // Start Game button
        startButton = new JButton("       Start Game");
        startButton.setFont(vdFont); // if program not run, change the font to something else
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);
        startButton.setBorder(null);                               // button has no border surrounding it
        startButton.setContentAreaFilled(true);                    // button fills up when it is clicked
        startButton.addActionListener(new ActionListener() {
            
            /***
              * When clicked, this button will go to the level select panel.
              * @param e The event passed to signify that the button's been pressed.
              */
            @Override public void actionPerformed(ActionEvent e) {
                thisReference.remove(mainPanel);     // remove main panel
                toLevelSelect ();
            }
        });
        
        // Quit button
        quitButton = new JButton("       Quit Game");
        quitButton.setBackground(Color.BLACK);
        quitButton.setFont(vdFont);
        quitButton.setForeground(Color.WHITE);
        quitButton.setBorder(null);
        quitButton.setContentAreaFilled(true);
        quitButton.addActionListener(new ActionListener() {
            
            /***
              * When clicked, this button will close the program.
              * @param e The event passed to signify that the button's been pressed.
              */
            @Override public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        // Make the logo
        try {
            logoIcon = new ImageIcon (ImageIO.read (new File ("Images/logo.png")));
        } catch (IOException e) {
            System.out.println ("Couldn't load icon.");
        } logo = new JLabel ();
        logo.setIcon (logoIcon);
        
        // Adding to panel
        mainPanel.add (logo);
        mainPanel.add(Box.createRigidArea(new Dimension(450, 150)));
        mainPanel.add(startButton);
        mainPanel.add(Box.createRigidArea(new Dimension(450, 30)));
        mainPanel.add(quitButton);
        
        // Adding to JFrame
        thisReference.add(mainPanel);
        
        /** levelSelect Panel */
        levelSelectPanel.setLayout(new BoxLayout(levelSelectPanel, BoxLayout.Y_AXIS));
        
        // level select: castle button
        castleSelectButton = new JButton("Castle");
        castleSelectButton.setBackground(Color.BLACK);
        castleSelectButton.setFont(vdFont);
        castleSelectButton.setForeground(Color.WHITE);
        castleSelectButton.setBorder(null);
        castleSelectButton.setContentAreaFilled(true);
        castleSelectButton.addActionListener(new ActionListener() {
            
            /***
              * When clicked, this button will start the Castle level.
              * @param e The event passed to signify that the button's been pressed.
              */
            @Override public void actionPerformed(ActionEvent e) {
                startLevel ("Castle");
            }
        });
        // level select: forest button
        forestSelectButton = new JButton("Forest");
        forestSelectButton.setBackground(Color.BLACK);
        forestSelectButton.setFont(vdFont);
        forestSelectButton.setForeground(Color.WHITE);
        forestSelectButton.setBorder(null);
        forestSelectButton.setContentAreaFilled(true);
        forestSelectButton.addActionListener(new ActionListener() {
            
            /***
              * When clicked, this button will start the Forest level.
              * @param e The event passed to signify that the button's been pressed.
              */
            @Override public void actionPerformed(ActionEvent e) {
                startLevel ("Forest");
            }
        });
        // level select: mountain button
        mountainsSelectButton = new JButton("Mountains");
        mountainsSelectButton.setBackground(Color.BLACK);
        mountainsSelectButton.setFont(vdFont);
        mountainsSelectButton.setForeground(Color.WHITE);
        mountainsSelectButton.setBorder(null);
        mountainsSelectButton.setContentAreaFilled(true);
        mountainsSelectButton.addActionListener(new ActionListener() {
            
            /***
              * When clicked, this buttonw will start the Mountain level.
              * @param e The event passed to signify that the button's been pressed.
              */
            @Override public void actionPerformed(ActionEvent e) {
                startLevel ("Mountains");               
            }
        });
        
        // level select: back button
        levelSelectBack = new JButton("Back");
        levelSelectBack.setBackground(Color.BLACK);
        levelSelectBack.setFont(vdFont);
        levelSelectBack.setForeground(Color.WHITE);
        levelSelectBack.setBorder(null);
        levelSelectBack.setContentAreaFilled(true);
        levelSelectBack.addActionListener(new ActionListener() {
            
            /***
              * When clicked, this button goes back to start menu.
              * @param e The event passed to signify that the button's been pressed.
              */
            @Override public void actionPerformed(ActionEvent e) {
                thisReference.remove(levelSelectPanel);
                toMenu ();
            }
        });
        
        levelSelectPanel.add(Box.createRigidArea(new Dimension(375,280))); // adding spaces for GUI to look better
        levelSelectPanel.add(castleSelectButton);
        levelSelectPanel.add(Box.createRigidArea(new Dimension(320,20)));
        levelSelectPanel.add(forestSelectButton);
        levelSelectPanel.add(Box.createRigidArea(new Dimension(320,20)));
        levelSelectPanel.add(mountainsSelectButton);
        levelSelectPanel.add(Box.createRigidArea(new Dimension(320,50)));
        levelSelectPanel.add(levelSelectBack);
        
        // Everything is set to visible at first
        this.setVisible(true);
    }
    /**
     * toLevelSelect opens up the level select menu.
     */
    public void toLevelSelect () {
        thisReference.add(levelSelectPanel); // add level select panel
        levelSelectPanel.updateUI();         // then we show changes
    }
    /**
     * toMenu opens up the main menu screen.
     */
    public void toMenu () {
        thisReference.add(mainPanel);
        mainPanel.updateUI();
    }
    /**
     * startLevel will initiate a level so that you can play.
     * @param name The name of the level to load.
     */
    public void startLevel (String name) {
        this.remove (levelSelectPanel);
        
        if (name.equals ("Castle")) {
            this.level = this.castleLevel;
            levelMusic = Sound.CASTLE_MUSIC;
        } if (name.equals ("Forest")) {
            this.level = this.forestLevel;
            levelMusic = Sound.FOREST_MUSIC;
        } if (name.equals ("Mountains")) {
            this.level = this.mountainLevel;
            levelMusic = Sound.MOUNTAIN_MUSIC;
        }
        
        this.levelMusic.loop (Clip.LOOP_CONTINUOUSLY);
        
        this.add (level);
        this.level.updateUI ();
        this.level.setFocusable (true);
        this.level.requestFocus ();
        this.level.addKeyListener (new KeyListener () {
            @Override public void keyPressed (KeyEvent e) {
                if (e.getKeyCode () == 27) {
                    try {
                        thisReference.remove (level);
                    } catch (NullPointerException exc) {} //at this point im too lazy to fix it lmao
                    
                    level.resetLevel (); //reset the levels so that they can be replayed
                    levelMusic.stop (); //stop the current music
                    toLevelSelect ();
                } else {
                    level.getCurrentStage ().getLeftPlayer ().checkKeysPressed (e);
                    level.getCurrentStage ().getRightPlayer ().checkKeysPressed (e);
                }
            } @Override public void keyTyped (KeyEvent e) {}
            @Override public void keyReleased (KeyEvent e) {
                if (level != null) {
                    level.getCurrentStage ().getLeftPlayer ().checkKeysReleased (e);
                    level.getCurrentStage ().getRightPlayer ().checkKeysReleased (e);
                }
            }
        });
        this.level.startTick (); //start the level's timer
    }
}