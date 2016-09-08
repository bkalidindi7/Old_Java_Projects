import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class SkipList<T extends Comparable<? super T>>
    implements SkipListInterface<T> {
    // Do not add any additional instance variables
    private CoinFlipper coinFlipper;
    private int size;
    private Node<T> head;

    /**
     * Constructs a SkipList object that stores data in ascending order.
     * When an item is inserted, the flipper is called until it returns a
     * tails. If, for an item, the flipper returns n heads, the corresponding
     * node has n + 1 levels.
     *
     * @param coinFlipper the source of randomness
     */
    public SkipList(CoinFlipper coinFlipper) {
        size = 0;
        head = new Node<T>(null, 1);
        this.coinFlipper = coinFlipper;
    }

    @Override
    public T first() {
        if (size == 0) {
            throw new NoSuchElementException("Skip List is empty");
        }
        Node<T> first = head;
        while (first.getLevel() != 1) {
            first = first.getDown();
        }
        return first.getNext().getData();
    }

    @Override
    public T last() {
        if (size == 0) {
            throw new NoSuchElementException("Skip List is empty");
        }
        Node<T> first = head;
        while (first.getDown() != null || first.getNext() != null) {
            if (first.getNext() != null) {
                first = first.getNext();
            } else {
                first = first.getDown();
            }
        }
        return first.getData();
    }

    @Override
    public void put(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        }
        Node<T> prevNode = finder(data);
        Node<T> newNode;
        if (prevNode.getData() != null && prevNode.getData().equals(data)) {
            while (prevNode.getDown() != null) {
                prevNode.setData(data);
                prevNode = prevNode.getDown();
            }
            prevNode.setData(data);
        } else {
            newNode = new Node<T>(data, 1);
            newNode.setPrev(prevNode);
            newNode.setNext(prevNode.getNext());
            if (prevNode.getNext() != null) {
                prevNode.getNext().setPrev(newNode);
            }
            prevNode.setNext(newNode);
            CoinFlipper.Coin coin = CoinFlipper.Coin.HEADS;
            int numFlips = 0;
            while (coin != CoinFlipper.Coin.TAILS) {
                coin = coinFlipper.flipCoin();
                numFlips++;
            }
            for (int i = 1; i < numFlips; i++) {
                Node<T> upNode = new Node<T>(newNode.getData(),
                        i + 1, null, null, null, newNode);
                newNode.setUp(upNode);
                Node<T> left = newNode.getPrev();
                Node<T> right = newNode.getNext();
                while (left.getData() != null && left.getUp() == null) {
                    left = left.getPrev();
                }
                while (right != null && right.getUp() == null) {
                    right = right.getNext();
                }
                if (left.getData() == null && right == null) {
                    Node<T> newHead = new Node<T>(null, i + 1);
                    head.setUp(newHead);
                    newHead.setDown(head);
                    newHead.setNext(upNode);
                    upNode.setPrev(newHead);
                    head = newHead;
                } else if (right == null) {
                    left = left.getUp();
                    upNode.setPrev(left);
                    left.setNext(upNode);
                } else {
                    left = left.getUp();
                    right = right.getUp();
                    upNode.setNext(right);
                    upNode.setPrev(left);
                    right.setPrev(upNode);
                    left.setNext(upNode);
                }
                newNode = upNode;
            }
            size++;
        }
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        }
        Node<T> node = finder(data);
        T remData = node.getData();
        if (node.getData() == null || !node.getData().equals(data)) {
            throw new NoSuchElementException("Data not found");
        } else {
            while (node.getDown() != null) {
                if (node.getNext() == null
                        && node.getPrev().getData() == null) {
                    head.setNext(null);
                    head = head.getDown();
                    head.setUp(null);
                } else if (node.getNext() == null) {
                    node.getPrev().setNext(null);
                } else {
                    node.getPrev().setNext(node.getNext());
                    node.getNext().setPrev(node.getPrev());
                }
                node = node.getDown();
            }
            if (node.getNext() != null) {
                node.getNext().setPrev(node.getPrev());
            }
            node.getPrev().setNext(node.getNext());
            size--;
            return remData;
        }
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        }
        Node<T> match = finder(data);
        if (match.getData() != null && match.getData().equals(data)) {
            return true;
        }
        return false;
    }

    /**
     * Finds the node whose data matches or the node data with the greatest
     * value that is less than the data.
     * @param data of the node being searched for
     * @return the node with the data or the node prior to it
     */
    private Node<T> finder(T data) {
        Node<T> currNode = head;
        while (currNode.getDown() != null || currNode.getNext() != null) {
            Node<T> next = currNode.getNext();
            if (next == null || next.getData().compareTo(data) > 0) {
                if (currNode.getLevel() == 1) {
                    return currNode;
                }
                currNode = currNode.getDown();
            } else if (next.getData().compareTo(data) < 0) {
                currNode = next;
            } else {
                return next;
            }
        }
        return currNode;
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        }
        Node<T> match = finder(data);
        if (match.getData() != null && match.getData().equals(data)) {
            return match.getData();
        }
        throw new NoSuchElementException("Data not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        head = new Node<T>(null, 1);
    }

    @Override
    public Set<T> dataSet() {
        Set<T> data = new HashSet<T>(size);
        Node<T> first = head;
        while (first.getLevel() != 1) {
            first = first.getDown();
        }
        while (first.getNext() != null) {
            first = first.getNext();
            data.add(first.getData());
        }
        return data;
    }

    @Override
    public Node<T> getHead() {
        return head;
    }
}
