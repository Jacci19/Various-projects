package Books_exercises.JavaReceptury.graphics;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/** 
 * Prosty program odtwarzający plik dźwiękowy.
 */
// BEGIN main
public class AudioPlay {

    static String defSounds[] = {
        "/audio/test.wav",
        "/music/midi/Beet5th.mid",
    };

    public static void main(String[] av) {
        if (av.length == 0)
            main(defSounds);
        else for (String a : av) {
            System.out.println("Odtwarzam " + a);
            try {
                URL snd = AudioPlay.class.getResource(a);
                if (snd == null) {
                    System.err.println("Nie można pobrać zasobu: "  + a);
                    continue;
                }
                AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(snd);
                final Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (Exception e) {
                System.err.println(e);
            }
         }
    }
}
// END main
