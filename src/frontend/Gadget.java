package frontend;

/* This is my first personal project!
 * Started on August 13, 2014.
 * Coded by Jeanne Pascua (Me)
 * Artwork by Emily Pascua
 */

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;

import javax.swing.Timer;
import javax.swing.JFrame;

import frontend.panels.MainPanel;
import backend.GameData;
import backend.GameIdleListener;

public class Gadget extends JFrame implements WindowListener {
	
	private static final long serialVersionUID = 1L;
	
	private GameData data = GameData.getInstance();
	
	private MainPanel mainPanel =  new MainPanel(this);
	
	private GameIdleListener gameIdleListener = new GameIdleListener(mainPanel);
	private Timer timer = this.gameIdleListener.getTimer();

	public Gadget() {
		this.add(mainPanel);
		this.addWindowListener(this);
		this.timer.start();
	}

	public void windowClosing(WindowEvent e) {		
		try {
			this.timer.stop();
			data.writeSaveData();
		}
		catch(FileNotFoundException ex) {
		}	
	}
	
	public void windowOpened(WindowEvent e) {	
		// Do nothing!
	}

	public void windowClosed(WindowEvent e) {
		// Do nothing!
	}

	public void windowIconified(WindowEvent e) {
		// Do nothing!
	}

	public void windowDeiconified(WindowEvent e) {
		// Do nothing!
	}

	public void windowActivated(WindowEvent e) {
		// Do nothing!
	}

	public void windowDeactivated(WindowEvent e) {
		// Do nothing!
	}
	
	public static void main(String[] args) {
		Gadget frame = new Gadget();
		frame.setTitle("Tamagotchi");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
	}

}
