package JavaFX.Almas_progs.EquationSolver;

/** https://www.youtube.com/watch?v=Fz-zdHVrDOY&list=PL4h6ypqTi3RR_bhBk6PtLfD83YkaJXXxw&index=4
 https://github.com/AlmasB/FXTutorials/blob/master/src/com/almasb/equations/ABCEqSolver.java                 */

public class ABCEqSolver extends EqSolver {

    public ABCEqSolver() {
        super("[0-9]+x [\\+\\-] [0-9]+ = [0-9]+");
    }

    @Override
    public String solve(String input) {

        String[] tokens = input.split(" ");

        String op = tokens[1];

        int a = Integer.parseInt(tokens[0].substring(0, tokens[0].length() - 1));
        int b = Integer.parseInt(tokens[2]);
        int c = Integer.parseInt(tokens[4]);

        if (op.equals("-")) {
            b = -b;
        }

        int result = c - b;

        String output = result + "/" + a;

        if (result % a == 0) {
            output = String.valueOf(result / a);
        }

        return output;
    }
}