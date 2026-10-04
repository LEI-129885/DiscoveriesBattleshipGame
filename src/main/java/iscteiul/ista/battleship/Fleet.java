package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the {@link IFleet} interface that manages the set of ships
 * belonging to a player in the Battleship game.
 * Allows adding ships, checking for collisions, querying states, and filtering fleets.
 *
 * @author ISCTE-IUL
 * @version 1.0
 */
public class Fleet implements IFleet {
    
    /**
     * Prints to standard output a textual representation of a given list of ships.
     *
     * @param ships the list of {@link IShip} objects to print.
     */
    static void printShips(List<IShip> ships) {
        for (IShip ship : ships)
            System.out.println(ship);
    }

    // -----------------------------------------------------

    /** Internal collection of ships belonging to the fleet. */
    private List<IShip> ships;

    /**
     * Default constructor that initializes a new empty fleet.
     */
    public Fleet() {
        ships = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IShip> getShips() {
        return ships;
    }

    /**
     * {@inheritDoc}
     * Adds the ship if the fleet size limit is not exceeded, 
     * if it is fully inside the board, and if there is no collision risk.
     */
    @Override
    public boolean addShip(IShip s) {
        boolean result = false;
        if ((ships.size() <= FLEET_SIZE) && (isInsideBoard(s)) && (!colisionRisk(s))) {
            ships.add(s);
            result = true;
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IShip> getShipsLike(String category) {
        List<IShip> shipsLike = new ArrayList<>();
        for (IShip s : ships)
            if (s.getCategory().equals(category))
                shipsLike.add(s);

        return shipsLike;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IShip> getFloatingShips() {
        List<IShip> floatingShips = new ArrayList<>();
        for (IShip s : ships)
            if (s.stillFloating())
                floatingShips.add(s);

        return floatingShips;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IShip shipAt(IPosition pos) {
        for (int i = 0; i < ships.size(); i++)
            if (ships.get(i).occupies(pos))
                return ships.get(i);
        return null;
    }

    /**
     * Checks whether all positions occupied by the ship are within 
     * the valid boundaries of the board.
     *
     * @param s the {@link IShip} to validate.
     * @return {@code true} if it is fully inside the board; {@code false} otherwise.
     */
    private boolean isInsideBoard(IShip s) {
        return (s.getLeftMostPos() >= 0 && s.getRightMostPos() <= BOARD_SIZE - 1 && s.getTopMostPos() >= 0
                && s.getBottomMostPos() <= BOARD_SIZE - 1);
    }

    /**
     * Checks if the introduced ship is too close to any other 
     * existing ship in the fleet (collision/contact risk).
     *
     * @param s the {@link IShip} to test.
     * @return {@code true} if there is a collision risk; {@code false} otherwise.
     */
    private boolean colisionRisk(IShip s) {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).tooCloseTo(s))
                return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * Shows the overall state of the fleet, including all ships, 
     * those still floating, and the segmented listing by category 
     * (Galeao, Fragata, Nau, Caravela, and Barca).
     */
    public void printStatus() {
        printAllShips();
        printFloatingShips();
        printShipsByCategory("Galeao");
        printShipsByCategory("Fragata");
        printShipsByCategory("Nau");
        printShipsByCategory("Caravela");
        printShipsByCategory("Barca");
    }

    /**
     * Prints all ships in the fleet belonging to a specific category.
     *
     * @param category the name of the ship category of interest.
     */
    public void printShipsByCategory(String category) {
        assert category != null;

        printShips(getShipsLike(category));
    }

    /**
     * Prints all ships in the fleet that have not yet been sunk (still floating).
     */
    public void printFloatingShips() {
        printShips(getFloatingShips());
    }

    /**
     * Prints all ships registered in the fleet, regardless of their state.
     */
    void printAllShips() {
        printShips(ships);
    }
}
