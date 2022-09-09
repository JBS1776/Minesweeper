import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Options implements Runnable{
	public Options(JFrame frame, boolean question) {
	JFrame options = new JFrame("Settings");
	frame.setEnabled(false);
	options.setEnabled(true);
	JPanel panel = new JPanel();
	JLabel width1 = new JLabel("Rows:");
	JLabel height1 = new JLabel("Cols:");
	JLabel mines1 = new JLabel("Mines:");
	JCheckBox enableQuestion = new JCheckBox("Questionmarks enabled", question);
	JTextField width = new JTextField(2);
	width.requestFocusInWindow();
	width.setText("" + Global.boardLength);
	JTextField height = new JTextField(2);
	height.requestFocusInWindow();
	height.setText("" + Global.boardHeight);
	JTextField mines = new JTextField(2);
	mines.requestFocusInWindow();
	mines.setText("" + Global.mineCount);
	JButton apply = new JButton("Apply");
	apply.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				int w = Integer.parseInt(width.getText());
				int h = Integer.parseInt(height.getText());
				int m = Integer.parseInt(mines.getText());
				if (w < Global.mineMin || h < Global.mineMin || m < Global.mineMin) {
					JOptionPane.showOptionDialog(null, "None of the bounds can be less than " + Global.mineMin + "!", "Invalid Input", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
				}
				else {
				if (w >= Global.lengthMin && w <= Global.lengthLimit && h >= Global.heightMin && h <= Global.heightLimit && m >= Global.mineMin && m <= (w * h) / 2) {
					Global.boardLength = w;
					Global.boardHeight = h;
					Global.mineCount = m;
					options.dispose();
					frame.dispose();
					SwingUtilities.invokeLater(new Game(enableQuestion.isSelected()));
				}
				else {
					JOptionPane.showOptionDialog(null, "Some of the given bounds are not supported.  \n\nSupported values for given dimensions: \nrows: {" + Global.lengthMin + ", " + Global.lengthLimit + "}\ncols: {" + Global.heightMin + ", " + Global.heightLimit + "} \nmines: {" + Global.mineMin + ", " + ((w * h) / 2) + "}", "Invalid input", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
				}
				}
			}	
			catch (NumberFormatException ex) {
			JOptionPane.showOptionDialog(null, "Please use only numbers as input!", "Invalid input!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
		}
		}
	});
	JButton cancel = new JButton("Cancel");
	cancel.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			options.dispose();
			frame.setEnabled(true);
		}					
	});
	panel.add(width1);
	panel.add(width);
	panel.add(height1);
	panel.add(height);
	panel.add(mines1);
	panel.add(mines);
	panel.add(enableQuestion);
	panel.add(apply);
	panel.add(cancel);
	options.getContentPane().add(panel);
	options.pack();
	options.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	options.addWindowListener(new WindowListener() {

		@Override
		public void windowOpened(WindowEvent e) {	
		}

		@Override
		public void windowClosing(WindowEvent e) {
			frame.setEnabled(true);
		}

		@Override
		public void windowClosed(WindowEvent e) {	
		}

		@Override
		public void windowIconified(WindowEvent e) {	
		}

		@Override
		public void windowDeiconified(WindowEvent e) {	
		}

		@Override
		public void windowActivated(WindowEvent e) {	
		}

		@Override
		public void windowDeactivated(WindowEvent e) {	
		}		
	});
	options.setVisible(true);
	}
	public static void main(String[] args) {
	}
	@Override
	public void run() {
		
	}

}
