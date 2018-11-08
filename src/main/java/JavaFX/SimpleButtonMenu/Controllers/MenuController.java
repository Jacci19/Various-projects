package JavaFX.SimpleButtonMenu.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuController {

    private MainController mainContr;



    @FXML
    public void onWindow01Action() throws IOException {
        System.out.println("Wcisnieto AppBtn");
        FXMLLoader window01Loader = new FXMLLoader(this.getClass().getResource("/JavaFX/SimpleButtonMenu/FXML/Window_01.fxml"));
        Pane w01Pane = window01Loader.load();

        Window_01_Controller window01contr = window01Loader.getController();
        window01contr.setMainContr(mainContr);
        mainContr.setScreen(w01Pane);
    }

    @FXML
    public void onWindow02Action(){
        System.out.println("Wciśnięto OptBtn");
        FXMLLoader window02Loader = new FXMLLoader(this.getClass().getResource("/JavaFX/SimpleButtonMenu/FXML/Window_02.fxml"));
        Pane w02Pane = null;
        try {                                                               //można zrobić też sposobem jak w metodzie onAppBtnAction (z throws IOException)
            w02Pane = window02Loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Window_02_Controller window02contr = window02Loader.getController();
        window02contr.setMainContr(mainContr);
        mainContr.setScreen(w02Pane);

    }
    @FXML
    public void onExitBtnAction(){
        System.out.println("Wciśnięto ExitBtn");
        Platform.exit();
    }


    public void setMainContr(MainController mainContr) {
        this.mainContr = mainContr;
    }
}
