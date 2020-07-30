/*
 * AP Computer Science A Final Project:
 * Artillery Fight
 * 
 * @author Nicholas Chao
 * @author Jeremy Magana
 * @author Elyas Obbad
 */

import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

import java.net.*;

import javax.imageio.ImageIO;
import java.io.*;

/**
 * Class that contains a main and
 * complementary methods to create the GUI for Artillery Fight.
 */
public class backgroundTest
{
    public static JButton b1 = new JButton(); // Creates the Play Button
    public static JButton b2 = new JButton(); // Creates the Quit button
    public static JFrame frame = new JFrame("Artillery Fight"); // Creates the GUI Frame named Artillery Fight

    public static void main(String[] args) throws IOException 
    {
        frame.setContentPane(new JPanel()
            {
                BufferedImage image = ImageIO.read(new URL("http://i.xomf.com/wlbll.png")); // Creates a BufferedImage object that reads a jpg/png URL to use for a background
                /**
                 * Method that takes the image from the URL above and uses it as a background for the JPanel.
                 */
                public void paintComponent(Graphics g) 
                {
                    super.paintComponent(g);
                    g.drawImage(image, 0, 0, 640, 480, this);
                }
            }
        ); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Closes the JFrame when you exit the window

        b1.setMinimumSize(new Dimension(205, 110)); b1.setMaximumSize(new Dimension(205, 110)); // Sets sizes of Play Button

        b2.setMinimumSize(new Dimension(205, 110)); b2.setMaximumSize(new Dimension(205, 110)); // Sets sizes of Quit Button

        b1.setContentAreaFilled(false); b2.setContentAreaFilled(false); // Makes Play and Quit Buttons Transparent
        b1.setBorderPainted(false); b2.setBorderPainted(false); // Makes Play and Quit Buttons borderless

        ClickListener listenerb1 = new ClickListener(); b1.addActionListener(listenerb1); // Makes the Play Button a ClickListener
        ClickListener listenerb2 = new ClickListener(); b2.addActionListener(new CloseListener()); // Makes the Quit Button a CloseListener

        createGUI();

        frame.pack(); frame.setSize(640, 480); frame.setResizable(false); frame.setVisible(true); // Makes GUI window not resizable, set to a certain resolution, and makes it visible.
    }

    /**
     * Creates a container that then has the Play Button
     * and Quit Button added to it.
     */
    public static void addComponents(Container pane)
    {
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        addB("", pane); addB("", pane);
    }

    /**
     * Makes the Play Button and Quit Button aligned in the center of the GUI window,
     * adds the buttons to the container, and creates blank pixel gaps between the two buttons.
     */
    public static void addB(String text, Container container)
    {  
        b1.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(Box.createRigidArea(new Dimension(0, 65)));
        container.add(b1);

        container.add(Box.createRigidArea(new Dimension(0, 50)));
        b2.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(b2);
    }

    /**
     * Runs the addComponents method which creates the game GUI.
     */
    public static void createGUI()
    {
        addComponents(frame.getContentPane());
    }
    
    /**
     * Makes the GUI visible.
     */
    public static void makeVisible()
    {
        frame.setVisible(true);
    }
    
    /**
     * Makes the GUI invisible.
     */
    public static void makeInvisible()
    {
        frame.setVisible(false);
    }
}