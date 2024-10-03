import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class MyStackTest {
    public MyStack<String> stringS;
    public String a="a", b="b", c="c", d="d", e="e", f="f";
    public ArrayList<String> fill = new ArrayList<String>();

    // STUDENT: student tests will use the doubleS
    public MyStack<Double> doubleS;
    public Double x = 1.1, y = 2.2, z = 3.3;

    @BeforeEach
    public void setUp() throws Exception {
        stringS = new MyStack<String>(5);
        stringS.push(a);
        stringS.push(b);
        stringS.push(c);

        // STUDENT: add setup for doubleS for student tests
        doubleS = new MyStack<Double>(3);
        doubleS.push(x);
        doubleS.push(y);
    }

    @AfterEach
    public void tearDown() throws Exception {
        stringS = null;
        doubleS = null;
    }

    @Test
    public void testIsEmpty() throws StackUnderflowException {
        assertEquals(false,stringS.isEmpty());
        stringS.pop();
        stringS.pop();
        stringS.pop();
        assertEquals(true, stringS.isEmpty());
    }

    @Test
    public void testIsFull() throws StackOverflowException {
        assertEquals(false, stringS.isFull());
        stringS.push(d);
        stringS.push(e);
        assertEquals(true, stringS.isFull());
    }

    @Test
    public void testPop() {
        try {
            assertEquals(c, stringS.pop());
            assertEquals(b, stringS.pop());
            assertEquals(a, stringS.pop());
            stringS.pop();  // Should cause StackUnderflowException
            assertTrue("This should have caused an StackUnderflowException", false);
        }
        catch (StackUnderflowException e){
            assertTrue("This should have caused an StackUnderflowException", true);
        }
        catch (Exception e){
            assertTrue("This should have caused an StackUnderflowException", false);
        }
    }

    // STUDENT TEST
    @Test
    public void testPopStudent() {
        try {
            assertEquals(y, doubleS.pop());
            assertEquals(x, doubleS.pop());
            doubleS.pop();  // Should cause StackUnderflowException
            assertTrue("This should have caused an StackUnderflowException", false);
        } catch (StackUnderflowException e) {
            assertTrue("This should have caused an StackUnderflowException", true);
        } catch (Exception e) {
            assertTrue("This should have caused an StackUnderflowException", false);
        }
    }

    @Test
    public void testTop() throws StackUnderflowException, StackOverflowException {
        assertEquals(c, stringS.top());
        stringS.push(d);
        assertEquals(d, stringS.top());
        stringS.pop();
        stringS.pop();
        assertEquals(b, stringS.top());		
    }

    @Test
    public void testSize() throws StackUnderflowException, StackOverflowException {
        assertEquals(3, stringS.size());
        stringS.push(d);
        assertEquals(4, stringS.size());
        stringS.pop();
        stringS.pop();
        assertEquals(2, stringS.size());
    }

    @Test
    public void testPush() {
        try {
            assertEquals(3, stringS.size());
            assertEquals(true, stringS.push(d));
            assertEquals(4, stringS.size());
            assertEquals(true, stringS.push(e));
            assertEquals(5, stringS.size());
            // Stack is full, next statement should cause StackOverflowException
            stringS.push(f);
            assertTrue("This should have caused an StackOverflowException", false);
        }
        catch (StackOverflowException e){
            assertTrue("This should have caused an StackOverflowException", true);
        }
        catch (Exception e){
            assertTrue("This should have caused an StackOverflowException", false);
        }
    }

    // STUDENT TEST
    @Test
    public void testPushStudent() {
        try {
            assertEquals(2, doubleS.size());
            assertEquals(true, doubleS.push(z));
            assertEquals(3, doubleS.size());
            // Stack is full, should cause StackOverflowException
            doubleS.push(4.4);
            assertTrue("This should have caused an StackOverflowException", false);
        } catch (StackOverflowException e) {
            assertTrue("This should have caused an StackOverflowException", true);
        } catch (Exception e) {
            assertTrue("This should have caused an StackOverflowException", false);
        }
    }

    @Test
    public void testToString() throws StackOverflowException {
        assertEquals("abc", stringS.toString());
        stringS.push(d);
        assertEquals("abcd", stringS.toString());
        stringS.push(e);
        assertEquals("abcde", stringS.toString());
    }

    // STUDENT TEST
    @Test
    public void testToStringStudent() {
        assertEquals("1.12.2", doubleS.toString());
        try {
            doubleS.push(z);
            assertEquals("1.12.23.3", doubleS.toString());
        } catch (StackOverflowException e) {
            fail("Push should not have thrown StackOverflowException");
        }
    }

    @Test
    public void testToStringDelimiter() throws StackOverflowException {
        assertEquals("a%b%c", stringS.toString("%"));
        stringS.push(d);
        assertEquals("a&b&c&d", stringS.toString("&"));
        stringS.push(e);
        assertEquals("a/b/c/d/e", stringS.toString("/"));
    }

    @Test
    public void testFill() throws StackUnderflowException {
        fill.add("apple");
        fill.add("banana");
        fill.add("carrot");
        // Start with an empty stack
        stringS = new MyStack<String>(5);
        // Fill with an ArrayList
        stringS.fill(fill);
        assertEquals(3, stringS.size());
        assertEquals("carrot", stringS.pop());
        assertEquals("banana", stringS.pop());
        assertEquals("apple", stringS.pop());		
    }

}
