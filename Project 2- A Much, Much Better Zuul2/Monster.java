
/**
 * Base class for all monster types. Hold the stats, actions, and dead/alive methods of Monster
 * 
 * @Gracie Carver-Dews
 * @3/18/15
 */
public class Monster
{
    protected int might; //Attack dice number & inventory space
    protected int speed; //speed dice number
    protected int sanity; //sanity dice number
    protected int knowledge; //knowledge dice number
    protected boolean alive; //checks if character alive 
    private int statChosen; //statChosen for 'fight'
    private Dice dice;
    protected String description;
    protected String name;
    

    public Monster (String name, String description, int might, int speed, int sanity, int knowledge )
    {
        this.name = name;
        this.description = description;
        this.might = might;
        this.speed = speed;
        this.sanity = sanity;
        this.knowledge = knowledge;
        alive = true;
    }
    
    public String getName()
    {
        return name;
    }

    //Stat Methods
    
    //get methods
    public int getMight()
    {
        return might;
    }
    public int getSpeed()
    {
        return speed;
    }
    public int getSanity()
    {
        return sanity;
    }
    public int getKnowledge()
    {
        return knowledge;
    }
    public String getAllStats()
    {
        return "Might: " + might + " Speed: " + speed + " Sanity: " + sanity + " Knowledge: " + knowledge;
    }

    //check if alive
    public boolean isAlive()
    {   
        if ((might <= 0) || (speed <= 0) || (sanity <= 0) || (knowledge <= 0))
        {
            alive = false;
        }
        return alive;
    }
    
    //stat changes
    public void changeMight(int change)
    {
        might = might+change;
    }
    public void changeSpeed(int change)
    {
        speed = speed+change;
    }
    public void changeSanity(int change)
    {
        sanity = sanity+change;
    }
    public void changeKnowledge(int change)
    {
        knowledge = knowledge+change;
    } 
    
    public int monAttack(int statChosen)
    {
        System.out.println("");
        System.out.println("Monster attacks!");
        this.statChosen = statChosen;
        dice = new Dice(statChosen);
        int resultRoll = dice.rollDice();
        return resultRoll;
    }
    
        //turns users string into the equivalent integer stat
    public int stringToStat(String statName)
    {
        int stat = 0;
        if (statName.equals("might"))
        {
            return stat = might;
        }
        else if (statName.equals("speed"))
        {
            return stat = speed;
        }
        else if (statName.equals("sanity"))
        {
            return stat = sanity;
        }   
        else if (statName.equals("knowledge"))
        {
            return stat = knowledge;
        }
        return stat;
    }
    
    //shows details of monster
    public void printInvestigate()
    {
        System.out.println("");
        System.out.println("Closer inspection reveals: " + name + ".");
        System.out.println("Description: " + description);
        System.out.println("Might: " + might + " Speed: " + speed + " Sanity: " + sanity + " Knowledge: " + knowledge);        
    }
    
}
