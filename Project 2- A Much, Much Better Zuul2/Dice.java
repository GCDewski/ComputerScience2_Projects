import java.util.Random;
/**
 * The number of dice is dependent on the stats of the character/monster. Rolling the dice and adding 
 * up the sum of the "dots" determines the battles between the user and the monsters. 
 * 
 * @Gracie Carver-Dews
 * @3/18/15
 */
public class Dice
{
    private int sum;
    private int numberOfDice;
    private Random rng;

    public Dice(int numberOfDice)
    {
        this.numberOfDice = numberOfDice;
        sum = 0;
        rng = new Random();
    }

    public int rollDice()
    {
        int dice = 0;
        while(numberOfDice > 0)
        {
            dice = rng.nextInt(7);
            if (dice != 0) //else roll again b/c 0 is not an option.
            {                    
                sum = sum + dice;
                numberOfDice--;
                System.out.println("Rolled a: " + dice);
            }           
        }
        System.out.println("Total value of roll: " + sum);
        return sum;
    }
}
