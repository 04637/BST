
import java.io.PrintStream;
import java.util.*;

public class BstMultiset<T extends Comparable<? super T>> extends Multiset<T> {

    private Node<T> root;

    public BstMultiset() {

    } // end of BstMultiset()

    @Override
    public void add(T item) {
        if (root == null) {
            root = new Node<>(item);
            return;
        }
        Node<T> curNode = root;
        while (true) {
            if (item.compareTo(curNode.item) > 0) {
                if (curNode.rightChild == null) {
                    curNode.rightChild = new Node<>(item);
                    return;
                } else {
                    curNode = curNode.rightChild;
                }
            } else if (item.compareTo(curNode.item) < 0) {
                if (curNode.leftChild == null) {
                    curNode.leftChild = new Node<>(item);
                    return;
                } else {
                    curNode = curNode.leftChild;
                }
            } else {
                curNode.count++;
                return;
            }
        }
    } // end of add()


    @Override
    public int search(T item) {
        Node<T> curNode = root;
        while (curNode != null) {
            if (item.compareTo(curNode.item) > 0) {
                curNode = curNode.rightChild;
            } else if (item.compareTo(curNode.item) < 0) {
                curNode = curNode.leftChild;
            } else {
                return curNode.count;
            }
        }
        return 0;
    } // end of add()

    @Override
    public void removeOne(T item) {
        Node<T> curNode = root;
        while (curNode != null) {
            if (item.compareTo(curNode.item) > 0) {
                curNode = curNode.rightChild;
            } else if (item.compareTo(curNode.item) < 0) {
                curNode = curNode.leftChild;
            } else {
                if (curNode.count == 1) {
                    removeAll(item);
                    return;
                } else {
                    curNode.count--;
                    return;
                }

            }
        }
    } // end of removeOne()


    @Override
    public void removeAll(T item) {
        Node<T> currentNode = root;
        Node<T> parentNode = root;
        boolean isLeftChild = true;
        while (currentNode != null && item.compareTo(currentNode.item) != 0) {
            parentNode = currentNode;
            if (item.compareTo(currentNode.item) < 0) {
                currentNode = currentNode.leftChild;
                isLeftChild = true;
            } else {
                currentNode = currentNode.rightChild;
                isLeftChild = false;
            }
        }
        if(currentNode == null){
            return;
        }
        if (currentNode.leftChild == null && currentNode.rightChild == null) {
            if (currentNode == root) {
                root = null;
            } else if (isLeftChild) {
                parentNode.leftChild = null;
            } else {
                parentNode.rightChild = null;
            }
        } else if (currentNode.rightChild == null) {
            if (currentNode == root) {
                root = currentNode.leftChild;
            } else if (isLeftChild) {
                parentNode.leftChild = currentNode.leftChild;
            } else {
                parentNode.rightChild = currentNode.leftChild;
            }
        } else if (currentNode.leftChild == null) {
            if (currentNode == root) {
                root = currentNode.rightChild;
            } else if (isLeftChild) {
                parentNode.leftChild = currentNode.rightChild;
            } else {
                parentNode.rightChild = currentNode.rightChild;
            }
        } else {
            Node<T> directPostNode = getDirectPostNode(currentNode);
            currentNode.item = directPostNode.item;
            currentNode.count = directPostNode.count;
        }
    } // end of removeAll()

    private Node<T> getDirectPostNode(Node<T> delNode) {

        Node<T> parentNode = delNode;
        Node<T> directPostNode = delNode;
        Node<T> currentNode = delNode.rightChild;
        while (currentNode != null) {
            parentNode = directPostNode;
            directPostNode = currentNode;
            currentNode = currentNode.leftChild;
        }
        if (directPostNode != delNode.rightChild) {
            parentNode.leftChild = directPostNode.rightChild;
            directPostNode.rightChild = null;
        }
        return directPostNode;

    }

    private void inOrder(Node<T> rootNode, PrintStream out) {
        if (rootNode != null) {
            inOrder(rootNode.leftChild, out);
            out.println(rootNode.item + printDelim + rootNode.count);
            inOrder(rootNode.rightChild, out);
        }
    }

    @Override
    public void print(PrintStream out) {
        inOrder(root, out);
    } // end of print()

    private static class Node<T> {
        T item;
        Integer count;
        Node<T> leftChild;
        Node<T> rightChild;

        Node(T item) {
            this.item = item;
            this.count = 1;
        }


    }

} // end of class BstMultiset
