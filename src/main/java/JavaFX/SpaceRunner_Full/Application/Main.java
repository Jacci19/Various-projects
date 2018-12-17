package JavaFX.SpaceRunner_Full.Application;

import JavaFX.SpaceRunner_Full.View.ViewManager;
import javafx.application.Application;
import javafx.stage.Stage;

/* Projekt ściągnięty z gita kogoś kto robił na podstawie kursu, nie działa...*/

public class Main extends Application {


    public static void main(String[] args) {

        System.setProperty("quantum.multithreaded", "false");
        Application.launch(Main.class, args);
    }

    @Override
    public void start(Stage primaryStage) {

        System.out.println("Main 1 dziala");
        ViewManager manager = new ViewManager();
        primaryStage = manager.getMainStage();
        System.out.println("Main 2 dziala");
        primaryStage.show();
        System.out.println("Main3 NIE dziala");

    }
}
