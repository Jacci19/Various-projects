package JavaFX.SpaceRunner;

/*** https://www.youtube.com/watch?v=6BKI26gxK2Q&index=2&list=PL4wcbt63yAbdtY-GOeuRjIePfUsukSJZ9
 * https://github.com/Bitclef/spaceRunner              //to nie jest repo autora kursu tylko kursanta!
 *
 * w tym package (SpaceRunner) jest tylko to co w kursie, swoje modyfikacje do gry wprowadziłem w package SpaceRunner2.   (Jacek)         * */

import JavaFX.SpaceRunner.View.ViewManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class SR_Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try{
            ViewManager manager = new ViewManager();
            primaryStage = manager.getMainStage();
            primaryStage.show();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
