import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    private BSTNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty BST
     */
    public BST() {
        root = null;
        size = 0;
    }

    /**
     * Initializes the BST with the data in the collection. The data in the BST
     * should be added in the same order it is in the collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        this();
        if (data == null || data.contains(null)) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        for (T elem : data) {
            add(elem);
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        root = addHelper(data, root);
    }

    /**
     * Helps add new data to the tree.
     * @param data Data to add to tree
     * @param currNode root of tree being added to
     * @return new tree with data added
     */
    private BSTNode<T> addHelper(T data, BSTNode<T> currNode) {
        if (currNode == null || currNode.getData() == null) {
            size++;
            return new BSTNode<T>(data);
        }
        if (data.compareTo(currNode.getData()) < 0) {
            currNode.setLeft(addHelper(data, currNode.getLeft()));
        }
        if (data.compareTo(currNode.getData()) > 0) {
            currNode.setRight(addHelper(data, currNode.getRight()));
        }
        return currNode;
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        BSTNode<T> replacementParent;
        BSTNode<T> currNode;
        T removedData;
        if (root == null) {
            throw new NoSuchElementException("Could not find data");
        }
        if (root.getData().equals(data)) {
            removedData = root.getData();
            if (root.getRight() == null && root.getLeft() == null) {
                root = null;
            } else if (root.getRight() == null) {
                root = root.getLeft();
            } else if (root.getLeft() == null) {
                root = root.getRight();
            } else {
                replacementParent = leftMost(root.getRight());
                if (replacementParent == null) {
                    root.setData(root.getRight().getData());
                    root.setRight(root.getRight().getRight());
                } else {
                    root.setData(replacementParent.getLeft().getData());
                    replacementParent.setLeft(
                            replacementParent.getLeft().getRight());
                }
            }
        } else {
            BSTNode<T> parent = findParent(data, root);
            if (parent.getRight() != null
                    && parent.getRight().getData().equals(data)) {
                currNode = parent.getRight();
                removedData = currNode.getData();
                if (currNode.getRight() == null
                        && currNode.getLeft() == null) {
                    parent.setRight(null);
                } else if (currNode.getRight() == null) {
                    parent.setRight(currNode.getLeft());
                } else if (currNode.getLeft() == null) {
                    parent.setRight(currNode.getRight());
                } else {
                    replacementParent = leftMost(currNode.getRight());
                    if (replacementParent == null) {
                        currNode.setData(currNode.getRight().getData());
                        currNode.setRight(currNode.getRight().getRight());
                    } else {
                        currNode.setData(
                                replacementParent.getLeft().getData());
                        replacementParent.setLeft(
                                replacementParent.getLeft().getRight());
                    }
                }
            } else {
                currNode = parent.getLeft();
                removedData = currNode.getData();
                if (currNode.getRight() == null && currNode.getLeft() == null) {
                    parent.setLeft(null);
                } else if (currNode.getLeft() == null) {
                    parent.setLeft(currNode.getRight());
                } else if (currNode.getRight() == null) {
                    parent.setLeft(currNode.getLeft());
                } else {
                    replacementParent = leftMost(currNode.getRight());
                    if (replacementParent == null) {
                        currNode.setData(currNode.getRight().getData());
                        currNode.setRight(currNode.getRight().getRight());
                    } else {
                        currNode.setData(
                                replacementParent.getLeft().getData());
                        replacementParent.setLeft(
                                replacementParent.getLeft().getRight());
                    }
                }
            }
        }
        size--;
        return removedData;
    }

    /** Finds the parent of the node which contains the data.
     * @throws java.util.NoSuchElementException is data is null
     * or if data is not found
     * @param data data of node whose parent is being found
     * @param currNode root of tree being searched
     * @return parent of data's node
     */
    private BSTNode<T> findParent(T data, BSTNode<T> currNode) {
        if (data.compareTo(currNode.getData()) < 0) {
            if (currNode.getLeft() == null) {
                throw new NoSuchElementException("Cannot find data");
            }
            if (currNode.getLeft().getData().equals(data)) {
                return currNode;
            }
            return findParent(data, currNode.getLeft());
        } else {
            if (currNode.getRight() == null) {
                throw new NoSuchElementException("Cannot find data");
            }
            if (currNode.getRight().getData().equals(data)) {
                return currNode;
            }
            return findParent(data, currNode.getRight());
        }
    }

    /**
     * Finds the parent of the leftmost node in the reference frame of the
     * current node.
     * @param currNode starting node from which the leftmost node will be found
     * @return the parent of the leftmost node.]
     */
    private BSTNode<T> leftMost(BSTNode<T> currNode) {
        if (currNode.getLeft() == null) {
            return null;
        }
        if (currNode.getLeft().getLeft() == null) {
            return currNode;
        }
        return leftMost(currNode.getLeft());
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        }
        if (size == 0) {
            throw new NoSuchElementException("Empty tree");
        }
        if (root.getData() == data) {
            return root.getData();
        }
        T same = null;
        BSTNode<T> parent = findParent(data, root);
        if (parent.getRight() != null
                && parent.getRight().getData().equals(data)) {
            same = parent.getRight().getData();
        } else {
            same = parent.getLeft().getData();
        }
        return same;
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        }
        if (root == null) {
            return false;
        }
        if (data.equals(root.getData())) {
            return true;
        }
        return containsHelper(data, root);
    }

    /**
     * Checks if the node or it's children contains the data
     * @param data what's being searched
     * @param currNode node whose children are being searched for the data
     * @return true if data is within the node's hierarchy, false otherwise
     */
    private boolean containsHelper(T data, BSTNode<T> currNode) {
        if (data.compareTo(currNode.getData()) < 0
                && currNode.getLeft() != null) {
            if (currNode.getLeft().getData().equals(data)) {
                return currNode.getLeft().getData().equals(data);
            }
            return containsHelper(data, currNode.getLeft());
        } else if (currNode.getRight() != null) {
            if (currNode.getRight().getData().equals(data)) {
                return currNode.getRight().getData().equals(data);
            }
            return containsHelper(data, currNode.getRight());
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> preorder() {
        List<T> ordered = new ArrayList<T>(size);
        preorderHelper(ordered, root);
        return ordered;
    }

    /**
     * Adds to the list all the data within the node preorder
     * @param accum the list being added to
     * @param currNode node whose data is being added to the list
     */
    private void preorderHelper(List<T> accum, BSTNode<T> currNode) {
        if (currNode != null) {
            accum.add(currNode.getData());
            preorderHelper(accum, currNode.getLeft());
            preorderHelper(accum, currNode.getRight());
        }
    }

    @Override
    public List<T> postorder() {
        List<T> ordered = new ArrayList<T>(size);
        postorderHelper(ordered, root);
        return ordered;
    }

    /**
     * Adds to the list all the data within the node postorder
     * @param accum the list being added to
     * @param currNode node whose data is being added to the list
     */
    private void postorderHelper(List<T> accum, BSTNode<T> currNode) {
        if (currNode != null) {
            postorderHelper(accum, currNode.getLeft());
            postorderHelper(accum, currNode.getRight());
            accum.add(currNode.getData());
        }
    }

    @Override
    public List<T> inorder() {
        List<T> ordered = new ArrayList<T>(size);
        inorderHelper(ordered, root);
        return ordered;
    }

    /**
     * Adds to the list all the data within the node inorder
     * @param accum the list being added to
     * @param currNode node whose data is being added to the list
     */
    private void inorderHelper(List<T> accum, BSTNode<T> currNode) {
        if (currNode != null) {
            inorderHelper(accum, currNode.getLeft());
            accum.add(currNode.getData());
            inorderHelper(accum, currNode.getRight());
        }
    }

    @Override
    public List<T> levelorder() {
        List<T> ordered = new ArrayList<T>(size);
        BSTNode[] accumArr = new BSTNode[size];
        int count = 0;
        int queueSize = 0;
        BSTNode<T> curr = root;
        while (curr != null) {
            ordered.add(curr.getData());
            if (curr.getLeft() != null) {
                accumArr[queueSize] = curr.getLeft();
                queueSize++;
            }
            if (curr.getRight() != null) {
                accumArr[queueSize] = curr.getRight();
                queueSize++;
            }
            curr = accumArr[count];
            count++;
        }
        return ordered;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        return heightHelper(root);
    }

    /**
     * Finds the height of the tree from the node
     * @param currNode node whose children's height is being found
     * @return height from the node entered
     */
    private int heightHelper(BSTNode<T> currNode) {
        if (currNode == null || currNode.getData() == null) {
            return -1;
        }
        int leftHeight = heightHelper(currNode.getLeft());
        int rightHeight = heightHelper(currNode.getRight());
        return Math.max(leftHeight, rightHeight) + 1;
    }

    @Override
    public int depth(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        }
        if (!contains(data)) {
            return -1;
        }
        return depthHelper(root, data);
    }

    /**
     * Finds the depth of the data within the node's hierarchy
     * @param currNode node being searched
     * @param data of the Node who's depth is being found
     * @return depth of the data within the node
     */
    private int depthHelper(BSTNode<T> currNode, T data) {
        if (currNode.getData().equals(data)) {
            return 1;
        } else if (data.compareTo(currNode.getData()) < 0
                && currNode.getLeft() != null) {
            return depthHelper(currNode.getLeft(), data) + 1;
        } else {
            return depthHelper(currNode.getRight(), data) + 1;
        }
    }

    /**
     * THIS METHOD IS ONLY FOR TESTING PURPOSES.
     * DO NOT USE IT IN YOUR CODE
     * DO NOT CHANGE THIS METHOD
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        return root;
    }
}