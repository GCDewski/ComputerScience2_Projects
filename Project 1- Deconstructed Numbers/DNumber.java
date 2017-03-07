
/**
 * A class that arranges decimal numbers into an equivalent list of binary digits. Has other methods 
 * including adding binary digits together, turning back into decimal and checking the equivalence with other objects
 * 
 * @Gracie Carver-Dews 
 * @2/23/15
 */
import java.util.ArrayList;
import java.util.Collections;

public class DNumber
{
    //array list to store binary digits
    private ArrayList<Digit> binaryNums;    
    //string for binary numbers
    private String binaryString = "";
    //Digits for adding binary numbers
    private Digit carry1;

    //constructor
    public DNumber()
    {
        binaryNums = new ArrayList<Digit>();
        Digit empty = new Digit(0);
        binaryNums.add(empty);
    }

    //constuctor for when any other decimal number is given
    public DNumber(int val)
    {
        binaryNums = new ArrayList<Digit>();
        int binaryVal = 0;
        int i = 0;

        //checks single digit options less than 2
        if (val == 0)
        {
            binaryNums.add(new Digit(0));
            i = 1;
        }
        if (val == 1)
        {
            binaryNums.add(new Digit(1));
            i = 1;
        }

        //loops creating the binary digits and placing into last place of array until val is equal to 0
        while(i != 1)
        {
            binaryVal = val%2;
            val = (val/2);
            Digit binaryNum = new Digit(binaryVal);
            binaryNums.add(binaryNum);
            //when val cannot be divided by 2 anymore
            if (val==0)
            {
                i = 1;
            }
        } 
    } 

    //Gives string value of Digits in array
    public String toString()
    {
        //for every digit, convert to string and add to the previous digit string
        for (int i = 0; i<binaryNums.size(); i++)
        {       
            binaryString = binaryString + binaryNums.get(i);
            binaryString.trim();
        }
        //reverse the array 
        String finalString = new StringBuilder(binaryString).reverse().toString();
        return finalString;
    }

    //adds two lists of binary arrays together **NOTE: Couldn't get it to work in the end :( **
    public void add(DNumber b)
    {
        //indexes
        int i = (binaryNums.size()-1);
        int index1 = (binaryNums.size()-1);
        int index2 = (b.binaryNums.size()-1);

        //finds largest array to set loop count
        if (index1 < index2)
        {
            binaryNums.add(new Digit(0));
            index1++;
        }
        if(index1 > index2)
        {
            b.binaryNums.add(new Digit(0));
            index2++;
        }

        //Loop thru all numbers
        while(i>= 0)
        {
            carry1 = ((binaryNums.get(i)).add(b.binaryNums.get(i)));
            Digit carry2 = ((binaryNums.get(i)).add(carry1));
            Digit carry3 = ((binaryNums.get(i)).add(carry2));

            carry2.add(carry3);
            carry1=carry2;
            i--;
        }  

        //if still a carry value by the end of the loop, add 1 out front.
        if(carry1.getValue()==1)
        {
            binaryNums.add(0,carry1);
        }
    } 

    //getting the decimal value
    public int getDecimalValue()
    {
        double sum = 0;
        //loop array size
        for(int i = 0; i<binaryNums.size(); i++)
        {
            double n = i;
            //the binary number is a 1
            if((binaryNums.get(i)).getValue() == 1)
            {
                sum = sum + Math.pow((2.0),(n));
            }
        }       
        int finalSum = (int)sum;
        return finalSum;
    }

    public boolean equals (Object e)
    {
        //for test
        if (this == e){
            return true;
        }
        //will not take this?!?! Formatting is messing up regardless of what I do...?
        //if(!(e instanceOf DNumber))
        //{
        //   return false;
        // }   

        DNumber d = ((DNumber)e); //cast the obejct

        boolean equalTo = false;
        int firstSum = getDecimalValue();
        int secondSum = getDecimalValue();
        if(firstSum == secondSum)
        {
            equalTo = true;
        }
        return equalTo;
    }
    
    //compares two binary lsits and depending on the equality relation, returns an int. put the equals class in here
    public int compareTo (Object other)
    {   
        //equals class
        boolean equalTo = false;
        int firstSum = this.getDecimalValue();
        DNumber newOther = ((DNumber)other); //cast the object
        int secondSum = getDecimalValue();
        if(firstSum == secondSum)
        {
            equalTo = true;
        }
        
        //compareTo
        if(equalTo == true)
        {
            return 0;
        }
        if(firstSum>secondSum)
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }
}

