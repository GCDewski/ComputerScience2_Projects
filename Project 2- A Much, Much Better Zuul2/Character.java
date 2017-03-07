import java.util.ArrayList;
/**
 * Handles stats and inventory space of character chosen at beginning of game.
 * 
 * @Gracie Carver-Dews 
 * @3/15/15
 */
public class Character
{
    private Character [] charaSelection = new Character [13]; //character selection at beginning of game
    private int might; //Attack dice number & inventory space
    private int speed; //speed dice number
    private int sanity; //sanity dice number
    private int knowledge; //knowledge dice number
    private int inventorySpace; //total inventory space
    private int statChosen; //the stat chosen when attacking
    private double inventoryUsed; //inventory space used
    private int keys; //key count required to win the game
    private boolean alive; //checks if character alive 
    private boolean allKeys; //checks if character has all keys
    private ArrayList<Item> inventory; //list of things in inventory
    private Dice dice; //dice to Attack

    public Character(int might, int speed, int sanity, int knowledge)
    {
        this.might = might;
        inventorySpace = might;
        this.speed = speed;
        this.sanity = sanity;
        this.knowledge = knowledge;

        inventoryUsed = 0;
        keys = 0; 
        alive = true;
        allKeys = false;

        inventory = new ArrayList<Item>();
    }

    //load characters into array
    public void addCharacters()
    {
        Character darrin, ox, jenny, heather, father, prof, madame, vivian, zoe, missy, brandon, peter, test;
        
        darrin = new Character(4,7,4,4);
        ox = new Character(6,5,4,4);
        jenny = new Character(5,5,5,4);
        heather = new Character(4,5,4,6);
        father = new Character(3,4,7,5);
        prof = new Character(4,5,4,6);
        madame = new Character(5,4,5,5);
        vivian = new Character(3,5,5,6);
        zoe = new Character(4,5,6,4);
        missy = new Character(4,6,4,5);
        brandon = new Character(5,5,5,4);
        peter = new Character(4,5,5,5);
        test = new Character(10,10,10,10);
        
        charaSelection [0] = (darrin);
        charaSelection [1] = (ox);
        charaSelection [2] = (jenny);
        charaSelection [3] = (heather);
        charaSelection [4] = (father);
        charaSelection [6] = (prof);
        charaSelection [7] = (madame);
        charaSelection [8] = (vivian);
        charaSelection [10] = (missy);
        charaSelection [9] = (zoe);
        charaSelection [5] = (brandon);
        charaSelection [11] = (peter);
        charaSelection [12] = (test);
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
        return "Might: " + might + "Speed: " + speed + "Sanity: " + sanity + "Knowledge: " + knowledge;
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

    //character 'alive' status based on stats
    public boolean isAlive()
    {
        if ((might <= 0) || (speed <= 0) || (sanity <= 0) || (knowledge <= 0))
        {
            alive = false;
        }
        return alive;
    }

    //list stats for user
    public void showStats()
    {
        System.out.println("Might: " + might + " Speed: " + speed + " Sanity: " + sanity + " Knowledge: " + knowledge);        
    }
    //Methods that handle inventory space

    //adding to inventory
    public void addToInventory(Item item)
    {
        double weight = item.itemWeight();
        //checks if inventory is full
        if((inventoryUsed + weight)> inventorySpace)
        {
            System.out.println("Inventory is full! Item is discarded! Remember: Use command 'use ITEM-NAME' to use items"); 
        }
        else 
        {
            inventoryUsed = inventoryUsed+ weight;
            inventory.add(item);
        }    
    }

    //dropping/using items from inventory
    public void subFromInventory(Item item)
    {
        double weight = item.itemWeight();
        inventoryUsed = inventoryUsed-weight;    
        inventory.remove(item);
    }

    //show all items in inventory
    public void showInventory()
    {
        for(Item item : inventory)
        {
            System.out.println("Item name: " + item.itemName() + " Weight: " + item.itemWeight());          
        }
        System.out.println("Space Left: " + (inventorySpace-inventoryUsed));
        System.out.println("Key Count: " + keys + "/3");
    }

    //checks inventory for certain item
    public boolean inInventory(String name)
    {
        boolean checkInventory = false;
        for(Item item : inventory)
        {
            if (name.equals(item.itemName()))
            {
                checkInventory = true;
            }
        }
        return checkInventory;
    }

    //Key Methods

    //checks the key count required to win
    public void keyCount()
    {
        keys++;
        if(keys == 3)
        {
            allKeys = true;
            System.out.println("");
            System.out.println("You have all three keys! Go to the Mystery Room to unlock the chest.");
        }
    } 

    //returns if the keys are all gathered or not
    public boolean checkKey()
    {
        return allKeys;
    }

    //attacking
    public int charAttack(int statChosen)
    {
        System.out.println("");
        System.out.println("You attack!");
        this.statChosen = statChosen;
        dice = new Dice(statChosen);
        int resultRoll = dice.rollDice(); 
        return resultRoll;
    }

    //event roll
    public int charRoll(int statChosen)
    {
        this.statChosen = statChosen;
        dice = new Dice(statChosen);
        int resultRoll = dice.rollDice(); 
        return resultRoll;
    }

    //turns users string into the equivalent integer stat
    public int stringToStat(String statName)
    {
        if (statName.equals("might"))
        {
            return might;
        }
        else if (statName.equals("speed"))
        {
            return speed;
        }
        else if (statName.equals("sanity"))
        {
            return sanity;
        }   
        else if (statName.equals("knowledge"))
        {
            return knowledge;
        }
        System.out.println("Should not get here");
        return 0;
    }

    //prints the characters so the user can see the selections
    public void printCharacters()
    {
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------ ");
        System.out.println("                                              Please Enter Your Chosen Character's Number                                                  ");
        System.out.println("0) Darrin Williams:    Might: 4, Speed: 7, Sanity: 4, Knowledge: 4 | 6)  Professor Longfellow: Might: 4, Speed: 5, Sanity: 4, Knowledge: 6 ");
        System.out.println("1) Ox Bellows:         Might: 6, Speed: 5, Sanity: 5, Knowledge: 4 | 7)  Madame Zostra:        Might: 5, Speed: 4, Sanity: 5, Knowledge: 5 ");
        System.out.println("2) Jenny LeClerc:      Might: 5, Speed: 5, Sanity: 5, Knowledge: 4 | 8)  Vivian Lopez:         Might: 3, Speed: 5, Sanity: 5, Knowledge: 6 ");
        System.out.println("3) Heather Granville:  Might: 4, Speed: 5, Sanity: 4, Knowledge: 6 | 9)  Zoe Ingstrum:         Might: 4, Speed: 5, Sanity: 6, Knowledge: 4 ");
        System.out.println("4) Father Rhinehardt:  Might: 3, Speed: 4, Sanity: 7, Knowledge: 5 | 10) Missy Dubourde:       Might: 4, Speed: 6, Sanity: 4, Knowledge: 5 ");
        System.out.println("5) Brandon Jaspers:    Might: 5, Speed: 5, Sanity: 5, Knowledge: 4 | 11) Peter Akimoto:        Might: 4, Speed: 5, Sanity: 5, Knowledge: 5 ");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public Character getCharacter(int choice)
    {
        Character charChoice;
        return charChoice = charaSelection[choice];       
    }
}
