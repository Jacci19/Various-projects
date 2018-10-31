package JavaFX.MojaBiblioteczka.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class TopMenuButtonsController {

    public static final String LIBRARY_FXML = "/JavaFX/MojaBiblioteczka/FXML/Library.fxml";
    public static final String LIST_BOOKS_FXML = "/JavaFX/MojaBiblioteczka/FXML/ListBooks.fxml";
    public static final String STATISTICS_FXML = "/JavaFX/MojaBiblioteczka/FXML/Statistics.fxml";
    public static final String ADD_BOOK_FXML = "/JavaFX/MojaBiblioteczka/FXML/AddBook.fxml";
    public static final String ADD_CATEGORY_FXML = "/JavaFX/MojaBiblioteczka/FXML/AddCategory.fxml";

    private MainController mainController;

    @FXML
    private ToggleGroup toggleButtons;

    @FXML
    public void onBibliotekaAction() {
        mainController.setCenter(LIBRARY_FXML);
    }
    @FXML
    public void onListaKsiazekAction() {
        mainController.setCenter(LIST_BOOKS_FXML);                                                      //setCenter zdefiniowane jest w MainController
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
        resetToggleButtons();
        mainController.setCenter(ADD_BOOK_FXML);                                                        //ustawia na środku formatkę AddBook
    }

    @FXML
    public void onAddCategoryAction(ActionEvent actionEvent) {
        resetToggleButtons();
        mainController.setCenter(ADD_CATEGORY_FXML);                                                   //ustawia na środku formatkę AddBook
    }

    private void resetToggleButtons() {
        if (toggleButtons.getSelectedToggle() != null){                                                //Aby po wciśnięciu addBook inne przyciski się wyciskały
            toggleButtons.getSelectedToggle().setSelected(false);
        }
    }

}
