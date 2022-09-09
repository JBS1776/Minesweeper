import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class Game implements Runnable{
	private Board board;
	private boolean gameEnded;
	private int tilesSelected;
	private int framewidth;
	private int frameheight;
	public Game(boolean questionEnabled) {
		board = new Board(Global.boardLength, Global.boardHeight);
		board.setMines();
		board.setNums();
		gameEnded = false;
		tilesSelected = Global.mineCount;
		framewidth = Global.boardLength * 50;
		frameheight = Global.boardHeight * 50;
		long initTime = System.currentTimeMillis();
		JFrame frame = new JFrame("Minesweeper");
		JPanel panel = new JPanel();
		JMenuBar menu = new JMenuBar();
		JMenu settings = new JMenu("Settings");
		settings.addMenuListener(new MenuListener() {
			@Override
			public void menuSelected(MenuEvent e) {
				SwingUtilities.invokeLater(new Options(frame, questionEnabled));
			}
			@Override
			public void menuDeselected(MenuEvent e) {
				
			}
			@Override
			public void menuCanceled(MenuEvent e) {
				
			}			
		});
		menu.add(settings);
		JMenu resetButton = new JMenu("Reset");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(framewidth, frameheight));
		GridLayout gl = new GridLayout(Global.boardHeight, Global.boardLength);
		panel.setLayout(gl);
		for (ArrayList<Tile> tiles : board) {
			for (Tile tile : tiles) {
				// Below line fixes a glitch where Font size changes slightly when "?" is selected as Tile's text
				tile.setFont(new Font(tile.getFont().getFontName(), tile.getFont().getStyle(), tile.getFont().getSize()));
				if (tile.getname() == 0) {
					for (Tile t : board.neighbors(tile)) {
						t.setText("" + t.getname());
						if (!t.isClicked()) {
							t.setClicked(true);
							tilesSelected += 1;
							t.setForeground(Global.colors[t.getname()]);
						}
					}
				}
				tile.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (!gameEnded) {
							// e.getModifiers() returns binary int representation of list of actions being performed
							// left click is 1000 (16 in binary) and e.CTRL_MASK is 10 (2 in binary)
							// e.getModifiers sums 1000 with e.CTRL_MASK when both are hit simultaneously giving 1010 (18)
							// 1010 & 0010 = 0010
						if ((e.getModifiers() & ActionEvent.CTRL_MASK) == ActionEvent.CTRL_MASK && !tile.isClicked()) {
							Image image = Global.flagpng.getImage().getScaledInstance(framewidth /(2 * Global.boardLength), frameheight / (2 * Global.boardHeight), Image.SCALE_SMOOTH);
							Global.flagpng.setImage(image);
							if (tile.getIcon() == null && tile.getText().isEmpty()) {
								tile.setIcon(Global.flagpng);
							}
							else {
								tile.setText(questionEnabled && tile.getIcon() != null ? "?" : "");
								tile.setIcon(null);
							}
						}
						else {
							if (tile.getIcon() == null) {
							tile.setClicked(true);
							tile.setIcon(null);
							if (tile instanceof Number) {
							tilesSelected += 1;
							tile.setText("" + tile.getname());
							tile.setForeground(Global.colors[tile.getname()]);
							}
							if (tile instanceof Mine || tilesSelected == Global.boardLength * Global.boardHeight) {
								gameEnded = true;
								long newTime = System.currentTimeMillis();
								long secs = (newTime - initTime) / 1000;
								long mins = secs / 60;
								secs %= 60;
								String minstring = (mins < 10) ? "0" + mins : "" + mins;
								String secstring = (secs < 10) ? ":0" + secs : ":" + secs;
								if (tile instanceof Mine) {
									for (Tile m : board.getMines()) {
										m.setText("");
										m.setIcon(Global.bombpng);
									} 
									// Below SwingWorker provides a background process while mines "explode" as doing a simple delay didn't work
									// Returns Void object as we need to represent a "void" type but doInBackground() needs to return something
									new SwingWorker<Void, Void>() {
										@Override
										protected Void doInBackground() throws Exception {
											Thread.sleep(500);
											return null;
										}
										@Override
										protected void done() {
											for (Tile m : board.getMines()) {
												m.setIcon(Global.boompng);
											}
										}
										
									}.execute();
								}
								JOptionPane.showOptionDialog(null, (tile instanceof Mine) ? "You lose!\nProgress: " +  tilesSelected + "/" + (Global.boardLength * Global.boardHeight) + "\nTime: " + minstring + secstring : "You win!" + "\nTime: " + minstring + secstring, (tile instanceof Mine) ? "Electrode used Explosion!" : "Minefield cleared!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, e);
							}
						}
						}
					}
					}
					
				});
				tile.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (SwingUtilities.isRightMouseButton(e) && !tile.isClicked()) {
							Image image = Global.flagpng.getImage().getScaledInstance(framewidth /(2 * Global.boardLength), frameheight / (2 * Global.boardHeight), Image.SCALE_SMOOTH);
							Global.flagpng.setImage(image);
							if (tile.getIcon() == null && tile.getText().isEmpty()) {
								tile.setIcon(Global.flagpng);
							}
							else {
								tile.setText(questionEnabled && tile.getIcon() != null ? "?" : "");
								tile.setIcon(null);
							}
						}
					}
				});
				panel.add(tile);
			}
		}
		frame.add(panel);
		resetButton.addMenuListener(new MenuListener() {
			@Override
			public void menuSelected(MenuEvent e) {
				frame.dispose();
				SwingUtilities.invokeLater(new Game(questionEnabled));
			}
			@Override
			public void menuDeselected(MenuEvent e) {
				
			}
			@Override
			public void menuCanceled(MenuEvent e) {
				
			}			
		});
		menu.add(resetButton);
		frame.setJMenuBar(menu);
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game(false));
	}
	@Override
	public void run() {
		
	}
}
