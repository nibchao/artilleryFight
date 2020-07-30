public class ActionClass
{
    //These two strings represent the actions that the methods should take when the user(s) interact.
    private static String clickAction = "";
    private static String buttonAction = "";
    
    /**
     * Takes action based on the variable clickAction.
     */
    public static boolean locationClicked(Location loc)
    {
        if(clickAction == "p1Placement") //Placing down Player One's soldiers
        {
            if(gridRunner.PlayerOne.getSoldiersAlive() < 10)
            {
                if(gridRunner.PlayerOne.setTileAt(loc,1))
                {
                    gridRunner.stampTile(loc, 1);
                    gridRunner.PlayerOne.incSoldiers();
                    gridRunner.world.setMessage("Player One, place down " + (10-gridRunner.PlayerOne.getSoldiersAlive()) + " more soldiers.");
                    if(gridRunner.PlayerOne.getSoldiersAlive() ==  10)
                    {
                        buttonAction = "p1ClosePlacement";
                        clickAction = "disabled";
                        gridRunner.world.setMessage("Press Next to end your turn.");
                    }
                }
            }
        }
        else if(clickAction == "p2Placement") //Placing down Player Two's soldiers
        {
            if(gridRunner.PlayerTwo.getSoldiersAlive() < 10)
            {
                if(gridRunner.PlayerTwo.setTileAt(loc,2))
                {
                    gridRunner.stampTile(loc,2);
                    gridRunner.PlayerTwo.incSoldiers();
                    gridRunner.world.setMessage("Player Two, place down " + (10-gridRunner.PlayerTwo.getSoldiersAlive()) + " more soldiers.");
                    if(gridRunner.PlayerTwo.getSoldiersAlive() ==  10)
                    {
                        buttonAction = "p2ClosePlacement";
                        clickAction = "disabled";
                        gridRunner.world.setMessage("Press Next to end your turn.");
                    }
                }
            }
        }
        else if(clickAction == "p1Firing")//Player One Firing Player Two
        {
            if(gridRunner.PlayerOne.getShots() > 0)
            {
                if(gridRunner.PlayerTwo.getTileAt(loc) == 2)
                {
                    gridRunner.stampTile(loc,3);
                    gridRunner.PlayerTwo.setTileAt(loc,3);
                    gridRunner.PlayerTwo.decSoldiers();
                    gridRunner.PlayerOne.decShots();
                    gridRunner.world.setMessage("Player One, you have " + gridRunner.PlayerOne.getShots() + " shots left.");
                }
                else if(gridRunner.PlayerTwo.getTileAt(loc) == 3 || gridRunner.PlayerTwo.getTileAt(loc) == 4)
                {
                    //Do nothing
                }
                else
                {
                    gridRunner.stampTile(loc,4);
                    gridRunner.PlayerTwo.setTileAt(loc,4);
                    gridRunner.PlayerOne.decShots();
                    gridRunner.world.setMessage("Player One, you have " + gridRunner.PlayerOne.getShots() + " shots left.");
                }
                if(gridRunner.PlayerOne.getShots() == 0)
                {
                    gridRunner.world.setMessage("Press Next to end your turn.");
                    buttonAction = "p1CloseFiring";
                }
                gridRunner.checkForWin();
            }
        }
        else if(clickAction == "p2Firing")//Player Two Firing Player One
        {
            if(gridRunner.PlayerTwo.getShots() > 0)
            {
                if(gridRunner.PlayerOne.getTileAt(loc) == 1)
                {
                    gridRunner.stampTile(loc,3);
                    gridRunner.PlayerOne.setTileAt(loc,3);
                    gridRunner.PlayerOne.decSoldiers();
                    gridRunner.PlayerTwo.decShots();
                    gridRunner.world.setMessage("Player Two, you have " + gridRunner.PlayerTwo.getShots() + " shots left.");
                }
                else if(gridRunner.PlayerOne.getTileAt(loc) == 3 || gridRunner.PlayerOne.getTileAt(loc) == 4)
                {
                    //Do nothing
                }
                else
                {
                    gridRunner.stampTile(loc,4);
                    gridRunner.PlayerOne.setTileAt(loc,4);
                    gridRunner.PlayerTwo.decShots();
                    gridRunner.world.setMessage("Player Two, you have " + gridRunner.PlayerTwo.getShots() + " shots left.");
                }
                if(gridRunner.PlayerTwo.getShots() == 0)
                {
                    gridRunner.world.setMessage("Press Next to end your turn.");
                    buttonAction = "p2CloseFiring";
                }
                gridRunner.checkForWin();
            }
        }
        else if(clickAction == "disabled")
        {
            //Disabled
        }
        else
        {
            System.out.println("Click Action Index out of Bounds!");
        }
        return true;
    }
    
    /**
     * Takes action based on the variable buttonAction.
     */
    public static boolean nextButton()
    {
        if(buttonAction == "p1ClosePlacement")
        {
            gridRunner.stopClear();
            gridRunner.sleep(1);
            //
            gridRunner.world.setMessage("Player Two, place down 10 more soldiers.");
            gridRunner.drawGrass();
            clickAction = "p2Placement";
        }
        else if(buttonAction == "p2ClosePlacement")
        {
            //COPY AND PASTE P2 CLOSE FIRING
            gridRunner.stopClear();
            gridRunner.sleep(1);
            gridRunner.stampGrid(gridRunner.PlayerOne.getSoldierGrid());
            buttonAction = "p1CloseOverview";
            if(gridRunner.checkForWin())
                gridRunner.world.setMessage("This is your battlefield, Player One.");
        }
        else if(buttonAction == "p1CloseOverview")
        {
            gridRunner.stopClear();
            gridRunner.stampGridWithoutSoldiers(gridRunner.PlayerTwo.getSoldierGrid());
            gridRunner.PlayerOne.resetShots();
            gridRunner.world.setMessage("Player One, you have " + gridRunner.PlayerOne.getShots() + " shots left.");
            clickAction = "p1Firing";
        }
        else if(buttonAction == "p1CloseFiring")
        {
            gridRunner.stopClear();
            gridRunner.sleep(1);
            gridRunner.stampGrid(gridRunner.PlayerTwo.getSoldierGrid());
            buttonAction = "p2CloseOverview";
            if(gridRunner.checkForWin())
                gridRunner.world.setMessage("This is your battlefield, Player Two.");
        }
        else if(buttonAction == "p2CloseOverview")
        {
            gridRunner.stopClear();
            gridRunner.stampGridWithoutSoldiers(gridRunner.PlayerOne.getSoldierGrid());
            gridRunner.PlayerTwo.resetShots();
            gridRunner.world.setMessage("Player Two, you have " + gridRunner.PlayerTwo.getShots() + " shots left.");
            clickAction = "p2Firing";
        }
        else if(buttonAction == "p2CloseFiring")
        {
            gridRunner.stopClear();
            gridRunner.sleep(1);
            gridRunner.stampGrid(gridRunner.PlayerOne.getSoldierGrid());
            buttonAction = "p1CloseOverview";
            if(gridRunner.checkForWin())
                gridRunner.world.setMessage("This is your battlefield, Player One.");
        }
        else if(buttonAction == "disabled")
        {
            //Disabled
        }
        else if(buttonAction == "quit")
        {
            backgroundTest.makeVisible();
            World.makeInvisible();
        }
        else
        {
            System.out.println("Button Action string not recognized!");
        }
        return true;
    }
    
    /**
     * Used to start the init. interactions.
     */
    public static void start()
    {
        buttonAction = "disabled";
        clickAction = "p1Placement";
    }
    
    /**
     * Disables interaction by the user(s).
     */
    public static void disableInteract()
    {
        buttonAction = "disabled";
        clickAction = "disabled";
    }
    
    /**
     * Disables interaction by the user(s).
     */
    public static void makeQuit()
    {
        buttonAction = "quit";
    }
}
