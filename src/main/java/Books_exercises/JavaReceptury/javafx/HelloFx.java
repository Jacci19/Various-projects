package Books_exercises.JavaReceptury.javafx;

// BEGIN main
import javafx.application.Application;           // <1>
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloFx extends Application {       // <2>

    @Override
    public void start(Stage stage) {             // <3>
        stage.setTitle("Witam w JavaFX!");
        Button btn = new Button();
        btn.setText("Uruchamiamy program powitalny.");
        btn.setOnAction(new EventHandler<ActionEvent>() { // <4>
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Witamy w JavaFX");
            }
        });

        StackPane rootPane = new StackPane();    // <5>
        rootPane.getChildren().add(btn);
        stage.setScene(new Scene(rootPane, 300, 200));
        stage.show();
    }
    
    public static void main(String[] args) {     // <6>
        launch(args);
    }
}
// END main
