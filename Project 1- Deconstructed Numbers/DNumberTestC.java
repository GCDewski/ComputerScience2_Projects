import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class DNumberTestC. Tests DNumber at level C.
 *
 * @author  Liz Johnson
 * @version 2/15/15
 */
public class DNumberTestC
{
    /**
     * Default constructor for test class DNumberTestC
     */
    public DNumberTestC()
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
    /**
     * Tests ints that can be represented in 1 bit
     */
    public void test1CConstructorAndToString()
    {
        DNumber dNumber1 = new DNumber();
        assertEquals("0", dNumber1.toString());
        DNumber dNumber2 = new DNumber(0);
        assertEquals("0", dNumber2.toString());
        DNumber dNumber3 = new DNumber(1);
        assertEquals("1", dNumber3.toString());
    }
    
    @Test
    /**
     * Tests larger ints
     */
    public void test2CConstructorAndToString()
    {
        DNumber dNumber1 = new DNumber(10);
        assertEquals("1010", dNumber1.toString());
        DNumber dNumber2 = new DNumber(1024);
        assertEquals("10000000000", dNumber2.toString());
        DNumber dNumber3 = new DNumber(255);
        assertEquals("11111111", dNumber3.toString());
        DNumber dNumber4 = new DNumber(40);
        assertEquals("101000", dNumber4.toString());
        DNumber dNumber5 = new DNumber(57);
        assertEquals("111001", dNumber5.toString());
    }
    
   
}
