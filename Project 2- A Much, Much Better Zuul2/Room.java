import java.util.Set;
import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.08
 */

public class Room 
{
    protected String description;
    protected String name;
    protected boolean discovered; //if room has been discovered or not
    protected boolean itemRoom; //if item room
    private boolean monRoom; //if monster room
    private HashMap<String, Room> exits;    // stores exits of this room.

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String name, String description, boolean discovered, boolean itemRoom) 
    {
        this.name = name;
        this.description = description;
        this.discovered = discovered;
        this.itemRoom = itemRoom;
        exits = new HashMap<String, Room>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        if (direction.equals(exits.put(direction, neighbor)));
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return name;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You enter into " + description + ".\n";
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    public String getExitString(boolean arrayEmpty)
    {
        String returnString = "Exits:";
        Room north = exits.get("north");
        Room east = exits.get("east");
        Room south = exits.get("south");
        Room west = exits.get("west");

        //get north exit room and repeat for all other directions

        if(north == null)
        {
            if(!arrayEmpty) //as long as not empty, list as unknown
            {
                returnString += " Unknown Room" + "(north)";
            }
        }
        else if(!(north.getShortDescription()).equals("blocked")) //doesn't show a blocked room
        {
            returnString += " " + north.getShortDescription() + "(north)";
        }

        if(east == null)
        {
            if(!arrayEmpty) //as long as not empty, list as unknown
            {
                returnString += " Unknown Room" + "(east)";
            }
        }
        else if(!(east.getShortDescription()).equals("blocked"))
        {
            returnString += " " + east.getShortDescription() + "(east)" ;
        }

        if(south == null)
        {
            if(!arrayEmpty) //as long as not empty, list as unknown
            {
                returnString += " Unknown Room" + "(south)";
            }
        }
        else if(!(south.getShortDescription()).equals("blocked"))
        {
            returnString += " " + south.getShortDescription() + "(south)";
        }

        if(west == null)
        {
            if(!arrayEmpty) //as long as not empty, list as unknown
            {
                returnString += " Unknown Room" + "(west)";
            }   
        }
        else if(!(west.getShortDescription()).equals("blocked"))
        {
            returnString += " " + west.getShortDescription() + "(west)";
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }

    //Handles if the room has been discoverd before or not.
    public boolean isDiscovered()
    {
        if(this.discovered == false)
        {
            this.discovered = true;
        }
        return discovered;
    }

    //returns if item is supposed to hold an item
    public boolean itemInRoom()
    {
        return itemRoom;
    }

    public void plusDescription(String itemName)
    {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        if(name.equals("The Kitchen."))
        {
            System.out.println("There is an energy drink on the counter. Closer inspection reveals it is a " + itemName + ".\n" + "Type 'take' " + itemName + " to place in inventory.");
        }
        else if(name.equals("The Library."))
        {
            System.out.println("You pull a random book from shelf nearest titled: " + itemName + ".\n" + "Type 'take' " + itemName + " to place in inventory.");
        }
        else if(name.equals("The Prayer Room."))
        {
            System.out.println("Something is laying on the floor near the alter. Looking closer, it turns out to be a " + itemName + ". How did this get here...\n" + "Type 'take' " + itemName + " to place in inventory.");
        }
        else if(name.equals("The Gym."))
        {
            System.out.println("Someone did more than lifting weights in here. There is a " + itemName+ " in the corner." + "\n" + "Type 'take' " + itemName + " to place in inventory.");
        }
        else if (name.equals("The Basement."))
        {
            System.out.println("There's a rustle in the corner. Further investigation reveals a humanoid form with beastial features sleeping with a Key around it's neck."); 
            System.out.println("Type 'investigate' or 'attack' to interact with the creature or leave the room to gather strength.");
        }
        else if (name.equals("The Attic."))
        {
            System.out.println("Opening the door reveals yellow eyes peering out of the darkness. The unknown creature is standing in front of a hook where a Key swings." + "\n" + "Type 'investigate' or 'attack' to interact with the creature or leave the room to gather strength.");    
        }
        else //it's the boss monsters location
        {
            System.out.println("The owner of the house is also in this room!" + "\n" + "Type 'investigate' or 'attack' to interact with the creature or leave the room to gather strength.");
        }
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    //checks if monster is in the room
    public boolean monsterRoom()
    {
        monRoom = false;
        if(name.equals("The Basement."))
        {
            monRoom = true;
        }
        else if(name.equals("The Attic."))
        {
            monRoom = true;
        }
        return monRoom;
    }
}

