package frontend.panels;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import backend.GameData;

public class PetImagePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private GameData data = GameData.getInstance();
	
	private JLabel icon;

	public PetImagePanel() {
		
		this.icon = new JLabel(new ImageIcon(data.getImage()));
		
		this.removeAll();
		
		this.add(icon);
	}
	
	public void refreshPetImage() {
		this.icon.setIcon(new ImageIcon(data.getImage()));
	}
	
	public void setPetImage(String s) {
		this.icon.setIcon(new ImageIcon(s));
	}

}
