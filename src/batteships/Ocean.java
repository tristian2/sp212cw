package batteships;

public interface Ocean {
    int getDimension();

    void placeAllShipsRandomly();

    @Override
    String toString();

    boolean isOccupied(int row, int column);

    boolean shootAt(int row, int column);

    boolean isGameOver();

    Ship[][] getShipArray();

    String printFinalScores();

    int getShotsFired();

    void setShotsFired(int shotsFired);

    int getHitCount();

    int getShipsSunk();
}
