import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * QuiltController is the main class of the Quilt project. It builds the initial frame
 * for the application. 
 * 
 * @Gracie Carver-Dews 
 * @4/18/15
 */
public class QuiltController
{
    private JFrame frame;

    /**
     * Constructor for objects of class QuiltController
     * Creates the display of the quilt (QuiltFrame) based on user input.
     */
    public QuiltController(int rows, int columns)
    {
        frame = new QuiltFrame(rows,columns);
        frame.pack();
        frame.setVisible(true);
    }

}
