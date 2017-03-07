import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class DigitTestD
 *
 * @author  Liz Johnson
 * @version 2/15/15
 */
public class DigitTestD
{
    /**
     * Default constructor for test class DigitTestD
     */
    public DigitTestD()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void test1Constructor()
    {
        Digit digit1 = new Digit(20);
        assertEquals(0, digit1.getValue());
    }

    @Test
    public void test2Constructor()
    {
        Digit digit1 = new Digit(1);
        assertEquals(1, digit1.getValue());
    }

    @Test
    public void test3Constructor()
    {
        Digit digit1 = new Digit(0);
        assertEquals(0, digit1.getValue());
    }

    @Test
    public void test4SetValue()
    {
        Digit digit1 = new Digit(0);
        digit1.setValue(6);
        assertEquals(0, digit1.getValue());
    }

    @Test
    public void test5SetValue()
    {
        Digit digit1 = new Digit(0);
        digit1.setValue(6768668);
        assertEquals(0, digit1.getValue());
    }

    @Test
    public void test6SetValue()
    {
        Digit digit1 = new Digit(1);
        assertEquals(1, digit1.getValue());
    }

    @Test
    public void test7ToString()
    {
        Digit digit1 = new Digit(0);
        assertEquals("0", digit1.toString());
    }

    @Test
    public void test8ToString()
    {
        Digit digit1 = new Digit(1);
        assertEquals("1", digit1.toString());
    }
}