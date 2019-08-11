/*
 * [Stage.java]
 * This file contains the class for a Stage object.
 * Author: Andy Wang
 * Started on 28 Dec 2018
 */

/** All classes used in the game other than Main */
package gameClasses;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * A Stage is a stage in the level: both pstages start in the middle of the Level (which is a stage) and they gradually move
 * towards the edges of the level (other stages).
 * @author Andy Wang
 * @since 28 Dec 2018
 */
public class Stage {
    private ArrayList <Entity> things = new ArrayList <Entity> (); //the things in the stage to be drawn
    private BufferedImage background; 
    private int plrLeftX, plrLeftY, plrRightX, plrRightY; //where each player spawns
    
    /** The level this stage is on. */
    protected Level level; 
    
    /**
     * This constructor initializes the stage.
     * @param mapFile The tile map to load, in .txt format.
     * @param bg The background image.
     * @param level The level to which the Stage is on.
     */
    Stage (File mapFile, BufferedImage bg, Level level) {
        this.loadMap (mapFile);
        this.background = bg;
        this.level = level;
    }
    /**
     * getBG returns the current Stage's background image.
     * @return The current Stage's background image.
     */
    public BufferedImage getBG () {
        return this.background;
    }
    /**
     * drawAll will draw everything in the stage.
     * @param g The Graphics object to be used.
     */
    public void drawAll (Graphics g) {
        for (Entity e : this.things) {
            e.draw (g);
        }
    }
    /** 
     * The add method will add an Entity to the Stage, just like adding a JComponent to a JFrame.
     * @param e The Entity to be added.
     */
    public void add (Entity e) {
        things.add (e);
    }
    /**
     * The remove method will remove something from the Stage.
     * @param n A list of names to remove.
     */
    public void remove (String...n) {
        for (String name : n) {
            for (int i = this.things.size () - 1; i > -1; i--) {
                if (this.things.get (i).name.equals (name)) {
                    this.things.remove (i);
                }
            }
        }
    }
    /**
     * The step method invokes all the Entities' step methods.
     */
    public void step () {
        for (Entity e : this.things) {
            if (e instanceof Moving) {
                ((Moving) e).step ();
            }
        }
    }
    /**
     * getContents () returns an array of everything on the stage.
     * @return An Entity[] containing all the entities on the stage.
     */
    public Entity[] getContents () {
        return this.things.toArray (new Entity[this.things.size ()]);
    }
    /**
     * resetPlayers () places the Players at their spawn locations for the current stage.
     */
    public void resetStage () {
        Player leftPlayer = this.getLeftPlayer ();
        Player rightPlayer = this.getRightPlayer ();
        Sword leftSword;
        Sword rightSword;
        
        leftPlayer.resetPlayer (plrLeftX, plrLeftY);
        rightPlayer.resetPlayer (plrRightX, plrRightY);
        
        this.remove ("Right Sword", "Left Sword");
        
        //just create new swords
        leftSword = new Sword (leftPlayer, Sprite.LEFT_SWORD, "Left Sword", this);
        rightSword = new Sword (rightPlayer, Sprite.RIGHT_SWORD, "Right Sword", this);
        
        this.add (leftSword);
        this.add (rightSword);
    }
    /**
     * getLeftPlayer returns the left player.
     * @return The left player's reference.
     */
    public Player getLeftPlayer () {
        for (Entity e : this.things ) {
            if (e.name.equals ("Left Player")) {
                return (Player) e;
            }
        } return null;
    } 
    /**
     * getRightPlayer returns the right player.
     * @return The right player's reference.
     */
    public Player getRightPlayer () {
        for (Entity e : this.things ) {
            if (e.name.equals ("Right Player")) {
                return (Player) e;
            }
        } return null;
    }
    /**
     * loadMap () loads a tile map from a text file. <b>DO NOT USE A COMMA OR SPACE TO REPRESENT A TILE.</b>
     * @param file The folder containing the map data.
     */
    public void loadMap (File file) {
        try {
            Scanner s = new Scanner (file);
            String fileName = file.getName ();
            String levelName = fileName.substring (fileName.indexOf ('_') + 1, fileName.indexOf ('.'));
            
            int y = 0;
            
            /* ADD LEVEL BORDERS */
            this.add (new Border (-200, 0, 200, PlatformerGame.HEIGHT, "Left Border", this));
            this.add (new Border (32 * Tile.TILE_LENGTH, 0, 200, PlatformerGame.HEIGHT, "Right Border", this));
            this.add (new Border (0, -200, PlatformerGame.WIDTH, 200, "Top Border", this)); 
            this.add (new Border (0, (int) (PlatformerGame.HEIGHT * 1.3), PlatformerGame.WIDTH, 200, "Bottom Border", this)); 
            
            /* ADD REGULAR TILES */
            while (s.hasNext ()) {
                String currentRow = s.nextLine ();
                for (int x = 0; x < currentRow.length (); x++) { //loop through the current line
                    char t = currentRow.charAt (x); //the current character
                    
                    if (levelName.equals ("Castle")) {
                        if (t == '#') {
                            this.add (new Tile (x * Tile.TILE_LENGTH, y, Sprite.CASTLE_STONE_BRICK, this));
                        } else if (t == '@') {
                            this.add (new Tile (x * Tile.TILE_LENGTH, y, Sprite.CASTLE_GROUND, this));
                        } else if (t == '$') {
                            this.add (new Tile (x * Tile.TILE_LENGTH, y, Sprite.CASTLE_CARPET, this));
                        }
                    } else if (levelName.equals ("Forest")) {
                        if (t == '@') {
                            this.add (new Tile (x * Tile.TILE_LENGTH, y, Sprite.FOREST_SOIL_TOP, this));
                        } else if (t == '#') {
                            this.add (new Tile (x * Tile.TILE_LENGTH, y, Sprite.FOREST_DEEP_SOIL, this));
                        } else if (t == 'm') {
                            this.add (new Tile (x * Tile.TILE_LENGTH, y, Sprite.FOREST_ROCK_MIDDLE, this));
                        } else if (t == 's') { //latin "sin"
                            this.add (new Tile (x * Tile.TILE_LENGTH, y, Sprite.FOREST_ROCK_LEFT, this));
                        } else if (t == 'd') { //latin "dex"
                            this.add (new Tile (x * Tile.TILE_LENGTH, y, Sprite.FOREST_ROCK_RIGHT, this));
                        } else if (t == 'r') {
                            this.add (new Tile (x * Tile.TILE_LENGTH, y, Sprite.FOREST_ROCK_WHOLE, this));
                        } else if (t == 'w') {
                            this.add (new Tile (x * Tile.TILE_LENGTH, y, Sprite.FOREST_HUT_WALL, this));
                        } else if (t == 'i') {
                            this.add (new Tile (x * Tile.TILE_LENGTH, y, Sprite.FOREST_HUT_INSIDE, this));
                        } 
                    } else if (levelName.equals ("Mountains")) {
                        if (t == 'c') {
                            this.add (new Tile (x * Tile.TILE_LENGTH, y, Sprite.MOUNTAINS_CLOUD, this));
                        } else if (t == '^') {
                            this.add (new Tile (x * Tile.TILE_LENGTH, y, Sprite.MOUNTAINS_ROCK_RIGHT_CORNER, this));
                        } else if (t == 'r') {
                            this.add (new Tile (x * Tile.TILE_LENGTH, y, Sprite.MOUNTAINS_ROCK, this));
                        } else if (t == 'l') {
                            this.add (new Tile (x * Tile.TILE_LENGTH, y, Sprite.MOUNTAINS_ROCK_LEFT_CORNER, this));
                        } else if (t == '#') {
                            this.add (new Tile (x * Tile.TILE_LENGTH, y, Sprite.MOUNTAINS_ROCK_LEFT_WALL, this));
                        } else if (t == 's') {
                            this.add (new Tile (x * Tile.TILE_LENGTH, y, Sprite.MOUNTAINS_ROCK_SINGLE, this));
                        }
                    } /* 
                     * Creating a player:
                     * Player (int x, int y, Sprite spr, String name, char up, char left, char right, char throwSwordKey, 
                     *        char highAttackKey, char medAttackKey, char lowAttackKey, char highParry, char medParry, char lowParry, Stage stage) {
                     */
                    
                    if (t == '%') {
                        Sword leftSword;
                        Player leftPlayer;
                        
                        this.plrLeftX = x * Tile.TILE_LENGTH;
                        this.plrLeftY = y;
                        
                        leftPlayer = new Player (x * Tile.TILE_LENGTH, y, Sprite.PLAYER_LEFT_IDLE, "Left Player", 'W', 'A', 'D', 'S', 'E', 
                                                 'R', 'T', 'Y', 'F', 'G', 'H', this);
                        leftSword = new Sword (leftPlayer, Sprite.LEFT_SWORD, "Left Sword", this);
                        
                        this.add (leftPlayer);
                        this.add (leftSword);
                    } else if (t == 'x') {
                        Sword rightSword;
                        Player rightPlayer;
                        
                        this.plrRightX = x * Tile.TILE_LENGTH;
                        this.plrRightY = y;
                        
                        //The right player starts flipped.
                        rightPlayer = new Player (x * Tile.TILE_LENGTH, y, Sprite.PLAYER_RIGHT_IDLE, "Right Player", (char)(38), (char)(37), (char)(39), (char)(40),  (char)(10), 
                                                  'O', 'P', '[', 'L', ';', (char)(222), this);
                        rightSword = new Sword(rightPlayer, Sprite.RIGHT_SWORD, "Right Sword", this);
                        
                        this.add (rightPlayer);
                        this.add (rightSword);
                    }
                    //STILL NEED TO ADD PLAYERS AND BORDERS       
                } y += Tile.TILE_LENGTH; //increase y value
            } s.close ();
        } catch (FileNotFoundException e) { System.out.println ("Oopsie woopsie! we made a fuckie wucki!"); } // reconsider the purpose of your existance andy
    }
}