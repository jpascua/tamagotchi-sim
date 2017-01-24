package frontend.panels;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import backend.GameData;

public class StatusMessagePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private GameData data = GameData.getInstance();
	
	private JLabel stamina = new JLabel("Stamina: " + data.calculateStaminaPercentage() + "%");
	private JLabel mood = new JLabel("Mood: " + data.getMood());
	private JLabel condition = new JLabel("Condition: " + data.getCondition());
	//private JLabel tamacoin = new JLabel("Tamacoin(s):" + "100" + "t");
	
	public StatusMessagePanel() {
		this.setLayout(new BorderLayout());
		
		this.removeAll();
		
		this.add(this.stamina, BorderLayout.NORTH);
		this.add(this.mood, BorderLayout.CENTER);
		//this.add(this.tamacoin, BorderLayout.EAST);
		this.add(this.condition, BorderLayout.SOUTH);
	}
	
	public void refreshStamina() {
		this.stamina.setText("Stamina: " + data.calculateStaminaPercentage() + "%");		
	}
	
	public void refreshMood() {
		this.mood.setText("Mood: " + data.calculateMood());
	}
	
	public void refreshCondition() {
		this.condition.setText("Condition: " + data.getCondition());
	}
	
	public void refreshAndCalculateCondition() {
		this.condition.setText("Condition: " + data.calculateCondition());
	}
	
}
