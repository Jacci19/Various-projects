package JavaFX.MojaBiblioteczka.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class TopMenuButtonsController {

    private MainController mainController;

    @FXML
    public void onBibliotekaAction() {
        System.out.println("onBibliotekaAction");
    }
    @FXML
    public void onListaKsiazekAction() {
        System.out.println("onListaKsiazekAction");

    }
    @FXML
    public void onStatystykiAction() {
        System.out.println("onStatystykiAction");

    }

    public void setMainController (MainController mainController){
        this.mainController = mainController;
    }
}
