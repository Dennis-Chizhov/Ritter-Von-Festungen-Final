/**
 * [Sound.java]
 * This class plays music and sound effects. Create a new instance of this to play sounds.
 * Author: Dennis Chizhov
 * Started on Jan 20 2019
 */

/** The classes used by the game. */
package gameClasses;

import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;

import java.io.File;
/**
 * Creates a new instance of sound effects and music.
 * Author: Dennis Chizhov
 * @since Jan 20 2019
 */
public class Sound {
    
    private Clip clipSave;
    
    private String soundName; // this is the name of the sound that will play
    private File soundFile;
    
    /* NOTE: YOU MAY NEED TO CHANGE THE DIRECTORIES */
    /** The music playing during the castle level. */
    public static final Sound CASTLE_MUSIC = new Sound ("Sound/Music/Castle.wav");
    /** The music playing during the forest level. */
    public static final Sound FOREST_MUSIC = new Sound ("Sound/Music/Forest.wav");
    /** The music playing during the mountain level. */
    public static final Sound MOUNTAIN_MUSIC = new Sound ("Sound/Music/Mountains.wav");
    /** The death sound. */
    public static final Sound DEATH = new Sound ("Sound/Effects/Death.wav");
    /** When someone gets stabbed. */
    public static final Sound HIT = new Sound ("Sound/Effects/SwordHit.wav");
    /** The sword swooshing. */
    public static final Sound SWOOSH = new Sound ("Sound/Effects/SwordSwoosh.wav");
    
    /**
     * This constructor initalises the name of the sound with the temporary instanciated String.
     * @param name The name of the sound file.
     */
    Sound (String name) {
        this.soundName = name;
        
        this.soundFile = new File (this.soundName); // placeholder to be replaced
        
        try {
            this.clipSave = AudioSystem.getClip ();
            this.clipSave.open (AudioSystem.getAudioInputStream(this.soundFile));
        } catch (Exception e) {
            System.out.println ("Something went wrong loading the sounds.");
        }
    }
    /**
     * loop will loop a sound file.
     * @param n How long to loop it for. 
     */
    public void loop (int n) {
        this.clipSave.loop (n);
    }
    /**
     * play will play a sound file.
     * @param fileName The name of the file to play.
     */
    public void play () {
        if (!this.clipSave.isActive ()) {
            this.clipSave.setMicrosecondPosition (0);
            this.clipSave.start ();
        }
    }
    /**
     * stop will stop a current sound.
     */
    public void stop () {
        this.clipSave.stop ();
        this.clipSave.setMicrosecondPosition (0); //reset
    }
}