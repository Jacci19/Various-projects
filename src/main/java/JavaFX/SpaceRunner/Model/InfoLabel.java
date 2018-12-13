package JavaFX.SpaceRunner.Model;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class InfoLabel extends Label {

    private final static String FONT_PATH = "JavaFX/SpaceRunner/fonts/font.ttf";
    //private final static String BACKGROUND_IMAGE = "JavaFX/SpaceRunner/ShipChooser/buttonYellow.png";

    public InfoLabel(String text) {

        setPrefWidth(600);
        setPrefHeight(400);
        setPadding(new Insets(40,40,40,40));
        setText(text);
        setWrapText(true);
        //setAlignment(Pos.CENTER);

/*
        BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE, 380, 49, false, true), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        setBackground(new Background(backgroundImage));
        System.out.println("InfoLabel NIE dziala");
*/

    }

    private void setLabelFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(new File(FONT_PATH)), 23));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 23));
            e.printStackTrace();
        }
    }

}
