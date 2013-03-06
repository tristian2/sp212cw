/*
 * Submarine class
 * Four submarines
 * Length 1
 * 
 */
package batteships;

/**
 * @author KLM
 */
public class SubmarineImpl extends ShipImpl implements Submarine {
    private static final int ONE = 1; // number of squares occupied

    public SubmarineImpl() {
        this.setLength(ONE);
    }

    /**
     * @return the type of the object - "submarine"
     */
    @Override
    public String getShipType() {
        return "submarine";
    }

    /**
     * @return a single-character String to use in the Ocean's print method
     */
    @Override
    public String toString() {
        return "S";
    }

    @Override
    public boolean shootAt(int row, int column) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
