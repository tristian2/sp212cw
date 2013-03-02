/*
 * Class Empty Sea
 * describes part of the ocean that doesn't have a ship in it.
 * Length of 1
 *
 */
package batteships;

/**
 * @author KLM
 */
public class EmptySeaImpl extends ShipImpl implements EmptySea {

    private final static int SIZE = 1;

    /**
     * sets the length & clears the hit array
     */
    public EmptySeaImpl() {
        super(SIZE, "EmptySea", ".");
    }
}


