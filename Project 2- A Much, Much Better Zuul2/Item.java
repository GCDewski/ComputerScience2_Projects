
/**
 * Handles all item types and thier effects on the character
 * 
 * @Gracie Carver-Dews 
 * @3/16/15
 */
public class Item
{
    private double weight;
    private String name;
    private String type;
    private boolean taken;

    /**
     * Constructor for objects of class Item
     */
    public Item(String name, String type)
    {
        this.name = name;
        weight = setWeight(type);
        this.type = type;
        taken = false;
    }

    //sets weight for constructor depending on index of array given in consturctor
    public double setWeight(String type)
    {
        if(type.equals("weapon")) //weapon indexes
        {
            weight = 2;
        }
        else if (type.equals("personalObj")) //object indexes
        {
            weight = .3;
        }
        else if (type.equals("drink")) //drink indexes
        {
            weight = .5;
        }
        else //last book indexes           
        {
            weight = 1;
        }  
        return weight;
    }

    //returns item name
    public String itemName()
    {
        return name;
    }

    //return item weight
    public double itemWeight()
    {
        return weight;
    }
    
    //return item type
    public String itemType()
    {
        return type;
    }
    
    //return if item has been taken from room yet or not
    public boolean itemTaken()
    {
        return taken;
    }
    
    //using items
    public void useItem(Character character)
    {  
        if(this.weight == 2)
        {
            character.changeMight(1);
            System.out.println("Practicing with the weapon give you a boost of confidence.");
            System.out.println("Might increased to: " + character.getMight());
        }
        else if(this.weight == .3)
        {
            character.changeSanity(1);
            System.out.println("You draw strength from the item.");
            System.out.println("Sanity increased to: " + character.getSanity());
        }       
        else if(this.weight == .5)
        {
            character.changeSpeed(1);
            System.out.println("A surge of energy helps you feel lighter on your feet.");
            System.out.println("Speed increased to: " + character.getSpeed());
        }        
        else
        {
            character.changeKnowledge(1);
            System.out.println("Some light reading goes a long way in helping you survive.");
            System.out.println("Knowledge increased to: " + character.getKnowledge());
        }
    }
    
    //take Item from room
    public void takeItem()
    {
        taken = true;
    }
    
    //make sure item goes with room
    public boolean itemPair(Room currentRoom)
    {
       boolean fitsInRoom = false;
       String roomString = currentRoom.getShortDescription();
        if(roomString.equals("The Kitchen.") && type.equals("drink"))
        {
            fitsInRoom = true;
        }
        else if(roomString.equals("The Library.") && type.equals("book"))
        {
            fitsInRoom = true;
        }
        else if(roomString.equals("The Prayer Room.") && type.equals("personalObj"))
        {
            fitsInRoom = true;
        }
        else if(roomString.equals("The Gym.") && type.equals("weapon"))
        {
            fitsInRoom = true;
        }
        return fitsInRoom;      
    }
}
