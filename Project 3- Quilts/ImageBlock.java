import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.IOException;

/**
 * The block that is able to display a panel filled with a specific image
 * 
 * @Gracie Carver-Dews
 * @4/25/15
 */
public class ImageBlock extends Block
{
    private OFImage panelImage;
    private int width;
    private int height;
    private int imageChoice;
    private int originalChoice;

    private String imageName;
    private JLabel topLabel;

    private String pathName = "C:/Users/GCDewski/OneDrive for Business/Documents/School/C.S/Spring 2015/Project 3- Quilts/"; //change this to get the image

    /**
     * Constructor for objects of class ImageBlock
     */
    public ImageBlock(Color color, int image)
    {
        super(color);
        imageChoice = image;
        originalChoice = image;
        panelImage = null;
        chooseImage();
    }

    public void paintNextBlock()
    {
        //catches case where it can't go up anymore
        if (imageChoice == 4)
        {
            imageChoice = 0;
        }
        else
        {
            imageChoice++;            
        }
        chooseImage();
        repaint();
    }  

    public void paintLastBlock()
    {
        //catches case where it can't go down anymore
        if (imageChoice == 0)
        {
            imageChoice = 4;
        }
        else
        {
            imageChoice--;            
        }
        chooseImage();
        repaint();
    } 
    
    public void paintOriginal()
    {
        imageChoice = originalChoice;
        chooseImage();
        repaint();
    }

    private void chooseImage()
    {
        BufferedImage img = null;
        OFImage quiltImage = null;
        switch(imageChoice)
        {
            case 0: 
            try
            {
                img = ImageIO.read(new File(pathName+"img0.gif"));
                imageName = "Laughing Mickey";
                quiltImage = new OFImage(img);    
                setImage(quiltImage);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            break;

            case 1: 
            try
            {
                img = ImageIO.read(new File(pathName+"img1.gif"));
                imageName = "Irked Mickey";
                quiltImage = new OFImage(img);  
                setImage(quiltImage);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            break;

            case 2: 
            try
            {
                img = ImageIO.read(new File(pathName+"img2.gif"));
                imageName = "Winking Mickey";
                quiltImage = new OFImage(img);    
                setImage(quiltImage);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            break;

            case 3: 
            try
            {
                img = ImageIO.read(new File(pathName+"img3.gif"));
                imageName = "Smiling Mickey";
                quiltImage = new OFImage(img);
                setImage(quiltImage);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            break;

            case 4: 
            try
            {
                img = ImageIO.read(new File(pathName+"img4.gif"));
                imageName = "Confused Mickey";
                quiltImage = new OFImage(img);   
                setImage(quiltImage);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            break;
        }
    }

    //sets the image for the panel
    private void setImage(OFImage image)
    {
        if(image != null) {
            width = image.getWidth();
            height = image.getHeight();
            panelImage = image;
        }
    }

    public JLabel createLabel()
    {
        topLabel = new JLabel(imageName);
        return topLabel;
    }

    //paints the picture onto the panel
    public void paintComponent(Graphics g)
    {
        Dimension size = this.getSize();
        g.clearRect(0,0,width,height);
        if(panelImage != null) {
            g.drawImage(panelImage, 0, 0, getWidth(), getHeight(), null);
        }
    }
}
