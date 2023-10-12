package main;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {
    Clip clip;
    URL musicaURL[] = new URL[30];

    public Sound(){
        musicaURL[0]=getClass().getResource("/sound/background.wav");
        musicaURL[1]=getClass().getResource("/sound/Fake-natty.wav");
        musicaURL[2]=getClass().getResource("/sound/Fake-natty (mp3cut.net).wav");
    }
    public void setFile(int i,int volumeSom){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(musicaURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volumeSom);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}

