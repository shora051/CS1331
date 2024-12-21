/**
 * Represents an individual node.
 * @param <T> The type of data being stored.
 * @author Sahil Hora
 * @version 1.0
 */
public class Node<T> {
    private T data;
    private Node<T> next;

    /**
     * Constructor that sets the data and next fields.
     * @param data The data of the node.
     * @param next The next node.
     */
    public Node(T data, Node<T> next) {
        setData(data);
        setNext(next);
    }

    /**
     * Another constructor that just takes in data field.
     * @param data The data of the node.
     */
    public Node(T data) {
        this(data, null);
    }

    /**
     * Returns the data stored in the node (whichever type it is).
     * @return the data.
     */
    public T getData() {
        return data;
    }

    /**
     * Returns the next node that the current node is connected to.
     * @return The next node.
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Setter for this node's data.
     * @param data Data field that is being set to a new data field.
     */
    public void setData(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be set to null.");
        } else {
            this.data = data;
        }
    }

    /**
     * Setter for the next field.
     * @param next The next node that user wants to refer.
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }
}
