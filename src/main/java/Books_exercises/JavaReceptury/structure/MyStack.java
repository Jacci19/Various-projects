package Books_exercises.JavaReceptury.structure;

/** Bardzo prosta implementacja stosu.
 */
@SuppressWarnings("unchecked")
// BEGIN main
public class MyStack<T> implements SimpleStack<T> {
    
    private int depth = 0;
    public static final int DEFAULT_INITIAL = 10;
    private T[] stack;
    
    public MyStack() {
        this(DEFAULT_INITIAL);
    }

    public MyStack(int howBig) {
        if (howBig <= 0) {
            throw new IllegalArgumentException(
            howBig + " musi być liczbą dodatnią, przekazano " + howBig);
        }
        stack = (T[])new Object[howBig];
    }
    
    @Override
    public boolean empty() {
        return depth == 0;
    }

    /** push - umieszczenie elementu na stosie */
    @Override
    public void push(T obj) {
        // Metoda mogłaby sprawdzać pojemność i w razie konieczności 
        // powiększać stos.
        stack[depth++] = obj;
    }

    /* pop - zdejmuje element ze stosu i zwraca go. */
    @Override
    public T pop() {
        --depth;
        T tmp = stack[depth];
        stack[depth] = null;
        return tmp;
    }
    
    /** peek - zwraca element z wierzchołka stosu, ale go nie usuwa. */
    @Override
    public T peek() {
        if (depth == 0) {
            return null;
        }
        return stack[depth-1];
    }
    
    public boolean hasNext() {
        return depth > 0;
    }

    public boolean hasRoom() {
        return depth < stack.length;
    }

    public int getStackDepth() {
        return depth;
    }
}
// END main
