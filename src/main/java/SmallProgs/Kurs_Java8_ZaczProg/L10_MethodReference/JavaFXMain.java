package SmallProgs.Kurs_Java8_ZaczProg.L10_MethodReference;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**           https://github.com/ZacznijProgramowac/KursJava8/blob/4fee4866d9b289a464ad880a82d2004c1ee978d7/src/main/java/methodReference/JavaFXMain.java
 http://zacznijprogramowac.net/referencje-metod-method-references-w-java-8/            */

public class JavaFXMain extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override public void start(Stage primaryStage) throws Exception {

        Button button = new Button("Przycisk");
        Scene scene = new Scene(button);
        primaryStage.setScene(scene);
        primaryStage.show();


        button.setOnAction(e->{
            System.out.println("Jestem przycisk");
        });

        button.setOnMouseEntered(this::onMouseEntered);

    }
    private void onMouseEntered(MouseEvent mouseEvent) {
        System.out.println("Jestem przycisk z referencji metody");
    }
}
