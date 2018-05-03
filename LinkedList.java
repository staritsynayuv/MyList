import org.omg.CORBA.Object;

import java.util.NoSuchElementException;

public class LinkedList implements Deque, List {
    private static final int NOT_FOUND = -1;

    private static class Node {
        private Object item;
        private Node next;
        private Node prev;


        private Node(Object item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
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
    public void add(Object item) {
        addLast(item);
    }

    @Override
    public void add(int index, Object item) {
        if (index < size/2)
        addFromLeft(index, item);
        else
            addFromRight(index, item);
        ++size;
    }

    private  void addFromLeft(int index, Object item) {
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        Node added = new Node(item, current, (current != null ) ? current.prev : null);
        if (added.next != null)
            added.prev.next = added;
        if (added.prev != null)
            added.next.prev = added;
    }

    private  void addFromRight(int index, Object item) {
        Node current = last;
        for (int i = size-1; i >= index; i++) {
            current = current.prev;
        }
        Node added = new Node(item, current, (current != null ) ? current.prev : null);
        if (added.next != null)
            added.next.prev = added;
        if (added.prev != null)
            added.prev.next = added;
    }

    @Override
    public Object get(int index) {
        Node current = first;
        for (int i = 0; i <= index; i++) {
            current = current.next;
        }

        return current.item;
    }

    @Override
    public int indexOf(Object obj) {
        Node current = first;
        for (int index = 0; index < size; index++) {
            current = current.next;
            if (obj.equals(current.item)) {
                return index;
            }
        }
        return NOT_FOUND;
    }

    @Override
    public int lastIndexOf(Object obj) {
        Node current = first;
        for (int i = size - 1; i >= 0; i--) {
            current = current.next;
            if (obj.equals(current.item)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    @Override
    public void set(int index, Object item) {
        Node current = first;
        for (int i = 0; i <= index; i++) {
            current = first.next;
        }
        current.item = item;

    }

    @Override
    public void remove(int index) {

    }

    @Override
    public boolean remove(Object item) {
        return false;
    }

    @Override
    public List subList(int from, int to) {
        return null;
    }

    private Node first;
    private Node last;
    private int size;

    @Override
    public void addFirst(Object item) {
        if (first != null) {
            first = new Node(item, first, null);
            first.next.prev = first;
        } else
            first = last = new Node(item, null, null);
        ++size;
    }

    @Override
    public void addLast(Object item) {
        if (last != null) {
            last = new Node(item, null, last);
            last.prev.next = last;
        } else
            first = last = new Node(item, null, null);
        ++size;
    }

    @Override
    public Object removeFirst() {
        checkForRange();
        return deleteFirstNode();
    }

    private Object deleteFirstNode() {
        Object deletedItem = first.item;

        first = first.next;
        first.prev.next = null;
        first.prev = null;
        --size;

        return deletedItem;
    }

    private void checkForRange() {
        if (size <= 0)
            throw new NoSuchElementException();
    }

    @Override
    public Object removeLast() {
        checkForRange();
        return deleteLastNode();
    }

    private Object deleteLastNode() {
        Object deletedItem = last.item;

        last = last.prev;
        last.next.prev = null;
        last.next = null;
        --size;

        return deletedItem;
    }

    @Override
    public Object getFirst() {
        checkForRange();
        return first.item;
    }

    @Override
    public Object getLast() {
        checkForRange();
        return last.item;
    }

    @Override
    public Object pollFirst() {
        return (size == 0) ? null : deleteFirstNode();
    }

    @Override
    public Object pollLast() {
        return (size == 0) ? null : deleteLastNode();
    }
}
