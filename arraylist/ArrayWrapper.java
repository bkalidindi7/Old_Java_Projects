import java.util.NoSuchElementException;

/**
 * This ArrayWrapper funtions as a resizable array that can take in
 * any element type.
 * @author Bharath Kalidindi
 * @version 1.0
 */
public class ArrayWrapper<T> implements SimpleCollection<T> {

    private T[] array;

    public ArrayWrapper() {
        array = (T[]) new Object[5];
    }

    /**
     * Adds an element into the ArrayWrapper.
     * If the new element would exceed the size of the backing array,
     * instead resize the array, doubling it in size and copy over the
     * old elements.
     *
     * @param elem The element being added.
     */
    public void add(T elem) {
        boolean empty = false;
        int length = array.length;
        for (int i = 0; i < length; i++) {
            if (array[i] == null) {
                array[i] = elem;
                empty = true;
                break;
            }
        }
        if (!empty) {
            T[] copy = (T[]) new Object[length * 2];
            for (int i = 0; i < length; i++) {
                copy[i] = array[i];
            }
            copy[length] = elem;
            array = copy;
        }
    }

    /**
     * Adds all elements in elems to the ArrayWrapper.
     * Hint: can this be implemented in terms of add(T elem)?
     *
     * @param elems Array of elements to be added.
     */
    public void addAll(T[] elems) {
        for (int i = 0; i < elems.length; i++) {
            this.add(elems[i]);
        }
    }

    /**
     * Remove elem from the ArrayWrapper. Removing an element
     * should shift all the elements behind it forward, ensuring
     * that the backing array is contiguous. For example:
     *
     * ArrayWrapper<String> collection = ["hi", "hello", "wsup", "hey", null]
     * collection after remove("hello") = ["hi", "wsup", "hey", null, null]
     *
     * @param elem Element to be removed.
     * @return true if the element was removed,
     *         false if it was not in the ArrayWrapper.
     */
    public boolean remove(T elem) {
        boolean inColl = false;
        int j;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == elem) {
                inColl = true;
                j = i;
                while (j < array.length - 1) {
                    array[j] = array[j + 1];
                    j++;
                }
                array[array.length - 1] = null;
            }
        }
        return inColl;
    }

    /**
     * Removes each element in elems from the ArrayWrapper.
     * Hint: can this be implemented in terms of remove(T elem)?
     *
     * @param elems Array of elements to be removed.
     * @return true if any elements were removed,
     *         false if no elements were removed.
     */
    public boolean removeAll(T[] elems) {
        boolean removed = false;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < elems.length; j++) {
                if (this.contains(elems[j])) {
                    removed = this.remove(elems[j]);
                }
            }
        }
        return removed;
    }

    /**
     * Checks to see if the ArrayWrapper contains a given element.
     *
     * @param elem The element we are checking for.
     * @return true if the ArrayWrapper contains elem, false otherwise.
     */
    public boolean contains(T elem) {
        for (int i = 0; i < this.size(); i++) {
            if (array[i].equals(elem)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets an element from the ArrayWrapper, using its 0-based index.
     * If the index is within our backing array but more than our last
     * element, rather than returning null, this should throw
     * a java.util.NoSuchElementException.
     *
     * @param index The index of the element we want.
     * @return The element at the specified index.
     */
    public T get(int index) {
        if (index >= this.size()) {
            throw new NoSuchElementException();
        }
        return array[index];
    }

    /**
     * Returns the current number of elements in the ArrayWrapper.
     *
     * @return The size of the collection.
     */
    public int size() {
        int sizeCnt = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                sizeCnt++;
            }
        }
        return sizeCnt;
    }

    /**
     * Returns the current capacity of the ArrayWrapper - namely, the
     * size of its backing array.
     *
     * @return The total capacity of the ArrayWrapper.
     */
    public int capacity() {
        return array.length;
    }

    /**
     * Clears the ArrayWrapper, resetting size and starting from a fresh
     * backing array of size 5.
     */
    public void clear() {
        T[] clearedArr = (T[]) new Object[5];
        array = clearedArr;
    }

    /**
     * Tests if the ArrayWrapper is empty, i.e. it contains no elements.
     *
     * @return true if the collection has no elements, false otherwise.
     */
    public boolean isEmpty() {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Follows the format:
     *
     * [element1, element2, element3, ..., elementN]
     *
     * The end of the list should not contain any nulls, even if the
     * backing array is larger than the number of elements.
     *
     * @return [element1, element2, element3, ..., elementN]
     */
    public String toString() {
        if (this.size() == 0) {
            return "[]";
        }
        String result = "[";
        for (int i = 0; i < this.size() - 1; i++) {
            result += array[i] + ", ";
        }
        result += array[this.size() - 1] + "]";
        return result;
    }
}