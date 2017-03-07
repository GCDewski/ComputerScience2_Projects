import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class contains the methods that each pattern block should have when created. All Blocks initially have a 
 * default color that can be replaced with a specified picture by the user in the classes extending this one.
 * 
 * @Gracie Carver-Dews
 * @4/20/15
 */
public abstract class Block extends JPanel implements MouseListener
{
    private Color blockColor;
    private JFrame blockFrame;

    //Constructor-adds mouse listener so user can look at specific patterns and sets background as default color.
    public Block(Color color)
    {
        blockColor = color;
        addMouseListener(this);
        setBackground(blockColor);
    }
    
    //called for replacing color
    public Color getColor()
    {
        return blockColor;
    }
    
    //All extensions must have label
    public abstract JLabel createLabel();
    //change the graphic to the next option
    public abstract void paintNextBlock();
    //change the graphic to the previous option
    public abstract void paintLastBlock();
    //go back to what it was originally
    public abstract void paintOriginal();        
    
    //mouse events
    public void mouseClicked(MouseEvent e)
    {
        blockFrame = new BlockFrame(this);
        blockFrame.pack();
        blockFrame.setVisible(true);
    }

    public void mouseEntered(MouseEvent e){}

    public void mouseExited(MouseEvent e){}

    public void mousePressed(MouseEvent e){}

    public void mouseReleased(MouseEvent e){}
}

