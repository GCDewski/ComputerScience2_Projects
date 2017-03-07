import java.util.ArrayList;
/**
 * All the possible events and then choosing a random one from the deck
 * 
 * @Gracie Carver-Dews
 * @3/20/15
 */
public class Event
{
    private static ArrayList<Event> events = new ArrayList<Event>();
    String name;
    String description;
    String effect;
    String loseResponse;
    String winResponse;

    public Event(String name, String description, String effect, String loseResponse, String winResponse)
    {
        this.name = name;
        this.description = description;
        this.effect = effect;
        this.loseResponse = loseResponse;
        this.winResponse = winResponse;
    }

    public void createEvents()
    {
        Event event1, event2, event3, event4, event5, event6, event7, event8, event9, event10, event11, event12, event13, event14, event15, event16, event17, event18, event19, event20, event21, event22, event23, event24, event25,
        event26, event27, event28, event29, event30, event31, event32, event33, event34, event35, event36, event37, event38, event39, event40, event41, event42, event43, event44, event45, event46, event47, event48, event49, event50;

        //possible static events (20 total)     
        event1 = new Event("Optical Illusions Are Fun", "A dress is suddenly suspended in the air at the center of the room. Cautiously approaching the dress, you notice that its pattern is blue & black...Or is it gold & white!?(Sanity: -1)", "minusSanity", "", "");
        event2 = new Event("A Mirror's Thanks", "A mirror appears on the wall showing an image of yourself. Your reflection begins to give you some monster tips in thanks for all those peptalks in Middle School. (Knowledge: +1)", "plusKnowledge", "", "");
        event3 = new Event("A Song Of Storms", "A familiar tune whispers through the room. Unfortunatly it also bring a cloud over your head that rains that slows your progress. (Speed: -1)", "minusSpeed", "", "");
        event4 = new Event("Casper The Cook", "There's yummy smell that suddenly fills the the air and leads you to a plate of food laid out with the note 'ghosts aren't all bad' on the side. (Might: +1)", "plusMight", "", "");
        event7 = new Event("Adreneline", "A spider the size of a fist lands on your shoulder. Faster than you have ever reacted before, you swipe it off your shoulder and are now on high alert for danger. (Speed: +1)", "plusSpeed", "", "");
        event8 = new Event("GhostBusters", "Looking around the room, you notice a DVD box set sitting on a table in the corner. Looking at the title and acknowledging your current situation, you decide to lock yourself into a room containing a TV and watch the set. (Knowledge: +1)", "plusKnowledge", "", "");
        event9 = new Event("Trekky", "There is a small phrase carved on the wall: 'Live long and prosper'. You feel more determined to do so. (Might: +1)", "plusMight", "", "");
        event10 = new Event("Good For The Soul", "A familiar theme rings throughout the house compelling you to find the source. It leads you to a videogame system already hooked up and ready to go. Disregarding the circumstances, you play for a couple hours to relax. (Sanity: +1)", "plusSanity", "", "");
        event11 = new Event("Footloose", "In defiance of how disturbing the current situation is, you put on your headphones and dance. Unfortunatly, you danced a little too hard and stubbed your toe. (Might: -1)", "minusMight", "", "");
        event12 = new Event("GERONIMO", "While inspecting a particularaly suspicious set of stairs, someone/something pushes you down them from behind you. (Might: -1)", "minusMight", "", "");
        event13 = new Event("It's Art", "A beautiful painting catches your attention on the wall. It makes you feel grounded just looking at it. (Sanity: +1)", "plusSanity", "", "");
        event14 = new Event("It's Art...", "A painting catches your attention on the wall. You spend way too much time trying to figure out what it actually is a painting of... (Sanity: -1)", "minusSanity", "", "");
        event15 = new Event("Trolls", "A yell of 'trolls incoming!' echoes down the hall toward you. You prepare for a fight but instead only come across a computer logged onto an angry forum. It's comforting to know not everything is so different in this house. (Sanity +1)", "plusSanity", "","");
        event16 = new Event("There's Always One", "Have you ever watched a movie so bad it numbs your mind? Well you have now. (Knowledge: -1)", "minusKnowledge", "", "");
        event17 = new Event("For Dummies", "You tried to take a shortcut and read a complex book on swordplay. Safe to say, you are more confused than when you started. (Knowledge: -1)", "minusKnowledge", "","");
        event18 = new Event("Watching", "It's like a cheezy horror movie. This picture of a woman that suddenly appeared on the wall keeps following you with its eyes regardless of where you move in the room. (Sanity: -1)", "minusSanity", "", "");
        event19 = new Event("Lights Out", "While walking around the room, the lights suddenly go out. You have a flashlight but it still slows your progress. (Speed: -1)", "minusSpeed", "", "");
        event20 = new Event("Barry Allen", "The lighting is dark and you attempt to plug in a nearby lamp to an outlet. Unfortunatly, it shocks you but the electricity that moves through your body makes you feel a little bit more energized. (Speed: +1", "plusSpeed", "", "");
        event21 = new Event("Dear Diary", "A previous inhabitant of the house wrote in a diary that you have just discovered. Bypassing all the teenage drama, you do learn a good deal of things about the house's layout. (Knowledge: +1)", "plusKnowledge","","");
        event22 = new Event("It's Dangerous To Go Alone", "An older man's voice reverberates down from the nearest hallway- 'Take this!' An energy bar slides down the hallway before stopping at your feet. It's surprisingly good. (Might: +1)" , "plusMight", "","");

        //possible diceRoll events (20 total)
        event5 = new Event("Ring-a-ling", "A phone rings in the room. You feel compelled to answer it. A sweet grandmontherly voice challenges you to a dice roll for information. (Knowledge Roll)", "rollKnowledge", "She picks your brain for useless information until your head hurts. (Knowledge: -1)" ,"She grudgingly gives you a tip about the monster wandering the house. (Knowledge: + 1)");
        event6 = new Event("Livin' on a Prayer", "A ghost dressed like a priest enters the room. Oddly enough, he accuses you of being the ghost!(Sanity Roll)", "rollSanity", "The 'priest' is so convincing that you start to wonder yourself about your humanity.(Sanity: -1)", "You are pursuasive enough that the 'priest' realizes the truth and wanders away. (Sanity: +1)");
        event23 = new Event("Something Slimy", "Something reaches out from under the furniture and wraps around your ankle. (Speed Roll)", "rollSpeed", "You get away in time, but lost a shoe in the process. (Speed: -1)", "You immediately react and get away before any damage is done. (Speed: +1)"); 
        event24 = new Event("Make Me Like Mike", "A pair of shoes is sprawled half hazardly in the corner of the room. Closer inspection reveals the initials 'MJ' on the tounge. You swap out your old shoes for these new ones. (Speed Roll)", "rollSpeed", "The shoes protest their new owner's unworthiness and tear. (Speed: -1)", "The shoes fit snug and give you a burst of speed. (Speed: +1)"); 
        event25 = new Event("Bazinga!", "Waking up from a quick cat nap, you are alarmed to see that the current room has been convered with trip wires by a prankster Goblin and you have just triggered one. (Sanity Roll)", "rollSanity", "Water balloons pelt you from all sides and your mood just got a notch worse. (Sanity: -1)", "Water balloons pelt you from all sides, but you decide to laugh it off. (Sanity: +1)");
        event26 = new Event("Hungry Like The Wolf", "A shadow chases behind you with loud breathing daring you to stop for even a second. While running, you quickly glance out a sealed window and confirm that it's a full moon. (Might Roll)", "rollMight", "You sprint away from the monster behind you, only stopping for brief moments until sunrise. It leaves you exhausted. (Might: -1)", "You pull away from the monster far enough to allow yourself enough time to duck in a room out of sight and rest til morning. (Might: +1)");
        event27 = new Event("Vegetarian", "You come across a zombie who politely informs you of his change in lifestyle and his new passion to teach battle tactics. (Might Roll)", "rollMight", "He relapses in the middle of giving you a lesson and takes a chunk out of you before you can get away. (Might: -1)","You recieve some great conversation and meaningful advice. (Might: +1)");
        event28 = new Event("Mother May I", "A witch challenges you to a game after you accidently knock over her cauldron. (Knowledge Roll)", "rollKnowledge", "You play the game for an obscene amount of time before you realize you can't win this type of game and run away. (Knowledge: -1)", "You trick her in such a way that you win the game. (Knowledge: +1)");
        event29 = new Event("For Science", "In the middle of a rest, the phone rings compelling you to answer it. A robotic voice congratulates you on being chosen for testing. (Knowledge Roll)", "rollKnowledge", "You wake up with no memory and a throbbing headache. (Knowledge: -1)", "You wake up with no memory, but a full cake is laid out in front of you for your enjoyment. (Knowledge: +1)");
        event30 = new Event("Skating On Thin Ice", "You come across a dispute between a werewolf and a vampire. To your dismay, they ask you to settle the dispute. (Sanity Roll)", "rollSanity", "The more you try to defuse the situation the worse it gets. The situation ends in a fight and you a little worse for wear in the aftermath. (Sanity: -1)", "You successfully disfuse the situation and escape unharmed. (Sanity: +1)");
        event31 = new Event("You Gotta Pick a Pocket", "A huge Dagon has it's back turned to you with a small key to a food cache in its back pocket. You decide to take the risk and try and grab it. (Speed Roll)" ,"rollSpeed", "Your attempt is unsuccessful. The Dagon notices and lands a few blows before you get away. (Speed: -1)", "The attempt is sucessful and The Dagon is none the wiser. You have a full belly to look forward to. (Speed: +1)");
        event32 = new Event("Hulk Smash", "Your progress is blocked by a golem who challenges you to a arm wrestle. Normally you wouldn't dare, but since the golem is clearly an adolescent, you accept. (Might Roll)" , "rollMight", "He overtakes you and sprains your wrist in the process. (Might: -1)", "You win the match and a better reputation. (Might: +1)");
        event33 = new Event("Don't Mess With TX", "A cyclops (that boasts an occupation of a cowboy) challenges you to a western style draw. (Speed Roll)", "rollSpeed", "You are too slow and get shot. Good thing it was a paintball gun. (Speed: -1)", "You are faster than your cowboy counterpart and win the draw. (Speed: +1)");
        event34 = new Event("Slow Motion", "Entering the room, you come face to face with a ghoul holding a blowdart aimed at you. (Speed Roll)", "rollSpeed", "You try to jump away but get nailed in the leg with the dart. The side effect is heavy dizziness. (Speed: -1)", "You react faster than the ghoul and dodge the dart by bending backwards almost horizontally. The ghoul flees as a result. (Speed: +1)");
        event35 = new Event("Shrieking Shack", "A group of Banshees are shrieking so loud you can't think. (Knowledge Roll)", "rollKnowledge", "Sick of hearing them, you recklessly storm them. You are sucessful, but the aftermath has your head ringing. (Knowledge: -1)", "Remembering that a Banshee's greatest strength is their voice, you stuff your ears before chasing them off. (Knowledge: +1)");
        event36 = new Event("Simon Says", "A Copycat (an annoying but harmless creature) has begun to shadow you. (Sanity Roll)", "rollSanity", "It was easy to ignore at first, but the mimicking has started to drive you mad. You spend the whole time trying to get it to stop until you collapse from exhaustion and it goes away. (Sanity: -1)", "Playing this game as a kid, you recognize that it will stop once it gets bored so you ignore it long enough that it leaves. (Sanity: +1)");
        event37 = new Event("Inception", "Every time you attempt to go into a different room, you enter back into the same room! (Sanity Roll)", "rollSanity", "You try absolutely everything before you becoming mentally exhausted. You wake up the next morning at it seems all the doors work as normal...? (Sanity: -1)", "Knowing that the idea of a never ending room is impossible, you jerk awake and away from a Dreamcatcher that had put you into a trance while resting. It was all a dream. (Sanity: +1)");
        event38 = new Event("Curiosity & Cat", "An expensive looking relic is sitting in the corner covered with dust. (Knowledge Roll)", "rollKnowledge", "Curiosity wins over reason and you pick it up. It gives you a nasty shock as a consequence. (Knowledge: -1)", "Without touching it, you decide to investigate. It looks dangerous so you instead turn your attention to the 'Monster Lore' book UNDER the object. (Knowledge: +1)");
        event39 = new Event("vs. The Shelf", "A secret room is blocked by a very heavy shelf. (Might Roll)", "rollMight", "The shelf only shifts enough to drop a book onto your foot. (Might: -1)", "The shelf moves! You gain access to a room of goodies that restore your strength. (Might: +1)");
        event40 = new Event("Classic", "Like all adventure games should have, there happens to be a section of snakes that requires a big jump to cross and get to the next room. Why'd it have to be snakes? (Might Roll)", "rollMight", "Well your jump wasn't great. You got far enough that you could pull yourself out but it still hurt. (Might: -1)", "Well that went better than expected! Not a scratch on you. (Might: +1)");

        //add to array
        events.add(event1);
        events.add(event2);
        events.add(event3);
        events.add(event4);
        events.add(event5);
        events.add(event6);
        events.add(event7);
        events.add(event8);
        events.add(event9);
        events.add(event10);
        events.add(event11);
        events.add(event12);
        events.add(event13);
        events.add(event14);
        events.add(event15);
        events.add(event16);
        events.add(event17);
        events.add(event18);
        events.add(event19);
        events.add(event20);
        events.add(event21);
        events.add(event22);
        events.add(event23);
        events.add(event24);
        events.add(event25);
        events.add(event26);
        events.add(event27);
        events.add(event28);
        events.add(event29);
        events.add(event30);
        events.add(event31);
        events.add(event32);
        events.add(event33);
        events.add(event34);
        events.add(event35);
        events.add(event36);
        events.add(event37);
        events.add(event38);
        events.add(event39);
        events.add(event40);

    }

    //get methods
    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public String winResponse()
    {
        return winResponse;
    }

    public String loseResponse()
    {
        return loseResponse;
    }

    public int getArraySize()
    {
        return events.size();
    }
    
    public String getEffect()
    {
        return effect;
    }
    
    //choose event to give from array
    public Event pickEvent(int randomNumber)
    {
        Event chosenEvent;
        chosenEvent = events.get(randomNumber);
        events.remove(chosenEvent);
        return chosenEvent;
    }

    //gets rid of Event in array after chosen
    public void removeEvent(int randomNumber)
    {
        events.remove(randomNumber);
    }

}
