package backend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import frontend.panels.MainPanel;

public class GameIdleListener implements ActionListener {

	private GameData data = GameData.getInstance();
	
	private int seconds = 60;
	private Timer timer = new Timer(seconds*1000, this);
	private MainPanel m;
	
	public GameIdleListener(MainPanel m) {
		this.m = m;
	}
	
	public void actionPerformed(ActionEvent e) {	
		this.data.subtractCurrentStamina(0.5);
		
		m.getStatusMessagePanel().refreshStamina();
		m.getStatusMessagePanel().refreshMood();
		m.getStatusMessagePanel().refreshAndCalculateCondition();
		m.getPetImagePanel().refreshPetImage();
	}
	
	public Timer getTimer() {
		return this.timer;
	}

}
