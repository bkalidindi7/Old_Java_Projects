import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Creates an AVL Tree
 *
 * @author YOUR_NAME HERE
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {

    // Do not make any new instance variables.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty BST
     */
    public AVL() {
        root = null;
        size = 0;
    }

    /**
     * Initializes the AVL with the data in the collection. The data
     * should be added in the same order it is in the collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
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
        if (root == null) {
            root = new AVLNode<T>(data);
            size++;
        } else {
            AVLNode<T> parent = getParent(root, data);
            AVLNode<T> added = new AVLNode<T>(data);
            if (parent.getData().compareTo(data) > 0) {
                parent.setLeft(added);
                size++;
            } else if (parent.getData().compareTo(data) < 0) {
                parent.setRight(added);
                size++;
            } else {
                return;
            }
            added.setHeight(0);
            added.setBalanceFactor(0);
            recalculateHeightBalance(root, added);
            findImbalanced(root, added);
        }
    }

    /**
     * Returns parent of node's data
     * @param currNode starting node
     * @param data node being added
     * @return the parent node
     */
    private AVLNode<T> getParent(AVLNode<T> currNode, T data) {
        if (data.compareTo(currNode.getData()) < 0) {
            if (currNode.getLeft() == null) {
                return currNode;
            }
            return getParent(currNode.getLeft(), data);
        }
        if (data.compareTo(currNode.getData()) > 0) {
            if (currNode.getRight() == null) {
                return currNode;
            }
            return getParent(currNode.getRight(), data);
        }
        return currNode;
    }

    /**
     * Searches for first imbalanced node within tree starting from
     * the child node
     * @param start beginning node
     * @param finish data being searched
     *
     */
    private void findImbalanced(AVLNode<T> start, AVLNode<T> finish) {
        T end = finish.getData();
        if (end.compareTo(start.getData()) < 0) {
            findImbalanced(start.getLeft(), finish);
        } else if (end.compareTo(start.getData()) > 0) {
            findImbalanced(start.getRight(), finish);
        }
        if (Math.abs(start.getBalanceFactor()) > 1) {
            restructure(start);
        }
    }

    /**
     * Rebalances the tree
     * @param z the gp node
     */
    private void restructure(AVLNode<T> z) {
        AVLNode<T> y;
        AVLNode<T> x;
        if (z.getRight() == null) {
            y = z.getLeft();
        } else if (z.getLeft() == null) {
            y = z.getRight();
        } else {
            y = (z.getLeft().getHeight() < z.getRight().getHeight())
                    ? z.getRight() : z.getLeft();
        }
        if (y.getRight() == null) {
            x = y.getLeft();
        } else if (y.getLeft() == null) {
            x = y.getRight();
        } else {
            x = (y.getLeft().getHeight() < y.getRight().getHeight())
                    ? y.getRight() : y.getLeft();
        }
        AVLNode<T> ggp = null;
        if (z != root) {
            ggp = findParent(z.getData(), root);
        }
        if (z.getLeft() == y && y.getLeft() == x) {
            rotateRight1(x, y, z);
        }
        if (z.getRight() == y && y.getRight() == x) {
            rotateLeft1(y, z);
        }
        if (z.getLeft() == y && y.getRight() == x) {
            doubleRotateRight(x, y, z);
        }
        if (z.getRight() == y && y.getLeft() == x) {
            doubleRotateLeft(x, y, z);
        }
        if (ggp != null) {
            recalculateHeightBalance(root, ggp);
        }
    }

    /**
     * Single left rotation
     * @param y parent node
     * @param z grand parent node
     */
    private void rotateLeft1(AVLNode<T> y, AVLNode<T> z) {
        AVLNode<T> t0 = z.getLeft();
        AVLNode<T> t1 = y.getLeft();
        int t0Height = 0;
        int t1Height = 0;
        int zHeight = 0;
        int xHeight = 0;
        if (t1 != null) {
            t1Height = t1.getHeight() + 1;
        }
        if (t0 != null) {
            t0Height = t0.getHeight() + 1;
        }
        z.setHeight(Math.max(t0Height, t1Height));
        y.setHeight(Math.max(y.getRight().getHeight(), z.getHeight()) + 1);
        y.setBalanceFactor(0);
        z.setBalanceFactor(t0Height - t1Height);
        if (z != root) {
            AVLNode<T> ggp = findParent(z.getData(), root);
            if (ggp.getLeft().equals(z)) {
                ggp.setLeft(y);
                y.setLeft(z);
                z.setRight(t1);
            } else {
                ggp.setRight(y);
                y.setLeft(z);
                z.setRight(t1);
            }
        } else {
            root = y;
            y.setLeft(z);
            z.setRight(t1);
        }
    }

    /**
     * Single right rotation
     * @param y parent node
     * @param z grand parent node
     * @param x child node
     */
    private void rotateRight1(AVLNode<T> x, AVLNode<T> y, AVLNode<T> z) {
        AVLNode<T> t3 = z.getRight();
        AVLNode<T> t2 = y.getRight();
        int t2Height = 0;
        int t3Height = 0;
        if (t3 != null) {
            t3Height = t3.getHeight() + 1;
        }
        if (t2 != null) {
            t2Height = t2.getHeight() + 1;
        }
        y.setBalanceFactor(0);
        z.setBalanceFactor(t2Height - t3Height);
        z.setHeight(Math.max(t2Height, t3Height));
        y.setHeight(Math.max(x.getHeight(), z.getHeight()) + 1);
        if (z != root) {
            AVLNode<T> ggp = findParent(z.getData(), root);
            if (ggp.getLeft() == z) {
                ggp.setLeft(y);
                y.setRight(z);
                z.setLeft(t2);
            } else {
                ggp.setRight(y);
                y.setRight(z);
                z.setLeft(t2);
            }
        } else {
            root = y;
            y.setRight(z);
            z.setLeft(t2);
        }
    }

    /**
     * Rotate double left
     * @param x child
     * @param y parent
     * @param z grandparent
     */
    private void doubleRotateLeft(AVLNode<T> x, AVLNode<T> y, AVLNode<T> z) {
        AVLNode<T> t0 = z.getLeft();
        AVLNode<T> t1 = x.getLeft();
        AVLNode<T> t2 = x.getRight();
        AVLNode<T> t3 = y.getRight();
        int t0Height = 0;
        int t1Height = 0;
        int t2Height = 0;
        int t3Height = 0;
        if (t0 != null) {
            t0Height = t0.getHeight() + 1;
        }
        if (t1 != null) {
            t1Height = t1.getHeight() + 1;
        }
        if (t2 != null) {
            t2Height = t2.getHeight() + 1;
        }
        if (t3 != null) {
            t3Height = t3.getHeight() + 1;
        }
        y.setHeight(Math.max(t3Height, t2Height));
        z.setHeight(Math.max(t0Height, t1Height));
        x.setHeight(Math.max(z.getHeight(), y.getHeight()) + 1);
        y.setBalanceFactor(t2Height - t3Height);
        x.setBalanceFactor(z.getHeight() - y.getHeight());
        z.setBalanceFactor(t0Height - t1Height);
        if (z != root) {
            AVLNode<T> ggp = findParent(z.getData(), root);
            if (ggp.getLeft() == z) {
                ggp.setLeft(x);
                x.setRight(y);
                y.setLeft(t2);
                x.setLeft(z);
                z.setRight(t1);
            } else {
                ggp.setRight(x);
                x.setRight(y);
                y.setLeft(t2);
                x.setLeft(z);
                z.setRight(t1);
            }
        } else {
            root = x;
            x.setRight(y);
            y.setLeft(t2);
            x.setLeft(z);
            z.setRight(t1);
        }
    }

    /**
     * Rotate double right
     * @param x child
     * @param y parent
     * @param z grandparent
     */
    private void doubleRotateRight(AVLNode<T> x, AVLNode<T> y, AVLNode<T> z) {
        AVLNode<T> t0 = z.getRight();
        AVLNode<T> t1 = x.getRight();
        AVLNode<T> t2 = x.getLeft();
        AVLNode<T> t3 = y.getLeft();
        int t0Height = 0;
        int t1Height = 0;
        int t2Height = 0;
        int t3Height = 0;
        if (t0 != null) {
            t0Height = t0.getHeight() + 1;
        }
        if (t1 != null) {
            t1Height = t1.getHeight() + 1;
        }
        if (t2 != null) {
            t2Height = t2.getHeight() + 1;
        }
        if (t3 != null) {
            t3Height = t3.getHeight() + 1;
        }
        y.setHeight(Math.max(t3Height, t2Height));
        z.setHeight(Math.max(t0Height, t1Height));
        x.setHeight(Math.max(z.getHeight(), y.getHeight()) + 1);
        y.setBalanceFactor(t3Height - t2Height);
        x.setBalanceFactor(y.getHeight() - z.getHeight());
        z.setBalanceFactor(t1Height - t0Height);
        if (z != root) {
            AVLNode<T> ggp = findParent(z.getData(), root);
            if (ggp.getLeft() == z) {
                ggp.setLeft(x);
                x.setRight(z);
                y.setRight(t2);
                x.setLeft(y);
                z.setLeft(t1);
            } else {
                ggp.setRight(x);
                x.setRight(z);
                y.setRight(t2);
                x.setLeft(y);
                z.setLeft(t1);
            }
        } else {
            root = x;
            x.setRight(z);
            y.setRight(t2);
            x.setLeft(y);
            z.setLeft(t1);
        }
    }

    /**
     * Recalculates node's height and balance
     * @param currNode starting node
     * @param end terminating node
     * @return height of currNode
     */
    private int recalculateHeightBalance(AVLNode<T> currNode, AVLNode<T> end) {
        if (currNode.getData().equals(end.getData())) {
            int rightHeight = 0;
            int leftHeight = 0;
            //Newly added
            if (currNode.getRight() == null && currNode.getLeft() == null) {
                currNode.setHeight(0);
                currNode.setBalanceFactor(0);
                return 0;
            } else { //GGP
                if (currNode.getLeft() != null) {
                    leftHeight = currNode.getLeft().getHeight() + 1;
                }
                if (currNode.getRight() != null) {
                    rightHeight = currNode.getRight().getHeight() + 1;
                }
                currNode.setHeight(Math.max(leftHeight, rightHeight));
                currNode.setBalanceFactor(leftHeight - rightHeight);
                return currNode.getHeight();
            }
        }
        if (currNode.getData().compareTo(end.getData()) > 0) {
            int rightH;
            if (currNode.getRight() != null) {
                rightH = currNode.getRight().getHeight();
            } else {
                rightH = 0;
            }
            int leftH = recalculateHeightBalance(currNode.getLeft(), end);
            if (leftH == 0) {
                if (rightH == 0) {
                    currNode.setHeight(1);
                    if (currNode.getRight() != null) {
                        currNode.setBalanceFactor(0);
                    } else {
                        currNode.setBalanceFactor(1);
                    }
                } else {
                    currNode.setHeight(rightH + 1);
                    currNode.setBalanceFactor(leftH - rightH);
                }
            } else {
                currNode.setHeight(Math.max(leftH, rightH) + 1);
                if (currNode.getRight() == null) {
                    currNode.setBalanceFactor((leftH + 1) - rightH);
                } else {
                    currNode.setBalanceFactor(leftH - rightH);
                }
            }
        }
        if (currNode.getData().compareTo(end.getData()) < 0) {
            int leftH;
            if (currNode.getLeft() != null) {
                leftH = currNode.getLeft().getHeight();
            } else {
                leftH = 0;
            }
            int rightH = recalculateHeightBalance(currNode.getRight(), end);
            if (rightH == 0) {
                if (leftH == 0) {
                    currNode.setHeight(1);
                    if (currNode.getLeft() != null) {
                        currNode.setBalanceFactor(0);
                    } else {
                        currNode.setBalanceFactor(-1);
                    }
                } else {
                    currNode.setHeight(leftH + 1);
                    currNode.setBalanceFactor(leftH - rightH);
                }
            } else {
                currNode.setHeight(Math.max(leftH, rightH) + 1);
                if (currNode.getLeft() == null) {
                    currNode.setBalanceFactor(leftH - (rightH + 1));
                } else {
                    currNode.setBalanceFactor(leftH - rightH);
                }
            }
        }
        return currNode.getHeight();
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        AVLNode<T> replacementParent = null;
        AVLNode<T> currNode;
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
                replacementParent = rightMost(root.getLeft());
                if (replacementParent == null) {
                    root.setData(root.getLeft().getData());
                    root.setLeft(root.getLeft().getLeft());
                } else {
                    root.setData(replacementParent.getRight().getData());
                    replacementParent.setRight(
                            replacementParent.getRight().getLeft());
                }
            }
        } else {
            AVLNode<T> parent = findParent(data, root);
            if (parent.getRight() != null
                    && parent.getRight().getData().equals(data)) {
                currNode = parent.getRight();
                removedData = currNode.getData();
                if (currNode.getRight() == null
                        && currNode.getLeft() == null) {
                    parent.setRight(null);
                    replacementParent = parent;
                } else if (currNode.getRight() == null) {
                    parent.setRight(currNode.getLeft());
                    replacementParent = parent;
                } else if (currNode.getLeft() == null) {
                    parent.setRight(currNode.getRight());
                    replacementParent = parent;
                } else {
                    replacementParent = rightMost(currNode.getLeft());
                    if (replacementParent == null) {
                        currNode.setData(currNode.getRight().getData());
                        currNode.setRight(currNode.getRight().getRight());
                    } else {
                        currNode.setData(
                                replacementParent.getRight().getData());
                        replacementParent.setRight(
                                replacementParent.getRight().getLeft());
                    }
                }
            } else {
                currNode = parent.getLeft();
                removedData = currNode.getData();
                if (currNode.getRight() == null && currNode.getLeft() == null) {
                    parent.setLeft(null);
                    replacementParent = parent;
                } else if (currNode.getLeft() == null) {
                    parent.setLeft(currNode.getRight());
                    replacementParent = parent;
                } else if (currNode.getRight() == null) {
                    parent.setLeft(currNode.getLeft());
                    replacementParent = parent;
                } else {
                    replacementParent = rightMost(currNode.getLeft());
                    if (replacementParent == null) {
                        currNode.setData(currNode.getLeft().getData());
                        currNode.setLeft(currNode.getLeft().getLeft());
                    } else {
                        currNode.setData(
                                replacementParent.getRight().getData());
                        replacementParent.setRight(
                                replacementParent.getRight().getLeft());
                    }
                }
            }
        }
        size--;
        if (replacementParent != null) {
            recalculateHeightBalance(root, replacementParent);
            findImbalanced(root, replacementParent);
            recalculateHeightBalance(root, replacementParent);
        }
        return removedData;
    }

    /** Finds the parent of the node which contains the data.
     * @throws java.util.NoSuchElementException is data is null
     * or if data is not found
     * @param data data of node whose parent is being found
     * @param currNode root of tree being searched
     * @return parent of data's node
     */
    private AVLNode<T> findParent(T data, AVLNode<T> currNode) {
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
     * Finds the parent of the rightMost node in the reference frame of the
     * current node.
     * @param currNode starting node from which the rightMost node will be found
     * @return the parent of the rightMost node.]
     */
    private AVLNode<T> rightMost(AVLNode<T> currNode) {
        if (currNode.getRight() == null) {
            return null;
        }
        if (currNode.getRight().getRight() == null) {
            return currNode;
        }
        return rightMost(currNode.getRight());
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
        AVLNode<T> parent = findParent(data, root);
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
    private boolean containsHelper(T data, AVLNode<T> currNode) {
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
    private void preorderHelper(List<T> accum, AVLNode<T> currNode) {
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
    private void postorderHelper(List<T> accum, AVLNode<T> currNode) {
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
    private void inorderHelper(List<T> accum, AVLNode<T> currNode) {
        if (currNode != null) {
            inorderHelper(accum, currNode.getLeft());
            accum.add(currNode.getData());
            inorderHelper(accum, currNode.getRight());
        }
    }

    @Override
    public List<T> levelorder() {
        List<T> ordered = new ArrayList<T>(size);
        AVLNode[] accumArr = new AVLNode[size];
        int count = 0;
        int queueSize = 0;
        AVLNode<T> curr = root;
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
    private int heightHelper(AVLNode<T> currNode) {
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
    private int depthHelper(AVLNode<T> currNode, T data) {
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
    public AVLNode<T> getRoot() {
        return root;
    }
}
