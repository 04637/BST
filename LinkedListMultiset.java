import java.io.PrintStream;
import java.util.*;

public class LinkedListMultiset<T> extends Multiset<T> {

    private Node<T> first;
    private Node<T> last;

    public LinkedListMultiset() {
    } // end of LinkedListMultiset()


    @Override
    public void add(T item) {
        for (Node<T> x = first; x != null; x = x.next) {
            if (item.equals(x.item)) {
                x.count++;
                return;
            }
        }
        final Node<T> l = last;
        final Node<T> newNode = new Node<>(item, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
    } // end of add()


    @Override
    public int search(T item) {
        for (Node<T> x = first; x != null; x = x.next) {
            if (item.equals(x.item)) {
                return x.count;
            }
        }
        return 0;
    } // end of add()


    @Override
    public void removeOne(T item) {
        Node<T> prev = null;
        for (Node<T> x = first; x != null; x = x.next) {
            if (item.equals(x.item)) {
                if (x.count == 1) {
                    final Node<T> next = x.next;
                    if (prev == null) {
                        first = next;
                    } else {
                        prev.next = next;
                    }
                    if (next == null) {
                        last = prev;
                    } else {
                        x.next = null;
                    }
                    x.item = null;
                } else {
                    x.count--;
                }
                return;
            }else{
                prev = x;
            }
        }
    } // end of removeOne()


    @Override
    public void removeAll(T item) {
        Node<T> prev = null;
        for (Node<T> x = first; x != null; x = x.next) {
            if (item.equals(x.item)) {
                final Node<T> next = x.next;
                if (prev == null) {
                    first = next;
                } else {
                    prev.next = next;
                }
                if (next == null) {
                    last = prev;
                } else {
                    x.next = null;
                }
                x.item = null;
                return;
            }else{
                prev = x;
            }
        }
    } // end of removeAll()


    @Override
    public void print(PrintStream out) {
        Node<T> p = first;

        while (p != null) {
            out.println(p.item + printDelim + p.count);
            p = p.next;
        }
    } // end of print()


    private static class Node<T> {
        T item;
        Integer count;
        Node<T> next;

        Node(T item, Node<T> next) {
            this.item = item;
            this.count = 1;
            this.next = next;
        }
    }

} // end of class LinkedListMultiset