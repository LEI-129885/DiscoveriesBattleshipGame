/**
 * Represents a specific coordinate or cell on the Battleship game grid.
 * Tracks the state of the position, such as whether it is occupied by a ship
 * and whether it has been shot at by the opponent.
 */
package iscteiul.ista.battleship;

/**
 * @author fba
 */
public interface IPosition {

    /**
     * Gets the row index of this position on the grid.
     * 
     * @return the integer representing the row.
     */
    int getRow();

    /**
     * Gets the column index of this position on the grid.
     * 
     * @return the integer representing the column.
     */
    int getColumn();

    /**
     * Compares this position to the specified object. The result is true if and only if 
     * the argument is not null and is an IPosition object that has the same row and column values.
     * 
     * @param other the object to compare this position against.
     * @return true if the given object represents an IPosition equivalent to this position, false otherwise.
     */
    boolean equals(Object other);

    /**
     * Checks if this position is immediately adjacent to another specified position 
     * on the grid (typically horizontally, vertically, or diagonally).
     * 
     * @param other the {@link IPosition} to check for adjacency.
     * @return true if the given position is adjacent to this one, false otherwise.
     */
    boolean isAdjacentTo(IPosition other);

    /**
     * Marks this position as occupied by a ship.
     */
    void occupy();

    /**
     * Registers a shot at this position, updating its state to indicate it has been hit.
     */
    void shoot();

    /**
     * Checks whether this position is currently occupied by a ship.
     * 
     * @return true if a ship is occupying this position, false otherwise.
     */
    boolean isOccupied();

    /**
     * Checks whether this position has been targeted and shot at during the game.
     * 
     * @return true if this position has received a shot, false otherwise.
     */
    boolean isHit();
}
