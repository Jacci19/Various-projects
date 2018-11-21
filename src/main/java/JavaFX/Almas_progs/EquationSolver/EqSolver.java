package JavaFX.Almas_progs.EquationSolver;

/** https://www.youtube.com/watch?v=Fz-zdHVrDOY&list=PL4h6ypqTi3RR_bhBk6PtLfD83YkaJXXxw&index=4    */

public abstract class EqSolver {

    private String pattern;

    public EqSolver(String pattern) {
        this.pattern = pattern;
    }

    public boolean matchesForm(String input) {
        return input.matches(pattern);
    }

    public abstract String solve(String input);
}