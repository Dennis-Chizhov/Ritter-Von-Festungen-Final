/*
 * [Sword].java
 * A sword that the player uses to kill or protect themselves with.
 * Author: Andy Wang, coordinates by Dennis Chizhov
 * Started on 17th Jan
 */

/** Classes used for the game. */
package gameClasses;

public class Sword extends Entity implements Moving {
    
    /* x and y displacement is whatever the distance is from the leftmost and the upmost dimensions.
     * displacement from the player sprite to make it look like the sword is in the hand
     */
    private static final int THROW_VEL = 1000; //how fast the sword goes when being thrown in pixels per second
    private static final int THROW_FPS = 10; //how fast the sword is animated when being thrown
    private int xDis, yDis;
    private Player player;
    private String playerToCheck;        // the other player
    
    private boolean flying = false; //if the sword is being thrown 
    
    private double xVel = 0, yVel = 0;
    
    /**
     * This constructor initialises a sword weapon.
     * @param player, to take in their coordinates. We use this to draw the x and y displacement for the sword sprite.
     * @param spr The sprite of the sword.
     * @param name The name of the sword.
     * @param stage Which stage the sword is drawn on.
     */
    Sword (Player player, Sprite spr, String name, Stage stage) {
        super (player, spr, stage);
        this.player = player;
        this.name = name;
        this.calculateDisplacement();
        
        try {
            this.playerToCheck = this.player.name.equals ("Left Player") ? "Right Player" : "Left Player"; // if left player then right player else left player
        } catch (NullPointerException e) {} //sometimes it just happens....
    } 
    /*
     * calculatDisplacement finds by how many pixels the sword sprite should be displaced.
     */
    private void calculateDisplacement () { 
        if ((this.player.sprite.equals (Sprite.PLAYER_LEFT_IDLE)) || (this.player.sprite.equals (Sprite.PLAYER_RIGHT_IDLE))) {
            this.sprite.frameIndex = 0;
            if (this.player.sprite.frameIndex == 0) {
                this.xDis = 80;
                this.yDis = 25;
            }
            if ((this.player.sprite.frameIndex == 1) || (this.player.sprite.frameIndex == 3)) { 
                this.xDis = 71;
                this.yDis = 24;
            }
            if (this.player.sprite.frameIndex == 2) {
                this.xDis = 70;
                this.yDis = 26;
            }    
        }
        else if ((this.player.sprite.equals (Sprite.PLAYER_LEFT_JUMP)) || (this.player.sprite.equals (Sprite.PLAYER_RIGHT_JUMP))) {
            this.sprite.frameIndex = 7;
            this.xDis = 60;
            this.yDis = 5;
        }
        else if ((this.player.sprite.equals (Sprite.PLAYER_LEFT_CROUCH)) || (this.player.sprite.equals (Sprite.PLAYER_RIGHT_CROUCH))) {
            this.sprite.frameIndex = 7;
            this.xDis = 30;
            this.yDis = 50;
        }
        else if ((this.player.sprite.equals (Sprite.PLAYER_LEFT_RUN)) || (this.player.sprite.equals (Sprite.PLAYER_RIGHT_RUN))) {
            this.sprite.frameIndex = 0;
            if (this.player.sprite.frameIndex == 0) {    
                this.xDis = 80;
                this.yDis = 26;
            }
            else if (this.player.sprite.frameIndex == 1) { 
                this.xDis = 79;
                this.yDis = 23;
            }
            else if (this.player.sprite.frameIndex == 2) {
                this.xDis = 81;
                this.yDis = 20;
            }
            else if (this.player.sprite.frameIndex == 3) {
                this.xDis = 79;
                this.yDis = 23;
            }
        }
        else if ((this.player.sprite.equals (Sprite.PLAYER_LEFT_DIE)) || (this.player.sprite.equals (Sprite.PLAYER_RIGHT_DIE))) {
            this.player = null;
        }
        else if ((this.player.isThrowing ()) && ((this.player.sprite.equals (Sprite.PLAYER_LEFT_THROW)) || (this.player.sprite.equals (Sprite.PLAYER_RIGHT_THROW)))) {
                if (this.player.sprite.frameIndex == 0) { 
                    this.sprite.frameIndex = 5;
                    this.xDis = 19;
                    this.yDis = -40;
                }
                else if (this.player.sprite.frameIndex == 1) { 
                    this.sprite.frameIndex = 6;
                    this.xDis = 22;
                    this.yDis = -50;
                }
                else if (this.player.sprite.frameIndex == 2) {
                    this.sprite.frameIndex = 7;
                    this.xDis = 58;
                    this.yDis = -43;
                }
                else if (this.player.sprite.frameIndex == 3) {
                    this.yDis = -40;
                    this.flying = true;
                    this.player = null;
                }
        }
        else if ((this.player.sprite.equals (Sprite.PLAYER_LEFT_ATTACK_HIGH)) || (this.player.sprite.equals (Sprite.PLAYER_RIGHT_ATTACK_HIGH))) {
            if (this.player.sprite.frameIndex == 0) { 
                this.sprite.frameIndex = 5;
                this.xDis = -12;
                this.yDis = -40;
            }
            else if (this.player.sprite.frameIndex == 1) { 
                this.sprite.frameIndex = 6;
                this.xDis = 22;
                this.yDis = -50;
            }
            else if (this.player.sprite.frameIndex == 2) {
                this.sprite.frameIndex = 7;
                this.xDis = 58;
                this.yDis = -43;
            }
            else if (this.player.sprite.frameIndex == 3) {
                this.sprite.frameIndex = 0;
                this.xDis = 76;
                this.yDis = 25;
            }
            else if (this.player.sprite.frameIndex == 4) { 
                this.sprite.frameIndex = 0;
                this.xDis = 78;
                this.yDis = 35;
            }
        }
        else if ((this.player.sprite.equals (Sprite.PLAYER_LEFT_ATTACK_MID)) || (this.player.sprite.equals (Sprite.PLAYER_RIGHT_ATTACK_MID))) {
            if ((this.player.sprite.frameIndex == 0) || (this.player.sprite.frameIndex == 6)) { 
                this.sprite.frameIndex = 4;
                this.xDis = -15;
                this.yDis = 30;
            }
            else if ((this.player.sprite.frameIndex == 1) || (this.player.sprite.frameIndex == 5)) { 
                this.sprite.frameIndex = 3;
                this.xDis = -5;
                this.yDis = 55;
            }
            else if ((this.player.sprite.frameIndex == 2) || (this.player.sprite.frameIndex == 4)) {
                this.sprite.frameIndex = 2;
                this.xDis = 25;
                this.yDis = 63;
            }
            else if (this.player.sprite.frameIndex == 3) { 
                this.sprite.frameIndex = 0;
                this.xDis = 106;
                this.yDis = 25;
            }
        }
        else if ((this.player.sprite.equals (Sprite.PLAYER_LEFT_ATTACK_LOW)) || (this.player.sprite.equals (Sprite.PLAYER_RIGHT_ATTACK_LOW))) {
            if (this.player.sprite.frameIndex == 0) {  
                this.sprite.frameIndex = 4;
                this.xDis = 22;
                this.yDis = 55;
            }
            else if ((this.player.sprite.frameIndex == 1) || (this.player.sprite.frameIndex == 5)) { 
                this.sprite.frameIndex = 3;
                this.xDis = 25;
                this.yDis = 53;
            }
            else if ((this.player.sprite.frameIndex == 2) || (this.player.sprite.frameIndex == 4)) { 
                this.sprite.frameIndex = 1;
                this.xDis = 83;
                this.yDis = 70;
            }
            else if (this.player.sprite.frameIndex == 3) { 
                this.sprite.frameIndex = 0;
                this.xDis = 124;
                this.yDis = 34;
            }
        }
        else if ((this.player.sprite.equals (Sprite.PLAYER_LEFT_PARRY_HIGH)) || (this.player.sprite.equals (Sprite.PLAYER_LEFT_PARRY_HIGH))) {
            this.sprite.frameIndex = 3;
            this.xDis = 0;
            this.yDis = 0;
        }
        else if ((this.player.sprite.equals (Sprite.PLAYER_LEFT_PARRY_MID)) || (this.player.sprite.equals (Sprite.PLAYER_LEFT_PARRY_MID))) {
            this.sprite.frameIndex = 3;
            this.xDis = 23;
            this.yDis = 32;
        }
        else if ((this.player.sprite.equals (Sprite.PLAYER_LEFT_PARRY_LOW)) || (this.player.sprite.equals (Sprite.PLAYER_LEFT_PARRY_LOW))) {
            this.sprite.frameIndex = 3;
            this.xDis = 21;
            this.yDis = 63;
        }
        
        // check if the sprite is flipped
        /*
         *   if swordsprite is not flipped but the player sprite is, then flip the sword sprite 
         *       make xDis negative and subtract the sword sprite's width 
         *   if the swordsprite is flipped but the player is not, then flip the sword sprite 
         *       make xDis positive and add the sword sprite's width
         */
        if (this.player != null) {
            if ((!this.sprite.flipped) && (this.player.sprite.flipped)) {
                this.sprite.flipped = true;
            }
            else if ((this.sprite.flipped) && (!this.player.sprite.flipped)) {
                this.sprite.flipped = false; 
            }
            
            if (this.sprite.flipped) { //putting this here because everytime this is called xDis gets set to positive, so this will turn it back to negative
                this.xDis = this.player.sprite.getWidth () - this.xDis - this.sprite.getWidth ();
            } 
        }
    }
    /**
     * The step method updates the sword's movement for every frame of the program. 
     */
    @Override public void step() {
        double xVelPPF, yVelPPF; //you thought all the math was over, didn't you? Haha! They return! Cower in fear!
        double dt = this.stage.level.getDeltaTime ();
        
        if (this.flying) { //if flying through the air because it was thrown
            this.sprite.fps = THROW_FPS;
            this.xVel = this.sprite.flipped ? -THROW_VEL : THROW_VEL;
            xVelPPF = this.xVel * dt;
            
            if (this.placeMeeting (this.x + xVelPPF, this.y, playerToCheck)) {
              Player otherPlayer = this.playerToCheck.equals ("Left Player") ? this.stage.getLeftPlayer () : this.stage.getRightPlayer ();
              if (!otherPlayer.midParrying ()) {
                otherPlayer.kill ();
                Sound.HIT.play ();
              }
                
                this.flying = false;
                xVelPPF = 0;
            } if (this.placeMeeting (this.x + xVelPPF, this.y, "gameClasses.Tile", "gameClasses.Border")) {
                this.flying = false;
                xVelPPF = 0;
            }
            
            //if, for some reason, you throw the sword directly into the left border at point blank range...
            if (this.placeMeeting (this.x + xVelPPF, this.y, "Left Border")) {
              this.x = 0;
            } //now do the same thing but for the right border
            if (this.placeMeeting (this.x + xVelPPF, this.y, "Right Border")) {
              this.x = PlatformerGame.WIDTH - this.sprite.getWidth ();
            }
            
            this.x += xVelPPF;
        } else if (this.player == null) { //if dropped because it hit something
            this.sprite.fps = 0;
            this.yVel += Level.GRAVITY * dt;
            yVelPPF = this.yVel * dt;
            
            this.sprite.frameIndex = 0;
            
            /* HANDLE Y COLLISIONS (becuase this is when the sword is dropping, ie going DOWNWARDS) */ 
            if (this.placeMeeting (this.x, this.y + yVelPPF, "gameClasses.Tile", "gameClasses.Border")) { //if moving vertically will collide with a Tile
                /* PERFECT PIXEL COLLISION */
                while (!this.placeMeeting (this.x, (int) (this.y + Math.signum (this.yVel)), "gameClasses.Tile", "gameClasses.Border")) {
                    this.y += (int) Math.signum (this.yVel); //it keeps moving by 1 pixel up or down until it is BESIDE the Tile
                    //it loops until the Player WILL collide (intersect) with the Tile, resulting in the Player stopping BESIDE it
                } this.yVel = 0; //stop moving vertically if collided
                yVelPPF = 0;
            }
            
            /* IF, FOR WHATEVER REASON THE SWORD IS STUCK INSIDE SOMETHING, THIS WILL MOVE IT */
            if (this.placeMeeting (this.x, this.y, "gameClasses.Tile", "gameClasses.Border")) { //if currently stuck
                int yMoveUp = 0, yMoveDown = 0;
                
                while (this.placeMeeting (this.x, this.y + yMoveDown, "gameClasses.Tile", "gameClasses.Border")) { //check how far down to move
                    yMoveDown++;
                } while (this.placeMeeting (this.x, this.y + yMoveUp, "gameClasses.Tile", "gameClasses.Border")) { //check up
                    yMoveUp--;
                } this.y += Math.min (Math.abs (yMoveUp), yMoveDown) == Math.abs (yMoveUp) ? yMoveUp : yMoveDown; //take the shortest path
            } 
            
            //check if a player picks up the dropped sword
            if (this.placeMeeting (this.x, this.y, "Left Player")) {
                if (!this.stage.getLeftPlayer ().hasSword ()) {
                    this.player = this.stage.getLeftPlayer ();
                    this.playerToCheck = "Right Player";
                    this.player.pickUpSword ();
                }
            } else if (this.placeMeeting (this.x, this.y, "Right Player")) {
                if (!this.stage.getRightPlayer ().hasSword ()) {
                    this.player = this.stage.getRightPlayer ();
                    this.playerToCheck = "Left Player";
                    this.player.pickUpSword ();
                }
            }
            
            this.y += yVelPPF;
        } else { //must mean a player is holding on to the sword
            Player otherPlayer = this.playerToCheck.equals ("Left Player") ? this.stage.getLeftPlayer () : this.stage.getRightPlayer ();
            this.sprite.fps = 0;
            this.calculateDisplacement(); // check every step
            
            try { //once the sword is thrown, this.player is set to null IN THIS SCOPE!! afterward, this scope is not run.
                this.x = this.player.x + xDis;
                this.y = this.player.y + yDis;
                
                /* MELEE COMBAT */
                if (this.placeMeeting (this.x, this.y, this.playerToCheck)) {
                    
                    /* SEPARATED INTO MULTIPLE IF STATEMENTS FOR READABILITY, I KNOW THESE COULD ALL BE IN ONE */
                    if ((this.player.highAttacking ()) && (!otherPlayer.highParrying ())) { //high
                        otherPlayer.kill ();
                        Sound.HIT.play ();
                    } if ((this.player.midAttacking ()) && (!otherPlayer.midParrying ())) { //mid 
                        otherPlayer.kill ();
                        Sound.HIT.play ();
                    } if ((this.player.lowAttacking ()) && (!otherPlayer.lowParrying ())) { //low
                        otherPlayer.kill ();
                        Sound.HIT.play ();
                    }
                }
            } catch (NullPointerException e) {}
        } this.sprite.updateImage (); //update the sprite
        this.updateRect ();
    }
}
