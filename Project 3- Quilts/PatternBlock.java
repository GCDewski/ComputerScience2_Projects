import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * The class that holds all the options for a patternBlock type. Pattern Blocks are done recursively
 * 
 * @Gracie Carver-Dews
 * @4/22/15
 */
public class PatternBlock extends Block
{
    protected Color firstColor;
    protected Color secondColor;
    protected int patternChoice;
    private int originalChoice;
    protected boolean color1 = true;
    private String patternName;
    private JLabel topLabel;

    /**
     * Constructor for objects of class PatternBlock
     */
    public PatternBlock(Color color, Color color2, int n)
    {
        super(color);
        firstColor = color;
        secondColor = color2;
        patternChoice = n;
        originalChoice = n;
    }

    public JLabel createLabel()
    {
        topLabel = new JLabel(patternName);
        return topLabel;
    }

    public void paintNextBlock()
    {
        //catches case where it can't go up anymore
        if (patternChoice == 4)
        {
            patternChoice = 0;
        }
        else
        {
            patternChoice++;            
        }
        repaint();
    }

    public void paintLastBlock()
    {
        //catches case where it can't go down anymore
        if (patternChoice == 0)
        {
            patternChoice = 4;
        }
        else
        {
            patternChoice--;            
        }
        repaint();
    } 

    public void paintOriginal()
    {
        patternChoice = originalChoice;
        repaint();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Dimension size = this.getSize();

        if(patternChoice == 0) //draw Arc design (recursion)
        {
            this.setBackground(secondColor);
            int arcAngle = 180;
            patternName = "Fan Design";
            drawArch(g, size.width, size.height, arcAngle);
        }
        else if(patternChoice == 1) //draw Corner Circle design
        {
            this.setBackground(firstColor);
            g.setColor(secondColor);
            patternName = "Corner Circle Design";
            drawOval(g);
        }
        else if (patternChoice == 2) //draw Stripes design
        {
            this.setBackground(firstColor);
            g.setColor(secondColor);
            patternName = "Stripes Design";
            drawStripes(g, size.width);
        }
        else if (patternChoice == 3) //draw Word design
        {
            this.setBackground(secondColor);
            patternName = "Xavier Design";
            drawWord(g);
        }
        else //draw weird cliff thiny design
        {
            this.setBackground(firstColor);
            g.setColor(secondColor);
            patternName = "Cliff Design";
            drawRoundSquare(g, size.width,size.height);
        }
    }

    private void drawArch (Graphics g, int width, int height, int arcAngle)
    {
        int startAngle = 225;
        int x = -5; // x-cord of upper left corner of arc
        int y = -5; // y-cord of upper left corner of arc

        if(arcAngle <= 0) //base
        {
            return;
        }
        else //recursive
        {
            //switch color each time arc drawn
            if(color1 == true)
            {
                g.setColor(secondColor);
                color1 = false;
            }
            else
            {
                g.setColor(firstColor);
                color1= true;
            }

            g.fillArc(x, y , width, height, startAngle, arcAngle);
            drawArch(g, width ,height, (arcAngle-20));
        }      
    }

    private void drawStripes(Graphics g, int width)
    {
        int x = -10; //x-cord of start
        int y = 10; // y-cord of upper left corner of arc
        int stripeWidth = width*2;
        int stripeHeight = 10;

        g.fillRect(x,y,stripeWidth,stripeHeight);

        int stripeCount = 9;
        while(stripeCount >= 0)
        {
            y = y+20;
            g.fillRect(x,y,stripeWidth,stripeHeight);
            stripeCount--;
        }   

    }

    private void drawOval(Graphics g)
    {
        int x = 0;
        int y = 0;
        int ovalWidth = 40;
        int ovalHeight = 40;

        g.fillOval(x,y,ovalWidth,ovalHeight);
    }

    private void drawRoundSquare(Graphics g, int width, int height)
    {
        int x = -10;
        int y = 10;
        int recWidth = width+10;
        int recHeight = height+10;
        int arcWidth = 20;
        int arcHeight = 30;

        g.fillRoundRect(x,y,recWidth,recHeight,arcWidth,arcHeight);

        g.setColor(firstColor);
        g.fillRoundRect(x-20,y-40,recWidth,recHeight,arcWidth,arcHeight);
    }

    private void drawWord(Graphics g)
    {
        int x = 25;
        int y = 45;
        String str = "XAVIER";

        Font patternFont = new Font("Arial", Font.BOLD, 17); 
        g.drawString(str,x,y);
    }
}
