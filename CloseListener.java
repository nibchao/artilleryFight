/*
 * AP Computer Science A Final Project:
 * Artillery Fight
 * 
 * @author Nicholas Chao
 * @author Jeremy Magana
 * @author Elyas Obbad
 */

import java.awt.event.*;
public class CloseListener implements ActionListener
{
    /**
     * Method that makes the GUI window exit
     * when the Quit Button is clicked.
     */
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        System.exit(0);
    }
}
