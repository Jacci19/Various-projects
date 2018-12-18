package JavaFX.SpaceRunner2.Model;

import javafx.geometry.Insets;
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

public class SmallInfoLabel extends Label {

    private static final String FONT_PATH = "";

    public SmallInfoLabel(String text){
        setPrefWidth(130);
        setPrefHeight(50);
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("JavaFX/SpaceRunner2/blue_info_label.png", 130,50,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        setBackground(new Background(backgroundImage));
        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10,10,10,10));
        setlabelFont();
        setText(text);
    }

    private void setlabelFont(){
        try {
            setFont(Font.loadFont(new FileInputStream(new File(FONT_PATH)), 15));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 15));
            //e.printStackTrace();
        }
    }
}