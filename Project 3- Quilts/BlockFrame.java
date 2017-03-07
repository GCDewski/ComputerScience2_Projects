import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * The popup window that appears when Block is clicked. Also has a menu that allows the user to change image with new one 
 * from file.
 * 
 * @Gracie Carver-Dews
 * @4/21/15
 */
public class BlockFrame extends JFrame
{
    private JPanel blockPanel;
    private JLabel topLabel;

    /**
     * Constructor for objects of class BlockFrame- changes label to the number rep of color and creates menu
     */
    public BlockFrame(JPanel main)
    {
        super();
        blockPanel = main;
        topLabel = ((Block)main).createLabel();

        Container contentPane = getContentPane();
        contentPane.add(blockPanel,BorderLayout.CENTER);
        contentPane.add(topLabel,BorderLayout.NORTH);

        createMenuBar(this);
    }

    //sets default size of window
    public Dimension getPreferredSize(){
        return new Dimension(300,300);
    }

    //create menu bar
    private void createMenuBar(JFrame frame)
    {
        final int SHORTCUT_MASK =
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        JMenu menu;
        JMenuItem itemNext;
        JMenuItem itemLast;
        JMenuItem itemOriginal;
        itemOriginal = new JMenuItem("Original Pattern"); //needs to be initialized here so it can be disabled etc.

        // create the File menu
        menu = new JMenu("File");
        menubar.add(menu);

        //Change to the next pattern
        itemNext = new JMenuItem("Next Pattern");
        itemNext.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, SHORTCUT_MASK)); 
        itemNext.addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                { 
                    ((Block)blockPanel).paintNextBlock();
                    topLabel = ((Block)blockPanel).createLabel(); //update label
                    itemOriginal.setEnabled(true);
                    
                    //Can't do this for colorBlock
                    if(blockPanel instanceof ColorBlock)
                    {
                        itemNext.setEnabled(false);
                    }
                }
            });

        menu.add(itemNext);

        //go back to previous pattern
        itemLast = new JMenuItem("Last Pattern");
        itemLast.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, SHORTCUT_MASK)); 
        itemLast.addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                { 
                    ((Block)blockPanel).paintLastBlock();
                    topLabel = ((Block)blockPanel).createLabel(); //update label
                    itemOriginal.setEnabled(true);
                    
                    //Can't do this for colorBlock
                    if(blockPanel instanceof ColorBlock)
                    {
                        itemNext.setEnabled(false);
                    }                    
                }
            });

        menu.add(itemLast);

        //Reverts back to the original pattern it was created with
        itemOriginal.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, SHORTCUT_MASK)); 
        itemOriginal.addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                { 
                    ((Block)blockPanel).paintOriginal();
                    topLabel = ((Block)blockPanel).createLabel(); //update label
                    itemOriginal.setEnabled(false);
                    
                    //Can't do this for colorBlock
                    if(blockPanel instanceof ColorBlock)
                    {
                        itemNext.setEnabled(false);
                    }
                }
            });

        menu.add(itemOriginal);
    }

}
