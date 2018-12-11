package JavaFX.SpaceRunner.View;

import JavaFX.SpaceRunner.Model.SpaceRunnerButton;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewManager {

    private static final int WIDTH = 1024, HEIGHT = 768;
    public static AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    public ViewManager(){
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createButtons();
    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void createButtons(){
        SpaceRunnerButton button = new SpaceRunnerButton("click ME!");
        mainPane.getChildren().add(button);
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