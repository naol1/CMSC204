import java.util.Comparator;
import java.util.ListIterator;

/**
 * SortedDoubleLinkedList class that maintains sorted order using a comparator.
 * @param <T> type parameter
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
    
    private Comparator<T> comp;

    /**
     * Creates an empty list that is associated with the specified comparator.
     * @param comparator2 Comparator to compare data elements
     */
    public SortedDoubleLinkedList(Comparator<T> comparator2) {
        comp = comparator2;
    }

    /**
     * Inserts the specified element at the correct position in the sorted list.
     * @param data Data to be added to the list
     * @return A reference to the current object
     */
    public SortedDoubleLinkedList<T> add(T data) {
        Link newLink = new Link(data);
        Link current = firstLink;
        Link previous = null;

        // Case when the list is empty
        if (sizeOfList == 0) {
            firstLink = newLink;
            lastLink = newLink;
            sizeOfList++;
            return this;
        }

        // Case when the list has one element
        if (sizeOfList == 1) {
            if (comp.compare(data, current.data) <= 0) {
                super.addToFront(data);
            } else {
                super.addToEnd(data);
            }
            return this;
        }

        // Traverse the list to find the correct insertion position
        while (current != null && comp.compare(current.data, data) < 0) {
            previous = current;
            current = current.next;
        }

        // Insert at the beginning of the list
        if (current == firstLink) {
            super.addToFront(data);
        }
        // Insert at the end of the list
        else if (current == null) {
            super.addToEnd(data);
        }
        // Insert in the middle of the list
        else {
            previous.next = newLink;
            newLink.previous = previous;
            newLink.next = current;
            current.previous = newLink;
            sizeOfList++;
        }

        return this;
    }

    @Override
    /**
     * Operation invalid for sorted list
     */
    public SortedDoubleLinkedList<T> addToEnd(T data) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    /**
     * Operation invalid for sorted list
     */
    public SortedDoubleLinkedList<T> addToFront(T data) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    /**
     * Implements the iterator by calling the super class iterator method.
     * @return an iterator positioned at the head of the list
     */
    public ListIterator<T> iterator() {
        return super.iterator();
    }

    @Override
    /**
     * Implements the remove operation by calling the super class remove method.
     * @param targetData Data element to be removed
     * @param comparator Comparator to determine equality of data elements
     * @return Reference to the current object
     */
    public SortedDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator) {
        super.remove(targetData, comparator);
        return this;
    }
}
