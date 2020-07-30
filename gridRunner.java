/*
 * AP Computer Science A Final Project:
 * Artillery Fight
 * 
 * @author Nicholas Chao
 * @author Jeremy Magana
 * @author Elyas Obbad
 */

import java.util.concurrent.TimeUnit;

/**
 * Class that contains methods related to the Grid.
 */
public class gridRunner
{
    public static ActorWorld world = new ActorWorld();
    public static Player PlayerOne;
    public static Player PlayerTwo;

    /**
     * Starts the grid by drawing the grass tiles onto each grid square
     * then disables the next button and enables the placement of soldiers for player 1.
     */
    public static void startGrid()
    {
        PlayerOne = new Player();
        PlayerTwo = new Player();
        drawGrass();
        ActionClass.start();
        world.setMessage("Player One, place your soldiers. Player Two, look away.");
    }

    /**
     * Goes through the entire grid (10x10) and adds a grass tile
     * to each location coordinate.
     */
    public static void drawGrass()
    {
        for(int x=0;x<10;x++)
            for(int y=0;y<10;y++)
                world.add(new Location(x, y), new GrassTile());
        world.show();
    }

    /**
     * Clears the entire grid by removing anything that could be
     * in all of the location coordinates.
     */
    public static void clearGrid()
    {
        for(int x=0;x<10; x++)
            for(int y=0;y<10;y++)
                world.remove(new Location(x, y));
        world.show();
    }

    /**
     * Creates various types of tiles depending on which value is called
     * when this method is called by the other classes.
     */
    public static void stampTile(Location loc, int value)
    {
        int x = loc.getRow();
        int y = loc.getCol();
        if (value == 0)
        {
            world.add(new Location(x, y), new GrassTile());
        }
        else if (value == 1)
        {
            world.add(new Location(x, y), new SoldierOne());
        }
        else if (value == 2)
        {
            world.add(new Location(x, y), new SoldierTwo());
        }
        else if (value == 3)
        {
            world.add(new Location(x, y), new DeadSoldier());
        }
        else if (value == 4)
        {
            world.add(new Location(x, y), new Explosion());
        }
        else if (value == 5)
        {
            world.add(new Location(x, y), new Actor());
        }
        else
        {
            System.out.println("Tile Index Out of Bounds!");
        }
        world.show();
    }

    /**
     * Prints out stuff from the stencil and adds onto the 10x10 grid.
     */
    public static void stampGrid(int[][] stencil)
    {
        for(int x=0;x<10;x++)
        {
            for(int y=0;y<10;y++)
            {
                if (stencil[x][y] == 0)
                {
                    world.add(new Location(x, y), new GrassTile());
                }
                else if (stencil[x][y] == 1)
                {
                    world.add(new Location(x, y), new SoldierOne());
                }
                else if (stencil[x][y] == 2)
                {
                    world.add(new Location(x, y), new SoldierTwo());
                }
                else if (stencil[x][y] == 3)
                {
                    world.add(new Location(x, y), new DeadSoldier());
                }
                else if (stencil[x][y] == 4)
                {
                    world.add(new Location(x, y), new Explosion());
                }
                else if (stencil[x][y] == 5)
                {
                    world.add(new Location(x, y), new Actor());
                }
                else
                {
                    System.out.println("Tile Index Out of Bounds!");
                }
            }
        }
        world.show();
    }

    /**
     * Prints out stuff from the stencil onto the 10x10 grid but doesn't
     * add any of the soldiers.
     */
    public static void stampGridWithoutSoldiers(int[][] stencil)
    {
        for(int x=0;x<10;x++)
        {
            for(int y=0;y<10;y++)
            {
                if (stencil[x][y] == 0 || stencil[x][y] == 1 || stencil[x][y] == 2)
                {
                    world.add(new Location(x, y), new GrassTile());
                }
                else if (stencil[x][y] == 3)
                {
                    world.add(new Location(x, y), new DeadSoldier());
                }
                else if (stencil[x][y] == 4)
                {
                    world.add(new Location(x, y), new Explosion());
                }
                else if (stencil[x][y] == 5)
                {
                    world.add(new Location(x, y), new Actor());
                }
                else
                {
                    System.out.println("Tile Index Out of Bounds!");
                }
            }
        }
        world.show();
    }

    /**
     * Method that sleeps for however long (seconds) depending on the value that is entered.
     */
    public static void sleep(int s)
    {
        try
        {
            TimeUnit.SECONDS.sleep(s);
        }
        catch(Exception e)
        {
            System.out.println("Sleep error.");
        }
    }

    /**
     * Method that disables any interactions with the game and clears the grid.
     */
    public static void stopClear()
    {
        ActionClass.disableInteract();
        clearGrid();
    }

    /**
     * Method that checks if any of the players won and sets the message at the top of the grid
     * to say Player (1/2) has won!
     */
    public static boolean checkForWin()
    {
        if(PlayerOne.getSoldiersAlive() == 0)
        {
            stopClear();
            drawGrass();
            world.setMessage("Player Two has won!");
            ActionClass.makeQuit();
            return false;
        }
        else if(PlayerTwo.getSoldiersAlive() == 0)
        {
            stopClear();
            drawGrass();
            world.setMessage("Player One has won!");
            ActionClass.makeQuit();
            return false;
        }
        return true;
    }
}