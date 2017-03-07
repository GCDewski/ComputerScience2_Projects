import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a text based adventure game based off the board game "Betrayal at House on
 *  the Hill." Users will choose a character with unique stats that will search the house for the 
 *  3 key fragments to unlock the Mystery Door. The monsters haunting the house will try and prevent 
 *  your character from doing so.
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @Gracie Carver-Dews
 * @3/10/15
 */
public class Game
{
    private Parser parser;
    private Room lastRoom;
    private Room currentRoom;
    private Item currentItem;
    private Event currentEvent = new Event ("", "", "","",""); //temporary 
    private Monster atticMonster;
    private Monster baseMonster;
    private BossMonster bossMonster1 = new BossMonster("","",0,0,0,0); //temporary
    private Random rng;
    private Character character = new Character (8,8,8,8); //temporary
    private int turn;
    private int roomsDiscovered;

    private boolean attackUsed = false; 
    private boolean inEvent = false;
    private boolean rollEvent = false;
    private boolean bossSpawn = false;
    private boolean chosenCharacter = false;

    private ArrayList<Room> rooms = new ArrayList<Room>(); 
    private ArrayList<Item> items = new ArrayList<Item>(); //for getting random elements
    private ArrayList<Monster> monsters = new ArrayList<Monster>();
    private HashMap<String, Item> itemMap = new HashMap<String,Item>(); //for tying Str to item
    private HashMap<Room, Item> itemRoomMap = new HashMap<Room,Item>(); //for tying item to a Room
    private HashMap<BossMonster, Room> monMap = new HashMap<BossMonster, Room>(); //keeps track of boss and his current room

    //Create the game and initialise the rooms
    public Game() 
    {
        rng = new Random();
        parser = new Parser();
        createMonsters();
        createRooms();
        createItems();
        currentEvent.createEvents();
        character.addCharacters();
        turn = 0;
        roomsDiscovered = 3;
    }

    //Show the user the choices avaliable for choosing character then wait for user input
    private void printCharacterChoices()
    {
        character.printCharacters();        
    }

    private void charaChoose(int choice)
    {
        character = character.getCharacter(choice);
        if(!(character == null))
        {
            chosenCharacter = true;
            System.out.println();
            System.out.println("Character selected! You may now begin your adventure.");
            System.out.println();
        }
    }

    private void createMonsters()
    {
        Monster hellhound, golem, arachas, vampire, harpy, drowner, manticore, ghost, lamia, werecat;

        //Create Monsters
        hellhound = new Monster("A Hellhound", "A creature of the underworld, a specter that assumes the form of a terrifying hound and tirelessly stalks its victims once it finds their trail.", 4, 4, 4, 3);
        golem = new Monster("A Golem", "A creature made of stone or wood, brought to life by a mage and animated with the use of magic.", 5, 4, 3, 3);
        arachas = new Monster("An Arachas", "Arachasae are lone hunters - they patiently wait for their prey to kill it with one swift strike when it appears.", 3, 5, 3, 4);
        vampire = new Monster("A Vampire", "As famous as they are deadly -  a vampire is a creature that thrives on blood.", 3, 4, 5, 3);
        harpy = new Monster("A Harpy", "A monster with a woman's head and a bird's body. They are known for their ugly temper and penchant for thieving.", 3, 4, 3, 5);
        drowner = new Monster("A Drowner", "A frightful creature of mud and scum that drags people down into mires and bubbling eddies.", 2, 4, 4, 5);
        manticore = new Monster("A Manticore", "A giant beast with the body of a lion, bat wings and scorpion tail.", 4, 3, 4, 4);
        ghost = new Monster("A Ghost", "Who ya gonna call? Takes some work to deal with these spectral creatures that generally haunt cemeteries and crypts.", 2, 3, 6, 4);
        lamia = new Monster("A Lamia", " A variety of vampire that has an apperance of a large snake with a female human head and bloody fangs. Cute.", 3, 4, 3, 5);
        werecat = new Monster("A Werecat", "Sentient creatures with shapeshifting abilities, being able to take on the appearance of either a human or a large, shaggy cat.", 3, 6, 3, 3);

        //Add to array
        monsters.add(hellhound);
        monsters.add(golem);
        monsters.add(arachas);
        monsters.add(vampire);
        monsters.add(harpy);
        monsters.add(drowner);
        monsters.add(manticore);
        monsters.add(ghost);
        monsters.add(lamia);
        monsters.add(werecat);

        //Fill monster variables       
        int monOneChosen = rng.nextInt(monsters.size()); //choose Attic monster //LIZ
        atticMonster = monsters.get(monOneChosen);
        monsters.remove(monOneChosen); //remove so no duplicates

        int monTwoChosen = rng.nextInt(monsters.size()); //choose basement monster
        baseMonster = monsters.get(monTwoChosen);
        monsters.remove(monTwoChosen);     

    }

    //creates the 11 rooms optional for the user and stores them in array
    private void createRooms()
    {
        Room foyer, dining, kitchen, closet, library, prayer, bathroom, kidsBedroom, masterBedroom, attic, basement, mystery, gym, blocked, trap;
        // create the rooms w/ inital descriptions used when first discovered.
        foyer = new Room("The Foyer.", "a beautiful, but dusty foyer area. The entry door to the south has been barred from the outside...", true, false);
        dining = new Room("The Dining Room.", "the dining room. Smells good...", false, false);
        kitchen = new Room("The Kitchen.", "a grimy kitchen with ingredients still sprawled out over the counters.", false, true);
        closet = new Room("The Closet.", "a cramped closet holding only one dress.", false, false);
        library = new Room("The Library.", "a room filled and filled with books.", false, true);
        prayer = new Room("The Prayer Room.", "a prayer room. Candle stubs surround an alter in the center of the room.", false, true);
        bathroom = new Room("The Bathroom.", "just a normal bathroom.", false, false);
        kidsBedroom = new Room("The Kid's Bedroom.", "a bedroom containing a small bed with an even smaller bear on the pillowcase.", false, false);
        masterBedroom = new Room("The Master Bedroom.", "a large bedroom. There is a layer of dust over everything.", false, false);
        gym = new Room ("The Gym.", "a surprisingly well stocked gym.", false, true);
        mystery = new Room("The Mystery Room.", "A single chest is in the center of the room. Closer inspection reveals 3 key slots...", true, false);
        attic = new Room("The Attic.", "", true, false);
        basement = new Room("The Basement.", "", true, false);
        trap = new Room("Trap Door Room.", "A blank room. Suddenly, the floor opens and you fall to the ground and hit hard enough to leave you winded and hurting.",false, false); 

        blocked = new Room("blocked", "", true, false); //when no door is there

        // place random rooms into Array
        rooms.add(dining);
        rooms.add(kitchen);
        rooms.add(closet);
        rooms.add(library);
        rooms.add(prayer);
        rooms.add(bathroom);
        rooms.add(kidsBedroom);
        rooms.add(masterBedroom);;
        rooms.add(mystery);
        rooms.add(gym); 
        rooms.add(trap);

        //place rooms with item capabilities to have no items yet
        itemRoomMap.put(kitchen, null);
        itemRoomMap.put(library, null);
        itemRoomMap.put(prayer, null);
        itemRoomMap.put(gym, null);

        // start game in & link stationary rooms attic & basement
        currentRoom = foyer;  
        foyer.setExit("west", attic);
        foyer.setExit("east", basement);

        //seals off restricted exits
        foyer.setExit("south", blocked);
        attic.setExit("north", blocked);
        attic.setExit("west", blocked);
        attic.setExit("south", blocked);
        basement.setExit("north", blocked);
        basement.setExit("east", blocked);
        basement.setExit("south", blocked);

        //place foyer in monMap where the monster will be spawned after conditions are met && store original instances for spawning 
        monMap.put(bossMonster1, foyer); 
        bossMonster1.setStartingRoom(foyer);

        //put the already discovered rooms in the old array for TARDIS
        SpecialRoom temp = new SpecialRoom("","",false,false);
        temp.addToArray(currentRoom);
        temp.addToArray(basement);
        temp.addToArray(attic);
    }

    //Links rooms together after room is discovered
    private void setRooms(String direction,Room room)
    {
        //set exit based on room character just left
        if(direction.equals("north"))
        {
            currentRoom.setExit("south", room);
        }
        else if (direction.equals("south"))
        {
            currentRoom.setExit("north",room);
        }
        else if (direction.equals("west"))
        {
            currentRoom.setExit("east", room);
        }
        else if (direction.equals("east"))
        {
            currentRoom.setExit("west", room);
        }

    }

    private boolean arrayEmpty()
    {
        return rooms.isEmpty();
    }

    private void createItems()
    {     
        Item blade, hammer, wand, axe, club, crucifix, familyPic, momLetter, luckCharm, mirror, monster, rockstar, redBull, nos,
        amp, botany, monsters101, noobSurvival, beSmart, ghostGhouls;

        // create the items with types that determine their weight
        blade = new Item("Blade", "weapon");
        hammer = new Item("Hammer", "weapon");
        axe = new Item("Axe", "weapon");
        wand = new Item("Wand", "weapon");
        club = new Item("Club", "weapon");

        crucifix = new Item("Crucifix", "personalObj");
        familyPic = new Item("Photograph", "personalObj");
        momLetter = new Item("Letter", "personalObj");
        luckCharm = new Item("Charm", "personalObj");
        mirror = new Item("Mirror", "personalObj");

        monster = new Item("Monster", "drink");
        rockstar = new Item("Rockstar", "drink");
        redBull = new Item("RedBull", "drink");
        nos = new Item("NOS", "drink");
        amp = new Item("Amp", "drink");

        botany = new Item("Botany", "book");
        monsters101 = new Item("Monsters:101", "book");
        noobSurvival = new Item("Survival", "book");
        beSmart = new Item("Intelligence&You", "book");
        ghostGhouls = new Item("Ghosts+Ghouls", "book");

        //add to array
        items.add(blade);
        items.add(hammer);
        items.add(axe);
        items.add(wand);
        items.add(club);
        items.add(crucifix);
        items.add(familyPic);
        items.add(momLetter);
        items.add(luckCharm);
        items.add(mirror);
        items.add(monster);
        items.add(rockstar);
        items.add(redBull);
        items.add(nos);
        items.add(amp);
        items.add(botany);
        items.add(monsters101);
        items.add(noobSurvival);
        items.add(beSmart);
        items.add(ghostGhouls);

        //Hashmap that will paid Strings with appropriate Item in inventory
        itemMap.put("Blade", blade);
        itemMap.put("Hammer", hammer);
        itemMap.put("Axe", axe);
        itemMap.put("Wand", wand);
        itemMap.put("Club",club);
        itemMap.put("Crucifix", crucifix);
        itemMap.put("Photograph", familyPic);
        itemMap.put("Letter", momLetter);
        itemMap.put("Charm", luckCharm);
        itemMap.put("Mirror", mirror);
        itemMap.put("Monster", monster);
        itemMap.put("Rockstar", rockstar);
        itemMap.put("RedBull", redBull);
        itemMap.put("NOS", nos);
        itemMap.put("Amp", amp);
        itemMap.put("Botany", botany);
        itemMap.put("Monsters:101", monsters101);
        itemMap.put("Survival-4-N00bs", noobSurvival);
        itemMap.put("Intelligence&You", beSmart);
        itemMap.put("Ghosts+Ghouls", ghostGhouls);

    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();
        printCharacterChoices();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
        boolean tardisSpawn = false;
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);

            //prevents trapping the character if the TARDIS spawns above the foyer
            if(roomsDiscovered >=5 && tardisSpawn == false)
            {
                Room tardis = new Room("The TARDIS.","a pulsing and flashing room that feels alive...", false, false);
                rooms.add(tardis);
                tardisSpawn = true;
            }

            //checks if enough rooms are dicovered to spawn the monster
            if(bossSpawn == false)
            {

                if(roomsDiscovered >= 6 && turn >= 5 && !inEvent && !rollEvent)
                {
                    bossSpawn = true;
                    bossSpawn();
                }
            }

            if(!(character == null)) //avoids the null excpetion if player doesn't choose character right away.
            {
                //if ur dead, you lose
                if(!character.isAlive())
                {
                    System.out.println("You have died and lost the game!");
                    finished = true;
                }

                //if you have all the keys and are in the mystery room, you win!
                if ((currentRoom.getShortDescription().equals("The Mystery Room.")) && character.checkKey() == true)
                {
                    System.out.println("");
                    System.out.println("");
                    System.out.println("||||      ||||    ||||||||     ||||     ||||   ");
                    System.out.println(" ||||    ||||  ||||||||||||||  ||||     ||||   ");
                    System.out.println("   ||||||||    ||||      ||||  ||||     ||||   ");
                    System.out.println("     ||||      ||||      ||||  ||||     ||||   ");
                    System.out.println("     ||||      ||||||||||||||   ||||   ||||    ");
                    System.out.println("     ||||         ||||||||       |||||||||     ");
                    System.out.println("");
                    System.out.println("");
                    System.out.println("  ||||      ||||   |||||||   ||||      ||||    ");
                    System.out.println("  ||||      ||||   |||||||   ||||      ||||    ");
                    System.out.println("  ||||  ||  ||||   |||||||   |||||     ||||    ");
                    System.out.println("  ||||  ||  ||||   |||||||   |||||||   |||     ");
                    System.out.println("  ||||||  ||||||   |||||||   |||| |||  |||     ");
                    System.out.println("    |||    |||     |||||||   ||||   ||||||     ");
                    System.out.println("");
                    System.out.println("");
                    System.out.println("Congrats you've won the game! \n The chest was opened with the three keys and revealed the contents- the lease of the household.");
                    System.out.println("Naturally, all monsters abide by leases, so the house (and all it's odd inhabitants and contents) belong to you!"); 
                    System.out.println("");
                    System.out.println("");
                    System.out.println("Created by Gracie Carver-Dews :)");
                    finished = true;
                }
            }
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("This is a text based adventure game based off the board game 'Betrayal at House on the Hill.'"); 
        System.out.println("How to Win: Search the house for the 3 keys and unlock the mystery chest to survive the house you have been trapped inside. 'The Owner' of the home will try to stop you.");
        System.out.println("How to Lose: If the monsters haunting the house drop any of your character's stats to 0- you lose... (NOTE: Events cannot drop stats to 0.)");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
        System.out.println(currentRoom.getExitString(arrayEmpty()));
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("");
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        //If the character hasn't been chosen, take the input and create the character. If any other actions are taken, give error message.
        if(chosenCharacter == false)
        {
            try
            {
                int intCommandWord  = Integer.parseInt(commandWord);
                charaChoose(intCommandWord);
            }catch(NumberFormatException e)
            {
                System.out.println("Must choose a character first. Please type the number of the player you want to play with.");
            }
        }
        else if(command.charaSelectCommands(commandWord)) //checks to see if it's a number after character has already been chosen
        {
            System.out.println("I don't know what you mean..");
        }
        else if (commandWord.equals("help")) {
            printHelp();
            System.out.println("");
        }
        else if (commandWord.equals("go")) {
            if(!inEvent  && !rollEvent) //don't clear while in event
            {
                System.out.print('\u000C'); //clear everything from previous room
            }

            goRoom(command);
            System.out.println("");
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
            System.out.println("");
        }

        else if (commandWord.equals("use")) {
            useItem(command);
            System.out.println("");
        }
        else if (commandWord.equals("show"))
        {
            showCommand(command);
            System.out.println("");
        } 

        else if (commandWord.equals("drop")) {
            dropItem(command);
            System.out.println("");
        }
        else if (commandWord.equals("take")){
            takeItem(command);
            System.out.println("");
        }        
        else if (commandWord.equals("investigate")){
            investigateMon(command);
            System.out.println("");
        }
        else if (commandWord.equals("attack")){

            if(command.hasSecondWord())//shouldn't have second word...
            {
                System.out.println("I don't know that you mean...");
            }

            else
            {
                System.out.println("Which stat would you like to attack with? ('might', 'speed', 'sanity', 'knowledge'.)");
                attackUsed = true;
            }
            System.out.println("");
        }
        else if (command.attackStat(commandWord)){
            System.out.println("");
            if(attackUsed == true && !command.hasSecondWord())
            {
                attackMonster(commandWord);
                attackUsed = false;
            }
            else
            {
                System.out.println("I don't know what you mean..."); 
            }
            System.out.println("");
        }
        else if(commandWord.equals("continue"))
        {
            System.out.println("");
            if(!inEvent) //has to be an event phase
            {
                System.out.println("I don't know what you mean...");
            }
            else if(command.hasSecondWord()) //shouldn't have second word
            {
                System.out.println("I don't know what you mean...");
            }
            else
            {
                normEvent();
                inEvent=false;
            }
            System.out.println("");
            System.out.println(currentRoom.getExitString(arrayEmpty()));
            System.out.println("");
        }
        else if(commandWord.equals("roll"))
        {
            System.out.println("");
            if(!rollEvent)
            {
                System.out.println("I don't know what you mean...");
            }
            else if (command.hasSecondWord())
            {
                System.out.println("I don't know what you mean...");
            }
            else
            {
                rollEvent();
                rollEvent = false;
            }
            System.out.println("");
            System.out.println(currentRoom.getExitString(arrayEmpty()));
            System.out.println("");
        }
        //command not recognized
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println();
        System.out.println("Goal: You need to get 3 keys from the monsters (standard ones + 'The Owner') in the house to unlock the chest in the Mystery Room");
        System.out.println();
        System.out.println("Monsters: The two monsters in the Attic & Basement are your standard creatures. Like the player, if thier stats drop to zero, they die and give you the key.");
        System.out.println();
        System.out.println("'The Owner': The main monster that comes in a differnt forms to haunt you and won't stop until you gather the keys. It is tough to defeat (all stats must be at zero) and will respawn after a couple of turns.");
        System.out.println();
        System.out.println("Events: These occur in almost every newly discovered room. Normal events have a predetermined result while roll events depend on your stats. Events will also randomly occur every 5 turns or so.");
        System.out.println();
        System.out.println("Your command words are: ");
        parser.showCommands();  
        System.out.println();
    }

    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message. Also handles monster movement, item placement, and event spawning.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord(); 
        boolean backCommand = false;
        //checks if one of the 4 cardinal directions
        if(command.goDirection(direction)  && !(direction.equals("back")))
        {
            System.out.println("Please use one of the four directions or 'go back' to go to previous room.");
            return;
        }

        //Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        if(direction.equals("back"))
        {
            if(lastRoom == null) //when someone who just started playing uses back command
            {
                System.out.println(currentRoom.getShortDescription());
                System.out.println("");
                System.out.println("Can't go back...");
                System.out.println("");
                System.out.println(currentRoom.getExitString(arrayEmpty()));
                System.out.println("");
                return;
            }
            else
            {
                backCommand = true;
                nextRoom = lastRoom;
                lastRoom = currentRoom;
            } 
        }

        if(inEvent == true || rollEvent == true)
        {
            System.out.println("Must complete event.");
            return;
        }

        boolean isEmpty = rooms.isEmpty();

        //replace null with random room from array
        if(nextRoom == null && isEmpty == false)
        {
            int roomChosen = rng.nextInt(rooms.size()); //replace null //LIZ
            nextRoom = rooms.get(roomChosen);
            rooms.remove(roomChosen); //remove from list so no duplicates
            System.out.println(nextRoom.getLongDescription());
            roomsDiscovered++;

            SpecialRoom temp = new SpecialRoom("","",false,false);
            //add to TARDIS array lsit for teleporting unless the room discovered is actually the TARDIS
            if(!(nextRoom.getShortDescription()).equals("The TARDIS."))
            {
                temp.addToArray(nextRoom); 
            }

            //check if event should be triggered in this new room and not in the special rooms/item rooms
            if(!nextRoom.itemInRoom() && (!(nextRoom.getShortDescription()).equals("The TARDIS.")) && (!(nextRoom.getShortDescription()).equals("Trap Door Room.")))
            {
                pickEvent();
                inEvent= true;
            }

            nextRoom.isDiscovered(); //change to discovered

            //check if item should be placed in room & place it if it should be based on which room
            if(nextRoom.itemInRoom() == true && (itemRoomMap.get(nextRoom)) == null)
            {
                int itemChosen = rng.nextInt(items.size()); //picks random number for array  //LIZ
                currentItem = items.get(itemChosen); // uses random number to get random item            
                boolean acceptable = currentItem.itemPair(nextRoom); //need to check if correct type and if not keep choosing random item til is.
                while(!acceptable)
                {
                    itemChosen = rng.nextInt(items.size()); //picks new random number from array  //LIZ
                    currentItem = items.get(itemChosen); //assigns new item again
                    acceptable = currentItem.itemPair(nextRoom); //checks if acceptable.If it is, it ends loop
                }
                itemRoomMap.put(nextRoom, currentItem);
                nextRoom.plusDescription(currentItem.itemName());
            }

            //if its a trap door, then do all the actions that go with it
            if ((nextRoom.getShortDescription()).equals("Trap Door Room."))
            {
                temp.trapDoorTriggered(character);
                character.showStats();
            }

            Room neighbor = currentRoom;
            lastRoom = currentRoom;
            currentRoom = nextRoom;
            setRooms(direction, neighbor); //exits for the new room
            neighbor.setExit(direction, currentRoom); //sets exits of room just left.

            System.out.println("");
            System.out.println(currentRoom.getExitString(arrayEmpty()));
            System.out.println("");

            //if the current room is the TARDIS- teleport.
            if((currentRoom.getShortDescription()).equals("The TARDIS."))
            {

                //teleport
                int choice = rng.nextInt(temp.oldArraySize()); //pick a random integer from the already discovered rooms  //LIZ
                currentRoom = temp.transportRoom(choice); //pick the room and change the current room to it.
                lastRoom = null; //You can't go back

                System.out.println("The console in the center of the room starts to shriek. You run out of the room only to find yourself back in the " + currentRoom.getShortDescription());
                System.out.println("");
                System.out.println(currentRoom.getExitString(arrayEmpty()));
                System.out.println("");
            }
        }        
        //check if blocked or all rooms used up
        else if ((isEmpty == true && nextRoom == null) || nextRoom.getShortDescription().equals("blocked")) 
        {
            System.out.println("There is no door!");
        }
        //room has already been discovered so just give short descrption
        else
        {
            System.out.println(nextRoom.getShortDescription());
            System.out.println(""); 

            //random event every 20 turns as long as in a room already discovered
            if (turn >= 15 && !nextRoom.itemInRoom()) //random event
            {
                pickEvent();
                inEvent= true;
                turn = 0; //reset
            }

            //check if item has been picked up or not & if not, show the user the item again.
            if (nextRoom.itemInRoom() == true)
            {
                currentItem = itemRoomMap.get(nextRoom);
                if((!(currentItem == null)) && !currentItem.itemTaken())
                {
                    currentItem = itemRoomMap.get(nextRoom);
                    nextRoom.plusDescription(currentItem.itemName());
                }
            }

            // checks if the room is a monster room and if alive 
            if(nextRoom.getShortDescription().equals("The Attic.") && atticMonster.isAlive() == true)
            {
                nextRoom.plusDescription(null);
            }    
            //basement & monster alive
            else if(nextRoom.getShortDescription().equals("The Basement.") && baseMonster.isAlive() == true)
            {
                nextRoom.plusDescription(null);
            }

            //checks is bossMonster is spawned and if it is in the current room (avoids null error)
            if (bossSpawn == true && nextRoom.getShortDescription().equals(((monMap.get(bossMonster1)).getShortDescription())))
            {
                nextRoom.plusDescription(null);
            }

            Room neighbor = currentRoom;
            lastRoom = currentRoom;
            currentRoom = nextRoom;
            if(backCommand == false)
            {
                setRooms(direction, neighbor);
            }
            System.out.println("");
            System.out.println(currentRoom.getExitString(arrayEmpty()));
            System.out.println("");
        }  
        turn++;
        goMoveBoss();
    }

    //BOSS MOVEMENT
    private void goMoveBoss()
    {
        //First Layer: checks if spawned. Second: if it should move or not. Third/Fourth: if the room is null/blocked then pick a new direction to move
        if(bossSpawn == true)
        {  
            bossAction(); //checks for player before movement
            if(bossMonster1.monsterMove() == 1)//prevents boss movement during an event and makes it move every other play movement.
            {
                String [] directions = {"north", "east", "west", "south"}; // choices for mon movement
                int directionPick = rng.nextInt(4);  //LIZ
                String monDirection = directions[directionPick];

                Room monRoom = (monMap.get(bossMonster1)).getExit(monDirection); // get the current room where monster is and then the exit for the direction randomly chosen.
                boolean isNull = false;

                //loops until direction is chosen that does not lead to an undiscovered room
                if(monRoom == null)
                {
                    isNull = true;
                    while(isNull)
                    {
                        directionPick = rng.nextInt(4);  //LIZ
                        monDirection = directions[directionPick];
                        monRoom = (monMap.get(bossMonster1)).getExit(monDirection);
                        if(!(monRoom == null))
                        {
                            isNull = false;
                        }
                    }
                }

                boolean acceptable =  bossMonster1.canMoveHere(monRoom.getShortDescription()); //checks not a blocked room/attic/basement
                while(!acceptable)
                {
                    directionPick = rng.nextInt(4);  //LIZ
                    monDirection = directions[directionPick];

                    monRoom = (monMap.get(bossMonster1)).getExit(monDirection);
                    //as long as not null do heck, if is null, start loop again
                    if(!(monRoom == null))
                    {
                        acceptable = bossMonster1.canMoveHere(monRoom.getShortDescription());
                    }
                }

                monMap.put(bossMonster1, monRoom);  
                System.out.println("");
                System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
                System.out.println("'The Owner' has moved to: " + monRoom.getShortDescription());
                System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
                bossMonster1.monsterWaitReset();
                bossAction(); //checks for player after movement
            }
            else //counts up the number of turns the monster has stayed in same room
            {
                bossMonster1.monsterWait();
            }
        }
    }

    //use item 
    private void useItem(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know which item...
            System.out.println("Use what?");
            character.showInventory();
            return;
        }

        String nameOfItem = command.getSecondWord();

        //checks if item in inventory
        if (!character.inInventory(nameOfItem))
        {
            System.out.println("Item not in inventory.");
            character.showInventory();
            return;
        }

        Item usedItem = itemMap.get(nameOfItem);
        usedItem.useItem(character);
        character.subFromInventory(usedItem);
        System.out.println("Item Used!");
    }

    //drops item
    private void dropItem(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know which item...
            System.out.println("Drop what?");
            character.showInventory();
            return;
        }

        String nameOfItem = command.getSecondWord();

        //checks if item in inventory
        if (!character.inInventory(nameOfItem))
        {
            System.out.println("Item not in inventory.");
            character.showInventory();
            return;
        }

        Item droppedItem = itemMap.get(nameOfItem);
        character.subFromInventory(droppedItem);
        System.out.println("Item Discarded!");
    }

    //take item from room
    private void takeItem(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take...
            System.out.println("Take what?");
            return;
        }

        String nameOfItem = command.getSecondWord();

        //if not a room that has an item or the item name is not in this room or item already taken then give error
        if((!currentRoom.itemInRoom()) || (!nameOfItem.equals(currentItem.itemName())) || currentItem.itemTaken())
        {
            System.out.println("That item isn't here to take.");
            //Question: should I make this make everything lower case so caps doesn't mess up
            return;
        }

        //If it gets to here, then the item typed is the current item in room so just add to inventory and remove from room
        character.addToInventory(currentItem);
        currentItem.takeItem();
        System.out.println("Item Taken!");
        character.showInventory();
    }

    //investigates the create and returns back info about it.
    private void investigateMon(Command command)
    {
        //checks if this room holds a monster
        if(!currentRoom.monsterRoom() && !(currentRoom.getShortDescription().equals(((monMap.get(bossMonster1)).getShortDescription())))) 
        {
            // if there is no monster in the room
            System.out.println("No monster to investigate.");
            return;
        }

        //prints info
        if(currentRoom.getShortDescription().equals("The Attic."))
        {
            atticMonster.printInvestigate();
        }
        else if(currentRoom.getShortDescription().equals("The Basement."))
        {
            baseMonster.printInvestigate();
        }
        else
        {
            bossMonster1.printInvestigate();
        }
    }

    //attack the monster
    private void attackMonster(String commandWord)
    {   
        //First layer of if statements checks it is is a valid command. Second- which room and monster. Third- who wins. Fourth- which stat is effected.

        if(commandWord.equals("might") || commandWord.equals("speed") || commandWord.equals("sanity") || commandWord.equals("knowledge"))
        {
            int monSum = 0;
            int charSum = 0;
            int charStat = character.stringToStat(commandWord);
            int monStat = 0;

            if(currentRoom.getShortDescription().equals("The Attic."))
            {

                //check is monster still alive- if not, return null
                if (!atticMonster.isAlive())
                {
                    System.out.println("The monster of this room has already been slain.");
                    System.out.println("");
                    System.out.println(currentRoom.getExitString(arrayEmpty()));
                    System.out.println("");
                    return;
                }

                monStat = atticMonster.stringToStat(commandWord);
                charSum = character.charAttack(charStat);
                monSum = atticMonster.monAttack(monStat);
                //compare the two sum totals and deal appropriate damage
                if(charSum > monSum) //if character wins
                {
                    //see which stat needs to dropped for monster
                    if(commandWord.equals("might"))
                    {
                        atticMonster.changeMight(-1);
                        System.out.println("");
                        System.out.println("Monster's might has been dropped to: " + atticMonster.getMight());
                    }
                    else if (commandWord.equals("speed"))
                    {
                        atticMonster.changeSpeed(-1);
                        System.out.println("");
                        System.out.println("Monster's speed has been dropped to: " + atticMonster.getSpeed());
                    }
                    else if (commandWord.equals("sanity"))
                    {
                        atticMonster.changeSanity(-1);
                        System.out.println("");
                        System.out.println("Monster's sanity has been dropped to: " + atticMonster.getSanity());
                    }
                    else
                    {
                        atticMonster.changeKnowledge(-1);
                        System.out.println("");
                        System.out.println("Monster's knowledge has been dropped to: " + atticMonster.getKnowledge());
                    }
                }
                else if (charSum < monSum) //if monster wins
                {
                    //stats that need to be changed for character
                    if(commandWord.equals("might"))
                    {
                        character.changeMight(-1);
                        System.out.println("");
                        System.out.println("Your might has been dropped to: " + character.getMight());
                    }
                    else if (commandWord.equals("speed"))
                    {
                        character.changeSpeed(-1);
                        System.out.println("");
                        System.out.println("Your speed has been dropped to: " + character.getSpeed());
                    }
                    else if (commandWord.equals("sanity"))
                    {
                        atticMonster.changeSanity(-1);
                        System.out.println("");
                        System.out.println("Your sanity has been dropped to: " + character.getSanity());
                    }
                    else
                    {
                        atticMonster.changeKnowledge(-1);
                        System.out.println("");
                        System.out.println("Your knowledge has been dropped to: " + character.getKnowledge());
                    }
                }
                else //they equal
                {
                    System.out.println("");
                    System.out.println("Attacks cancel each other out! No damage was done.");
                    System.out.println("");
                }

                //check if attic monster is defeated and give key to player if so
                if(!atticMonster.isAlive())
                {
                    System.out.println("Monster was defeated! Your character grabbed the key!");
                    character.keyCount();
                }

                System.out.println("");
                System.out.println(currentRoom.getExitString(arrayEmpty()));
                System.out.println("");
            }

            //basement monster
            else if(currentRoom.getShortDescription().equals("The Basement."))
            {

                //check is monster still alive- if not, return null
                if (!baseMonster.isAlive())
                {
                    System.out.println("The monster of this room has already been slain.");
                    return;
                }

                monStat = baseMonster.stringToStat(commandWord);
                charSum = character.charAttack(charStat);
                monSum = baseMonster.monAttack(monStat);
                //compare the two sum totals and deal appropriate damage
                //compare the two sum totals and deal appropriate damage
                if(charSum > monSum) //if character wins
                {
                    //see which stat needs to dropped for monster
                    if(commandWord.equals("might"))
                    {
                        baseMonster.changeMight(-1);
                        System.out.println("");
                        System.out.println("Monster's might has been dropped to: " + baseMonster.getMight());
                    }
                    else if (commandWord.equals("speed"))
                    {
                        baseMonster.changeSpeed(-1);
                        System.out.println("");
                        System.out.println("Monster's speed has been dropped to: " + baseMonster.getSpeed());
                    }
                    else if (commandWord.equals("sanity"))
                    {
                        baseMonster.changeSanity(-1);
                        System.out.println("");
                        System.out.println("Monster's sanity has been dropped to: " + baseMonster.getSanity());
                    }
                    else
                    {
                        baseMonster.changeKnowledge(-1);
                        System.out.println("");
                        System.out.println("Monster's knowledge has been dropped to: " + baseMonster.getKnowledge());
                    }

                }
                else if (charSum < monSum) //if monster wins
                {
                    //stats that need to be changed for character
                    if(commandWord.equals("might"))
                    {
                        character.changeMight(-1);
                        System.out.println("");
                        System.out.println("Your might has been dropped to: " + character.getMight());
                    }
                    else if (commandWord.equals("speed"))
                    {
                        character.changeSpeed(-1);
                        System.out.println("");
                        System.out.println("Your speed has been dropped to: " + character.getSpeed());
                    }
                    else if (commandWord.equals("sanity"))
                    {
                        atticMonster.changeSanity(-1);
                        System.out.println("");
                        System.out.println("Your sanity has been dropped to: " + character.getSanity());
                    }
                    else
                    {
                        atticMonster.changeKnowledge(-1);
                        System.out.println("");
                        System.out.println("Your knowledge has been dropped to: " + character.getKnowledge());
                    }
                }
                else //they equal
                {
                    System.out.println("");                 
                    System.out.println("Attacks cancel each other out! No damage was done.");
                    System.out.println("");
                }

                //checks if basement monster is defeated and give key to player if so
                if(!baseMonster.isAlive())
                {
                    System.out.println("Monster was defeated! Your character grabbed the key!");
                    System.out.println("");
                }

                System.out.println("");
                System.out.println(currentRoom.getExitString(arrayEmpty()));
                System.out.println("");
            }

            else //bossMonster
            {
                boolean monsterAttackedFirst = false; //needed for monster raction to character attacked.          

                //check if monster is spawned in foyer yet or in the same room- if not, return null
                if (bossSpawn == false || !((monMap.get(bossMonster1)).getShortDescription()).equals(currentRoom.getShortDescription()))
                {
                    System.out.println("");
                    System.out.println("There is nothing to attack.");
                    return;
                }

                //determine who is attacking whom
                if(bossMonster1.getAttackedValue()) //bossMonster attacked
                {
                    monSum = bossMonster1.monAttack(bossMonster1.getHighestStat());
                    charSum = character.charAttack(charStat);
                    monsterAttackedFirst = true;
                }
                else //char attacked first
                {
                    monStat = bossMonster1.stringToStat(commandWord);
                    //can't attack something already at 0
                    if(monStat == 0)
                    {
                        System.out.println("'The Owner' is already defeated in this stat. Please attack with another.");
                        System.out.println(bossMonster1.getAllStats());
                        return;
                    }
                    charSum = character.charAttack(charStat);                
                    monSum = bossMonster1.monAttack(monStat);
                }

                //compare the two sum totals and deal appropriate damage
                if(charSum > monSum) //if character wins
                {
                    //see which stat needs to dropped for monster
                    if(commandWord.equals("might"))
                    {
                        bossMonster1.changeMight(-1);
                        System.out.println("");
                        System.out.println("Monster's might has been dropped to: " + bossMonster1.getMight());
                    }
                    else if (commandWord.equals("speed"))
                    {
                        bossMonster1.changeSpeed(-1);
                        System.out.println("");
                        System.out.println("Monster's speed has been dropped to: " + bossMonster1.getSpeed());
                    }
                    else if (commandWord.equals("sanity"))
                    {
                        bossMonster1.changeSanity(-1);
                        System.out.println("");
                        System.out.println("Monster's sanity has been dropped to: " + bossMonster1.getSanity());
                    }
                    else
                    {
                        bossMonster1.changeKnowledge(-1);
                        System.out.println("");
                        System.out.println("Monster's knowledge has been dropped to: " + bossMonster1.getKnowledge());
                    }

                }
                else if (charSum < monSum) //if monster wins
                {
                    //stats that need to be changed for character
                    if(commandWord.equals("might"))
                    {
                        character.changeMight(-1);
                        System.out.println("");
                        System.out.println("Your might has been dropped to: " + character.getMight());
                    }
                    else if (commandWord.equals("speed"))
                    {
                        character.changeSpeed(-1);
                        System.out.println("");
                        System.out.println("Your speed has been dropped to: " + character.getSpeed());
                    }
                    else if (commandWord.equals("sanity"))
                    {
                        atticMonster.changeSanity(-1);
                        System.out.println("");
                        System.out.println("Your sanity has been dropped to: " + character.getSanity());
                    }
                    else
                    {
                        atticMonster.changeKnowledge(-1);
                        System.out.println("");
                        System.out.println("Your knowledge has been dropped to: " + character.getKnowledge());
                    }
                }
                else //they equal
                {
                    System.out.println("");
                    System.out.println("Attacks cancel each other out! No damage was done.");
                    System.out.println("");
                }

                //reaction of monster if player attacked first
                if(bossMonster1.isAlive() && !monsterAttackedFirst)
                {
                    bossAction();   
                }

                //checks if monster is defeated and if this is the first time defeated, get key
                if(!bossMonster1.isAlive())
                {
                    boolean gotKeyAlready = bossMonster1.gotKey();
                    if(gotKeyAlready)
                    {
                        System.out.println("Already have recieved key from this monster.");
                    }
                    else
                    {
                        System.out.println("Monster was defeated! Your character grabbed the key!");
                        character.keyCount();
                        bossMonster1.alreadyGotKey(); //changes to true
                        turn = 0;
                        bossSpawn = false;
                        monMap.put(bossMonster1,(bossMonster1.getStartingRoom()));                       
                    }
                }
                else
                {
                    System.out.println("");
                    System.out.println("Type 'attack' to begin attacking again or leave to another room to gather your strength.");
                }

                System.out.println("");
                System.out.println(currentRoom.getExitString(arrayEmpty()));
                System.out.println("");
            }
        }

        else
        {
            System.out.println("I don't know what that means...Type 'attack' if you want to attack the monster.");
        }
    }

    //event methods. Has 3 b/c it allows the user time to read the info and process before other parts of event take place.

    //choose the current event from random array in the Event class
    private void pickEvent()
    {
        System.out.println("############################################################################################################################");
        int randomNumber = rng.nextInt(currentEvent.getArraySize()); //LIZ
        currentEvent = currentEvent.pickEvent(randomNumber); //sets currentEvent as the one chosen in the events array

        System.out.println("Event triggered: " + "'" + currentEvent.getName() + "'");
        System.out.println("Type 'continue' to progress through the event.");       
    }

    //events that have an already determined effect
    private void normEvent()
    {
        System.out.println(currentEvent.getDescription());
        String effect = currentEvent.getEffect();       

        //effect on character depeneding on 'effect' parameter of event
        if(effect.equals("plusMight"))
        {
            character.changeMight(1);
            System.out.println("Character's might is now: " + character.getMight());
        }
        else if (effect.equals("plusSpeed"))
        {
            character.changeSpeed(1);
            System.out.println("Character's speed is now: " + character.getSpeed());
        }
        else if (effect.equals("plusSanity"))
        {
            character.changeSanity(1);
            System.out.println("Character's sanity is now: " + character.getSanity());
        }
        else if (effect.equals("plusKnowledge"))
        {
            character.changeKnowledge(1);
            System.out.println("Character's knowledge is now: " + character.getKnowledge());
        }
        else if (effect.equals("minusMight") && character.getMight() > 1) //events can't kill character
        {
            character.changeMight(-1);
            System.out.println("Character's might is now: " + character.getMight());
        }
        else if (effect.equals("minusSpeed") && character.getSpeed() > 1)
        {
            character.changeSpeed(-1);
            System.out.println("Character's speed is now: " + character.getSpeed());
        }
        else if (effect.equals("minusSanity")&& character.getSanity() > 1)
        {
            character.changeSanity(-1);
            System.out.println("Character's sanity is now: " + character.getSanity());
        }
        else if (effect.equals("minusKnowledge") && character.getKnowledge() > 1)
        {
            character.changeKnowledge(-1);
            System.out.println("Character's knowledge is now: " + character.getKnowledge());
        }
        else if (character.getKnowledge() > 1) //must be a roll event then.
        {    
            rollEvent = true;
            System.out.println("This is a 'stat' event! Must roll greater than a 15 sum with the event stat to 'win' the event.");
            System.out.println("Type 'roll' to begin the stat roll.");
        }
        else
        {
            System.out.println("");
            System.out.println("Though the event impacted you negatively, you cannot be killed by events so there is no impact on stats.");
        }
        System.out.println("############################################################################################################################");
    }

    //events that depend on stats to determine the outcome.
    private void rollEvent()
    {
        String effect = currentEvent.getEffect();  
        //roll events
        if (effect.equals("rollMight"))
        {
            System.out.println("Your might is: " + character.getMight());
            int sum = character.charRoll(character.getMight());

            //result from roll         
            if( sum <= 15 && character.getMight() == 1) //catches if the character stat is 1 first b/c events can't kill
            {
                System.out.println("");
                System.out.println(currentEvent.loseResponse());
                System.out.println("Though you lost the roll, you cannot be killed by events and so no effect impacts you.");
            }
            else if (sum <= 15) //lose
            {
                System.out.println("");
                System.out.println(currentEvent.loseResponse());
                character.changeMight(-1);
            }
            else //win
            {
                System.out.println("");
                System.out.println(currentEvent.winResponse());
                character.changeMight(1);
            }
            System.out.println("Character's might is now: " + character.getMight());
        }
        else if (effect.equals("rollSpeed"))
        {
            System.out.println("Your speed is: " + character.getSpeed());
            int sum = character.charRoll(character.getSpeed());

            if( sum <= 15 && character.getSpeed() == 1) //catches if the character stat is 1 first b/c events can't kill
            {
                System.out.println("");
                System.out.println(currentEvent.loseResponse());
                System.out.println("Though you lost the roll, you cannot be killed by events and so no effect impacts you.");
            }
            else if (sum <= 15) //lose
            {
                System.out.println("");
                System.out.println(currentEvent.loseResponse());
                character.changeSpeed(-1);
            }
            else //win
            {
                System.out.println("");
                System.out.println(currentEvent.winResponse());
                character.changeSpeed(1);
            }
            System.out.println("Character's speed is now: " + character.getSpeed());
        }
        else if (effect.equals("rollSanity"))
        {
            System.out.println("Your sanity is: " + character.getSanity());
            int sum = character.charRoll(character.getSanity());

            if( sum <= 15 && character.getSanity() == 1) //catches if the character stat is 1 first b/c events can't kill
            {
                System.out.println("");
                System.out.println(currentEvent.loseResponse());
                System.out.println("Though you lost the roll, you cannot be killed by events and so no effect impacts you.");
            }
            else if (sum <= 15) //lose
            {
                System.out.println("");
                System.out.println(currentEvent.loseResponse());
                character.changeSanity(-1);
            }
            else //win
            {
                System.out.println("");
                System.out.println(currentEvent.winResponse());
                character.changeSanity(1);
            }
            System.out.println("Character's sanity is now: " + character.getSanity());
        }
        else if (effect.equals("rollKnowledge"))
        {
            System.out.println("Your knowledge is: " + character.getKnowledge());
            int sum = character.charRoll(character.getKnowledge());

            if( sum <= 15 && character.getKnowledge() == 1) //catches if the character stat is 1 first b/c events can't kill
            {
                System.out.println("");
                System.out.println(currentEvent.loseResponse());
                System.out.println("Though you lost the roll, you cannot be killed by events and so no effect impacts you.");
            }
            else if (sum <= 15) //lose
            {
                System.out.println("");
                System.out.println(currentEvent.loseResponse());
                character.changeKnowledge(-1);
            }
            else //win
            {
                System.out.println("");
                System.out.println(currentEvent.winResponse());
                character.changeKnowledge(1);
            }
            System.out.println("Character's knowledge is now: " + character.getKnowledge());
        }
        //the character has a negative normal event but the stat is already at 1
        else
        {
            System.out.println("Should never reach here.");
        }
        rollEvent = false;
        System.out.println("############################################################################################################################");
    }

    //spawns the boss after at least 3 rooms have been discovered
    private void bossSpawn()
    {
        System.out.println("");
        System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("'The Owner' has appeared in the house and has begun to look for you!");
        System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("");
        int bossMonChosen = rng.nextInt(monsters.size()); //choose bossMonster  //LIZ
        BossMonster bossMonsterTemp = new BossMonster (monsters.get(bossMonChosen));
        BossMonster tempBossMonster = bossMonster1;
        bossMonster1 = bossMonsterTemp;

        Room monCurrentRoom = monMap.put(bossMonster1, (monMap.get(tempBossMonster))); //replaces temp bossMonster as the key value for the original room 'foyer'
    }

    //decides if the boss takes action or not.
    private void bossAction()
    {
        String monCurrentRoom = (monMap.get(bossMonster1)).getShortDescription();        
        //if monster is in the same room as the character
        if ((monCurrentRoom.equals(currentRoom.getShortDescription())) && (!inEvent))
        {
            System.out.println("");
            System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
            System.out.println("'The Owner' is in this room!");
            bossMonster1.bossAttack();
            //if it did attack during the method, do the attack rolls in game.
            if(bossMonster1.getAttackedValue() == true)
            {
                attackMonster(bossMonster1.getStringStat());
                bossMonster1.attackDone();
                System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
            }
        }        
    }

    private void showCommand(Command command)
    {
        String word = command.getSecondWord();
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take...
            System.out.println("Show what? (inventory, player, exits, commands)");
            return;
        }
        word.trim();
        word.toLowerCase();

        if(word.equals("inventory")) //all items in inventory, space taken, space left, and also the ammount of keys you have
        {
            System.out.println();
            character.showInventory();
        }

        else if(word.equals("player")) //to get your own stats
        {
            System.out.println();
            character.showStats();
        }
        else if(word.equals("exits")) //shows exits
        {
            System.out.println();
            System.out.println(currentRoom.getExitString(arrayEmpty()));
        }
        else if(word.equals("commands")) //shows commands
        {
            parser.showCommands();
        }
        else
        {
            System.out.println();
            System.out.println("I don't know what you mean..");
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
