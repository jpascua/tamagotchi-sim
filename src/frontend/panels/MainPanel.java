package frontend.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import frontend.Gadget;

public class MainPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private Gadget g;

	private StatusMessagePanel sm = new StatusMessagePanel();
	private PetImagePanel pd = new PetImagePanel();
	private ControlButtonsPanel cb = new ControlButtonsPanel(this);
	
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel();
	private JPanel reservedPanel = new JPanel();

	public MainPanel(Gadget g) {
		this.g = g;
		
		this.setLayout(new BorderLayout(20, 20));
		
		this.p1.add(sm);
		this.p2.add(pd);
		this.p3.add(cb);
		
		this.add(p1, BorderLayout.NORTH);
		this.add(p2, BorderLayout.CENTER);
		this.add(reservedPanel, BorderLayout.EAST);
		this.add(p3, BorderLayout.SOUTH);	
	}
	
	public Gadget getGadget() {
		return this.g;
	}

	public StatusMessagePanel getStatusMessagePanel() {
		return this.sm;
	}
	
	public PetImagePanel getPetImagePanel() {
		return this.pd;
	}
	
	public ControlButtonsPanel getControlButtonsPanel() {
		return this.cb;
	}
	
	public JPanel getReservedPanel() {
		return this.reservedPanel;
	}
	
}
