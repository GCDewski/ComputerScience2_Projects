import java.util.ArrayList;
/**
 * Handles the TARDIS and trapdoor rooms. A trapdoor room knocks all players stats down one. A TARDIS teleports the player to
 * a previously discovered room.
 * 
 * @Gracie Carver-Dews 
 * @3/30/15
 */
public class SpecialRoom extends Room
{
    //an array of all the already discovered rooms that the TARDIS can teleport to.
    private static ArrayList<Room> oldRooms = new ArrayList<Room>();
    
    public SpecialRoom(String name, String description, boolean discovered, boolean itemRoom)
    {
        super(name,description,discovered,itemRoom);
    }
    
    //basically casting so I can use the Room array.
    public SpecialRoom (Room room)
    {
        super(room.name, room.description, room.discovered, room.itemRoom);
    }

    public void trapDoorTriggered(Character character)
    {
        //events can't kill so check if stat is above 1 and then drop it one. 
        if(character.getMight() > 1)
        {
            character.changeMight(-1);
        }
        if(character.getSpeed() > 1)
        {
             character.changeSpeed(-1);
        }
        if(character.getSanity() > 1)
        {
            character.changeSpeed(-1);
        }
        if(character.getKnowledge() > 1)
        {
            character.changeKnowledge(-1);
        }
        System.out.println("You fell through a trap door! Luckily, you are able to climb out of the room you fell into and continue trying to survive the house.");
        System.out.println("All player stats greater than one go down by one.");      
    }
    
    public void addToArray(Room room)
    {
        oldRooms.add(room);
    }
    
    //returns array size +1 because this will be used with the nextInt method in game
    public int oldArraySize()
    {
        return oldRooms.size();
    }
    
    public Room transportRoom(int choice)
    {
        Room tardisRoom = oldRooms.get(choice);
        return tardisRoom;      
    }
}
