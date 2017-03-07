
/**
 * The bossMonster which is a subclass of Monster with the ability to move rooms & be impacted by events. Also 
 * has higher stats.
 * 
 * @Gracie Carver-Dews 
 * @3/19/15
 */
public class BossMonster extends Monster
{ 
    private boolean spawnAgain = false;
    private boolean attack = false;
    private String highestStat = "";
    private int highStat = 0;
    private int monsterMove = 0;
    private Room startingRoom; //for spawning again
    private static final BossMonster originalTempMonster = null; //for spawning again

    public BossMonster(String name, String description, int might, int speed, int sanity, int knowledge)
    {
        super(name, description, might, speed, sanity, knowledge);
    }

    //basically casting so I can use the monster array.
    public BossMonster(Monster monster)
    {
        super(monster.name, monster.description, monster.might, monster.speed, monster.sanity, monster.knowledge);
    }

    public boolean getAttackedValue()
    {
        return attack;
    }

    public void attackDone()
    {
        attack = false;
    }

    public String getStringStat()
    {
        return highestStat;
    }
    
    public int monsterMove()
    {
        return monsterMove;
    }
    
    public void monsterWait()
    {
        monsterMove++;
    }
    
    public void monsterWaitReset()
    {
        monsterMove = 0;
    }
    
    public int getHighestStat()
    {
        return highStat;
    }
    
    public boolean gotKey()
    {
        return spawnAgain;
    }

    public void alreadyGotKey()
    {
        spawnAgain = true;
    }

    public boolean canMoveHere(String room)
    {
        boolean moveHere = true;
        if(room.equals("blocked") || room.equals("The Attic.") || room.equals("The Basement."))
        {
            moveHere = false;
        }
        return moveHere;
    }

    public int bossAttack()
    {
        highStat = might;
        highestStat = "might";
        String phrase = "'The Owner' noticed you and attacks with might!";
        
        //find highest stat
        if(highStat < speed)
        {
            highStat = speed;
            highestStat = "speed";
            phrase = "'The Owner' noticed you and attacks with speed!";
        }

        if (highStat < sanity)
        {
            highStat = sanity;
            highestStat = "sanity";
            phrase = "'The Owner' noticed you and attacks with sanity!";
        }

        if (highStat < knowledge)
        {
            highStat = knowledge;
            highestStat = "knowledge";
            phrase = "'The Owner' noticed you and attacks with knowledge!";
        }
        
        if(highStat > 1)
        {
            System.out.println(phrase);
            attack = true;
        }
        
        else
        {
            System.out.println("'The Owner' is wounded and does not attack you.");
        }
        return highStat;
    }
    
    public boolean isAlive()
    {   
        if ((might <= 0) && (speed <= 0) && (sanity <= 0) && (knowledge <= 0))
        {
            alive = false;
        }
        return alive;
    }
    
    public void setStartingRoom(Room room)
    {
        startingRoom = room;
    }
    
    public Room getStartingRoom()
    {
        return startingRoom;
    }
}
