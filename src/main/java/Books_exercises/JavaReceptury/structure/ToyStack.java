package Books_exercises.JavaReceptury.structure;

/** Bardzo prosty stos. 
 */
// BEGIN main
public class ToyStack {

    /** Maksymalna głębokość stosu */
    protected int MAX_DEPTH = 10;
    /** Aktualna głębokość stosu */
    protected int depth = 0;
    /* Stos */
    protected int[] stack = new int[MAX_DEPTH];

    /** push - dodaje element na wierzchołku stosu. */
    protected void push(int n) {
        stack[depth++] = n;
    }
    /** pop - zwraca i usuwa element z wierzchołka stosu. */
    protected int pop() {
        return stack[--depth];
    }
    /** peek - zwraca element z wierzchołka bez jego usuwania. */
    protected int peek() {
        return stack[depth-1];
    }
}
// END main
