package JavaFX.MojaBiblioteczka.Utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;


import java.util.Optional;
import java.util.ResourceBundle;

public class DialogsUtils {

    static ResourceBundle bundle = FxmlUtils.getResourceBundle();

    public static void dialogAboutApplication() {                                                                                    //Okno "O aplikacji"
        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
        informationAlert.setTitle(bundle.getString("about.title"));
        informationAlert.setHeaderText(bundle.getString("about.header"));
        informationAlert.setContentText(bundle.getString("about.content"));
        informationAlert.showAndWait();
    }

    public static Optional<ButtonType> confirmationDialog() {                    //metoda zwracająca obiekt typu Optional<ButtonType>        //Okno "Zamknij"
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle(bundle.getString("exit.title"));
        confirmationDialog.setHeaderText(bundle.getString("exit.header"));
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        return result;
    }

    public static void errorDialog(String error) {                                                                                       //Okno "error" wywoływane w catch loader.load
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(bundle.getString("error.title"));
        errorAlert.setHeaderText(bundle.getString("error.header"));

        TextArea myTextArea = new TextArea(error);
        errorAlert.getDialogPane().setContent(myTextArea);
        errorAlert.showAndWait();
    }

    public static String editDialog(String value){                                      //okno wyskakujące po wciśnięciu editCategory
        TextInputDialog dialog = new TextInputDialog(value);
        dialog.setTitle(bundle.getString("edit.title"));
        dialog.setHeaderText(bundle.getString("edit.header"));
        dialog.setContentText(bundle.getString("edit.content"));
        Optional<String> result = dialog.showAndWait();                                                         //pobieramy wartość która będzie zmieniona w polu tekstowym
        if (result.isPresent()){                                                    //jeżeli wartość faktycznie jest...
            return result.get();                                                    // ...to ją pobieramy i zwracamy
        }
        return null;
    }
}