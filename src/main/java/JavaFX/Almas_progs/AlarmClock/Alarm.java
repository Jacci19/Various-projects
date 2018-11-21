package JavaFX.Almas_progs.AlarmClock;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.time.LocalTime;


public class Alarm extends StackPane {

    private boolean active = true;
    private LocalTime time;

    public Alarm(LocalTime time) {
        this.time = time;

        getStyleClass().add("alarm");

        Text text = new Text(time.toString());
        text.getStyleClass().add("alarm_text");

        getChildren().add(text);
        setAlignment(Pos.CENTER_LEFT);
    }

    public LocalTime getTime() {
        return time;
    }

    public boolean isActive() {
        return active;
    }

    public void report() {
        active = false;
        System.out.println("Alarm went off");
    }
}
