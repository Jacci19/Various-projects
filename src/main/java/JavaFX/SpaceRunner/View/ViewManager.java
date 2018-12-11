package JavaFX.SpaceRunner.View;

import JavaFX.SpaceRunner.Model.SpaceRunnerButton;
import JavaFX.SpaceRunner.Model.SpaceRunnerSubScene;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ViewManager {

    private static final int WIDTH = 1024, HEIGHT = 768;
    private final static int MENU_BUTTONS_START_X = 100;
    private final static int MENU_BUTTONS_START_Y = 150;

    public static AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    List<SpaceRunnerButton> menuButtons;

    public ViewManager(){
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createButtons();
        createBackground();
        createLogo();                                                                               //tytuł gry

        SpaceRunnerSubScene subScene = new SpaceRunnerSubScene();                                   //żółte pole
        subScene.setLayoutX(200);
        subScene.setLayoutY(100);
        mainPane.getChildren().add(subScene);

    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void addMenuButton(SpaceRunnerButton button){
        button.setLayoutX(MENU_BUTTONS_START_X);
        button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 100);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    private void createButtons(){
        createStartButton();
        createScoreButton();
        createHelpButton();
        createCreditsButton();
        createExitButton();
    }

    private void createStartButton(){
        SpaceRunnerButton startButton = new SpaceRunnerButton("PLAY");
        addMenuButton(startButton);
    }
    private void createScoreButton(){
        SpaceRunnerButton scoreButton = new SpaceRunnerButton("SCORES");
        addMenuButton(scoreButton);
    }
    private void createHelpButton(){
        SpaceRunnerButton helpButton = new SpaceRunnerButton("HELP");
        addMenuButton(helpButton);
    }
    private void createCreditsButton(){
        SpaceRunnerButton creditsButton = new SpaceRunnerButton("CREDITS");
        addMenuButton(creditsButton);
    }
    private void createExitButton(){
        SpaceRunnerButton exitButton = new SpaceRunnerButton("EXIT");
        addMenuButton(exitButton);
    }

    private void createBackground(){
        Image backgroundImage = new Image("JavaFX/SpaceRunner/blueBackground.png", 256, 256, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }

    private void createLogo(){
        ImageView logo = new ImageView("JavaFX/SpaceRunner/gameTitle_Logo.png");
        logo.setLayoutX(300);
        logo.setLayoutY(50);
        logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(new DropShadow());
            }
        });
        logo.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(null);
            }
        });
        mainPane.getChildren().add(logo);
    }
}



/*
zwykły button z lekcji 2:
    private void createButtons(){
        Button button = new Button();
        button.setLayoutX(100);
        button.setLayoutY(100);
        mainPane.getChildren().add(button);

        button.setOnMouseEntered(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                System.out.println("najechano na button");
            }
        });
    }

 */