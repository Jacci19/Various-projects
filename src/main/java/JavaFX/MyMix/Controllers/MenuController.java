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
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/JavaFX/MyMix/FXML/W01_FadeTransition.fxml"));
        Pane pane = loader.load();

        W01_Controller_FadeTransition windowContr = loader.getController();
        windowContr.setMainContr(mainContr);
        mainContr.setScreen(pane);
    }

    @FXML
    public void onWindow02BtnAction(){
        System.out.println("Wcisnieto Window02Btn");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/JavaFX/MyMix/FXML/W02_PathTransition.fxml"));
        Pane pane = null;
        try {                                                               //można zrobić też sposobem jak w metodzie onWindow01BtnAction (z throws IOException)
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        W02_Controller_PathTransition windowContr = loader.getController();
        windowContr.setMainContr(mainContr);
        mainContr.setScreen(pane);
    }

    @FXML
    public void onWindow03BtnAction() throws IOException {
        System.out.println("Wcisnieto Window03Btn");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/JavaFX/MyMix/FXML/W03_ParallelTransition.fxml"));
        Pane pane = loader.load();

        W03_Controller_ParallelTransition windowContr = loader.getController();
        windowContr.setMainContr(mainContr);
        mainContr.setScreen(pane);
    }

    @FXML
    public void onWindow04BtnAction() throws IOException {
        System.out.println("Wcisnieto Window04Btn");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/JavaFX/MyMix/FXML/W04_SequentialTransition.fxml"));
        Pane pane = loader.load();

        W04_Controller_SequentialTransition windowContr = loader.getController();
        windowContr.setMainContr(mainContr);
        mainContr.setScreen(pane);
    }

    @FXML
    public void onWindow05BtnAction() throws IOException {
        System.out.println("Wcisnieto Window05Btn");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/JavaFX/MyMix/FXML/W05_BasicTimeline.fxml"));
        Pane pane = loader.load();

        W05_Controller_BasicTimeline windowContr = loader.getController();
        windowContr.setMainContr(mainContr);
        mainContr.setScreen(pane);
    }

    @FXML
    public void onWindow06BtnAction() throws IOException {
        System.out.println("Wcisnieto Window06Btn");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/JavaFX/MyMix/FXML/W06_TimelineEvents.fxml"));
        Pane pane = loader.load();

        W06_Controller_TimelineEvents windowContr = loader.getController();
        windowContr.setMainContr(mainContr);
        mainContr.setScreen(pane);
    }

    @FXML
    public void onWindow07BtnAction() throws IOException {
        System.out.println("Wcisnieto Window07Btn");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/JavaFX/MyMix/FXML/Window_07.fxml"));
        Pane pane = loader.load();

        Window_07_Controller windowContr = loader.getController();
        windowContr.setMainContr(mainContr);
        mainContr.setScreen(pane);
    }

    @FXML
    public void onWindow08BtnAction() throws IOException {
        System.out.println("Wcisnieto Window08Btn");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/JavaFX/MyMix/FXML/Window_08.fxml"));
        Pane pane = loader.load();

        Window_08_Controller windowContr = loader.getController();
        windowContr.setMainContr(mainContr);
        mainContr.setScreen(pane);
    }

    @FXML
    public void onWindow09BtnAction() throws IOException {
        System.out.println("Wcisnieto Window09Btn");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/JavaFX/MyMix/FXML/Window_09.fxml"));
        Pane pane = loader.load();

        Window_09_Controller windowContr = loader.getController();
        windowContr.setMainContr(mainContr);
        mainContr.setScreen(pane);
    }

    @FXML
    public void onWindow10BtnAction() throws IOException {
        System.out.println("Wcisnieto Window10Btn");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/JavaFX/MyMix/FXML/Window_10.fxml"));
        Pane pane = loader.load();

        Window_10_Controller windowContr = loader.getController();
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
