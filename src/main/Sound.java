package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    //Sounds Effects/Music provided by RyiSnow on Youtube
    Clip clip;
    URL soundURL[] = new URL[30]; //stores file paths to the sound files

    public Sound() {
        soundURL[0] = getClass().getResource("/sounds/BlueBoyAdventure.wav"); //game theme song (WILL BE REPLACED)
        soundURL[1] = getClass().getResource("/sounds/powerUp.wav");
        soundURL[2] = getClass().getResource("/sounds/unlock.wav");
        soundURL[3] = getClass().getResource("/sounds/coin.wav");
        soundURL[4] = getClass().getResource("/sounds/fanfare.wav");
    }

    //
    public void setFile(int soundIndex) {
        try { //open audio file
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[soundIndex]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    //Plays current audio file
    public void play() {
        clip.start();
    }

    //loops the current audio file
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    //Stops the current audio file
    public void stop() {
        clip.stop();
    }
}
