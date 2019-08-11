/*
 * [Moving,java]
 * This file contains the Moving interface.
 * Author: Andy Wang
 * Started on 28 Dec 2018
 */

/** All classes used in the game other than Main */
package gameClasses;

/**
 * The Moving interface signifies that an Entity does indeed move through each step.
 * @author Andy Wang
 * @since 28 Dec 2018
 */
public interface Moving {
    /** The Entity moves or changes every step of the game. */
    public void step ();
}