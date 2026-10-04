package iscteiul.ista.battleship;

import java.util.List;

/**
 * Interface representing the fleet of ships in the Battleship game.
 * Defines the basic operations to manage, query, and interact with the ships
 * positioned on the board.
 *
 * @author ISCTE-IUL
 * @version 1.0
 */
public interface IFleet {
    /** Standard size of the game board (10x10). */
    Integer BOARD_SIZE = 10;

    /** Maximum number of ships allowed in the fleet. */
    Integer FLEET_SIZE = 10;

    /**
     * Returns the complete list of ships comprising the fleet.
     *
     * @return a list of {@link IShip} objects representing all the ships.
     */
    List<IShip> getShips();

    /**
     * Adds a new ship to the fleet, validating whether it respects the board boundaries
     * and if there is no risk of collision with other ships.
     *
     * @param s the {@link IShip} to be added.
     * @return {@code true} if the ship was successfully added;
     *         {@code false} otherwise.
     */
    boolean addShip(IShip s);

    /**
     * Returns a sublist of all ships in the fleet belonging to a specific
     * category (e.g., "Fragata", "Galeao").
     *
     * @param category the string identifying the category of the desired ships.
     * @return a list of {@link IShip} objects matching the specified category.
     */
    List<IShip> getShipsLike(String category);

    /**
     * Returns a list of all ships in the fleet that are still floating
     * (i.e., that have not been completely hit or sunk).
     *
     * @return a list of floating {@link IShip} objects.
     */
    List<IShip> getFloatingShips();

    /**
     * Checks which ship is present at a specific position on the board.
     *
     * @param pos the {@link IPosition} to query.
     * @return the {@link IShip} occupying that position, or {@code null} if the position is empty.
     */
    IShip shipAt(IPosition pos);

    /**
     * Displays the current state of the fleet, printing detailed information
     * about the ships, categories, and floating ships.
     */
    void printStatus();
}
