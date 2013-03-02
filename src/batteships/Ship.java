package batteships;

public interface Ship {
    boolean okToPlaceShipAt(int row, int column, boolean horizontal,
                            Ocean ocean);

    void placeShipAt(int row, int column, boolean horizontal, Ocean ocean);

    boolean shootAt(int row, int column);

    boolean isSunk();

    @Override
    String toString();

    int getLength();

    int getBowRow();

    int getBowColumn();

    boolean isHorizontal();

    String getShipType();
}
