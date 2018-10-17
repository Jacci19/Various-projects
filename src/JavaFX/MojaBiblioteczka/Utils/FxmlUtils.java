package JavaFX.MojaBiblioteczka.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ResourceBundle;

public class FxmlUtils {

    public static Pane fxmlLoader(String fxmlPath){

/*przenieśliśmy z "MyBib_Main" te 4 linie i przemodelowaliśmy taj jak poniżej
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("FXML/BorderPaneMain.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("JavaFX.MojaBiblioteczka.Bundles.messages");
        loader.setResources(bundle);
        BorderPane myBorderPane = loader.load();
*/

        FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(fxmlPath));
        loader.setResources(getResourceBundle());
        try {
            return loader.load();
        } catch (Exception e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
        return null;
    }

    public static ResourceBundle getResourceBundle(){
        return ResourceBundle.getBundle("JavaFX.MojaBiblioteczka.Bundles.messages");
    }
}
