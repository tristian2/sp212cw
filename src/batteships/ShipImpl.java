/*
 * Class Ship describes characteristics common to all the ships.
 *
 */
package batteships;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author MARGARET WRIGHT
 * @author KLM
 */

public abstract class ShipImpl implements Ship {
    /**
     * the number of squares occupied by the ship. An "empty sea" location has length 1.
     */
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private int length;

    /**
     * the row (0 to 9) which contains the bow (front) of the ship.
     */
    @Getter
    @Setter(AccessLevel.PACKAGE)
    private int bowRow;

    /**
     * the column (0 to 9) which contains the bow (front) of the ship.
     */
    @Getter
    @Setter(AccessLevel.PACKAGE)
    private int bowColumn;


    @Getter
    @Setter(AccessLevel.PACKAGE)
    private boolean horizontal;

    /**
     * An array of boolean which indicates whether that part of the ship has been hit.
     * Only battleships use all four locations;
     * cruisers use the first three;
     * destroyers 2;
     * submarines 1; and
     * "empty sea" either one or none..
     */
    protected boolean[] hit;

    /**
     * clears the hit array indicating whether that part of the "Ship" has been
     * hit
     */
    protected ShipImpl() {
        hit = new boolean[this.getLength()];
        for (int i = 0; i < hit.length; i++)
            hit[i] = false;
    }

    /**
     * Checks that ship of this size will not overlap another ship, or touch
     * another ship (vertically, horizontally, or diagonally) and that ship will
     * not "stick out" beyond the array.
     *
     * @param row        that will contain the bow
     * @param column     that will contain the bow
     * @param horizontal
     * @param ocean
     * @return true if it is okay to put a ship of this size with its bow in
     *         this location, with the given orientation.
     */
    @Override
    public boolean okToPlaceShipAt(int row, int column, boolean horizontal,
                                   Ocean ocean) {
        // TODO add appropriate code and comments

        return true;
    }


    /**
     * "places" the ship in the ocean, assigning values to the bowRow, bowColumn, and
     * horizontal.
     * Places a reference to the ship in the ships array in the Ocean object.
     *
     * @param row        to contain the bow
     * @param column     to contain the bow
     * @param horizontal
     * @param ocean
     */
    @Override
    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {

        this.setBowRow(row);
        this.setBowColumn(column);
        this.setHorizontal(horizontal);

        Ship ships[][] = ocean.getShipArray();

        for (int i = 0; i < this.getLength(); i++) {
            // set position in array to contain the ship
            ships[row][column] = this;
            if (horizontal) {
                column++;
            } else {
                row++;
            }
        }
    }

    /**
     * If this ship has been hit, marks that part of the ship as "hit"
     *
     * @param row    User's supplied row shot
     * @param column User's supplied column shot
     * @return true if ship is hit, false otherwise
     */
    @Override
    public boolean shootAt(int row, int column) {
        if ((isHorizontal() && (row != getBowRow()))
                || (!isHorizontal() && (column != getBowColumn())))
            return false; // it's not a hit

        // it's a hit. Work out offset & set that position in hit array to true
        hit[(row - getBowRow() + column - getBowColumn())] = true;

        return true;
    }

    /**
     * checks whether this ship is sunk - using the hit array
     *
     * @return true if every part of the ship has been hit, false otherwise.
     */
    @Override
    public boolean isSunk() {

        for (boolean b : hit)
            if (!b)
                return false;

        return true;
    }

    /**
     * @return a single character String to use in Ocean's print method
     */
    @Override
    public String toString() {
        return this.getClass().getName();
    }
}
