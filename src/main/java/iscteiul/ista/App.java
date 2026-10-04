package iscteiul.ista;

import iscteiul.ista.battleship.Fleet;
import iscteiul.ista.battleship.Tasks;

/**
 * Entry point of the Discoveries Battleship Game.
 * <p>
 * Prints the game banner and starts one of the tasks defined in
 * {@link Tasks}. Currently, {@link Tasks#taskB()} is executed; the other
 * tasks can be enabled by uncommenting the corresponding line.
 *
 * @author britoeabreu
 * @author adrianolopes
 * @author miguelgoulao
 */
public class App
{
    /**
     * Starts the game by running the currently selected task.
     *
     * @param args command-line arguments (not used)
     */
    public static void main( String[] args )
    {

        System.out.printf("\n***  Battleship Game ***\n");

        // Tasks.taskA();
        Tasks.taskB();
        //	Tasks.taskC();
        //	Tasks.taskD();
    }
}
