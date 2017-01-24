package frontend.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ParkPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private MainPanel m;
	private ParkPanel p;

	JPanel backgroundPanel = new JPanel();
	JPanel returnPanel = new JPanel();
	
	JButton jbtReturn = new JButton("Return");

	public ParkPanel(MainPanel m) {
		
		this.m = m;
		this.p = this;
		
		this.setLayout(new BorderLayout());
		
		this.backgroundPanel.add(new JLabel(new ImageIcon("images/park/background.gif")));
		this.returnPanel.add(this.jbtReturn);
		
		this.add(backgroundPanel, BorderLayout.NORTH);
		this.add(returnPanel, BorderLayout.SOUTH);
		
		this.jbtReturn.addActionListener(new ButtonListener());
	}
	
	class ButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == jbtReturn) {
				m.getGadget().remove(p);
				m.getGadget().add(m);
				m.getGadget().pack();
			}
		}
		
	}

}
