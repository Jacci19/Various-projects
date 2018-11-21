package JavaFX.Almas_progs.Calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class CalcController {

    @FXML
    private Text output;

    private long number1 = 0;
    private String operator = "";
    private boolean start = true;

    private CalcModel model = new CalcModel();

    @FXML
    private void processNumpad(ActionEvent event) {                                         //_______________akcja na wciśnięcie klawiszy 0 - 9
        if (start) {
            output.setText("");                                                             //wyczyść ekranik aby wciśnięta cyfra nie dopisywała się do poprzedniego wyniku
            start = false;
        }

        String value = ((Button)event.getSource()).getText();                               //przypisanie wciśniętej cyfry do value
        System.out.println("value:  " + value);
        output.setText(output.getText() + value);                                           //wyświetlenie value na ekraniku
    }

    @FXML
    private void processOperator(ActionEvent event) {                                       //_______________akcja na wciśnięcia klawiszy działań +,-,*,/,=
        String value = ((Button)event.getSource()).getText();                               //przypisanie wciśniętego operatora do value

        if (!"=".equals(value)) {                                                           // jeśli value to "="
            if (!operator.isEmpty())                                                            // jeśli operator NIE jest pusty to wyjdź z tej metody
                return;

            operator = value;                                                                   // jeśli jest pusty to przypisz do operatora value
            number1 = Long.parseLong(output.getText());                                         // a do number1 przypisz liczbę z ekraniku
            output.setText("");                                                                 // wyczyść ekranik
        }
        else {                                                                              // jeśli value to nie jest "="
            if (operator.isEmpty())                                                             // jeśli operator jest pusty to wyjdź z tej metody
                return;

            output.setText(String.valueOf(model.calculate(number1, Long.parseLong(output.getText()), operator)));       //jeśli nie to wykonaj działanie (z klasy CalcModel)
            operator = "";
            start = true;
        }
    }
}