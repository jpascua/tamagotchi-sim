package frontend.labels;

import javax.swing.JLabel;

import backend.GameData;

public class MessageLabel extends JLabel {

	private static final long serialVersionUID = 1L;
	
	GameData data = GameData.getInstance();

	public MessageLabel() { }
	
	public void currencyMessage() {
		this.updateMessage("Tamacoin: " + this.data.getTamacoin());
	}
	
	public void updateMessage(String message) {
		this.setText("<html><body>" + message + "</body></html>");
	}
	
}
