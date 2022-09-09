import java.awt.Color;
import java.net.URL;

import javax.swing.ImageIcon;

public class Global {
	
	public static int boardLength = 10;
	
	public static int boardHeight = 10;

	public static int mineCount = 10;
	
	public static final int lengthMin = 2;
	
	public static final int heightMin = 2;
	
	public static final int mineMin = 2;
	
	public static final int lengthLimit = 50;
	
	public static final int heightLimit = 50;
	
	public static final String path = System.getProperty("user.dir");
	
	public static final URL bomburl = Game.class.getResource("/bomb.png");
	
	public static final URL flagurl = Game.class.getResource("/flag.png");
	
	public static final URL boomurl = Game.class.getResource("/boom.png");
	
	public static ImageIcon bombpng = new ImageIcon(bomburl);
	
	public static ImageIcon flagpng = new ImageIcon(flagurl);
	
	public static ImageIcon boompng = new ImageIcon(boomurl);
	
	public static final Color[] colors = {Color.BLACK, Color.BLUE, Color.GREEN.darker(), Color.RED, Color.MAGENTA, Color.blue.darker(), Color.CYAN, Color.DARK_GRAY, Color.ORANGE};
	
	public static void main(String[] args) {

	}

}
