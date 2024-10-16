import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SortedDoubleLinkedListTest {

    SortedDoubleLinkedList<String> sortedLinkedString;
    SortedDoubleLinkedList<Double> sortedLinkedDouble;
    SortedDoubleLinkedList<Car> sortedLinkedCar;
    StringComparator comparator;
    DoubleComparator comparatorD;
    CarComparator comparatorCar;

    public Car a = new Car("Ford", "F150", 2005);
    public Car b = new Car("Jeep", "Renegade", 2005);
    public Car c = new Car("Honda", "Civic", 2005);
    public Car d = new Car("Subaru", "Outback", 2005);
    public Car e = new Car("Chevrolet", "Silverado", 2005);
    public Car f = new Car("Chrysler", "PTCruiser", 2005);
    //alphabetic order: e f a c b d

    @Before
    public void setUp() throws Exception {
        comparator = new StringComparator();
        sortedLinkedString = new SortedDoubleLinkedList<String>(comparator);

        comparatorD = new DoubleComparator();
        sortedLinkedDouble = new SortedDoubleLinkedList<Double>(comparatorD);

        comparatorCar = new CarComparator();
        sortedLinkedCar = new SortedDoubleLinkedList<Car>(comparatorCar);
    }

    @After
    public void tearDown() throws Exception {
        comparator = null;
        comparatorD = null;
        comparatorCar = null;
        sortedLinkedString = null;
        sortedLinkedDouble = null;
        sortedLinkedCar = null;
    }

    @Test
    public void testAddToEnd() {
        try {
            sortedLinkedString.addToEnd("Hello");
            assertTrue("Did not throw an UnsupportedOperationException", false);
        }
        catch (UnsupportedOperationException e) {
            assertTrue("Successfully threw an UnsupportedOperationException", true);
        }
        catch (Exception e) {
            assertTrue("Threw an exception other than the UnsupportedOperationException", false);
        }
    }

    @Test
    public void testAddToFront() {
        try {
            sortedLinkedString.addToFront("Hello");
            assertTrue("Did not throw an UnsupportedOperationException", false);
        }
        catch (UnsupportedOperationException e) {
            assertTrue("Successfully threw an UnsupportedOperationException", true);
        }
        catch (Exception e) {
            assertTrue("Threw an exception other than the UnsupportedOperationException", false);
        }
    }

    @Test
    public void testIteratorSuccessfulNext() {
        sortedLinkedCar.add(a);
        sortedLinkedCar.add(b);
        sortedLinkedCar.add(c);
        sortedLinkedCar.add(d);
        ListIterator<Car> iterator = sortedLinkedCar.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(a, iterator.next());
        assertEquals(c, iterator.next());
        assertEquals(b, iterator.next());
        assertEquals(true, iterator.hasNext());
    }

    @Test
    public void testIteratorSuccessfulStringPrevious() {
        sortedLinkedString.add("Begin");
        sortedLinkedString.add("World");
        sortedLinkedString.add("Hello");
        sortedLinkedString.add("Zebra");
        ListIterator<String> iterator = sortedLinkedString.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals("Begin", iterator.next());
        assertEquals("Hello", iterator.next());
        assertEquals("World", iterator.next());
        assertEquals("Zebra", iterator.next());
        assertEquals(true, iterator.hasPrevious());
        assertEquals("Zebra", iterator.previous());
        assertEquals("World", iterator.previous());
        assertEquals("Hello", iterator.previous());
    }

    @Test
    public void testIteratorSuccessfulCarPrevious() {
        sortedLinkedCar.add(e);
        sortedLinkedCar.add(c);
        sortedLinkedCar.add(b);
        sortedLinkedCar.add(d);
        ListIterator<Car> iterator = sortedLinkedCar.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(e, iterator.next());
        assertEquals(c, iterator.next());
        assertEquals(b, iterator.next());
        assertEquals(d, iterator.next());
        assertEquals(true, iterator.hasPrevious());
        assertEquals(d, iterator.previous());
        assertEquals(b, iterator.previous());
        assertEquals(c, iterator.previous());
    }

    @Test
    public void testIteratorSuccessfulDoubleNext() {
        sortedLinkedDouble.add(new Double(8));
        sortedLinkedDouble.add(new Double(6));
        sortedLinkedDouble.add(new Double(1));
        sortedLinkedDouble.add(new Double(2));
        ListIterator<Double> iterator = sortedLinkedDouble.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(new Double(1), iterator.next());
        assertEquals(new Double(2), iterator.next());
        assertEquals(new Double(6), iterator.next());
        assertEquals(true, iterator.hasNext());
    }

    @Test
    public void testIteratorSuccessfulDoublePrevious() {
        sortedLinkedDouble.add(new Double(5));
        sortedLinkedDouble.add(new Double(10));
        sortedLinkedDouble.add(new Double(8));
        sortedLinkedDouble.add(new Double(2));
        ListIterator<Double> iterator = sortedLinkedDouble.iterator();
        assertEquals(new Double(2), iterator.next());
        assertEquals(new Double(5), iterator.next());
        assertEquals(new Double(8), iterator.next());
        assertEquals(true, iterator.hasPrevious());
        assertEquals(new Double(8), iterator.previous());
        assertEquals(true, iterator.hasPrevious());
    }

    @Test
    public void testIteratorNoSuchElementException() {
        sortedLinkedCar.add(e);
        sortedLinkedCar.add(c);
        sortedLinkedCar.add(b);
        sortedLinkedCar.add(d);
        ListIterator<Car> iterator = sortedLinkedCar.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(e, iterator.next());
        assertEquals(c, iterator.next());
        assertEquals(b, iterator.next());
        assertEquals(true, iterator.hasNext());
        assertEquals(d, iterator.next());
        try {
            iterator.next(); //no more elements in list
            assertTrue("Did not throw a NoSuchElementException", false);
        } catch (NoSuchElementException e) {
            assertTrue("Successfully threw a NoSuchElementException", true);
        } catch (Exception e) {
            assertTrue("Threw an exception other than the NoSuchElementException", false);
        }
    }

    @Test
    public void testIteratorUnsupportedOperationExceptionString() {
        sortedLinkedCar.add(e);
        sortedLinkedCar.add(c);
        sortedLinkedCar.add(b);
        sortedLinkedCar.add(d);
        ListIterator<Car> iterator = sortedLinkedCar.iterator();
        try {
            iterator.remove(); //remove is not supported for the iterator
            assertTrue("Did not throw an UnsupportedOperationException", false);
        } catch (UnsupportedOperationException e) {
            assertTrue("Successfully threw an UnsupportedOperationException", true);
        } catch (Exception e) {
            assertTrue("Threw an exception other than the UnsupportedOperationException", false);
        }
    }

    @Test
    public void testAddCar() {
        sortedLinkedCar.add(a);
        sortedLinkedCar.add(b);
        sortedLinkedCar.add(c);
        assertEquals(a, sortedLinkedCar.getFirst());
        assertEquals(b, sortedLinkedCar.getLast());
        sortedLinkedCar.add(d);
        sortedLinkedCar.add(e);
        assertEquals(e, sortedLinkedCar.getFirst());
        assertEquals(d, sortedLinkedCar.getLast());
        assertEquals(d, sortedLinkedCar.retrieveLastElement());
        assertEquals(b, sortedLinkedCar.getLast());
    }

    @Test
    public void testRemoveFirstCar() {
        sortedLinkedCar.add(b);
        sortedLinkedCar.add(c);
        assertEquals(c, sortedLinkedCar.getFirst());
        assertEquals(b, sortedLinkedCar.getLast());
        sortedLinkedCar.add(a);
        assertEquals(a, sortedLinkedCar.getFirst());
        sortedLinkedCar.remove(a, comparatorCar);
        assertEquals(c, sortedLinkedCar.getFirst());
    }

    @Test
    public void testRemoveEndCar() {
        sortedLinkedCar.add(b);
        sortedLinkedCar.add(f);
        assertEquals(f, sortedLinkedCar.getFirst());
        assertEquals(b, sortedLinkedCar.getLast());
        sortedLinkedCar.add(d);
        assertEquals(d, sortedLinkedCar.getLast());
        sortedLinkedCar.remove(d, comparatorCar);
        assertEquals(b, sortedLinkedCar.getLast());
    }

    @Test
    public void testRemoveMiddleCar() {
        sortedLinkedCar.add(a);
        sortedLinkedCar.add(b);
        sortedLinkedCar.add(f);
        assertEquals(f, sortedLinkedCar.getFirst());
        assertEquals(b, sortedLinkedCar.getLast());
        assertEquals(3, sortedLinkedCar.getSize());
        sortedLinkedCar.remove(a, comparatorCar);
        assertEquals(f, sortedLinkedCar.getFirst());
        assertEquals(b, sortedLinkedCar.getLast());
        assertEquals(2, sortedLinkedCar.getSize());
    }

    private class StringComparator implements Comparator<String> {
        @Override
        public int compare(String arg0, String arg1) {
            return arg0.compareTo(arg1);
        }
    }

    private class DoubleComparator implements Comparator<Double> {
        @Override
        public int compare(Double arg0, Double arg1) {
            return arg0.compareTo(arg1);
        }
    }

    private class CarComparator implements Comparator<Car> {
        @Override
        public int compare(Car arg0, Car arg1) {
            return arg0.getMake().compareTo(arg1.getMake());
        }
    }

    private class Car {
        private String make;
        private String model;
        private int year;

        public Car(String make, String model, int year) {
            this.make = make;
            this.model = model;
            this.year = year;
        }

        public String getMake() {
            return make;
        }

        public String getModel() {
            return model;
        }

        public int getYear() {
            return year;
        }

        @Override
        public String toString() {
            return make + " " + model + " " + year;
        }
    }
}
