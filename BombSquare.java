import java.util.*;

public class BombSquare extends GameSquare {
	private boolean thisSquareHasBomb = false;
	public static final int MINE_PROBABILITY = 10;
	public boolean revealed = false;

	public BombSquare(int x, int y, GameBoard board) {
		super(x, y, "images/blank.png", board);

		Random r = new Random();
		thisSquareHasBomb = (r.nextInt(MINE_PROBABILITY) == 0);
	}

	public void clicked() {
		if (thisSquareHasBomb == true) {
			super.setImage("images/bomb.png");
			System.out.println("you lost the game");// game over
		} else {
			int bombCount = CountBombsAround();
			revealed = true;

			switch (bombCount) {
				case 0:
					super.setImage("images/" + bombCount + ".png");
					expandEmptySpace();
					revealed = true;
					break;
				case 1:
					super.setImage("images/" + bombCount + ".png");
					revealed = true;

					break;
				case 2:
					super.setImage("images/" + bombCount + ".png");
					revealed = true;

					break;
				case 3:
					super.setImage("images/" + bombCount + ".png");
					revealed = true;

					break;
				case 4:
					super.setImage("images/" + bombCount + ".png");
					revealed = true;
					break;
				case 5:
					super.setImage("images/" + bombCount + ".png");
					revealed = true;
					break;
				case 6:
					super.setImage("images/" + bombCount + ".png");
					revealed = true;
					break;
				case 7:
					super.setImage("images/" + bombCount + ".png");
					revealed = true;
					break;
				case 8:
					super.setImage("images/" + bombCount + ".png");
					revealed = true;
					break;
				case 9:
					super.setImage("images/" + bombCount + ".png");
					revealed = true;
					break;
				default:
					break;
			}

		}
	}

	private int CountBombsAround() {
		int BombAmount = 0;
		int[] xAroundBomb = { -1, 0, 1, -1, 1, -1, 0, 1 };// the corresponding indexes in both arrays are coordinates
															// relative to where user clicks
		int[] yAroundBomb = { -1, -1, -1, 0, 0, 1, 1, 1 }; // the coord of click is 0,0

		for (int i = 0; i < xAroundBomb.length; i++) {
			int newX = xLocation + xAroundBomb[i];
			int newY = yLocation + yAroundBomb[i];
			BombSquare SquareNextTo = (BombSquare) board.getSquareAt(newX, newY);
			if (SquareNextTo != null && SquareNextTo.thisSquareHasBomb) { // checks if there are any points about 0,0
																			// and returns how many bombs there is
				BombAmount++;
			}
		}

		return BombAmount;
	}

	private void expandEmptySpace() {

		int[] xAroundBomb = { -1, 0, 1, -1, 1, -1, 0, 1 };
		int[] yAroundBomb = { -1, -1, -1, 0, 0, 1, 1, 1 };

		for (int i = 0; i < xAroundBomb.length; i++) {
			int newX = xLocation + xAroundBomb[i];
			int newY = yLocation + yAroundBomb[i];

			if (newX >= 0 && newX < board.getWidth() && newY >= 0 && newY < board.getHeight()) { // checks if coord is
																									// on the board
				BombSquare adjacentSquare = (BombSquare) board.getSquareAt(newX, newY);
				if (adjacentSquare != null && !adjacentSquare.revealed) { // checks if adjacent sq exists and is not revealed
					adjacentSquare.clicked();
				}
			}
		}
	}

}
