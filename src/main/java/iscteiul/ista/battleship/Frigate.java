/**
 * Representa uma Fragata na Batalha Naval (correspondente ao navio de 4 canhões),
 * com uma dimensão fixa de 4 quadrados[cite: 2, 5].
 * Estende a classe abstrata {@link Ship}.
 *
 * @author ISCTE-IUL
 * @version 1.0
 */
package iscteiul.ista.battleship;

public class Frigate extends Ship {
    /** Dimensão fixa da Fragata (4 posições). */
    private static final Integer SIZE = 4;

    /** Designação textual padrão do tipo de navio. */
    private static final String NAME = "Fragata";

    /**
     * Constrói uma nova Fragata com base na orientação (bearing) e na posição inicial fornecidas.
     * Calcula automaticamente as 4 posições consecutivas ocupadas no tabuleiro
     * consoante o sentido (Norte, Sul, Este ou Oeste).
     *
     * @param bearing a orientação do navio (ex: {@link Compass#NORTH}, {@link Compass#EAST}, etc.).
     * @param pos a posição inicial de referência {@link IPosition}.
     * @throws IllegalArgumentException se a orientação fornecida for inválida ou nula.
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
