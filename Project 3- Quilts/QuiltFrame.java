import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.util.HashMap;
import java.awt.Graphics;
/**
 * This is the window where the user sees thier created quilt.
 * 
 * @author Gracie Carver-Dews
 * @version 4/18/15
 */
public class QuiltFrame extends JFrame 
{
    private JPanel mainPanel;
    private JLabel topLabel;
    private int rows;
    private int columns;
    private Random rng;
    private final static int SQUARE_SIZE=100;

    private HashMap<Integer, Color> colors = new HashMap();
    private HashMap<Integer, JPanel> blocks = new HashMap(); 

    /**
     * Constructor for objects of class QuiltFrame
     */
    public QuiltFrame(int rows, int columns)
    {
        super();
        this.rows = rows;
        this.columns = columns;
        rng=new Random();
        mainPanel = new JPanel();

        Container contentPane = getContentPane();
        contentPane.add(mainPanel,BorderLayout.CENTER);
        topLabel = new JLabel("Empty Quilt");
        contentPane.add(topLabel,BorderLayout.NORTH);

        mainPanel.setLayout(new GridLayout(rows,columns,6,6));
        createMenuBar(this); //create menu bar
    }

    //set preferred size of frame
    public Dimension getPreferredSize()
    {
        return new Dimension(rows*SQUARE_SIZE,columns*SQUARE_SIZE);
    }

    //method that builds the default 5 color blocks randomly
    private void blockColors()
    {
        int n = 0;

        while (n <= 4)
        {
            Color squareColor=new Color(rng.nextInt(256),rng.nextInt(256),rng.nextInt(256));
            colors.put(n, squareColor);
            n++;
        }
    }

    //method that builds the 5 pattern blocks for the hashmap
    private void patternBuild()
    {
        int n =0;

    }

    //build the quilt made out of just colors.
    private void colorQuilt()
    {
        mainPanel.removeAll();
        blockColors(); //fill Hashmap w/ default colors

        topLabel.setText("Color Only Quilt");

        int firstBlock = rng.nextInt(5); //randomly chooses first Block
        JPanel panel=new ColorBlock(colors.get(firstBlock));

        mainPanel.add(panel);

        int nextBlock = firstBlock+1;
        int boxCount = 1;

        //create the pattern and draw it on the grid
        for(int i=0; i < (rows*columns)-1; i++)
        {
            boxCount++;

            //new row
            if (boxCount > columns)
            {
                boxCount = 1;
                nextBlock = firstBlock+1;
                firstBlock = nextBlock;

                // the nextBlock can't be 6 so reset the firstBlock back to 0
                if(firstBlock == 5)
                {
                    firstBlock = 0;
                }
            }

            //if out of patterns, reset to the pattern given.
            if(nextBlock >=5)
            {
                nextBlock = 0;
            }

            JPanel nextPanel=new ColorBlock(colors.get(nextBlock));
            mainPanel.add(nextPanel);
            nextBlock++;
        }
    } 

    private void patternQuilt()    
    {
        mainPanel.removeAll();
        topLabel.setText("Pattern Only Quilt");

        //sets random color scheme and pattern for Block 
        Color firstColor = new Color(rng.nextInt(256),rng.nextInt(256),rng.nextInt(256));
        Color secondColor = new Color(rng.nextInt(256),rng.nextInt(256),rng.nextInt(256));
        int firstBlock = rng.nextInt(5);

        JPanel panel=new PatternBlock(firstColor, secondColor, firstBlock);
        mainPanel.add(panel);

        int nextBlock = firstBlock+1;
        int boxCount = 1;

        //create the pattern and draw it on the grid
        for(int i=0; i < (rows*columns)-1; i++)
        {
            boxCount++;

            //new row
            if (boxCount > columns)
            {
                boxCount = 1;
                nextBlock = firstBlock+1;
                firstBlock = nextBlock;

                // the nextBlock can't be 6 so reset the firstBlock back to 0
                if(firstBlock == 5)
                {
                    firstBlock = 0;
                }
            }

            //if out of patterns, reset to the pattern given.
            if(nextBlock >=5)
            {
                nextBlock = 0;
            }

            JPanel nextPanel=new PatternBlock(firstColor, secondColor, nextBlock);
            mainPanel.add(nextPanel);
            nextBlock++;
        }   
    }

    private void imgQuilt()    
    {
        mainPanel.removeAll();
        topLabel.setText("Image Only Quilt");

        int firstBlock = rng.nextInt(5);

        JPanel panel=new ImageBlock(Color.WHITE, firstBlock);
        mainPanel.add(panel);

        int nextBlock = firstBlock+1;
        int boxCount = 1;

        //create the pattern and draw it on the grid
        for(int i=0; i < (rows*columns)-1; i++)
        {
            boxCount++;

            //new row
            if (boxCount > columns)
            {
                boxCount = 1;
                nextBlock = firstBlock+1;
                firstBlock = nextBlock;

                // the nextBlock can't be 6 so reset the firstBlock back to 0
                if(firstBlock == 5)
                {
                    firstBlock = 0;
                }
            }

            //if out of patterns, reset to the pattern given.
            if(nextBlock >=5)
            {
                nextBlock = 0;
            }

            JPanel nextPanel=new ImageBlock(Color.WHITE, nextBlock);
            mainPanel.add(nextPanel);
            nextBlock++;
        }   
    }

    private void allQuilt()
    {
        mainPanel.removeAll();
        topLabel.setText("Combo Quilt");
        JPanel panel;
        JPanel nextPanel;

        //sets random color schemes for pattern/solid colors 
        Color firstColor = new Color(rng.nextInt(256),rng.nextInt(256),rng.nextInt(256));
        Color secondColor = new Color(rng.nextInt(256),rng.nextInt(256),rng.nextInt(256));
        blockColors();

        //fill Hashmap with random panels from all block classes
        for(int n=0; n < 5; n++)
        {
            int blockChoice = rng.nextInt(3); //decides which block is made           
            switch(blockChoice)
            {              
                case 0: //solid color
                panel = new ColorBlock(Color.WHITE);
                blocks.put(n, panel);
                break;

                case 1: //pattern
                panel = new PatternBlock(Color.WHITE, Color.WHITE, 0);
                blocks.put(n, panel);
                break;

                case 2: //img               
                panel = new ImageBlock(Color.WHITE, 0);
                blocks.put(n, panel);
                break;                
            }
        }

        int firstBlock = rng.nextInt(5);
        //create the block based on Hashmap storage
        if(blocks.get(firstBlock) instanceof ColorBlock)
        {     
            panel=new ColorBlock(colors.get(firstBlock));
        }
        else if (blocks.get(firstBlock) instanceof PatternBlock)
        {
            panel = new PatternBlock(firstColor, secondColor, firstBlock);
        }
        else
        {  
            panel = new ImageBlock(Color.WHITE, firstBlock);
        }
        mainPanel.add(panel);

        int nextBlock = firstBlock+1;
        int boxCount = 1;

        //create the pattern and draw it on the grid
        for(int i=0; i < (rows*columns)-1; i++)
        {
            boxCount++;

            //new row
            if (boxCount > columns)
            {
                boxCount = 1;
                nextBlock = firstBlock+1;
                firstBlock = nextBlock;

                // the nextBlock can't be 6 so reset the firstBlock back to 0
                if(firstBlock == 5)
                {
                    firstBlock = 0;
                }
            }

            //if out of patterns, reset to the pattern given.
            if(nextBlock >=5)
            {
                nextBlock = 0;
            }

            if(blocks.get(nextBlock) instanceof ColorBlock)
            {     
                nextPanel=new ColorBlock(colors.get(nextBlock));
            }
            else if (blocks.get(nextBlock) instanceof PatternBlock)
            {
                nextPanel = new PatternBlock(firstColor, secondColor, nextBlock);
            }
            else //imageBlock
            {  
                nextPanel = new ImageBlock(Color.WHITE, nextBlock);
            }
            
            mainPanel.add(nextPanel);
            nextBlock++;
        } 
    }
    
    private void repaintPanel(JPanel changedPanel)
    {
        mainPanel.add(changedPanel);   
    }

    //create menu bar to select the type of quilt made
    private void createMenuBar(JFrame frame)
    {
        final int SHORTCUT_MASK =
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        JMenu menu;
        JMenuItem itemColorBuild;
        JMenuItem itemPatBuild;
        JMenuItem itemImgBuild;
        JMenuItem itemComboBuild;

        // create the File menu
        menu = new JMenu("Quilt Type");
        menubar.add(menu);

        //create items
        itemColorBuild = new JMenuItem("Color Quilt");
        itemPatBuild = new JMenuItem("Pattern Quilt");
        itemImgBuild = new JMenuItem("Image Quilt");
        itemComboBuild = new JMenuItem("Combo Quilt");

        //Build a color only quilt
        itemColorBuild.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, SHORTCUT_MASK)); 
        itemColorBuild.addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                { 
                    colorQuilt(); 
                    itemComboBuild.setEnabled(true);
                    itemColorBuild.setEnabled(false);
                    itemPatBuild.setEnabled(true);
                    itemImgBuild.setEnabled(true);
                }
            });

        menu.add(itemColorBuild);

        //Build a Pattern only quilt

        itemPatBuild.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, SHORTCUT_MASK));
        itemPatBuild.addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                { 
                    patternQuilt();  
                    itemComboBuild.setEnabled(true);
                    itemColorBuild.setEnabled(true);
                    itemPatBuild.setEnabled(false);
                    itemImgBuild.setEnabled(true);
                }
            });
        menu.add(itemPatBuild);

        //Build Img only quilt
        itemImgBuild.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, SHORTCUT_MASK));
        itemImgBuild.addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                { 
                    imgQuilt();
                    itemComboBuild.setEnabled(true);
                    itemImgBuild.setEnabled(false);
                    itemColorBuild.setEnabled(true);
                    itemPatBuild.setEnabled(true);
                }
            });
        menu.add(itemImgBuild);

        //Build combo quilt (has potentail for all Block types)
        itemComboBuild.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
        itemComboBuild.addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                { 
                    allQuilt();
                    itemComboBuild.setEnabled(false);
                    itemImgBuild.setEnabled(true);
                    itemColorBuild.setEnabled(true);
                    itemPatBuild.setEnabled(true);
                }
            });
        menu.add(itemComboBuild);
    }
    
}
