/*
 * [Sprite.java]
 * File containing the class for a sprite, usually an animation.
 * Author: Andy Wang
 * Started on 28 Dec 2018
 */

/** All classes used in the game other than Main */
package gameClasses;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

/**
 * The Sprite class represents an Entity's sprite, which is used when drawing it.
 * @author Andy Wang
 * @since 28 Dec 2018
 */
public class Sprite {
    public static final Sprite INVIS = new Sprite ("Images/Tiles/barrier");
    
    public static final Sprite CASTLE_GROUND = new Sprite ("Images/Tiles/Castle/ground");
    public static final Sprite CASTLE_STONE_BRICK = new Sprite ("Images/Tiles/Castle/stone_brick");
    public static final Sprite CASTLE_CARPET = new Sprite ("Images/Tiles/Castle/carpet");
    
    public static final Sprite FOREST_DEEP_SOIL = new Sprite ("Images/Tiles/Forest/deep_soil");
    public static final Sprite FOREST_HUT_INSIDE = new Sprite ("Images/Tiles/Forest/hut_inside");
    public static final Sprite FOREST_HUT_WALL = new Sprite ("Images/Tiles/Forest/hut_wall");
    public static final Sprite FOREST_ROCK_LEFT = new Sprite ("Images/Tiles/Forest/rock_left");
    public static final Sprite FOREST_ROCK_MIDDLE = new Sprite ("Images/Tiles/Forest/rock_middle");
    public static final Sprite FOREST_ROCK_RIGHT = new Sprite ("Images/Tiles/Forest/rock_right");
    public static final Sprite FOREST_ROCK_WHOLE = new Sprite ("Images/Tiles/Forest/rock_whole");
    public static final Sprite FOREST_SOIL_TOP = new Sprite ("Images/Tiles/Forest/soil_top");
    
    public static final Sprite MOUNTAINS_CLOUD = new Sprite ("Images/Tiles/Mountains/cloud");
    public static final Sprite MOUNTAINS_ROCK = new Sprite ("Images/Tiles/Mountains/rock");
    public static final Sprite MOUNTAINS_ROCK_LEFT_CORNER = new Sprite ("Images/Tiles/Mountains/rock_left_corner");
    public static final Sprite MOUNTAINS_ROCK_LEFT_WALL = new Sprite ("Images/Tiles/Mountains/rock_left_wall");
    public static final Sprite MOUNTAINS_ROCK_RIGHT_CORNER = new Sprite ("Images/Tiles/Mountains/rock_right_corner");
    public static final Sprite MOUNTAINS_ROCK_SINGLE = new Sprite ("Images/Tiles/Mountains/rock_single");
    
    public static final Sprite PLAYER_LEFT_IDLE = new Sprite ("Images/Players/Left/idle", 1);
    public static final Sprite PLAYER_LEFT_RUN = new Sprite ("Images/Players/Left/run", 10);
    public static final Sprite PLAYER_LEFT_CROUCH = new Sprite ("Images/Players/Left/crouch");
    public static final Sprite PLAYER_LEFT_DIE = new Sprite ("Images/Players/Left/die", 7);
    public static final Sprite PLAYER_LEFT_JUMP = new Sprite ("Images/Players/Left/jump");
    public static final Sprite PLAYER_LEFT_ATTACK_HIGH = new Sprite ("Images/Players/Left/highSwing", 6);
    public static final Sprite PLAYER_LEFT_ATTACK_MID = new Sprite ("Images/Players/Left/attackMid", 6); 
    public static final Sprite PLAYER_LEFT_ATTACK_LOW = new Sprite ("Images/Players/Left/attackLow", 6);
    public static final Sprite PLAYER_LEFT_PARRY_HIGH = new Sprite ("Images/Players/Left/parryHigh");
    public static final Sprite PLAYER_LEFT_PARRY_MID = new Sprite ("Images/Players/Left/parryMid");
    public static final Sprite PLAYER_LEFT_PARRY_LOW = new Sprite ("Images/Players/Left/parryLow");
    public static final Sprite PLAYER_LEFT_THROW = new Sprite ("Images/Players/Left/highSwing", 2);
    
    public static final Sprite PLAYER_RIGHT_IDLE = new Sprite ("Images/Players/Right/idle", 1);
    public static final Sprite PLAYER_RIGHT_RUN = new Sprite ("Images/Players/Right/run", 10);
    public static final Sprite PLAYER_RIGHT_CROUCH = new Sprite ("Images/Players/Right/crouch");
    public static final Sprite PLAYER_RIGHT_DIE = new Sprite ("Images/Players/Right/die", 7);
    public static final Sprite PLAYER_RIGHT_JUMP = new Sprite ("Images/Players/Right/jump");
    public static final Sprite PLAYER_RIGHT_ATTACK_HIGH = new Sprite ("Images/Players/Right/highSwing", 6);
    public static final Sprite PLAYER_RIGHT_ATTACK_MID = new Sprite ("Images/Players/Right/attackMid", 6);
    public static final Sprite PLAYER_RIGHT_ATTACK_LOW = new Sprite ("Images/Players/Right/attackLow", 6);
    public static final Sprite PLAYER_RIGHT_PARRY_HIGH = new Sprite ("Images/Players/Right/parryHigh");
    public static final Sprite PLAYER_RIGHT_PARRY_MID = new Sprite ("Images/Players/Right/parryMid");
    public static final Sprite PLAYER_RIGHT_PARRY_LOW = new Sprite ("Images/Players/Right/parryLow");
    public static final Sprite PLAYER_RIGHT_THROW = new Sprite ("Images/Players/Right/highSwing", 2);
    
    public static final Sprite LEFT_SWORD = new Sprite ("Images/Sword/flying");
    public static final Sprite RIGHT_SWORD = new Sprite ("Images/Sword/flying"); //can't have one static var because they'll point to the same thing
    
    private File spriteFile;                              //the folder where the frames are kept
    private File[] spriteFiles;                           //a File array with the files of the frames
    
    private BufferedImage[] spriteImages;                 //an Image array with all the images of the frame
    
    private long now = System.currentTimeMillis ();
    private double animationDelay;                        //The delay between each frame of the animation in milliseconds.
    private int delay;                                    //used for animating the sprite
    private boolean isFlipped = false;                    //if the sprite is actually flipped
    private int lastIndex;
    private int width, height; //dimensions of the sprite
    private int [][] collisionDetectors; //dimensions of collision detectors (rectangles)
    
    /** The index of which frame of the animation to be drawn. */
    protected int frameIndex = 0;                    
    
    /** The speed of the sprite, in frames per second. */
    protected int fps;
    
    /** The current image being drawn. */
    protected BufferedImage currentImage;
  
    /** If the sprite should be flipped */
    protected boolean flipped = false;
    
    
    /**
     * This constructor initializes the Sprite, giving it the name of a folder containing the Sprite's frames, and FPS
     * if it's an animation.
     * @param name The name of the folder.
     * @param speed The frames per second of the sprite's animation.
     */
    Sprite (String name, int speed) {
        this.initialize (name);
        
        this.width = this.currentImage.getWidth (null);
        this.height = this.currentImage.getHeight (null);
        
        this.animationDelay = 1000 / speed;
        this.fps = speed;
    } 
    /**
     * This constructor initializes the Sprite, giving it the name of a folder containing the Sprite's frames (use this for 1-frame sprites). 
     * @param name The name of the folder.
     */
    Sprite (String name) {
        this.initialize (name);
        
        this.width = this.currentImage.getWidth (null);
        this.height = this.currentImage.getHeight (null);
        
        this.fps = 0;
    }
    /**
     * This constructor initializes the Sprite, giving it the name of a folder containing the Sprite's frames and custom dimensions. This constructor
     * should only be used for still sprites.
     * @param name The name of the folder.
     * @param width The overridden width of the sprite.
     * @param height The overridden height of the sprite.
     */
    Sprite (String name, int width, int height) {
        this.initialize (name);
        
        this.width = width;
        this.height = height;
        
        this.fps = 0;
    }
    /**
     * equals checks if two sprite objects hold the same sprite animation.
     * @param other The other sprite to compare.
     */
    @Override public boolean equals (Object other) {
        return (this.spriteFile.getName ().equals (((Sprite) other).getSpriteFile ().getName ()));
    }
    
    /**
     * getSpriteFile gets the Sprite's folder containing all its frames.
     * @return The sprite's folder.
     */
    public File getSpriteFile () {
        return this.spriteFile;
    }
    
    /**
     * getLastIndex returns the last frame index of the animation.
     * @return The last frame index of the animation.
     */
    public int getLastIndex() {
      return this.lastIndex;
    }
    /**
     * This method returns the width of the sprite.
     * @return The width of the sprite.
     */
    public int getWidth () {
        return this.width;
    }
    /**
     * This method returns the height of the sprite.
     * @return The height of the sprite.
     */
    public int getHeight () {
        return this.height;
    }
    
    /**
     * initialize initializes the basic settings for the sprite, makes overloading the constructors easier.
     * @param name THe name of the folder's name that contains all the frames.
     */
    public void initialize (String name) {
        this.spriteFile = new File (name);
        this.spriteFiles = this.spriteFile.listFiles ();
        this.spriteImages = new BufferedImage [this.spriteFiles.length];
        this.lastIndex = this.spriteFiles.length-1; 
          
        for (int i = 0; i < this.spriteImages.length; i++) {
            String frameName = name + "/" + Integer.toString (i) + ".png"; //.listFiles () may not load them in order...
            try {
                this.spriteImages[i] = ImageIO.read (new File (frameName));
            } catch (IOException e) {
                System.out.println ("Oopsie whoopsie! There was a problem loading a sprite!"); // andy you're a degenerate
            }
        } this.currentImage = this.spriteImages [this.frameIndex];
        
        //load all the collision detectors (rectangle objects)
        this.collisionDetectors = new int [this.spriteFiles.length][4];

        for (int i = 0; i <= this.lastIndex; i++) {
            this.collisionDetectors [i] = this.getTightestDimensions (this.spriteImages [i]);
        }
    }
    /**
     * flip will flip the Sprite without changing its 'flipped' member. This is called when entities change sprites.
     */
    public void flip () {
        for (int i = 0; i < this.spriteImages.length; i++) {
            BufferedImage bi = this.spriteImages [i];
            BufferedImage img = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_ARGB);
            for (int x = bi.getWidth () - 1; x > 0; x--){
                for (int y = 0; y < bi.getHeight(); y++){
                    img.setRGB (bi.getWidth() - x, y, bi.getRGB(x, y));
                }
            } this.spriteImages [i] = img;
        } 
    }
    /**
     * updateImage updates the Sprite's image and dimensions. This is only called in Entities that implement Moving.
     */
    public void updateImage () {
        /* Animate the sprite using its fps */
        long temp = System.currentTimeMillis ();
        delay += (int) (temp - this.now);
        
        if (this.fps > 0) {
            if (delay >= this.animationDelay) {
                this.frameIndex ++;
                this.frameIndex %= this.spriteImages.length; //wrap-around, prevents ArrayIndexOutOfBounds exception
                this.now = temp;
                delay = 0; //reset the delay
            } this.animationDelay = 1000 / this.fps;//update the delay, in case speed changes
        }
        
        /* Update stats */
        this.currentImage = this.spriteImages [this.frameIndex];
        this.width = this.currentImage.getWidth (null);
        this.height = this.currentImage.getHeight (null);
        
        /* Handle image orientation */
        if ((this.flipped) && (!this.isFlipped)) { //if it should be flipped but isn't
            this.flip ();
            this.isFlipped = true;
        } else if ((!this.flipped) && (this.isFlipped)) { //if it shouldn't be flipped but is
            this.flip ();
            this.isFlipped = false;
        }
    }
    /**
     * getTightestDimensionsForCurrentFrame returns the collision detector for the current frame of the sprite.
     * @return The dimensions of the collision detector for the current frame of the sprite.
     */
    public int[] getTightestDimensionsForCurrentFrame () {
        return this.collisionDetectors [this.frameIndex];
    }
    /*
     * getTightestDimensions returns the rectangle whose dimensions bound the opaque pixels of the current frame of the sprite.
     * Said rectangle must reach all the way to the bottom of the image.
     * @param bi The image (frame of sprite) on which to calculate the tightest bounding rectangle.
     * @return The dimensions of the rectangle bounding all the opaque pixels (essentially trimmed).
     */
    private int[] getTightestDimensions (BufferedImage bi) {
        int xDis = getLeftOpaqueX (bi); //x displacement from origin
        int yDis = getHighestOpaqueY (bi); //y displacement from origin
        int width = getRightOpaqueX (bi) - xDis;
        int height = bi.getHeight () - yDis; //rectangle must reach bottom of image

        return new int[] {xDis, yDis, width, height};
    }
    /*
     * getHighestOpaqueY returns the y coordinate of the topmost opaque pixel in an image.
     * @param bi The image to use.
     * @return The y coordinate of the topmost opaque pixel in an image. Returns 0 if nothing is found.
     */
    private int getHighestOpaqueY (BufferedImage bi) {
        for (int yy = 0; yy < bi.getHeight (); yy++) {
            for (int xx = 0; xx < bi.getWidth (); xx++) {
                if (!isTransparent (bi, xx, yy)) { //find topmost opaque
                    return yy;
                }
            }
        } return 0;           
    }
    /*
     * getLeftOpaqueX returns the x coordinate of the leftmost opaque pixel in an image.
     * @param bi The image to use.
     * @return The x coordinate of the leftmost opaque pixel in an image. 0 if nothing is found.
     */
    private int getLeftOpaqueX (BufferedImage bi) {
        for (int xx = 0; xx < bi.getWidth (); xx++) {
            for (int yy = 0; yy < bi.getHeight (); yy++) {
                if (!isTransparent (bi, xx, yy)) {
                    return xx;
                }
            }
        } return 0;   
    }
    /*
     * getRightOpaqueX returns the x coordinate of the rightmost opaque pixel in an image.
     * @param bi The image to use.
     * @return The x coordinate of the rightmost opaque pixel in an image. 0 if nothing is found.
     */
    private int getRightOpaqueX (BufferedImage bi) {
        for (int xx = bi.getWidth () - 1; xx > -1; xx--) {
            for (int yy = 0; yy < bi.getHeight (); yy++) {
                if (!isTransparent (bi, xx, yy)) {
                    return xx;
                }
            }
        } return 0;   
    }
    /*
     * isTransparent sees if a pixel in an image is transarent.
     * @param bi The image to use.
     * @param x The x coordinate to check.
     * @param y The y coordinate to check.
     */
    private boolean isTransparent (BufferedImage bi, int x, int y) {
        return (bi.getRGB (x, y) >> 24) == 0x00; //bitwise operation to see if pixel is transparent. Stolen from stack overflow.
    }

}