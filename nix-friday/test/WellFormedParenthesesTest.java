import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class WellFormedParenthesesTest {

    private WellFormedParentheses wellFormedParentheses;

    @Before
    public void setUp() throws Exception {
        this.wellFormedParentheses = new WellFormedParentheses();
    }

    @Test
    public void solveEmptyStringTest() {
        assertEquals(0, wellFormedParentheses.solve(""));
    }

    @Test
    public void solveNotWellformedTest() {
        assertEquals(0, wellFormedParentheses.solve(")("));
    }

    @Test
    public void solveWellFormedTest() {
        assertEquals(2, wellFormedParentheses.solve("()"));
    }

    @Test
    public void solveTest1() {
        assertEquals(4, wellFormedParentheses.solve(")()())"));
    }

    @Test
    public void solveTest2() {
        assertEquals(0, wellFormedParentheses.solve("))))))))))))))"));
    }

    @Test
    public void solveTest3() {
        assertEquals(0, wellFormedParentheses.solve("((((((((((((((("));
    }

    @Test
    public void solveTest4() {
        assertEquals(12, wellFormedParentheses.solve("(((((()))))))))((((((((("));
    }

    @Test
    public void solveTest5() {
        assertEquals(0, wellFormedParentheses.solve(")))))))))((((((((("));
    }

    @Test
    public void solveTest6() {
        assertEquals(2, wellFormedParentheses.solve("()("));
    }

    @Test
    public void solveTest7() {
        assertEquals(2, wellFormedParentheses.solve("()(()"));
    }

    @Test
    public void solveTest8() {
        assertEquals(10, wellFormedParentheses.solve(")()(((())))("));
    }
}