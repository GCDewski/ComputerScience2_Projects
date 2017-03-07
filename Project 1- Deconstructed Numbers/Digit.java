
/**
 * A class representing the bit and it's functions including getting the value, setting a new value, 
 * and creating a string from the bit value.
 * 
 * @Gracie Carver-Dews 
 * @2/17/15
 */
public class Digit
{
    //fields
    private int bitVal;
    
    /**
     * Constructor for objects of class Digit
     */
    public Digit(int val)
    {
        bitVal=val;
        // checks to see if not 0 or 1 and sets to 0 if it is not.
        if(bitVal!=0 && bitVal!=1)
        {
            System.out.println("Illegal value- Must be 0 or 1. Value has been set to 0");
            bitVal = 0;
        }
        
    }

    //return value of bit as int
    public int getValue()
    {        
        return bitVal;
    }
    
    //sets new value based on parameter 
    public int setValue(int newVal)
    {
        //checks if newvalue is 0 or 1 and sets changes bits value if it is.
        if(newVal==0 || newVal==1)
        {
            bitVal=newVal;
        }
        return bitVal;
    }
    
    //creates a String rep of bit value
    public String toString()
    {   
        return "" + bitVal;  
    }    
    
    //adds another bit to current digit changing the value using binary math
    public Digit add (Digit b)
    {
        //creates carry digit with inital value 0 that can be used in all classes
        Digit carry = new Digit(0);
        
        //checks int value for both and then if original bit is 0, it changes it to one w/ no carry
        if (this.getValue() == 0 && b.getValue() == 1)
        {
            this.setValue(1);
            return carry;
        }
        //checks same thing as above but w/ flipped values
        if (this.getValue() == 1 && b.getValue() == 0)
        {
            this.setValue(1);
            return carry;
        }
        //checks to see if initial bit is 1 so it can change the val to 0 and carry digit
        if (this.getValue() == 1 && b.getValue() == 1)
        {
            this.setValue(0);
            carry.setValue(1);
            return carry;
        }
        
        if(this.getValue() == 0 && b.getValue() == 0)
        {
            
        }
        return carry;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
