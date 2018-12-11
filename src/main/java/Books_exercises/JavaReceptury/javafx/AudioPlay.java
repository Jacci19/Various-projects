package Books_exercises.JavaReceptury.javafx;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

// BEGIN main
/** Proste odtwarzanie pliku dźwiękowego przy użyciu JavaFX API. */
public class AudioPlay {
    public static void main(String[] args) {
        String clipName = "demo.mp3";
        Media clip = new Media(clipName);
        MediaPlayer mediaPlayer = new MediaPlayer(clip);
        mediaPlayer.play();
    }
}
// END main
