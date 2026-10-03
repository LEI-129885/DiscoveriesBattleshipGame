/**
 * Represents a ship in the Battleship game.
 * Defines the core behaviors and properties of a ship, including its position,
 * size, orientation, and status (whether it is still floating or sunk).
 */
package iscteiul.ista.battleship;

import java.util.List;

public interface IShip {

    /**
     * Gets the category or type of the ship (e.g., "Carrier", "Battleship", "Submarine").
     * 
     * @return a String representing the category of the ship.
     */
    String getCategory();

    /**
     * Gets the size of the ship, which corresponds to the number of grid positions it occupies.
     * 
     * @return the integer size of the ship.
     */
    Integer getSize();

    /**
     * Retrieves all the grid positions occupied by this ship.
     * 
     * @return a List of {@link IPosition} objects representing the exact coordinates the ship covers.
     */
    List<IPosition> getPositions();

    /**
     * Retrieves the primary or starting position of the ship (typically the bow or stern).
     * 
     * @return the base {@link IPosition} of the ship.
     */
    IPosition getPosition();

    /**
     * Gets the orientation or direction the ship is facing on the grid.
     * 
     * @return the {@link Compass} bearing of the ship (e.g., NORTH, EAST).
     */
    Compass getBearing();

    /**
     * Checks if the ship is still floating (i.e., has not been hit on all its positions).
     * 
     * @return true if the ship is still floating, false if it is completely sunk.
     */
    boolean stillFloating();

    /**
     * Gets the top-most row (minimum Y coordinate) occupied by the ship.
     * 
     * @return the top-most coordinate value.
     */
    int getTopMostPos();

    /**
     * Gets the bottom-most row (maximum Y coordinate) occupied by the ship.
     * 
     * @return the bottom-most coordinate value.
     */
    int getBottomMostPos();

    /**
     * Gets the left-most column (minimum X coordinate) occupied by the ship.
     * 
     * @return the left-most coordinate value.
     */
    int getLeftMostPos();
    
    /**
     * Gets the right-most column (maximum X coordinate) occupied by the ship.
     * 
     * @return the right-most coordinate value.
     */
    int getRightMostPos();

    /**
     * Determines if the ship occupies the specified position on the grid.
     * 
     * @param pos the {@link IPosition} to check.
     * @return true if the ship is located at the given position, false otherwise.
     */
    boolean occupies(IPosition pos);

    /**
     * Checks if this ship is placed too close to another ship, based on game rules 
     * (e.g., ships cannot touch each other or overlap).
     * 
     * @param other the other {@link IShip} to check distance against.
     * @return true if the ships are too close or overlapping, false otherwise.
     */
    boolean tooCloseTo(IShip other);

    /**
     * Checks if this ship is placed too close to a specific grid position.
     * 
     * @param pos the {@link IPosition} to check distance against.
     * @return true if the position is too close to this ship, false otherwise.
     */
    boolean tooCloseTo(IPosition pos);

    /**
     * Registers a shot at the specified position on the ship. 
     * If the position matches one of the ship's coordinates, it registers as a hit.
     * 
     * @param pos the {@link IPosition} where the shot was fired.
     */
    void shoot(IPosition pos);
}
