/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two strings: a command word and a second
 * word (for example, if the command was "take map", then the two strings
 * obviously are "take" and "map").
 * 
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the command word is <null>.
 *
 * If the command had only one word, then the second word is <null>.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.08
 */

public class Command
{
    private String commandWord;
    private String secondWord;

    /**
     * Create a command object. First and second word must be supplied, but
     * either one (or both) can be null.
     * @param firstWord The first word of the command. Null if the command
     *                  was not recognised.
     * @param secondWord The second word of the command.
     */
    public Command(String firstWord, String secondWord)
    {
        commandWord = firstWord;
        this.secondWord = secondWord;
    }

    /**
     * Return the command word (the first word) of this command. If the
     * command was not understood, the result is null.
     * @return The command word.
     */
    public String getCommandWord()
    {
        return commandWord;
    }

    /**
     * @return The second word of this command. Returns null if there was no
     * second word.
     */
    public String getSecondWord()
    {
        return secondWord;
    }

    /**
     * @return true if this command was not understood.
     */
    public boolean isUnknown()
    {
        return (commandWord == null);
    }

    /**
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }

    public boolean goDirection(String direction)
    {
        direction.toLowerCase();
        direction.trim();
        boolean correctEntry = true;
        if(direction.equals("west") || direction.equals("north") || direction.equals("east") || direction.equals("south"))
        {
            correctEntry = false;
        }
        return correctEntry;
    }
    
    public boolean attackStat(String stat)
    {
        stat.toLowerCase();
        stat.trim();
        boolean correctEntry = false;
        if(stat.equals("might") || stat.equals("speed") || stat.equals("sanity") || stat.equals("knowledge"))
        {
            correctEntry = true;
        }
        return correctEntry;
    }
    
        public boolean charaSelectCommands(String command)
    {
        boolean selectCommands = false;
        if(command.equals("0") || command.equals("1") || command.equals("2") || command.equals("3") || command.equals("4") || command.equals("5") || command.equals("6") || command.equals("7") || command.equals("8") ||
        command.equals("9") || command.equals("10") || command.equals("11") || command.equals("12"))
        {
            selectCommands = true;
        }
        return selectCommands;
    }
}

