import java.util.NoSuchElementException;

public class MaxHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] arr;
    private int size;
    // Do not add any more instance variables

    /**
     * Creates a MaxHeap.
     */
    public MaxHeap() {
        arr = (T[]) new Comparable[STARTING_SIZE];
        size = 0;
    }

    @Override
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Data is null");
        }
        if (arr.length == size + 1) {
            T[] copy = (T[]) new Comparable[arr.length * 2];
            for (int i = 1; i < arr.length; i++) {
                copy[i] = arr[i];
            }
            arr = copy;
        }
        arr[size + 1] = item;
        int x = size + 1;
        boolean isOrdered = false;
        if (x == 2) {
            if (arr[x].compareTo(arr[x / 2]) > 0) {
                T child = arr[x];
                T parent = arr[x / 2];
                arr[x / 2] = child;
                arr[x] = parent;
            }
            x /= 2;
        }
        while (!isOrdered && x >= 2) {
            if (arr[x].compareTo(arr[x / 2]) > 0) {
                T child = arr[x];
                T parent = arr[x / 2];
                arr[x / 2] = child;
                arr[x] = parent;
                x /= 2;
            } else {
                isOrdered = true;
            }
        }
        size++;
    }

    @Override
    public T remove() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
        T data = arr[1];
        arr[1] = arr[size];
        arr[size] = null;
        int x = 1;
        boolean isOrdered = false;
        size--;
        while (!isOrdered) {
            if ((x * 2) <= size && (x * 2 + 1) <= size
                    && arr[x].compareTo(arr[2 * x + 1]) < 0
                    && arr[x].compareTo(arr[2 * x]) < 0) {
                T parent = arr[x];
                T child;
                if (arr[2 * x].compareTo(arr[2 * x + 1]) <= 0) {
                    child = arr[2 * x + 1];
                    arr[2 * x + 1] = parent;
                    arr[x] = child;
                    x = 2 * x + 1;
                } else {
                    child = arr[2 * x];
                    arr[2 * x] = parent;
                    arr[x] = child;
                    x *= 2;
                }
            } else if (((x * 2 + 1) <= size)
                    && arr[x].compareTo(arr[2 * x + 1]) < 0) {
                T parent1 = arr[x];
                T child1 = arr[2 * x + 1];
                arr[x] = child1;
                arr[2 * x + 1] = parent1;
                x = 2 * x + 1;
            } else if ((x * 2 <= size)
                    && arr[x].compareTo(arr[2 * x]) < 0) {
                T parent2 = arr[x];
                T child2 = arr[2 * x];
                arr[x] = child2;
                arr[2 * x] = parent2;
                x = 2 * x;
            } else {
                isOrdered = true;
            }
        }
        return data;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        arr = (T[]) new Comparable[STARTING_SIZE];
        size = 0;
    }

    @Override
    public Comparable[] getBackingArray() {
        return arr;
    }
}