/**
 * Represents a concrete implementation of a grid position in the Battleship game.
 * This class stores the coordinates (row and column) of a specific cell on the game board
 * and tracks its current state, including whether it is occupied by a ship and 
 * whether it has been shot at.
 */
package iscteiul.ista.battleship;

import java.util.Objects;

public class Position implements IPosition {
    private int row;
    private int column;
    private boolean isOccupied;
    private boolean isHit;

    /**
     * Constructs a new Position at the specified row and column.
     * By default, a new position is neither occupied nor hit.
     * 
     * @param row    the integer representing the row index.
     * @param column the integer representing the column index.
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        this.isOccupied = false;
        this.isHit = false;
    }

    /**
     * Retrieves the row index of this position.
     * 
     * @return the integer representing the row.
     */
    @Override
    public int getRow() {
        return row;
    }

     /**
     * Retrieves the column index of this position.
     * 
     * @return the integer representing the column.
     */
    @Override
    public int getColumn() {
        return column;
    }


    /**
     * Generates a hash code for this position based on its coordinates and state.
     * 
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(column, isHit, isOccupied, row);
    }

     /**
     * Compares this position to the specified object. The result is true if and only if 
     * the argument is not null and is an IPosition object that has the same row and column values.
     * 
     * @param otherPosition the object to compare this position against.
     * @return true if the given object represents an equivalent position, false otherwise.
     */
    @Override
    public boolean equals(Object otherPosition) {
        if (this == otherPosition)
            return true;
        if (otherPosition instanceof IPosition) {
            IPosition other = (IPosition) otherPosition;
            return (this.getRow() == other.getRow() && this.getColumn() == other.getColumn());
        } else {
            return false;
        }
    }

    /**
     * Checks if this position is immediately adjacent to another specified position 
     * on the grid (horizontally, vertically, or diagonally).
     * 
     * @param other the {@link IPosition} to check for adjacency.
     * @return true if the given position is adjacent to this one, false otherwise.
     */
    @Override
    public boolean isAdjacentTo(IPosition other) {
        return (Math.abs(this.getRow() - other.getRow()) <= 1 && Math.abs(this.getColumn() - other.getColumn()) <= 1);
    }

    /**
     * Marks this position as being occupied by a ship.
     */
    @Override
    public void occupy() {
        isOccupied = true;
    }

     /**
     * Registers a shot at this position, changing its state to indicate it has been hit.
     */
    @Override
    public void shoot() {
        isHit = true;
    }

     /**
     * Checks whether this position is currently occupied by a ship.
     * 
     * @return true if a ship is occupying this position, false otherwise.
     */
    @Override
    public boolean isOccupied() {
        return isOccupied;
    }

     /**
     * Checks whether this position has been targeted and shot at during the game.
     * 
     * @return true if this position has received a shot, false otherwise.
     */
    @Override
    public boolean isHit() {
        return isHit;
    }

    /**
     * Returns a string representation of the position, detailing its row and column.
     * 
     * @return a formatted string describing the position's coordinates.
     */
    @Override
    public String toString() {
        return ("Linha = " + row + " Coluna = " + column);
    }

}
