import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StackTest {
    private Stack<String> stack;

    @Before
    public void setUp(){
        stack = new Stack<>();
        stack.push("1");
        stack.push("2");
    }

    @Test
    public void sizeTest(){
        assertEquals(stack.size(), 2);
        stack.push("3");
        assertEquals(stack.size(), 3);
        stack.pop();
        stack.pop();
        assertEquals(stack.size(), 1);
        stack.pop();
        assertEquals(stack.size(), 0);
        stack.pop();
        assertEquals(stack.size(), 0);
    }

    @Test
    public void popTest(){
        assertEquals("2", stack.pop());
        assertEquals("1", stack.pop());
        assertEquals(null, stack.pop());
    }

    @Test
    public void iteratorTest() throws Exception {
        for (String element: stack) {
            System.out.println(element);
        }
    }
}
