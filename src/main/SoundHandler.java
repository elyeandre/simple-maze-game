package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
public class SoundHandler {
        private Clip clip;
        public URL[] sound = new URL[7];
        public SoundHandler() {

            sound[0] = getClass().getResource("/sounds/Die.wav");
            sound[1] = getClass().getResource("/sounds/Fruit.wav");
            sound[2] = getClass().getResource("/sounds/GameMusic.wav");
            sound[3] = getClass().getResource("/sounds/LoseMusic.wav");
            sound[4] = getClass().getResource("/sounds/MenuMusic.wav");
            sound[5] = getClass().getResource("/sounds/WinMusic.wav");
            sound[6] = getClass().getResource("/sounds/IceDestroy.wav");
        }
        public void setFile(int i) {
            try {
                AudioInputStream ais =  AudioSystem.getAudioInputStream(sound[i]);
                clip = AudioSystem.getClip();
                clip.open(ais);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        public void play() {
            clip.start();
        }
        public void stop()  { clip.stop(); }
        public void loop() {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }

}

