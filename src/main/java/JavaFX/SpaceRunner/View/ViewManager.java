package JavaFX.SpaceRunner.View;

import JavaFX.SpaceRunner.Model.*;
import javafx.event.ActionEvent;
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

    private  SpaceRunnerSubScene creditsSubScene;
    private  SpaceRunnerSubScene helpSubScene;
    private  SpaceRunnerSubScene scoreSubScene;
    private  SpaceRunnerSubScene shipChooserSubScene;

    private SpaceRunnerSubScene sceneToHide;

    List<SpaceRunnerButton> menuButtons;                                                            //lista przycisków menu
    List<ShipPicker> shipsList;                                                                     //lista vboxów do wyboru z menu (vbox zawiera: kształtka + statek)
    private SHIP chosenShip;

    public ViewManager(){
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        menuButtons = new ArrayList<>();                                                            //play, score, help, credits, exit
        createSubScenes();                                                                          //wjeżdżające plansze menu
        createButtons();                                                                            //przyciski menu
        createBackground();                                                                         //tło nmenu
        createLogo();                                                                               //tytuł gry
    }

    private void showSubScene(SpaceRunnerSubScene subScene){
        if (sceneToHide != null){                                                                   //aby przy wjeżdżaniu subSceny poprzednia wyjeżdżała
            sceneToHide.moveSubScene();
        }
        subScene.moveSubScene();
        sceneToHide = subScene;
    }

    private void createSubScenes(){
        creditsSubScene = new SpaceRunnerSubScene();                                              //żółte wjeżdżające pole
        mainPane.getChildren().add(creditsSubScene);

        helpSubScene = new SpaceRunnerSubScene();                                                 //żółte wjeżdżające pole
        mainPane.getChildren().add(helpSubScene);

        scoreSubScene = new SpaceRunnerSubScene();                                                 //żółte wjeżdżające pole
        mainPane.getChildren().add(scoreSubScene);

/*
        shipChooserSubScene = new SpaceRunnerSubScene();                                           //żółte wjeżdżające pole
        mainPane.getChildren().add(shipChooserSubScene);
*/

        createShipChooserSubScene();
    }

    private void createShipChooserSubScene() {                                                      //wybór pojazdu
        shipChooserSubScene = new SpaceRunnerSubScene();
        mainPane.getChildren().add(shipChooserSubScene);

        InfoLabel chooseShipLabel = new InfoLabel("CHOOSE YOUR SHIP: ");
        chooseShipLabel.setLayoutX(110);
        chooseShipLabel.setLayoutY(25);
        shipChooserSubScene.getPane().getChildren().add(chooseShipLabel);                           // wstawienie labela:   CHOOSE YOUR SHIP
        shipChooserSubScene.getPane().getChildren().add(createShipsToChoose());                     // wstawienie statków do wyboru
        shipChooserSubScene.getPane().getChildren().add(createButtonToStart());                     // wstawienie przycisku start

    }

    private HBox createShipsToChoose(){
        HBox box = new HBox();
        box.setSpacing(33);                                                                         //odstęp pomiędzy statkami
        shipsList = new ArrayList<>();
        for(SHIP ship: SHIP.values()){                                                              //rób to dla każdego statku możliwego do wyboru
            ShipPicker shipToPick = new ShipPicker(ship);                                           //jeden vbox (kształtka + statek)
            shipsList.add(shipToPick);
            box.getChildren().add(shipToPick);                                                      //wstawia vboxa do hboxa (vboxy ułożą się więc w poziomie)
            shipToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    for (ShipPicker ship : shipsList){
                        ship.setIsCircleChosen(false);                                              //Każdą kształtkę ustaw na niewybraną...
                    }
                    shipToPick.setIsCircleChosen(true);                                             //...a tą akurat klikniętą na wybraną
                    chosenShip = shipToPick.getShip();
                }
            });
        }
        box.setLayoutX(50);
        box.setLayoutY(100);
        return box;
    }

    private SpaceRunnerButton createButtonToStart(){                                                // przycisk Start pod wyborem statków
        SpaceRunnerButton startButton = new SpaceRunnerButton("START");
        startButton.setLayoutX(350);
        startButton.setLayoutY(300);
        return startButton;
    }

    private void addMenuButton(SpaceRunnerButton button){
        button.setLayoutX(MENU_BUTTONS_START_X);
        button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 100);
        menuButtons.add(button);                                                                   //dodanie buttona do listy
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

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               showSubScene(shipChooserSubScene);
            }
        });

    }
    private void createScoreButton(){
        SpaceRunnerButton scoreButton = new SpaceRunnerButton("SCORES");
        addMenuButton(scoreButton);

        scoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(scoreSubScene);
            }
        });
    }
    private void createHelpButton(){
        SpaceRunnerButton helpButton = new SpaceRunnerButton("HELP");
        addMenuButton(helpButton);

        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(helpSubScene);
            }
        });
    }
    private void createCreditsButton(){
        SpaceRunnerButton creditsButton = new SpaceRunnerButton("CREDITS");
        addMenuButton(creditsButton);

        creditsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(creditsSubScene);
            }
        });
    }
    private void createExitButton(){
        SpaceRunnerButton exitButton = new SpaceRunnerButton("EXIT");
        addMenuButton(exitButton);

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainStage.close();
            }
        });
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

    public Stage getMainStage() {
        return mainStage;
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