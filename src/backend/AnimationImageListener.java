package backend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import frontend.panels.MainPanel;

public class AnimationImageListener implements ActionListener {
	
	int seconds = 5;
	Timer timer = new Timer(seconds, this);
	MainPanel m;
	
	public AnimationImageListener(MainPanel m) {
		this.m = m;
	}

	public void actionPerformed(ActionEvent e) {
		m.getPetImagePanel().setPetImage("images/eating.gif");
	}
	
	public Timer getTimer() {
		return this.timer;
	}
	
}
