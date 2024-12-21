import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Generic class that represents a LinkedList.
 * @param <T> Data Type.
 * @author Sahil Hora
 * @version 1.0
 */
public class LinkedList<T> implements List<T> {
    private Node<T> head;
    private int size;

    /**
     * No-Argument Consutructor.
     */
    public LinkedList() {
        head = null;
        size = 0;
    }

    /**
     * Constructor that takes in an array of type T.
     * @param data The array being taken in and being set into a LinkedList.
     */
    public LinkedList(T[] data) {
        if (data == null) {
            throw new IllegalArgumentException("Data array is empty.");
        }
        for (T datum : data) {
            add(datum); // keep adding to end
        }
    }

    /**
     * Returns the head of the Linked List structure.
     * @return Returns Linked List head.
     */
    public Node<T> getHead() {
        return head;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(
                String.format("===== LINKEDLIST %d =====\nisEmpty: %s\nsize: %d\nhead: %s\ndata: [",
                        hashCode(),
                        isEmpty(),
                        size(),
                        (head == null ? "null" : head.getData())));

        T[] data = toArray();
        if (data == null) {
            sb.append("TODO: Implement toArray method...");
        } else {
            for (int i = 0; i < data.length - 1; ++i) {
                sb.append(String.format("%s, ", data[i])); // append all but last value
            }
            if (data.length > 0) {
                sb.append(String.format("%s", data[data.length - 1])); // append last value
            }
        }
        sb.append("]\n============================");
        return sb.toString();
    }

    @Override
    public void add(T element) throws IllegalArgumentException {
        if (element == null) {
            throw new IllegalArgumentException("Element is null.");
        }

        Node<T> node = new Node<>(element);
        if (head == null) {
            head = node;
        } else {
            Node<T> finalNode = head;
            while (finalNode.getNext() != null) {
                finalNode = finalNode.getNext();
            }
            finalNode.setNext(node);
        }
        size++;
    }

    @Override
    public void add(int index, T element) throws IndexOutOfBoundsException, IllegalArgumentException {
        if (index > size() || index < 0) {
            throw new IndexOutOfBoundsException("Passed in index is out of bounds!");
        }
        if (element == null) {
            throw new IllegalArgumentException("Element passed in is null.");
        }

        Node<T> beingAdded = new Node<>(element);
        if (isEmpty()) {
            beingAdded.setNext(head);
            head = beingAdded;
        } else if (index == 0) {
            Node<T> tempHead = head;
            head = beingAdded;
            head.setNext(tempHead);
        } else {
            Node<T> tempNode = head;
            for (int i = 0; i < index - 1; ++i) {
                tempNode = tempNode.getNext();
            }
            beingAdded.setNext(tempNode.getNext());
            tempNode.setNext(beingAdded);
        }
        size++;
    }

    @Override
    public T remove() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        Node<T> frontNode = head;
        head = head.getNext();
        size--;
        return frontNode.getData();
    }

    @Override
    public T remove(int index) throws NoSuchElementException, IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new NoSuchElementException("The list is empty.");
        }
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException("Index is invalid.");
        }

        Node<T> currentNode = head;
        Node<T> remove;
        if (index == 0) {
            head = head.getNext();
            size--;
            return currentNode.getData();
        } else {
            for (int i = 0; i < index - 1; ++i) {
                currentNode = currentNode.getNext();
            }
            remove = currentNode.getNext();
            currentNode.setNext(remove.getNext());
            size--;
            return remove.getData();
        }

    }

    @Override
    public T remove(T element) throws IllegalArgumentException, NoSuchElementException {
        if (element == null) {
            throw new IllegalArgumentException("Passed in element is null.");
        }

        int index;
        boolean elementNotFound = true;
        Node<T> remove = head;
        Node<T> back = head;
        for (index = 0; index < size(); ++index) {
            if (index > 1) {
                back = back.getNext();
            }
            if (remove.getData().equals(element)) {
                elementNotFound = false;
                break;
            }
            remove = remove.getNext();
        }

        if (elementNotFound) {
            throw new NoSuchElementException("Element doesn't exist.");
        } else if (index == 0) {
            head = remove.getNext();
        } else {
            back.setNext(remove.getNext());
        }
        size--;
        return remove.getData();
    }

    @Override
    public T set(int index, T element) throws IndexOutOfBoundsException, IllegalArgumentException {
        if ((index >= size() || index < 0) && element == null) {
            throw new IndexOutOfBoundsException("Index is out of bounds AND element is null");
        }
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        if (element == null) {
            throw new IllegalArgumentException("Element is null.");
        }

        if (index == 0) {
            T data = head.getData();
            Node<T> added = new Node<>(element);
            added.setNext(head.getNext());
            head = added;
            return data;
        }
        Node<T> currentNode = head;
        for (int i = 0; i < index - 1; ++i) {
            currentNode = currentNode.getNext();
        }
        Node<T> nodeChange = currentNode.getNext();
        T data = nodeChange.getData();
        nodeChange = new Node<>(element, nodeChange.getNext());
        currentNode.setNext(nodeChange);
        return data;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }

        Iterator<T> iterate = iterator();
        int position = 0;
        T data = head.getData();
        while (position <= index) {
            data = iterate.next();
            position++;
        }
        return data;
    }

    @Override
    public boolean contains(T element) throws IllegalArgumentException {
        if (element == null) {
            throw new IllegalArgumentException("Element passed in is null.");
        }
        Iterator<T> iterate = iterator();
        while (iterate.hasNext()) {
            if (iterate.next().equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
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
    public Iterator<T> iterator() {
        return new LinkedListIterator(this);
    }

    /**
     * Turns LinkedList into an array of element of type T.
     * @return an array of type T.
     */
    public T[] toArray() {
        T[] arr = (T[]) new Object[size];
        int position = 0;
        Iterator<T> iterate = iterator();
        while (iterate.hasNext() && position < size) {
            arr[position++] = iterate.next();
        }
        return arr;
    }
}