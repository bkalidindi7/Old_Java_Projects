/**
 * ArrayQueue
 * Implementation of a queue using
 * an array as the backing structure
 *
 * @author Your Name Here
 * @version 1.0
 */
import java.util.NoSuchElementException;

public class ArrayQueue<T> implements QueueADT<T> {

    // Do not add instance variables
    private T[] backing;
    private int size;
    private int front;
    private int back;

    /**
     * Construct an ArrayQueue with an
     * initial capacity of INITIAL_CAPACITY
     *
     * Use Constructor Chaining
     */
    public ArrayQueue() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Construct an ArrayQueue with the specified
     * initial capacity of initialCapacity
     * @param initialCapacity Initial capacity of the backing array.
     */
    public ArrayQueue(int initialCapacity) {
        if (initialCapacity == 0) {
            initialCapacity = INITIAL_CAPACITY;
        }
        backing = (T[]) new Object[initialCapacity];
        size = 0;
        front = 0;
        back = 0;
    }

    @Override
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null as an element");
        }
        if (size == backing.length) {
            T[] copy = (T[]) new Object[size * 2];
            if (back > front) {
                for (int x = front; x < back; x++) {
                    copy[x - front] = backing[x];
                }
            }
            if (back <= front) {
                for (int x = front; x < size; x++) {
                    copy[x - front] = backing[x];
                }
                for (int x = 0; x < back; x++) {
                    copy[size - front + x] = backing[x];
                }
            }
            backing = copy;
            front = 0;
            back = size;
        } else if (back == backing.length) {
            back = 0;
        }
        backing[back] = data;
        back++;
        size++;
    }

    @Override
    public T dequeue() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("There are no"
                + "elements in the ArrayQueue");
        }
        T front2 = backing[front];
        backing[front] = null;
        size--;
        front++;
        if (front == backing.length) {
            front = 0;
        }
        return front2;
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
