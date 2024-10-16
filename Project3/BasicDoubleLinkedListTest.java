import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BasicDoubleLinkedListTest {
    private BasicDoubleLinkedList<String> linkedString;
    private BasicDoubleLinkedList<Double> linkedDouble;
    private BasicDoubleLinkedList<Car> linkedCar;
    private StringComparator stringComparator;
    private DoubleComparator doubleComparator;
    private CarComparator carComparator;

    private Car a = new Car("Ford", "F150", 2005);
    private Car b = new Car("Jeep", "Renegade", 2005);
    private Car c = new Car("Honda", "Civic", 2005);
    private Car d = new Car("Subaru", "Outback", 2005);
    private Car e = new Car("Chevrolet", "Silverado", 2005);
    private Car f = new Car("Chrysler", "PTCruiser", 2005);

    private ArrayList<Car> carList = new ArrayList<Car>();

    @Before
    public void setUp() throws Exception {
        linkedString = new BasicDoubleLinkedList<String>();
        linkedString.addToEnd("Hello");
        linkedString.addToEnd("World");
        stringComparator = new StringComparator();

        linkedDouble = new BasicDoubleLinkedList<Double>();
        linkedDouble.addToEnd(15.0);
        linkedDouble.addToEnd(100.0);
        doubleComparator = new DoubleComparator();

        linkedCar = new BasicDoubleLinkedList<Car>();
        linkedCar.addToEnd(b);
        linkedCar.addToEnd(c);
        carComparator = new CarComparator();
    }

    @After
    public void tearDown() throws Exception {
        linkedString = null;
        linkedDouble = null;
        linkedCar = null;
        stringComparator = null;
        doubleComparator = null;
        carComparator = null;
    }

    @Test
    public void testGetSize() {
        assertEquals(2, linkedString.getSize());
        assertEquals(2, linkedDouble.getSize());
        assertEquals(2, linkedCar.getSize());
    }

    @Test
    public void testAddToEnd() {
        assertEquals("World", linkedString.getLast());
        linkedString.addToEnd("End");
        assertEquals("End", linkedString.getLast());

        assertEquals(c, linkedCar.getLast());
        linkedCar.addToEnd(d);
        assertEquals(d, linkedCar.getLast());
    }

    @Test
    public void testAddToFront() {
        assertEquals("Hello", linkedString.getFirst());
        linkedString.addToFront("Begin");
        assertEquals("Begin", linkedString.getFirst());

        assertEquals(b, linkedCar.getFirst());
        linkedCar.addToFront(a);
        assertEquals(a, linkedCar.getFirst());
    }

    @Test
    public void testGetFirst() {
        assertEquals("Hello", linkedString.getFirst());
        linkedString.addToFront("New");
        assertEquals("New", linkedString.getFirst());

        assertEquals(b, linkedCar.getFirst());
        linkedCar.addToFront(a);
        assertEquals(a, linkedCar.getFirst());
    }

    @Test
    public void testGetLast() {
        assertEquals("World", linkedString.getLast());
        linkedString.addToEnd("New");
        assertEquals("New", linkedString.getLast());

        assertEquals(c, linkedCar.getLast());
        linkedCar.addToEnd(d);
        assertEquals(d, linkedCar.getLast());
    }

    @Test
    public void testToArrayList() {
        ArrayList<Car> list;
        linkedCar.addToFront(a);
        linkedCar.addToEnd(d);
        list = linkedCar.toArrayList();
        assertEquals(a, list.get(0));
        assertEquals(b, list.get(1));
        assertEquals(c, list.get(2));
        assertEquals(d, list.get(3));
    }

    @Test
    public void testIteratorSuccessfulNext() {
        linkedString.addToFront("Begin");
        linkedString.addToEnd("End");
        ListIterator<String> iterator = linkedString.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Begin", iterator.next());
        assertEquals("Hello", iterator.next());
        assertEquals("World", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("End", iterator.next());

        linkedCar.addToFront(a);
        linkedCar.addToEnd(d);
        ListIterator<Car> iteratorCar = linkedCar.iterator();
        assertTrue(iteratorCar.hasNext());
        assertEquals(a, iteratorCar.next());
        assertEquals(b, iteratorCar.next());
        assertEquals(c, iteratorCar.next());
        assertTrue(iteratorCar.hasNext());
        assertEquals(d, iteratorCar.next());
    }

    @Test
    public void testIteratorSuccessfulPrevious() {
        linkedCar.addToFront(a);
        linkedCar.addToEnd(d);
        ListIterator<Car> iteratorCar = linkedCar.iterator();
        assertTrue(iteratorCar.hasNext());
        assertEquals(a, iteratorCar.next());
        assertEquals(b, iteratorCar.next());
        assertEquals(c, iteratorCar.next());
        assertEquals(d, iteratorCar.next());
        assertTrue(iteratorCar.hasPrevious());
        assertEquals(d, iteratorCar.previous());
        assertEquals(c, iteratorCar.previous());
        assertEquals(b, iteratorCar.previous());
        assertEquals(a, iteratorCar.previous());
    }

    @Test
    public void testIteratorNoSuchElementExceptionNext() {
        linkedCar.addToFront(a);
        linkedCar.addToEnd(d);
        ListIterator<Car> iteratorCar = linkedCar.iterator();
        assertTrue(iteratorCar.hasNext());
        assertEquals(a, iteratorCar.next());
        assertEquals(b, iteratorCar.next());
        assertEquals(c, iteratorCar.next());
        assertTrue(iteratorCar.hasNext());
        assertEquals(d, iteratorCar.next());

        try {
            iteratorCar.next();
            fail("Did not throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            assertTrue(true); // Correct exception
        }
    }

    @Test
    public void testIteratorNoSuchElementExceptionPrevious() {
        linkedCar.addToFront(a);
        linkedCar.addToEnd(d);
        ListIterator<Car> iteratorCar = linkedCar.iterator();
        assertTrue(iteratorCar.hasNext());
        assertEquals(a, iteratorCar.next());
        assertEquals(b, iteratorCar.next());
        assertEquals(c, iteratorCar.next());
        assertEquals(d, iteratorCar.next());
        assertTrue(iteratorCar.hasPrevious());
        assertEquals(d, iteratorCar.previous());
        assertEquals(c, iteratorCar.previous());
        assertEquals(b, iteratorCar.previous());
        assertEquals(a, iteratorCar.previous());

        try {
            iteratorCar.previous();
            fail("Did not throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            assertTrue(true); // Correct exception
        }
    }

    @Test
    public void testIteratorUnsupportedOperationException() {
        linkedCar.addToFront(a);
        linkedCar.addToEnd(d);
        ListIterator<Car> iteratorCar = linkedCar.iterator();
        assertTrue(iteratorCar.hasNext());
        assertEquals(a, iteratorCar.next());
        assertEquals(b, iteratorCar.next());
        assertEquals(c, iteratorCar.next());
        assertTrue(iteratorCar.hasNext());
        assertEquals(d, iteratorCar.next());

        try {
            iteratorCar.remove();
            fail("Did not throw UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            assertTrue(true); // Correct exception
        }
    }

    @Test
    public void testRemove() {
        linkedCar.addToFront(a);
        assertEquals(a, linkedCar.getFirst());
        linkedCar.remove(a, carComparator);
        assertEquals(b, linkedCar.getFirst());

        linkedCar.addToEnd(d);
        linkedCar.remove(d, carComparator);
        assertEquals(c, linkedCar.getLast());
    }

    @Test
    public void testRetrieveFirstElement() {
        linkedCar.addToFront(a);
        assertEquals(a, linkedCar.retrieveFirstElement());
        assertEquals(b, linkedCar.getFirst());

        linkedString.addToFront("New");
        assertEquals("New", linkedString.retrieveFirstElement());
        assertEquals("Hello", linkedString.getFirst());
    }

    @Test
    public void testRetrieveLastElement() {
        linkedCar.addToEnd(d);
        assertEquals(d, linkedCar.retrieveLastElement());
        assertEquals(c, linkedCar.getLast());

        linkedString.addToEnd("New");
        assertEquals("New", linkedString.retrieveLastElement());
        assertEquals("World", linkedString.getLast());
    }

    private static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s1.compareTo(s2);
        }
    }

    private static class DoubleComparator implements Comparator<Double> {
        @Override
        public int compare(Double d1, Double d2) {
            return d1.compareTo(d2);
        }
    }

    private static class CarComparator implements Comparator<Car> {
        @Override
        public int compare(Car c1, Car c2) {
            return c1.toString().compareTo(c2.toString());
        }
    }

    private static class Car {
        private String make;
        private String model;
        private int year;

        public Car(String make, String model, int year) {
            this.make = make;
            this.model = model;
            this.year = year;
        }

        @Override
        public String toString() {
            return make + " " + model + " " + year;
        }
    }
}
