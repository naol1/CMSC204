import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * BasicDoubleLinkedList class
 * @param <T> type parameter
 */
public class BasicDoubleLinkedList<T> {
    protected Link firstLink;
    protected Link lastLink;
    protected int sizeOfList = 0;

    /**
     * Default constructor sets firstLink and lastLink to null
     */
    public BasicDoubleLinkedList() {
        firstLink = null;
        lastLink = null;
    }

    /**
     * Returns the number of elements in the list
     * @return sizeOfList
     */
    public int getSize() {
        return sizeOfList;
    }

    /**
     * Checks if the list is empty
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return sizeOfList == 0;
    }

    /**
     * Adds an element to the back of the list 
     * @param data data for the node to add to the list
     * @return reference to current object
     */
    public BasicDoubleLinkedList<T> addToEnd(T data) {
        Link tempLink = new Link(data);
        if (isEmpty()) {
            firstLink = tempLink;
        } else {
            lastLink.next = tempLink;
            tempLink.previous = lastLink;
        }
        lastLink = tempLink;
        sizeOfList++;
        return this;
    }

    /**
     * Adds an element to the front of the list
     * @param data data for the node to add to the list
     * @return reference to current object
     */
    public BasicDoubleLinkedList<T> addToFront(T data) {
        Link tempLink = new Link(data);
        if (isEmpty()) {
            lastLink = tempLink;
        } else {
            tempLink.next = firstLink;
            firstLink.previous = tempLink;
        }
        firstLink = tempLink;
        sizeOfList++;
        return this;
    }

    /**
     * Returns the first element of the list
     * @return data or null
     */
    public T getFirst() {
        if (isEmpty()) {
            return null;
        } else {
            return firstLink.data;
        }
    }

    /**
     * Returns the last element of the list
     * @return data or null
     */
    public T getLast() {
        if (isEmpty()) {
            return null;
        } else {
            return lastLink.data;
        }
    }

    /**
     * Removes the first instance of targetData in the list
     * @param targetData data element to be removed
     * @param comparator comparator to determine equality of data elements
     * @return reference to current object
     */
    public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator) {
        Link current = firstLink;

        while (current != null) {
            if (comparator.compare(targetData, current.data) == 0) {
                if (current == firstLink) {
                    firstLink = current.next;
                    if (firstLink != null) {
                        firstLink.previous = null;
                    }
                } else if (current == lastLink) {
                    lastLink = current.previous;
                    if (lastLink != null) {
                        lastLink.next = null;
                    }
                } else {
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                }
                sizeOfList--;
                return this;
            }
            current = current.next;
        }

        return this; // Element not found
    }

    /**
     * Removes and returns the first element of the list
     * @return first data element or null
     */
    public T retrieveFirstElement() {
        if (isEmpty()) {
            return null;
        }
        T first = getFirst();
        if (firstLink == lastLink) {
            firstLink = lastLink = null;
        } else {
            firstLink = firstLink.next;
            firstLink.previous = null;
        }
        sizeOfList--;
        return first;
    }

    /**
     * Removes and returns the last element of the list
     * @return last data element or null
     */
    public T retrieveLastElement() {
        if (isEmpty()) {
            return null;
        }
        T last = getLast();
        if (firstLink == lastLink) {
            firstLink = lastLink = null;
        } else {
            lastLink = lastLink.previous;
            lastLink.next = null;
        }
        sizeOfList--;
        return last;
    }

    /**
     * Returns an ArrayList of the items in the list from head of list to tail of list
     * @return an ArrayList of the items in the list
     */
    public ArrayList<T> toArrayList() {
        ArrayList<T> list = new ArrayList<>();
        ListIterator<T> it = iterator();
        while (it.hasNext()) {
            list.add(it.next());
        }
        return list;
    }

    /**
     * Creates a new iterator object and returns it
     * @return iterator object
     */
    public ListIterator<T> iterator() {
        return new DoubleLinkedListIterator();
    }

    /**
     * Link class
     */
    protected class Link {
        public Link previous;
        public Link next;
        public T data;

        /**
         * Constructor to assign value to data
         * @param data new data element to assign
         */
        Link(T data) {
            this.data = data;
            this.previous = null;
            this.next = null;
        }
    }

    /**
     * DoubleLinkedListIterator class
     */
    protected class DoubleLinkedListIterator implements ListIterator<T> {
        private Link previousLink;
        private Link currentLink;

        /**
         * Constructor to assign previousLink and currentLink starting values
         */
        DoubleLinkedListIterator() {
            previousLink = null;
            currentLink = firstLink;
        }

        @Override
        public boolean hasNext() {
            return currentLink != null;
        }

        @Override
        public boolean hasPrevious() {
            return previousLink != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            previousLink = currentLink;
            currentLink = currentLink.next;
            return previousLink.data;
        }

        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            currentLink = previousLink;
            previousLink = previousLink.previous;
            return currentLink.data;
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(T e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(T e) {
            throw new UnsupportedOperationException();
        }
    }
}
