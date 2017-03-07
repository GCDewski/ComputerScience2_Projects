import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class DigitTestCD
 *
 * @author  Liz Johnson
 * @version 2/15/15
 */
public class DigitTestCD
{
    /**
     * Default constructor for test class DigitTestD
     */
    public DigitTestCD()
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
    public void test1DConstructor()
    {
        Digit digit1 = new Digit(20);
        assertEquals(0, digit1.getValue());
    }

    @Test
    public void test2DConstructor()
    {
        Digit digit1 = new Digit(1);
        assertEquals(1, digit1.getValue());
    }

    @Test
    public void test3DConstructor()
    {
        Digit digit1 = new Digit(0);
        assertEquals(0, digit1.getValue());
    }

    @Test
    public void test4DSetValue()
    {
        Digit digit1 = new Digit(0);
        digit1.setValue(6);
        assertEquals(0, digit1.getValue());
    }

    @Test
    public void test5DSetValue()
    {
        Digit digit1 = new Digit(0);
        digit1.setValue(6768668);
        assertEquals(0, digit1.getValue());
    }

    @Test
    public void test6DSetValue()
    {
        Digit digit1 = new Digit(1);
        assertEquals(1, digit1.getValue());
    }

    @Test
    public void test7DToString()
    {
        Digit digit1 = new Digit(0);
        assertEquals("0", digit1.toString());
    }

    @Test
    public void test8DToString()
    {
        Digit digit1 = new Digit(1);
        assertEquals("1", digit1.toString());
    }
    
    @Test
    /**
     * tests adding 0 and 0
     */
    public void test9CAdd()
    {
        Digit digit1 = new Digit(0);
        Digit digit2 = new Digit(0);
        Digit carry = digit1.add(digit2);
        assertEquals("0", digit1.toString());
        assertEquals("0", digit2.toString());
        assertEquals("0", carry.toString());
    }
    
    @Test
    /**
     * tests adding 0 and 1
     */
    public void test10CAdd()
    {
        Digit digit1 = new Digit(0);
        Digit digit2 = new Digit(1);
        Digit carry = digit1.add(digit2);
        assertEquals("1", digit1.toString());
        assertEquals("1", digit2.toString());
        assertEquals("0", carry.toString());
    }
    
    @Test
    /**
     * tests adding 1 and 0
     */
    public void test11CAdd()
    {
        Digit digit1 = new Digit(1);
        Digit digit2 = new Digit(0);
        Digit carry = digit1.add(digit2);
        assertEquals("1", digit1.toString());
        assertEquals("0", digit2.toString());
        assertEquals("0", carry.toString());
    }
    
    @Test
    /**
     * tests adding 1 and 1
     */
    public void test12CAdd()
    {
        Digit digit1 = new Digit(1);
        Digit digit2 = new Digit(1);
        Digit carry = digit1.add(digit2);
        assertEquals("0", digit1.toString());
        assertEquals("1", digit2.toString());
        assertEquals("1", carry.toString());
    }
}
