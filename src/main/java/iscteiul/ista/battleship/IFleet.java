/**
 * Interface que representa a frota de navios no jogo Batalha Naval.
 * Define as operações básicas para gerir, consultar e interagir com os navios
 * posicionados no tabuleiro.
 *
 * @author ISCTE-IUL
 * @version 1.0
 */
package iscteiul.ista.battleship;

import java.util.List;

public interface IFleet {
    /** Tamanho padrão do tabuleiro do jogo (10x10). */
    Integer BOARD_SIZE = 10;

    /** Número máximo de navios permitidos na frota. */
    Integer FLEET_SIZE = 10;

    /**
     * Retorna a lista completa de navios que compõem a frota.
     *
     * @return uma lista de objetos {@link IShip} representando todos os navios.
     */
    List<IShip> getShips();

    /**
     * Adiciona um novo navio à frota, validando se respeita os limites do tabuleiro
     * e se não existe risco de colisão com outros navios.
     *
     * @in the ship to be added
     * @param s o navio {@link IShip} a adicionar.
     * @return {@code true} se o navio foi adicionado com sucesso;
     *         {@code false} caso contrário.
     */
    boolean addShip(IShip s);

    /**
     * Retorna uma sublista com todos os navios da frota que pertencem
     * a uma determinada categoria (ex: "Fragata", "Galeao").
     *
     * @param category a string que identifica a categoria dos navios pretendidos.
     * @return uma lista de navios {@link IShip} que correspondem à categoria especificada.
     */
    List<IShip> getShipsLike(String category);

    /**
     * Retorna uma lista com todos os navios da frota que ainda se encontram
     * a flutuar (ou seja, que não foram totalmente atingidos/afundados).
     *
     * @return uma lista de navios {@link IShip} flutuantes.
     */
    List<IShip> getFloatingShips();

    /**
     * Verifica qual o navio presente numa determinada posição do tabuleiro.
     *
     * @param pos a posição {@link IPosition} a consultar.
     * @return o navio {@link IShip} que ocupa essa posição, ou {@code null} se a posição estiver vazia.
     */
    IShip shipAt(IPosition pos);

    /**
     * Apresenta o estado atual da frota, imprimindo informações detalhadas
     * sobre os navios, categorias e navios a flutuar.
     */
    void printStatus();
}
