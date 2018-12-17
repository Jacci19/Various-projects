package JavaFX.SpaceRunner2.Model;

//NAPIS Z PROSTOKĄTNYM TŁEM

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class SubSceneTitleLabel extends Label {

    private final static String FONT_PATH = "JavaFX/SpaceRunner/fonts/kenvector_future.ttf";
    private final static String BACKGROUND_IMAGE = "JavaFX/SpaceRunner/ShipChooser/yellowBackground.png";

    public SubSceneTitleLabel(String text) {

        this.setPrefWidth(380);
        this.setPrefHeight(49);
        this.setLayoutX(110);
        this.setLayoutY(25);
        this.setText(text);
        this.setWrapText(true);
        this.setLabelFont();
        this.setAlignment(Pos.CENTER);


        BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE, 380, 49, false, true), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);                          //2 x BGrepeat bo w osiach X i Y

        this.setBackground(new Background(backgroundImage));
    }

    private void setLabelFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(new File(FONT_PATH)), 23));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 23));
            //e.printStackTrace();
        }
    }

}
