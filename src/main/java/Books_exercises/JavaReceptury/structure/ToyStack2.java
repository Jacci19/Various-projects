package Books_exercises.JavaReceptury.structure;

// BEGIN main
/** Prosty stos implementujący interfejs SimpleStack. 
 */
public class ToyStack2 implements SimpleStack<Integer> {

    /** Maksymalna głębokość stosu */
    protected int MAX_DEPTH = 10;
    /** Aktualna głębokość stosu */
    protected int depth = 0;
    /* Stos */
    protected int[] stack = new int[MAX_DEPTH];

    @Override
    public boolean empty() {
        return depth == 0;
    }
    
    @Override
    public void push(Integer n) {
        stack[depth++] = n;
    }
    
    @Override
    public Integer pop() {
        return stack[--depth];
    }
    
    @Override
    public Integer peek() {
        return stack[depth-1];
    }
}
// END main
