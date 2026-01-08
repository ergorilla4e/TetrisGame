
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import java.io.File;

public class Music {
    public File musicPath;
    public Clip clip;

    public Music(String MusicLocation) {
        try {
            musicPath = new File(MusicLocation);
            File musicPath = new File(MusicLocation);

            if (musicPath.exists()) {
                AudioInputStream audioinput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioinput);
            }
        } catch (Exception Ex) {
            Ex.printStackTrace();
        }
    }

    void playMusic(int start, int end) {

        if (musicPath.exists()) {
        	
        	clip.setFramePosition(start);
        	clip.setLoopPoints(start, end);
        	
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
			
        } else {
            System.out.println("Traccia non trovata!!");
        }
    }

    void playEffects(int start, int end, boolean condizione) {

        	clip.setFramePosition(start);
        	clip.setLoopPoints(start, end);

            // Aggiungi un listener per rilevare la fine della riproduzione
            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        // Riproduzione terminata, riavvia il suono se necessario
                        if (condizione) {
                            clip.setFramePosition(start);
                            clip.start();
                        }else
                        	clip.stop();         
                    }
                }
            });
            // Riproduci il suono
            clip.start();
    }


    void stopMusic() {
        if (musicPath.exists()) {
            clip.stop();
        } else {
            System.out.println("Traccia non trovata!!");
        }
    }
}
