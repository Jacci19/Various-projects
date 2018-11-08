package JavaFX.MyMix.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuController {

    private MainController mainContr;

    //w każdej poniższej metodzie możemy nadawać takie same nazwy obiektom bo te obiekty wzajemnie się nie widzą

    @FXML
    public void onWindow01BtnAction() throws IOException {
        System.out.println("Wcisnieto Window01Btn");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/JavaFX/MyMix/FXML/Window_01.fxml"));
        Pane pane = loader.load();

        Window_01_Controller windowContr = loader.getController();
        windowContr.setMainContr(mainContr);
        mainContr.setScreen(pane);
    }

    @FXML
    public void onWindow02BtnAction(){
        System.out.println("Wcisnieto Window02Btn");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/JavaFX/MyMix/FXML/Window_02.fxml"));
        Pane pane = null;
        try {                                                               //można zrobić też sposobem jak w metodzie onWindow01BtnAction (z throws IOException)
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Window_02_Controller windowContr = loader.getController();
        windowContr.setMainContr(mainContr);
        mainContr.setScreen(pane);
    }

    @FXML
    public void onWindow03BtnAction() throws IOException {
        System.out.println("Wcisnieto Window03Btn");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/JavaFX/MyMix/FXML/Window_03.fxml"));
        Pane pane = loader.load();

        Window_03_Controller windowContr = loader.getController();
        windowContr.setMainContr(mainContr);
        mainContr.setScreen(pane);
    }

    @FXML
    public void onWindow04BtnAction() throws IOException {
        System.out.println("Wcisnieto Window04Btn");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/JavaFX/MyMix/FXML/Window_04.fxml"));
        Pane pane = loader.load();

        Window_04_Controller windowContr = loader.getController();
        windowContr.setMainContr(mainContr);
        mainContr.setScreen(pane);
    }

    @FXML
    public void onWindow05BtnAction() throws IOException {
        System.out.println("Wcisnieto Window05Btn");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/JavaFX/MyMix/FXML/Window_05.fxml"));
        Pane pane = loader.load();

        Window_05_Controller windowContr = loader.getController();
        windowContr.setMainContr(mainContr);
        mainContr.setScreen(pane);
    }

    @FXML
    public void onWindow06BtnAction() throws IOException {
        System.out.println("Wcisnieto Window06Btn");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/JavaFX/MyMix/FXML/Window_06.fxml"));
        Pane pane = loader.load();

        Window_06_Controller windowContr = loader.getController();
        windowContr.setMainContr(mainContr);
        mainContr.setScreen(pane);
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
