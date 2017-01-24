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

public class StorePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private GameData data = GameData.getInstance();
	
	private MainPanel m;
	
	private JButton f1 = new JButton(new ImageIcon("images/store/apple.png"));
	private JButton f2 = new JButton(new ImageIcon("images/store/salad.png"));
	private JButton f3 = new JButton(new ImageIcon("images/store/alfajor.png"));
	private JButton f4 = new JButton(new ImageIcon("images/store/takoyaki.png"));
	private JButton f5 = new JButton(new ImageIcon("images/store/icecream.png"));
	private JButton f6 = new JButton(new ImageIcon("images/store/pizza.png"));
	private JButton jbtReturn = new JButton("Return");
	
	private JPanel currencyPanel = new JPanel();
	private JPanel cabinetPanel = new JPanel(new GridLayout(2, 0));
	private JPanel returnPanel = new JPanel(new BorderLayout(10, 10));
	private JPanel p1 = new JPanel();
	
	private MessageLabel currency = new MessageLabel();
	private MessageLabel cost = new MessageLabel();
	private MessageLabel description = new MessageLabel();
		
	public StorePanel(MainPanel m) {
		this.m = m;
		
		this.removeAll();
		
		this.setLayout(new BorderLayout());
		
		this.currency.updateMessage("Tamacoin: " + this.data.getTamacoin());	// FIX
		
		this.currencyPanel.add(this.currency);
		this.cabinetPanel.add(this.f1);
		this.cabinetPanel.add(this.f2);
		this.cabinetPanel.add(this.f3);
		this.cabinetPanel.add(this.f4);		
		this.cabinetPanel.add(this.f5);
		this.cabinetPanel.add(this.f6);
		this.p1.add(jbtReturn);
		this.returnPanel.add(this.p1, BorderLayout.NORTH);
		this.returnPanel.add(this.cost, BorderLayout.CENTER);
		this.returnPanel.add(this.description, BorderLayout.SOUTH);
		
		this.add(this.currencyPanel, BorderLayout.NORTH);
		this.add(this.cabinetPanel, BorderLayout.CENTER);
		this.add(this.returnPanel, BorderLayout.SOUTH);
		
		this.f1.addActionListener(new ButtonPurchaseListener());
		this.f2.addActionListener(new ButtonPurchaseListener());
		this.f3.addActionListener(new ButtonPurchaseListener());
		this.f4.addActionListener(new ButtonPurchaseListener());
		this.f5.addActionListener(new ButtonPurchaseListener());
		this.f6.addActionListener(new ButtonPurchaseListener());
		this.jbtReturn.addActionListener(new ButtonPurchaseListener());
		
		this.f1.addMouseListener(new ButtonHoverAdapter());
		this.f2.addMouseListener(new ButtonHoverAdapter());
		this.f3.addMouseListener(new ButtonHoverAdapter());
		this.f4.addMouseListener(new ButtonHoverAdapter());
		this.f5.addMouseListener(new ButtonHoverAdapter());
		this.f6.addMouseListener(new ButtonHoverAdapter());
		
		/*
		data.setChanceInCurrencyListener(new ChangeInCurrencyListener() {
			public void refreshCurrency() {
				currency.updateMessage("Tamacoin: " + data.getTamacoin());
			}
		});
		*/
	}
	
	
	class ButtonHoverAdapter extends MouseAdapter {
		
		public void mouseEntered(MouseEvent e) {
			if(e.getSource() != jbtReturn) {
				if(e.getSource() == f1) {
					cost.updateMessage("50 Tamacoin.");
					description.updateMessage("\"Freshly harvested and organic.\""); 	// Apple
				}
				else if(e.getSource() == f2) {
					cost.updateMessage("100 Tamacoin.");
					description.updateMessage("\"Eat your veggies, now!\"");	// Salad
				}
				else if(e.getSource() == f3) {
					cost.updateMessage("150 Tamacoin.");
					description.updateMessage("\"Delicate Argentine confectionery.\"");		// Alfajor
				}
				else if(e.getSource() == f4) {
					cost.updateMessage("200 Tamacoin.");
					description.updateMessage("\"The ultimate Osaka treat.\"");		// Takoyaki
				}
				else if(e.getSource() == f5) {
					cost.updateMessage("250 Tamacoin.");
					description.updateMessage("\"Perfect for hot weathers.\"");	// Ice Cream
				}
				else if(e.getSource() == f6) {
					cost.updateMessage("300 Tamacoin.");
					description.updateMessage("\"Fresh from the oven!\"");		// Pizza
				}
			}
			
			m.getGadget().pack();
		}
		
	}
	
	class ButtonPurchaseListener implements ActionListener {
		
		private int cost;
		private double staminaRestored;
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() != jbtReturn) {
				if (data.calculateStaminaPercentage() < 100) {
					if(e.getSource() == f1) {		
						this.cost = 50;
						this.staminaRestored = 2;
					}
					else if(e.getSource() == f2) {
						this.cost = 100;
						this.staminaRestored = 4;
					}
					else if(e.getSource() == f3) {					
						this.cost = 150;
						this.staminaRestored = 6;
					}
					else if(e.getSource() == f4) {
						this.cost = 200;
						this.staminaRestored = 8;
					}
					else if(e.getSource() == f5) {
						this.cost = 250;
						this.staminaRestored = 10;
					}
					else if(e.getSource() == f6) {
						this.cost = 300;
						this.staminaRestored = 12;
					}
					
					if(data.calculateRemainingTamacoin(this.cost) < 0) {
						description.updateMessage("<font color='red'>Not enough funds!</font>");
					} else {
						data.addCurrentStamina(this.staminaRestored);
						data.subtractTamacoin(this.cost);
						
						description.updateMessage("Successfully purchased!");
						m.getControlButtonsPanel().updateMessage("You fed Tamagotchi.");
					}
					
					currency.updateMessage("Tamacoin: " + data.getTamacoin());
					
					//data.refreshCurrency();
					
					m.getStatusMessagePanel().refreshStamina();
					m.getStatusMessagePanel().refreshMood();
					m.getPetImagePanel().refreshPetImage();
				} else {
					m.getControlButtonsPanel().updateMessage("Tamagotchi's not hungry.");
				}
			} else {
				m.getReservedPanel().removeAll();
				m.getGadget().pack();
			}
		}
		
	}

}
