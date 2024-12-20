import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class is a generic iterator that enables iteration over a LinkedList.
 * @param <T> Represents the type of data that can be used in this class.
 * @author Sahil Hora
 * @version 1.0
 */
public class LinkedListIterator<T> implements Iterator<T> {
    private Node<T> next;

    /**
     * A constructor that takes in a LinkedList object and sets the value of next.
     * @param list LinkedList object being passed in.
     */
    public LinkedListIterator(LinkedList list) {
        if (list == null) {
            throw new IllegalArgumentException("List cannot be empty.");
        }
        this.next = list.getHead();
    }

    @Override
    public boolean hasNext() {
        if (next == null) {
            return false;
        }
        return true;
    }

    @Override
    public T next() {
        if (hasNext())  {
            T current = next.getData();
            next = next.getNext();
            return current;
        }
        throw new NoSuchElementException("No more nodes left to iterate over!");
    }
}
