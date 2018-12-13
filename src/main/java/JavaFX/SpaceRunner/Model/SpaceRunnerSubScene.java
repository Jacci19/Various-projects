package JavaFX.SpaceRunner.Model;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class SpaceRunnerSubScene extends SubScene {

    private final static String FONT_PATH = "JavaFX/SpaceRunner/fonts/font.ttf";
    private final static String BACKGROUND_IMAGE = "JavaFX/SpaceRunner/yellow_panel.png";

    private boolean isHidden;

    public SpaceRunnerSubScene() {                                                          //konstruktor
        super(new AnchorPane(), 600, 400);
        this.prefWidth(600);
        this.prefHeight(400);

        BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE, 600, 400, false, true),
                                                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        AnchorPane root2 = (AnchorPane)this.getRoot();
        root2.setBackground(new Background(image));

        isHidden = true;

        setLayoutX(1024);
        setLayoutY(180);
    }

    public void moveSubScene(){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);
        if(isHidden){
            transition.setToX(-676);                                            // jeśli jest ukryta to ma wjechać na scenę
            isHidden = false;                                                   // i przestać być ukryta
        }
        else{
            transition.setToX(0);                                               // jeśli nie jest ukryta to ma wyjechać ze sceny. Zero to położenie początkowe a nie PUW
            isHidden = true;                                                    // i się ukryć.
        }

        transition.play();
    }
}
