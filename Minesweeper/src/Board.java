import java.util.ArrayList;
import java.util.Random;
public class Board extends ArrayList<ArrayList<Tile>> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Random random = new Random();
	private Tile[] mines;
	public Board(int length, int height) {
		for (int i = 0; i < height; i++) {
			this.add(new ArrayList<Tile>());
			for (int j = 0; j < length; j++) {
					this.get(i).add(new Number(0, j, i));
			}
		}
	}
	public void setMines() {
		int mineLengthIndex = random.nextInt(Global.boardLength);
		int mineHeightIndex = random.nextInt(Global.boardHeight);
		this.mines = new Mine[Global.mineCount];
		for (int i = 0; i < Global.mineCount; i++) {
			while (this.get(mineHeightIndex).get(mineLengthIndex) instanceof Mine) {
				mineLengthIndex = random.nextInt(Global.boardLength);
				mineHeightIndex = random.nextInt(Global.boardHeight);
			}
			this.get(mineHeightIndex).set(mineLengthIndex, new Mine(-1, mineLengthIndex, mineHeightIndex));
			this.mines[i] = this.get(mineHeightIndex).get(mineLengthIndex);
		}
	}
	public Tile[] getMines() {
		return this.mines;
	}
	public void setNums() {
		for (ArrayList<Tile> tiles : this) {
			for (Tile tile : tiles) {
				if (tile instanceof Mine) {
					int x = tile.getposx();
					int y = tile.getposy();
					for (int i = y - 1; i <= y + 1; i++) {
						if (i >= 0 && i < Global.boardHeight) {
						for (int j = x - 1; j <= x + 1; j++) {
							if (j >= 0 && j < Global.boardLength) {
							Tile t = this.get(i).get(j);
							if (!(t instanceof Mine)) 
							t.setname(t.getname() + 1);
							}
						}
						}
					}
				}
			}
		}
	}
	public ArrayList<Tile> neighbors(Tile t) {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		int x = t.getposx();
		int y = t.getposy();
		for (int i = y - 1; i <= y + 1; i++) {
			if (i >= 0 && i < Global.boardHeight) {
				for (int j = x - 1; j <= x + 1; j++) {
					if ((j >= 0 && j < Global.boardLength)) {
						tiles.add(this.get(i).get(j));
					}
				}
			}
		}
		return tiles;
	}
	public static void main(String[] args) {
		
		}

}
