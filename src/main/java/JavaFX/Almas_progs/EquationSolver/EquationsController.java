package JavaFX.Almas_progs.EquationSolver;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;


 /** https://www.youtube.com/watch?v=Fz-zdHVrDOY&list=PL4h6ypqTi3RR_bhBk6PtLfD83YkaJXXxw&index=4    */

public class EquationsController {

    @FXML
    private TextField fieldInput;

    @FXML
    private Label labelOutput;

    public void initialize() {
        fieldInput.setFont(Font.font(24));
        labelOutput.setFont(Font.font(24));
    }

    public void solve() {
        EqSolver solver = new ABCEqSolver();

        String input = fieldInput.getText();

        String output = solver.matchesForm(input) ? solver.solve(input) : "No solver found!";

        labelOutput.setText(output);
    }
}