package JavaFX.MyMix.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuController {

    private MainController mainContr;



    @FXML
    public void onWindow01Action() throws IOException {
        System.out.println("Wcisnieto Window01Btn");
        FXMLLoader window01Loader = new FXMLLoader(this.getClass().getResource("/JavaFX/MyMix/FXML/Window_01.fxml"));
        Pane w01Pane = window01Loader.load();

        Window_01_Controller window01contr = window01Loader.getController();
        window01contr.setMainContr(mainContr);
        mainContr.setScreen(w01Pane);
    }

    @FXML
    public void onWindow02Action(){
        System.out.println("Wcisnieto Window02Btn");
        FXMLLoader window02Loader = new FXMLLoader(this.getClass().getResource("/JavaFX/MyMix/FXML/Window_02.fxml"));
        Pane w02Pane = null;
        try {                                                               //można zrobić też sposobem jak w metodzie onWindow01Action (z throws IOException)
            w02Pane = window02Loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Window_02_Controller window02contr = window02Loader.getController();
        window02contr.setMainContr(mainContr);
        mainContr.setScreen(w02Pane);
    }

    @FXML
    public void onWindow03Action() throws IOException {
        System.out.println("Wcisnieto Window03Btn");
        FXMLLoader window03Loader = new FXMLLoader(this.getClass().getResource("/JavaFX/MyMix/FXML/Window_03.fxml"));
        Pane w03Pane = window03Loader.load();

        Window_03_Controller window01contr = window03Loader.getController();
        window01contr.setMainContr(mainContr);
        mainContr.setScreen(w03Pane);
    }



    @FXML
    public void onExitBtnAction(){
        System.out.println("Wcisnieto ExitBtn");
        Platform.exit();
    }


    public void setMainContr(MainController mainContr) {
        this.mainContr = mainContr;
    }
}
