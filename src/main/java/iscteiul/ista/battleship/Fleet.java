package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementação da interface {@link IFleet} que gere o conjunto de navios
 * pertencentes a um jogador no jogo Batalha Naval.
 * Permite adicionar navios, verificar colisões, consultar estados e filtrar frotas.
 *
 * @author ISCTE-IUL
 * @version 1.0
 */

public class Fleet implements IFleet {

    /**
     * Imprime no standard output uma representação textual de uma lista de navios fornecida.
     *
     * @param ships a lista de navios {@link IShip} a imprimir.
     */
    static void printShips(List<IShip> ships) {
        for (IShip ship : ships)
            System.out.println(ship);
    }

    // -----------------------------------------------------

    /** Coleção interna de navios pertencentes à frota. */
    private List<IShip> ships;

    /**
     * Construtor padrão que inicializa uma nova frota vazia.
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
     * Adiciona o navio caso o limite da frota não seja excedido,
     * se estiver totalmente dentro do tabuleiro e se não houver risco de colisão.
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
     * Verifica se todas as posições ocupadas pelo navio se encontram dentro
     * dos limites válidos do tabuleiro.
     *
     * @param s o navio {@link IShip} a validar.
     * @return {@code true} se estiver totalmente dentro do tabuleiro; {@code false} caso contrário.
     */
    private boolean isInsideBoard(IShip s) {
        return (s.getLeftMostPos() >= 0 && s.getRightMostPos() <= BOARD_SIZE - 1 && s.getTopMostPos() >= 0
                && s.getBottomMostPos() <= BOARD_SIZE - 1);
    }

    /**
     * Verifica se o navio introduzido está demasiado próximo de algum outro
     * navio já existente na frota (risco de colisão/contacto).
     *
     * @param s o navio {@link IShip} a testar.
     * @return {@code true} se houver risco de colisão; {@code false} caso contrário.
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
     * Mostra o estado global da frota, incluindo todos os navios,
     * os que continuam a flutuar e a listagem segmentada por categorias
     * (Galeão, Fragata, Nau, Caravela e Barca).
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
     * Imprime todos os navios da frota que pertencem a uma categoria específica.
     *
     * @param category o nome da categoria de navios de interesse.
     */
    public void printShipsByCategory(String category) {
        assert category != null;

        printShips(getShipsLike(category));
    }

    /**
     * Imprime todos os navios da frota que ainda não foram afundados (continuam a flutuar).
     */
    public void printFloatingShips() {
        printShips(getFloatingShips());
    }

    /**
     * Imprime todos os navios registados na frota, independentemente do estado.
     */
    void printAllShips() {
        printShips(ships);
    }
}
