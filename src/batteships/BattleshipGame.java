/*
 * A Battleship Game class
 * sets up the game; accepts "shots" from the user; displays the results;
 * prints final scores; and asks the user if he/she wants to play again.
 */
package batteships;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author MARGARET WRIGHT
 * @author KLM
 */
public class BattleshipGame {

	public static void main(String[] args) {

		Set<String> replies = new HashSet<>();
		replies.add("Yes");
		replies.add("yes");
		replies.add("y");

		int limit;
		String reply = "";
		Scanner input = new Scanner(System.in);

		do {
			// set up the game
			Ocean oc = new Ocean();
            oc.placeAllShipsRandomly();
            System.out.println();
            System.out.println(oc);
            limit = oc.getDimension();

			do {
				// read in the shot
				Position p = getValidInput(input, limit);
				// accept shots & check whether it's a hit (in Ocean)
				if (!oc.shootAt(p.getX(), p.getY())) // a miss
					System.out.println("A miss, try again.");

				System.out.println(oc);
				System.out.println("[. is empty sea; X is a miss; H is a hit; $ is a sunken ship.]");
				System.out.println();
			} while (!oc.isGameOver());
			// print out final scores
			printFinalScores(oc);

			System.out.print("Do you want to play again (Yes or No)? ");
			reply = input.next();
		} while (replies.contains(reply)); // play again?
	}



	/*
	 * Gets valid input from user for row (x) & column (y)
	 * 
	 * @param user input from keyboard
	 * 
	 * @return the valid shot location
	 */
	private static Position getValidInput(Scanner input, int limit) {
		// x is the row; y is the column
		int x, y;
		System.out.println();
		System.out.println("Where do you want to fire?");
		x = askForInput(input, "x/row = ", limit);
		y = askForInput(input, "y/col = ", limit);
		return new Position(x, y);
	}

	/*
	 * Checks for valid user input for x or y
	 * 
	 * @param input from user
	 * 
	 * @return the valid co-ordinate
	 */
	private static int askForInput(Scanner input, String rowCol, int limit) {
		int coOrd;
		do {
			try {
				do {
					System.out.print(rowCol);
					coOrd = input.nextInt();
				} while (coOrd < 0 || coOrd > limit - 1);
				return coOrd;
			} // checks for not an integer
			catch (Exception ex) {
				System.err
						.println("Invalid answer - please enter a number between 0-"
								+ limit + ".");
				input.nextLine();
			} // end of catch
		} while (true);
	}

	/**
	 * Prints final results for hits, ships sunk and shots fired
	 * 
	 * @param oc
	 *            the current ocean
	 */
	private static void printFinalScores(Ocean oc) {
		System.out.println("GAME OVER!! You scored " + oc.getHitCount() + ".");
		System.out.print("You sank " + oc.getShipsSunk() + " ships");
		System.out.println(" and used " + oc.getShotsFired() + " shots" + ".");
	}
}
