package JavaFX.MojaBiblioteczka.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class TopMenuButtonsController {

    public static final String LIBRARY_FXML = "../FXML/Library.fxml";
    public static final String LIST_BOOKS_FXML = "../FXML/ListBooks.fxml";
    public static final String STATISTICS_FXML = "../FXML/Statistics.fxml";
    public static final String ADD_BOOK_FXML = "../FXML/AddBook.fxml";

    private MainController mainController;

    @FXML
    private ToggleGroup toggleButtons;

    @FXML
    public void onBibliotekaAction() {
        mainController.setCenter(LIBRARY_FXML);
    }
    @FXML
    public void onListaKsiazekAction() {
        mainController.setCenter(LIST_BOOKS_FXML);
    }
    @FXML
    public void onStatystykiAction() {
        mainController.setCenter(STATISTICS_FXML);
    }

    public void setMainController (MainController mainController){
        this.mainController = mainController;
    }

    @FXML
    public void onAddBookAction() {
        if (toggleButtons.getSelectedToggle() != null){                                                     //Aby po wciśnięciu addBook inne przyciski się wyciskały
            toggleButtons.getSelectedToggle().setSelected(false);
        }

        mainController.setCenter(ADD_BOOK_FXML);                                                   //ustawia na środku formatkę AddBook

    }
}
