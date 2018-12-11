package JavaFX.SpaceRunner.Model;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SpaceRunnerButton extends Button {

    private final String FONT_PATH = "JavaFX/SpaceRunner/font.ttf";                            //nie wczytuje się ??
    private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; " +
                                                "-fx-background-image: url('JavaFX/SpaceRunner/yellow_button_pressed.png');";
    private final String BUTTON_FREE_STYLE =    "-fx-background-color: transparent;" +
            "                                    -fx-background-image: url('JavaFX/SpaceRunner/yellow_button.png');";

    public SpaceRunnerButton(String text) {             //konstruktor

        setText(text);                      //metoda dziedziczona z klasy nadrzędnej
        setButtonFont();
        setPrefWidth(190);
        setPrefHeight(49);
        setStyle(BUTTON_FREE_STYLE);
        initializeButtonListeners();

    }


    private void setButtonFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            setFont(Font.font("Verdana", 23));
        }
    }

    private void setButtonPressedStyle(){
        setStyle(BUTTON_PRESSED_STYLE);
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 4);
    }

    private void setButtonReleasedStyle(){
        setStyle(BUTTON_FREE_STYLE);
        setPrefHeight(49);
        setLayoutY(getLayoutY() - 4);
    }

    private void initializeButtonListeners() {
        setOnMousePressed(new EventHandler<MouseEvent>() {                  //Wciśnięcie przycisku
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonPressedStyle();
                }
            }
        });
        setOnMouseReleased(new EventHandler<MouseEvent>() {                 //Puszczenie  przycisku
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonReleasedStyle();
                }
            }
        });

        setOnMouseEntered(new EventHandler<MouseEvent>() {                  //Najechanie na przycisk
            @Override
            public void handle(MouseEvent event) {
                setEffect(new DropShadow());
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {                   //Zjechanie z przycisku
            @Override
            public void handle(MouseEvent event) {
                setEffect(null);
            }
        });

    }
}


/*z repo bitClefa z lambdami
        setOnMousePressed(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                setBUTTON_PRESSED();
            }
        });

        setOnMouseReleased(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                setBUTTON_NOT_PRESSED();
            }
        });

        setOnMouseEntered(event -> setEffect(new DropShadow()));

        setOnMouseExited(event -> setEffect(null));

 */