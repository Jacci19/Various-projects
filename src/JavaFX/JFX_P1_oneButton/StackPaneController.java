package JavaFX.JFX_P1_oneButton;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StackPaneController {

    @FXML
    private Button btn1;                //wstrzykniecie btn1 do kontrolera, teraz sam się bedzie on inicjalizował przy uruchomieniu (niepotrzebne jest new)

    public StackPaneController() {                                  //konstruktor kontrolera
        System.out.println("Jestem kontrolerem");
    }

    @FXML
    void initialize(){                  //gwarantuje inicjalizację wszytkich wstrzykniętych tu elementów fxml, musi być od razu po konstruktorze
                                        // jeśli tej metody nie będzie to nie można się będzie odwoływać do elementów fxml (np. do btn1)
        btn1.setText("Startuj");        //zmiana ze "Start" na "Startuj". To polecenie nie zadziałałoby w konstruktorze.
    }
    @FXML
    //public void onActionBtn(ActionEvent e){                      //obsługa eventu onAction (podanie "ActionEvent e" jest opcjonalne)
    public void onActionBtn(){                      //obsługa eventu onAction (podanie "ActionEvent e" jest opcjonalne)
        //System.out.println("wciśnięto btn1 " + e.getSource());       //robi to po nacisnięciu przycisku btn1
    }

    @FXML
    //public void onMouseEnteredBtn(MouseEvent e){                      //obsługa eventu onAction  (podanie "ActionEvent e" jest opcjonalne)
    public void onMouseEnteredBtn(){                      //obsługa eventu onAction  (podanie "ActionEvent e" jest opcjonalne)
        //System.out.println("najechano na btn1 " + e.getSource());       //robi to po nacisnięciu przycisku btn1
    }


}
