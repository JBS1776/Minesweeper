import javax.swing.JButton;

public abstract class Tile extends JButton{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int name;
	private boolean isclicked;
	private int x;
	private int y;
	public Tile(int name, int x, int y) {
		this.name = name;
		this.isclicked = false;
		this.x = x;
		this.y = y;
	}
	public Tile(int name) {
		this.name = name;
	}

	public boolean isClicked() {
		return this.isclicked;
	}
	public void setClicked(boolean val) {
		this.isclicked = val;
	}
	public int getname() {
		return this.name;
	}
	public String nameToString() {
		return "" + this.name;
	}
	public void setname(int n) {
		this.name = n;
	}
	public int getposx() {
		return this.x;
	}
	public int getposy() {
		return this.y;
	}
	public void setposx(int val) {
		this.x = val;
	}
	public void setposy(int val) {
		this.y = val;
	}
	public static void main(String[] args) {

	}

}
