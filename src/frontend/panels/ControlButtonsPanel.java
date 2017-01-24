package frontend.panels;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlButtonsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private MainPanel m;
	
	private JButton jbtStore = new JButton("Store");
	private JButton jbtPark = new JButton("Park");
	private JButton jbtClinic = new JButton("Clinic");
	private JButton jbtMiniGame = new JButton("Mini Game");
	
	private JPanel messagePanel = new JPanel();
	private JPanel plazaPanel = new JPanel(new GridLayout(1, 0));
	private JPanel miniGamePanel = new JPanel();
	
	private JLabel message = new JLabel("");
	
	public ControlButtonsPanel(MainPanel m) {
		this.m = m;
		
		this.setLayout(new BorderLayout());
		
		this.removeAll();
		this.messagePanel.add(this.message);
		this.miniGamePanel.add(this.jbtMiniGame);
		
		this.plazaPanel.add(this.jbtStore);
		this.plazaPanel.add(this.jbtPark);
		this.plazaPanel.add(this.jbtClinic);
		
		this.add(messagePanel, BorderLayout.NORTH);
		this.add(plazaPanel, BorderLayout.CENTER);
		this.add(miniGamePanel, BorderLayout.SOUTH);
		
		this.jbtStore.addActionListener(new ControlButtonListener());
		this.jbtPark.addActionListener(new ControlButtonListener());
		this.jbtClinic.addActionListener(new ControlButtonListener());
		this.jbtMiniGame.addActionListener(new ControlButtonListener());
	}
	
	public void updateMessage(String message) {
		this.message.setText("<html><body>" + message + "</body></html>");
	}
	
	class ControlButtonListener implements ActionListener {
		
		private StorePanel s = new StorePanel(m);
		private ClinicPanel c = new ClinicPanel(m);
		private MiniGameRPSPanel mg = new MiniGameRPSPanel(m);
		private ParkPanel p = new ParkPanel(m);
			
		public void actionPerformed(ActionEvent e) {		
			if(e.getSource() == jbtStore) {
				updateMessage("Tamagotchi is drooling.");
				m.getReservedPanel().removeAll();
				m.getReservedPanel().add(this.s);
				m.getGadget().pack();
			}
			else if(e.getSource() == jbtPark) {
				m.getGadget().remove(m);
				m.getGadget().add(p);
				m.getGadget().pack();	
			}
			else if(e.getSource() == jbtClinic) {
				updateMessage("You took Tamagotchi for a check-up.");
				
				m.getReservedPanel().removeAll();
				m.getReservedPanel().add(this.c);
				m.getGadget().pack();
			}
			else if(e.getSource() == jbtMiniGame) {			
				m.getGadget().remove(m);
				m.getGadget().add(mg);
				m.getGadget().pack();
			}
			
			m.getStatusMessagePanel().refreshAndCalculateCondition();
			m.getPetImagePanel().refreshPetImage();
		}
		
	}

}
