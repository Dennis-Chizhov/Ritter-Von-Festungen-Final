/*
 * [Entity.java]
 * Contains class representing some sort of thing.
 * Author: Andy Wang
 * Started on 28 Dec 2018
 */

/** All classes used in the game other than Main */
package gameClasses;

import java.awt.Graphics;

import java.awt.Rectangle;

/**
 * Entity represents any sort of thing, with an x and y position and dimensions.
 * @author Andy Wang
 * @since 28 Dec 2018
 */
public abstract class Entity {
    private Rectangle rect; //for collision
    
    /* the sprite of the entity */
    protected Sprite sprite; 
    
    /** The stage that the Entity is drawn on. */
    protected Stage stage;
    /** Optional name (or tag) for the Entity. */
    protected String name = ""; 
    /** The x-coordinate of the Entity. */
    protected double x;
    /** The y-coordinate of the Entity. */
    protected double y;
    
    /**
     * This constructor initializes the Entity without giving it a name (aka a tag). If no name is given, its class name
     * will be used.
     * @param x The x coordinate (top-left).
     * @param y The y coorindate (top-left).
     * @param spr The Sprite to be used with the Entity.
     * @param stage The Stage that the Entity is on.
     */
    Entity (int x, int y, Sprite spr, Stage stage) {
        this.x = x;
        this.y = y;
        this.name = this.getClass ().getName ();
        this.sprite = spr;
        this.rect = new Rectangle ((int) x, (int) y, this.sprite.getWidth (), this.sprite.getHeight ());
        this.stage = stage;
    } 
    /**
     * This constructor initializes the Entity, but with a custom name.
     * @param x The x coordinate (top-left).
     * @param y The y coorindate (top-left).
     * @param spr The Sprite to be used with the Entity.
     * @param name The custom name to give it.
     * @param stage The Stage that the Entity is on.
     */
    Entity (int x, int y, Sprite spr, String name, Stage stage) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.sprite = spr;
        this.rect = new Rectangle ((int) x, (int) y, this.sprite.getWidth (), this.sprite.getHeight ());
        this.stage = stage;
    }
    /**
     * This constructor initialises a Sword.
     * @param player, to take in their coordinates. We use this to draw the x and y displacement for the sword sprite.
     * @param spr The Sprite used by the sword; this is a single folder with multiple frames, each indicating direction.
     * @param stage The stage on which the Sword is drawn.
     */
    Entity (Player player, Sprite spr, Stage stage) {
        this.x = player.x;
        this.y = player.y;
        this.sprite = spr;
        this.stage = stage;
        this.rect = new Rectangle ((int) x, (int) y, this.sprite.getWidth (), this.sprite.getHeight ());
    }
    /**
     * getRect returns the Entity's Rectangle, or bounding box to be used for collison detection.
     * @return The Entity's Rectangle, or boundinf box.
     */
    public Rectangle getRect () {
        return this.rect;
    }
    /**
     * updateRect () updates an Entity's Rectangle object. This is called only in an Entity's step method, if it implements Moving.
     */
    public void updateRect () {
        int[] rectDimensions = this.sprite.getTightestDimensionsForCurrentFrame ();
        this.rect = new Rectangle ((int) (this.x + rectDimensions [0]), (int) (this.y + rectDimensions [1]), rectDimensions [2], rectDimensions [3]);
    }
    /**
     * placeMeeting checks if the current Entity will collide with another Entity at the given coordinates.
     * @param xT The x coordinate to check for.
     * @param yT The y corodinate to check for.
     * @param n The names of the Entities to check for.
     * @return Whether the other Entity is <b>intersecting</b> the current one. This will return false if they are
     * simply side by side. Returns false if the Entity with the given name does not exist.
     */
    public boolean placeMeeting (double xT, double yT, String...n) {
        Entity[] contents = this.stage.getContents ();
        for (String name : n) {
            /* Wondering why I don't use Stage's search method? That returns the FIRST instance of an Entity with the same name.
             * This needs to search ALL the entities. */
            for (Entity e : contents) {
                if ((e.name.equals (name)) || (e.getClass ().getName ().equals (name))) {
                    Rectangle tempRect = new Rectangle ((int) Math.round (xT), (int) Math.round (yT), this.sprite.getWidth (), this.sprite.getHeight ());
                    
                    if (tempRect.intersects (e.getRect ())) {
                        return true;
                    }
                } 
            }
        } return false;
    }
    /**
     * changeSprite changes the Entity's sprite and flips it accordingly. Does nothing if newSprite is already the Entity's sprite.
     * @param newSprite The sprite to change it to.
     */
    public void changeSprite (Sprite newSprite) { 
        if (!this.sprite.equals (newSprite)) {
            boolean flip = this.sprite.flipped;
            this.sprite.frameIndex = 0;
            this.sprite = newSprite;
            this.sprite.flipped = flip;
        }
    }
    /**
     * The draw method draws the current Entity.
     * @param g The Graphics object to use.
     */
    public void draw (Graphics g) {
        g.drawImage (this.sprite.currentImage, (int) Math.round (this.x), (int) Math.round (this.y), this.sprite.getWidth (), this.sprite.getHeight (), null);
    }
}