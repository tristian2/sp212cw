/*
 * Class Ocean contains array of Ships, representing the "ocean"
 * Ocean keeps track of shots fired, hit count and number of ships sunk.
 * Ocean provides checks for whether a shot hits a ship, sinks it or misses.
 */
package batteships;

/**
 * @author MARGARET WRIGHT
 * @author KLM
 */

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

public class OceanImpl implements Ocean {

    private static final int UPPER;  // upper bound of the (square) board

    private final ShipImpl[][] board;
    @Getter
    @Setter
    private int shotsFired;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private int hitCount;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private int shipsSunk;

    static {  // just to show how to use static initialization blocks
        UPPER = 10;
    }

    /**
     * Creates an "empty" ocean (fills the board array with EmptySeas). Also
     * initialises game variables, shotsFired, hitCount & shipsSunk
     */
    public OceanImpl() {
        board = new ShipImpl[UPPER][UPPER];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new EmptySeaImpl();
            }
        }
        setShotsFired(0);
        setHitCount(0);
        setShipsSunk(0);
    }

    /**
     * @return the size of the row/column
     */
    @Override
    public int getDimension() {
        return board.length;
    }

    /**
     * Places all 10 board randomly on the (initially empty) ocean.
     */
    @Override
    public void placeAllShipsRandomly() {
        // TODO
        // this does not have a "random" fleet - you should have one
        Ship[] fleet = new Ship[UPPER];
        fleet[0] = new BattleShipImpl();
        fleet[1] = new CruiserImpl();
        fleet[2] = new CruiserImpl();
        fleet[3] = new DestroyerImpl();
        fleet[4] = new DestroyerImpl();
        fleet[5] = new DestroyerImpl();
        fleet[6] = new SubmarineImpl();
        fleet[7] = new SubmarineImpl();
        fleet[8] = new SubmarineImpl();
        fleet[9] = new SubmarineImpl();

        Random m = new Random();
        // x is row position; y is column position
        int x, y;
        // boolean to represent horizontal
        boolean b;
        for (Ship s : fleet) {
            do {
                // generate random x, y, horizontal
                x = m.nextInt(UPPER); // x/row is random 0-9
                y = m.nextInt(UPPER); // y/col is random 0-9
                b = m.nextInt(2) == 1; // horizontal : 1 is true; 0 is false
            } while (!s.okToPlaceShipAt(x, y, b, this));

            // it is safe to place the ship
            s.placeShipAt(x, y, b, this);
        }
    }

    /**
     * Prints the ocean.
     */
    @Override
    public String toString() { // this replaces the "print" method of the spec
        final String SPACES = " ";
        // have to use a mutable String based data structure for efficiency
        StringBuilder buffer = new StringBuilder();
        buffer.append(" ");
        for (int i = 0; i < board[0].length; i++) {
            buffer.append(SPACES);
            buffer.append(i);
        }
        buffer.append("\n");

        for (int i = 0; i < board.length; i++) {
            buffer.append(i);
            for (int j = 0; j < board[0].length; j++) {
                buffer.append(SPACES);
                buffer.append(board[i][j]);
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }

    /**
     * Checks whether the location contains anything other than empty sea.
     *
     * @param row    the x position on the board
     * @param column the y position on the board
     * @return true if the given location contains a ship, false if it does not.
     */
    @Override
    public boolean isOccupied(int row, int column) {
        return !(board[row][column] instanceof EmptySeaImpl);
    }

    /**
     * Checks whether the location contains a ship, still afloat. Marks that
     * ship as hit and checks whether whole ship is sunk. Updates the number of
     * shots that have been fired, and number of hits.
     *
     * @param row    the x position
     * @param column the y position
     * @return true if location contains a "real" ship, still afloat, false if not.
     */
    @Override
    public boolean shootAt(int row, int column) {
        // increment the number of shots fired regardless of result
        // use of accessor so that internal representation can change without effecting usage
        setShotsFired(getShotsFired() + 1);

        // check for a ship
        if (isOccupied(row, column)) {  // okay - this is a ship
            // get the ship
            board[row][column].shootAt(row, column);
            setHitCount(getHitCount() + 1);
            return true;
        }
        return false;
    }


    /**
     * Returns true if all the ships on the board have been sunk, otherwise false.
     *
     * @return the "fleet" has been sunk
     */

    @Override
    public boolean isGameOver() {
        // check whether all board in fleet have been sunk
        return getShipsSunk() == UPPER;
    }

    /**
     * Gets the board so that the Ocean
     * parameter can look at the contents of this array;
     * the placeShipAt method can modify it this board (not a good idea).
     *
     * @return the array of board
     */
    @Override
    public Ship[][] getShipArray() {
        return board;
    }

    /**
     * A string containing the final results for hits, ships sunk and shots fired
     */
    @Override
    public String printFinalScores() {
        StringBuilder strbld = new StringBuilder();
        strbld.append("GAME OVER!! You scored ").append(this.getHitCount()).append(".");
        strbld.append("You sank ").append(this.getShipsSunk()).append(" ships");
        strbld.append(" and used ").append(this.getShotsFired()).append(" shots" + ".");
        return strbld.toString();
    }
}
