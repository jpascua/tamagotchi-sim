package frontend.panels;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import frontend.labels.MessageLabel;
import backend.GameData;

public class ClinicPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private GameData data = GameData.getInstance();
	
	private MainPanel m;
	
	private JButton m1 = new JButton(new ImageIcon("images/clinic/coldmed.png"));
	private JButton m2 = new JButton(new ImageIcon("images/clinic/antidote.png"));
	private JButton m3 = new JButton(new ImageIcon("images/clinic/remedy.png"));
	private JButton jbtReturn = new JButton("Return");
	
	private JPanel currencyPanel = new JPanel();
	private JPanel cabinetPanel = new JPanel(new GridLayout(1, 0));
	private JPanel returnPanel = new JPanel(new BorderLayout(10, 10));
	private JPanel p1 = new JPanel();
	
	private MessageLabel currency = new MessageLabel();
	private MessageLabel cost = new MessageLabel();
	private MessageLabel description = new MessageLabel();
	
	public ClinicPanel(MainPanel m) {
		this.m = m;
		
		this.removeAll();
		
		this.setLayout(new BorderLayout());
		
		this.currency.updateMessage("Tamacoin: " + this.data.getTamacoin()); // FIX
		
		this.currencyPanel.add(currency);
		this.cabinetPanel.add(m1);
		this.cabinetPanel.add(m2);
		this.cabinetPanel.add(m3);
		this.p1.add(jbtReturn);
		this.returnPanel.add(p1, BorderLayout.NORTH);
		this.returnPanel.add(cost, BorderLayout.CENTER);
		this.returnPanel.add(description, BorderLayout.SOUTH);
		
		this.add(currencyPanel, BorderLayout.NORTH);
		this.add(cabinetPanel, BorderLayout.CENTER);
		this.add(returnPanel, BorderLayout.SOUTH);
		
		this.m1.addActionListener(new ButtonPurchaseListener());
		
		this.m1.addMouseListener(new ButtonHoverAdapter());
		this.m2.addMouseListener(new ButtonHoverAdapter());
		this.m3.addMouseListener(new ButtonHoverAdapter());
		this.jbtReturn.addActionListener(new ButtonPurchaseListener());
	}
	
	class ButtonHoverAdapter extends MouseAdapter {
		
		public void mouseEntered(MouseEvent e) {
			if(e.getSource() != jbtReturn) {
				if(e.getSource() == m1) {
					cost.updateMessage("100 Tamacoin.");
					description.updateMessage("\"Cures sickness.\""); 	// Cold Medicine
				}
				else if(e.getSource() == m2) {
					cost.updateMessage("<font color='red'>Out of stock.</font>");
					description.updateMessage("\"Cures poison.\""); 	// Antidote
				}
				else if(e.getSource() == m3) { 
					cost.updateMessage("<font color='red'>Out of stock.</font>");
					description.updateMessage("\"Cures all status aliments.\""); 	// Remedy
				}
			}
			
			m.getGadget().pack();
		}
		
	}
	
	class ButtonPurchaseListener implements ActionListener {
		
		private int cost;
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() != jbtReturn) {
				if(e.getSource() == m1) {		
					this.cost = 100;
				}
				
				if(data.calculateRemainingTamacoin(this.cost) < 0) {
					description.updateMessage("<font color='red'>Not enough funds!</font>");
				} else {
					data.subtractTamacoin(this.cost);
							
					description.updateMessage("Successfully purchased!");
					
					data.setCondition("Healthy");
					
					m.getControlButtonsPanel().updateMessage("Tamagotchi's sickness goes away.");
				}
				
				currency.updateMessage("Tamacoin: " + data.getTamacoin());
				
				m.getStatusMessagePanel().refreshCondition();
				m.getPetImagePanel().refreshPetImage();
				
			} else {
				m.getReservedPanel().removeAll();
				m.getGadget().pack();
			}
		}
		
	}

}
