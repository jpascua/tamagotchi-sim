package frontend.panels;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frontend.labels.MessageLabel;
import backend.GameData;
import backend.RockPaperScissors;

public class MiniGameRPSPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private GameData data = GameData.getInstance();
	
	private MainPanel m;
	private MiniGameRPSPanel mg;
	
	private JButton jbtRock = new JButton(new ImageIcon("images/minigame/rock.gif"));
	private JButton jbtPaper = new JButton(new ImageIcon("images/minigame/paper.gif"));
	private JButton jbtScissors = new JButton(new ImageIcon("images/minigame/scissors.gif"));
	private JButton jbtReturn = new JButton("Return");
	
	private MessageLabel message = new MessageLabel();
	private MessageLabel currency = new MessageLabel();
	
	private JLabel backgroundLabel = new JLabel(new ImageIcon("images/minigame/background.gif"));
	
	private JPanel currencyPanel = new JPanel();
	private JPanel backgroundPanel = new JPanel();
	private JPanel messagePanel = new JPanel();
	private JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
	private JPanel returnPanel = new JPanel();
	private JPanel p1 = new JPanel(new BorderLayout());
	private JPanel p2 = new JPanel();

	public MiniGameRPSPanel(MainPanel m) {
		
		this.m = m;
		this.mg = this;
		
		this.setLayout(new BorderLayout());
		
		this.message.updateMessage("Here, you will play a game of Rock-Paper-Scissors.");
		
		this.currency.updateMessage("Tamacoin: " + this.data.getTamacoin());
		
		this.currencyPanel.add(this.currency);
		this.backgroundPanel.add(this.backgroundLabel);
		this.messagePanel.add(this.message);
		this.p1.add(currencyPanel, BorderLayout.NORTH);
		this.p1.add(backgroundPanel, BorderLayout.CENTER);
		this.p1.add(messagePanel, BorderLayout.SOUTH);
		
		this.buttonPanel.add(this.jbtRock);
		this.buttonPanel.add(this.jbtPaper);
		this.buttonPanel.add(this.jbtScissors);
		this.p2.add(this.buttonPanel);
		
		this.returnPanel.add(this.jbtReturn);

		this.add(this.p1, BorderLayout.NORTH);
		this.add(this.p2, BorderLayout.CENTER);
		this.add(this.returnPanel, BorderLayout.SOUTH);
		
		this.jbtRock.addActionListener(new MiniGameButtonListener());
		this.jbtPaper.addActionListener(new MiniGameButtonListener());
		this.jbtScissors.addActionListener(new MiniGameButtonListener());
		this.jbtReturn.addActionListener(new MiniGameButtonListener());
	}
	
	class MiniGameButtonListener implements ActionListener {

		private RockPaperScissors rps = new RockPaperScissors();
		
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() != jbtReturn) {
				if (e.getSource() == jbtRock) {
					message.updateMessage(rps.getResult(0));
				}
				else if (e.getSource() == jbtPaper) {
					message.updateMessage(rps.getResult(1));
				}
				else if (e.getSource() == jbtScissors) {
					message.updateMessage(rps.getResult(2));
				}
				
				if((data.getTamacoin() + rps.getPrizeTamacoin()) <= 0) {
					data.setTamacoin(0);
				} else {
					data.addTamacoin(rps.getPrizeTamacoin());
				}
				
				currency.updateMessage("Tamacoin: " + data.getTamacoin());
				
			} else {
				m.getGadget().remove(mg);
				m.getGadget().add(m);
				m.getGadget().pack();
			}
		}
		
	}

}
