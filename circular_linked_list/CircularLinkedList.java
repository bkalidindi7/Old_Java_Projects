/**
 * CircularLinkedList implementation
 * @author Bharath Kalidindi
 * @version 1.0
 */
public class CircularLinkedList<T> implements LinkedListInterface<T> {

    // Do not add new instance variables.
    private LinkedListNode<T> head;
    private int size;

    @Override
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            LinkedListNode<T> newNode = new LinkedListNode<T>(data);
            LinkedListNode<T> n = head;
            for (int i = 0; i < index; i++) {
                n = n.getNext();
            }
            LinkedListNode<T> prev = n.getPrevious();
            newNode.setNext(n);
            newNode.setPrevious(prev);
            prev.setNext(newNode);
            n.setPrevious(newNode);
            size++;
        }
    }

    @Override
    public T get(int index) {
        LinkedListNode<T> n = head;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < index; i++) {
            n = n.getNext();
        }
        return n.getData();
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        T data = head.getData();
        if (index == 0) {
            data = this.removeFromFront();
        } else if (index == size - 1) {
            data = this.removeFromBack();
        } else {
            LinkedListNode<T> n = head;
            for (int i = 0; i < index; i++) {
                n = n.getNext();
            }
            data = n.getData();
            LinkedListNode<T> previousNode = n.getPrevious();
            LinkedListNode<T> nextNode = n.getNext();
            previousNode.setNext(nextNode);
            nextNode.setPrevious(previousNode);
            size--;
        }
        return data;
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        } else if (size == 0) {
            head = new LinkedListNode<T>(data);
            head.setNext(head);
            head.setPrevious(head);
        } else {
            LinkedListNode<T> newTail = head.getPrevious();
            LinkedListNode<T> head2 = head;
            head = new LinkedListNode<T>(data, newTail, head);
            head2.setPrevious(head);
            newTail.setNext(head);
        }
        size++;
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        } else if (size == 0) {
            head = new LinkedListNode<T>(data);
            head.setNext(head);
            head.setPrevious(head);
        } else {
            LinkedListNode<T> newNode = new LinkedListNode<T>(data,
                head.getPrevious(), head);
            head.getPrevious().setNext(newNode);
            head.setPrevious(newNode);
        }
        size++;
    }

    @Override
    public T removeFromFront() {
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            T data = head.getData();
            this.clear();
            return data;
        }
        T data = head.getData();
        LinkedListNode<T> newHead = head.getNext();
        newHead.setPrevious(head.getPrevious());
        head.getPrevious().setNext(newHead);
        head = newHead;
        size--;
        return data;
    }

    @Override
    public T removeFromBack() {
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            T data = head.getData();
            this.clear();
            return data;
        }
        LinkedListNode<T> tail = head.getPrevious();
        T data = tail.getData();
        LinkedListNode<T> newTail = tail.getPrevious();
        newTail.setNext(head);
        head.setPrevious(newTail);
        size--;
        return data;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        LinkedListNode<T> newNode = head;
        array[0] = head.getData();
        for (int i = 1; i < size; i++) {
            newNode = newNode.getNext();
            array[i] = newNode.getData();
        }
        return array;
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
        size = 0;
        head = null;
    }

    @Override
    public LinkedListNode<T> getHead() {
        return head;
    }
}
