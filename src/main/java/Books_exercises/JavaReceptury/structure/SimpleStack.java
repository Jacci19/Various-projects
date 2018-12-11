package Books_exercises.JavaReceptury.structure;

// BEGIN main
public interface SimpleStack<T> {

    /** empty - zwraca true, jeśli stos jest pusty. */
    abstract boolean empty();
    
    /** push - dodaje element na wierzchołku stosu. */
    abstract void push(T n);

    /** pop - zwraca i usuwa element z wierzchołka stosu. */
    abstract T pop();

    /** peek - zwraca element z wierzchołka bez jego usuwania. */
    abstract T peek();
}
// END main
