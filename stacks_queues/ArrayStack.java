/**
 * ArrayStack
 * Implementation of a stack using
 * an array as a backing structure
 *
 * @author Your Name Here
 * @version 1.0
 */
import java.util.NoSuchElementException;

public class ArrayStack<T> implements StackADT<T> {

    // Do not add instance variables
    private T[] backing;
    private int size;

    /**
     * Construct an ArrayStack with
     * an initial capacity of INITIAL_CAPACITY.
     *
     * Use constructor chaining.
     */
    public ArrayStack() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Construct an ArrayStack with the specified
     * initial capacity of initialCapacity
     * @param initialCapacity Initial capacity of the backing array.
     */
    public ArrayStack(int initialCapacity) {
        if (initialCapacity == 0) {
            initialCapacity = INITIAL_CAPACITY;
        }
        backing = (T[]) new Object[initialCapacity];
        size = 0;
    }

    @Override
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null as an element");
        }
        if (size == backing.length) {
            T[] copy = (T[]) new Object[size * 2];
            for (int x = 0; x < size; x++) {
                copy[x] = backing[x];
            }
            backing = copy;
        }
        backing[size] = data;
        size++;
    }

    @Override
    public T pop() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("There are no"
                + "elements in the ArrayStack");
        }
        size--;
        T front = backing[size];
        backing[size] = null;
        return front;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the backing array for your queue.
     * This is for grading purposes only. DO NOT EDIT THIS METHOD.
     *
     * @return the backing array
     */
    public Object[] getBackingArray() {
        return backing;
    }
}
