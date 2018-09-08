import java.io.PrintStream;
import java.util.*;

public class SortedLinkedListMultiset<T extends Comparable<? super T>> extends Multiset<T> {
    private Node<T> first;

    public SortedLinkedListMultiset() {

    } // end of SortedLinkedListMultiset()


    @Override
    public void add(T item) {

        for (Node<T> x = first; x != null; x = x.next) {
            if (item.equals(x.item)) {
                x.count++;
                return;
            }
        }

        if (first == null) {
            first = new Node<>(item, null);
        } else {
            if (first.item.compareTo(item) >= 0) {
                first = new Node<>(item, first);
                return;
            }

            for (Node<T> x = first; x != null; x = x.next) {
                if (item.compareTo(x.item) >= 0) {
                    if (x.next == null) {
                        x.next = new Node<>(item, x.next);
                        return;
                    } else if (item.compareTo(x.next.item) <= 0) {
                        x.next = new Node<>(item, x.next);
                        return;
                    }
                }
            }
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
                    if (next != null) {
                        x.next = null;
                    }
                    x.item = null;
                } else {
                    x.count--;
                }
                return;
            } else {
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
                if (next != null) {
                    x.next = null;
                }
                x.item = null;
                return;
            } else {
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


} // end of class SortedLinkedListMultiset