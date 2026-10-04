package iscteiul.ista.battleship;

/**
 * Represents a Frigate in the Battleship game (corresponding to the 4-cannon ship),
 * with a fixed size of 4 squares.
 * Extends the abstract class {@link Ship}.
 *
 * @author ISCTE-IUL
 * @version 1.0
 */
public class Frigate extends Ship {
    /** Fixed size of the Frigate (4 positions). */
    private static final Integer SIZE = 4;
    
    /** Standard textual designation for the ship type. */
    private static final String NAME = "Fragata";

    /**
     * Constructs a new Frigate based on the provided bearing and initial position.
     * Automatically calculates the 4 consecutive positions occupied on the board
     * according to the direction (North, South, East, or West).
     *
     * @param bearing the orientation of the ship (e.g., {@link Compass#NORTH}, {@link Compass#EAST}, etc.).
     * @param pos the initial reference position {@link IPosition}.
     * @throws IllegalArgumentException if the provided bearing is invalid or null.
     */
    public Frigate(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Frigate.NAME, bearing, pos);
        switch (bearing) {
            case NORTH:
            case SOUTH:
                for (int r = 0; r < SIZE; r++)
                    getPositions().add(new Position(pos.getRow() + r, pos.getColumn()));
                break;
            case EAST:
            case WEST:
                for (int c = 0; c < SIZE; c++)
                    getPositions().add(new Position(pos.getRow(), pos.getColumn() + c));
                break;
            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the frigate");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getSize() {
        return Frigate.SIZE;
    }
}
