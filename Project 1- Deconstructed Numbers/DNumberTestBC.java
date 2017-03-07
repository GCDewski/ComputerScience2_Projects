import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class DNumberTestBC. Tests DNumber at level B and C.
 *
 * @author  Liz Johnson
 * @version 2/15/15
 */
public class DNumberTestBC
{
    /**
     * Default constructor for test class DNumberTestBC
     */
    public DNumberTestBC()
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
    
   @Test
    /**
     * Tests adding 2 one bit numbers
     */
    public void test3BCAdd()
    {
       DNumber dNumber1 = new DNumber(0);
       DNumber dNumber2 = new DNumber(0);
       dNumber1.add(dNumber2);
       assertEquals("0", dNumber1.toString());
       assertEquals("0", dNumber2.toString());
       
       DNumber dNumber3 = new DNumber(0);
       DNumber dNumber4 = new DNumber(1);
       dNumber3.add(dNumber4);
       assertEquals("1", dNumber3.toString());
       assertEquals("1", dNumber4.toString());
       
       DNumber dNumber5 = new DNumber(1);
       DNumber dNumber6 = new DNumber(0);
       dNumber5.add(dNumber6);
       assertEquals("1", dNumber5.toString());
       assertEquals("0", dNumber6.toString());
       
       DNumber dNumber7 = new DNumber(1);
       DNumber dNumber8 = new DNumber(1);
       dNumber7.add(dNumber8);
       assertEquals("10", dNumber7.toString());
       assertEquals("1", dNumber8.toString());
    }
    
    @Test
    /**
     * Tests adding larger numbers
     */
    public void test4BCAdd()
    {
       DNumber dNumber1 = new DNumber(255);
       DNumber dNumber2 = new DNumber(1);
       dNumber1.add(dNumber2);
       assertEquals("100000000", dNumber1.toString());
       assertEquals("1", dNumber2.toString());
       
       DNumber dNumber3 = new DNumber(1);
       DNumber dNumber4 = new DNumber(255);
       dNumber3.add(dNumber4);
       assertEquals("100000000", dNumber3.toString());
       assertEquals("11111111", dNumber4.toString());
       
       DNumber dNumber5 = new DNumber(15);
       DNumber dNumber6 = new DNumber(15);
       dNumber5.add(dNumber6);
       assertEquals("11110", dNumber5.toString());
       assertEquals("1111", dNumber6.toString());
       
       DNumber dNumber7 = new DNumber(0);
       DNumber dNumber8 = new DNumber(254);
       dNumber7.add(dNumber8);
       assertEquals("11111110", dNumber7.toString());
       assertEquals("11111110", dNumber8.toString());
    }
    
    @Test
    /**
     * Tests of getDecimalValue
     */
    public void test5BCGetDecimalValue()
    {
       DNumber dNumber1 = new DNumber(255);
       assertEquals(255, dNumber1.getDecimalValue());
       
       DNumber dNumber2 = new DNumber(254);
       assertEquals(254, dNumber2.getDecimalValue());
       
       DNumber dNumber3 = new DNumber(20);
       assertEquals(20, dNumber3.getDecimalValue());
       
       DNumber dNumber4 = new DNumber(0);
       assertEquals(0, dNumber4.getDecimalValue());
       
       DNumber dNumber5 = new DNumber(1);
       assertEquals(1, dNumber5.getDecimalValue());
       
       DNumber dNumber6 = new DNumber(2);
       assertEquals(2, dNumber6.getDecimalValue());
       
       DNumber dNumber7 = new DNumber(3);
       assertEquals(3, dNumber7.getDecimalValue());
    }
    @Test
    /**
     * Tests of equals
     */
    public void test6BCEquals()
    {
       DNumber dNumber1 = new DNumber(255);
       assertEquals(true, dNumber1.equals(dNumber1));
       
       DNumber dNumber2 = new DNumber(255);
       assertEquals(false, dNumber2.equals("test"));
       
       DNumber dNumber3 = new DNumber(25);
       DNumber dNumber4 = new DNumber(25);
       assertEquals(true, dNumber3.equals(dNumber4));
       assertEquals(true, dNumber4.equals(dNumber3));
       
       DNumber dNumber5 = new DNumber(25);
       DNumber dNumber6 = new DNumber(24);
       assertEquals(false, dNumber5.equals(dNumber6));
       assertEquals(false, dNumber6.equals(dNumber5));
    }
    
    @Test
    /**
     * Tests of compareTo
     */
    public void test7BCCompareTo()
    {
       DNumber dNumber1 = new DNumber(255);
       assertEquals(0, dNumber1.compareTo(dNumber1));
       
       DNumber dNumber2 = new DNumber(255);
       assertEquals(0, dNumber2.compareTo(dNumber1));
       
       DNumber dNumber3 = new DNumber(24);
       DNumber dNumber4 = new DNumber(25);
       assertTrue(dNumber3.compareTo(dNumber4)<0);
       assertTrue(dNumber4.compareTo(dNumber3)>0);
       
    }
}
