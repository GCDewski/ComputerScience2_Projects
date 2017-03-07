import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * A block that just has a solid color instead of a graphic.
 * 
 * @Gracie Carver-Dews
 * @4/20/15
 */
public class ColorBlock extends Block
{
    private Color blockColor;
    private JLabel topLabel;

    //constructor
    public ColorBlock(Color color)
    {
        super(color);
        blockColor = color;
        //originalColor = color;
    }
 
    //gives Label if block is a solid color
    public JLabel createLabel()
    {        
        topLabel = new JLabel("Color("+blockColor.getRed()+","+blockColor.getGreen()+","+blockColor.getBlue()+")");
        return topLabel;
    }

    //mandatory Block classes (do nothing here)
    public void paintNextBlock(){}
    public void paintLastBlock() {}
    public void paintOriginal(){}
}
