/**
 * Provides an abstract base implementation of the {@link IShip} interface for the Battleship game.
 * This class handles common state and logic for all ships, such as tracking positions,
 * determining boundaries, checking for overlap/adjacency, and handling hits.
 * Specific ship types (like Galleon, Frigate, etc.) should extend this class.
 */
package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Ship implements IShip {

    private static final String GALEAO = "galeao";
    private static final String FRAGATA = "fragata";
    private static final String NAU = "nau";
    private static final String CARAVELA = "caravela";
    private static final String BARCA = "barca";

    /**
     * Factory method that constructs and returns a specific type of ship based on the provided string identifier.
     * @param shipKind
     * @param bearing
     * @param pos
     * @return
     */
    static Ship buildShip(String shipKind, Compass bearing, Position pos) {
        Ship s;
        switch (shipKind) {
            case BARCA:
                s = new Barge(bearing, pos);
                break;
            case CARAVELA:
                s = new Caravel(bearing, pos);
                break;
            case NAU:
                s = new Carrack(bearing, pos);
                break;
            case FRAGATA:
                s = new Frigate(bearing, pos);
                break;
            case GALEAO:
                s = new Galleon(bearing, pos);
                break;
            default:
                s = null;
        }
        return s;
    }


    private String category;
    private Compass bearing;
    private IPosition pos;
    protected List<IPosition> positions;


    /**
     * Constructs a new Ship with the specified category, bearing, and starting position.
     * Initializes the internal list of positions.
     * @param category
     * @param bearing
     * @param pos
     */
    public Ship(String category, Compass bearing, IPosition pos) {
        assert bearing != null;
        assert pos != null;

        this.category = category;
        this.bearing = bearing;
        this.pos = pos;
        positions = new ArrayList<>();
    }

    /**
     * Retrieves the category of the ship.
     * 
     * @return a String representing the ship's category.
     */
    @Override
    public String getCategory() {
        return category;
    }

     /**
     * Gets the list of grid positions that this ship occupies.
     * 
     * @return a List of {@link IPosition} objects making up the ship.
     */
    public List<IPosition> getPositions() {
        return positions;
    }

    /**
     * Retrieves the starting position of the ship.
     * 
     * @return the base {@link IPosition} of the ship.
     */
    @Override
    public IPosition getPosition() {
        return pos;
    }
    

    /**
     * Retrieves the orientation of the ship.
     * 
     * @return the {@link Compass} bearing of the ship.
     */
    @Override
    public Compass getBearing() {
        return bearing;
    }
    

    /**
     * Checks if the ship is still floating by verifying if there is at least 
     * one position on the ship that has not been hit.
     * 
     * @return true if the ship has surviving parts, false if it is completely sunk.
     */
    @Override
    public boolean stillFloating() {
        for (int i = 0; i < getSize(); i++)
            if (!getPositions().get(i).isHit())
                return true;
        return false;
    }
    
    /**
     * Calculates the top-most row (minimum row index) occupied by the ship.
     * 
     * @return the minimum row index among all the ship's positions.
     */
    @Override
    public int getTopMostPos() {
        int top = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() < top)
                top = getPositions().get(i).getRow();
        return top;
    }

    /**
     * Calculates the bottom-most row (maximum row index) occupied by the ship.
     * 
     * @return the maximum row index among all the ship's positions.
     */
    @Override
    public int getBottomMostPos() {
        int bottom = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() > bottom)
                bottom = getPositions().get(i).getRow();
        return bottom;
    }


    /**
     * Calculates the left-most column (minimum column index) occupied by the ship.
     * 
     * @return the minimum column index among all the ship's positions.
     */
    @Override
    public int getLeftMostPos() {
        int left = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() < left)
                left = getPositions().get(i).getColumn();
        return left;
    }

     /**
     * Calculates the right-most column (maximum column index) occupied by the ship.
     * 
     * @return the maximum column index among all the ship's positions.
     */
    @Override
    public int getRightMostPos() {
        int right = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() > right)
                right = getPositions().get(i).getColumn();
        return right;
    }

    /**
     * Checks if the ship occupies the specified position.
     * 
     * @param pos the {@link IPosition} to verify. Must not be null.
     * @return true if the position is part of this ship, false otherwise.
     */
    @Override
    public boolean occupies(IPosition pos) {
        assert pos != null;

        for (int i = 0; i < getSize(); i++)
            if (getPositions().get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * Checks if this ship is placed too close to another ship, 
     * meaning any part of this ship is adjacent to or overlapping with the other ship.
     * 
     * @param other the other {@link IShip} to check against. Must not be null.
     * @return true if the ships are too close, false otherwise.
     */
    @Override
    public boolean tooCloseTo(IShip other) {
        assert other != null;

        Iterator<IPosition> otherPos = other.getPositions().iterator();
        while (otherPos.hasNext())
            if (tooCloseTo(otherPos.next()))
                return true;

        return false;
    }

    /**
     * Checks if any part of this ship is too close (adjacent or overlapping) to a specific grid position.
     * 
     * @param pos the {@link IPosition} to check distance against.
     * @return true if the position is adjacent to any of the ship's positions, false otherwise.
     */
    @Override
    public boolean tooCloseTo(IPosition pos) {
        for (int i = 0; i < this.getSize(); i++)
            if (getPositions().get(i).isAdjacentTo(pos))
                return true;
        return false;
    }


    /**
     * Processes a shot fired at the given position. 
     * If the specified position matches one of the ship's positions, it marks that position as hit.
     * 
     * @param pos the {@link IPosition} being shot at. Must not be null.
     */
    @Override
    public void shoot(IPosition pos) {
        assert pos != null;

        for (IPosition position : getPositions()) {
            if (position.equals(pos))
                position.shoot();
        }
    }


    /**
     * Returns a string representation of the ship, including its category, bearing, and starting position.
     * 
     * @return a formatted string describing the ship's basic attributes.
     */
    @Override
    public String toString() {
        return "[" + category + " " + bearing + " " + pos + "]";
    }

}
