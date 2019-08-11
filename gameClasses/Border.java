/*
 * [Border.java]
 * File containing the Border class.
 * Author: Andy Wang
 * Started on Jan 10 2018
 */

/** Classes used for the game. */
package gameClasses;

/**
 * Border represents the level boundaries, preventing the players from leaving the stage.
 * @author Andy Wang
 * @since 10 Jan 2019
 */
public class Border extends Entity {
    Border (int x, int y, int width, int height, String name, Stage stage) {
        super (x, y, new Sprite ("Images/Tiles/barrier", width, height), name, stage);
    }
}