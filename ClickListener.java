/*
 * AP Computer Science A Final Project:
 * Artillery Fight
 * 
 * @author Nicholas Chao
 * @author Jeremy Magana
 * @author Elyas Obbad
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickListener extends backgroundTest implements ActionListener
{
    /**
     * Method where the runner class is called to create the Grid and sets the GUI
     * window to become invisible.
     */
	public void actionPerformed(ActionEvent event)
	{
	    backgroundTest.makeInvisible();
	    World.makeVisible();
	}
}
