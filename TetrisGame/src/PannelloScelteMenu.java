import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class PannelloScelteMenu extends JPanel{

	private JButton[] buttons = new JButton[5];
	private PannelloCardLayout card;

	private boolean dentro0,dentro1,dentro2,dentro3,dentro4;

	private int row=21 , col=14, size=35;

	public PannelloScelteMenu(JFrame finestraPrecedente, PannelloCardLayout cardLayout)
	{
		this.card = cardLayout;

		//Imposto il layout
		setPreferredSize(new Dimension(305,500));
		setLayout(new GridBagLayout());

		//Creo ed assegno i nomi ai bottoni
		buttons[0] = new JButton("NEW") {
			protected void paintComponent(Graphics g) { 
				if(dentro0 == false){
					ImageIcon back = new ImageIcon("NEW.jpg");
					Image Minecraft1 = back.getImage();
					g.drawImage(Minecraft1, 0, 0, getWidth(), getHeight(), this);
				}
				else
				{
					ImageIcon back = new ImageIcon("NEW_SELECTED.jpg");
					Image Minecraft2 = back.getImage();
					g.drawImage(Minecraft2, 0, 0, getWidth(), getHeight(), this);
				}
			}
		};

		buttons[1] = new JButton("SCORE"){
			protected void paintComponent(Graphics g) { 
				if(dentro1 == false){
					ImageIcon back = new ImageIcon("SCORE.jpg");
					Image Minecraft1 = back.getImage();
					g.drawImage(Minecraft1, 0, 0, getWidth(), getHeight(), this);
				}
				else
				{
					ImageIcon back = new ImageIcon("SCORE_SELECTED.jpg");
					Image Minecraft2 = back.getImage();
					g.drawImage(Minecraft2, 0, 0, getWidth(), getHeight(), this);
				}
			}
		};

		buttons[2] = new JButton("DIFFICULTY"){
			protected void paintComponent(Graphics g) { 
				if(dentro2 == false){
					ImageIcon back = new ImageIcon("DIFFICULTY.jpg");
					Image Minecraft1 = back.getImage();
					g.drawImage(Minecraft1, 0, 0, getWidth(), getHeight(), this);
				}
				else
				{
					ImageIcon back = new ImageIcon("DIFFICULTY_SELECTED.jpg");
					Image Minecraft2 = back.getImage();
					g.drawImage(Minecraft2, 0, 0, getWidth(), getHeight(), this);
				}
			}
		};

		buttons[3] = new JButton("COMANDS"){
			protected void paintComponent(Graphics g) { 
				if(dentro3 == false){
					ImageIcon back = new ImageIcon("COMANDS.jpg");
					Image Minecraft1 = back.getImage();
					g.drawImage(Minecraft1, 0, 0, getWidth(), getHeight(), this);
				}
				else
				{
					ImageIcon back = new ImageIcon("COMANDS_SELECTED.jpg");
					Image Minecraft2 = back.getImage();
					g.drawImage(Minecraft2, 0, 0, getWidth(), getHeight(), this);
				}
			}
		};

		buttons[4] = new JButton("EXIT"){
			protected void paintComponent(Graphics g) { 
				if(dentro4 == false){
					ImageIcon back = new ImageIcon("EXIT.jpg");
					Image Minecraft1 = back.getImage();
					g.drawImage(Minecraft1, 0, 0, getWidth(), getHeight(), this);
				}
				else
				{
					ImageIcon back = new ImageIcon("EXIT_SELECTED.jpg");
					Image Minecraft2 = back.getImage();
					g.drawImage(Minecraft2, 0, 0, getWidth(), getHeight(), this);
				}
			}
		};

		//Implemento i rispettivi actionListener
		buttons[0].addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {

				finestraPrecedente.dispose();
				TetrisFrame nuovaFinestra = new TetrisFrame("Tetris Game", row, col, size);
				nuovaFinestra.setVisible(true);
				nuovaFinestra.setGameOption();
				nuovaFinestra.start();
			}
		});

		buttons[0].addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				dentro0 = true;
			}

			public void mouseExited(MouseEvent e) {
				dentro0 = false;
			}
		});

		buttons[1].addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				card.aggiornaScore();
				card.showSCORE();
			}
		});

		buttons[1].addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				dentro1 = true;
			}

			public void mouseExited(MouseEvent e) {
				dentro1 = false;
			}
		});

		buttons[2].addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				card.showDIFFICULTY();
			}
		});

		buttons[2].addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				dentro2 = true;
			}

			public void mouseExited(MouseEvent e) {
				dentro2 = false;
			}
		});

		buttons[3].addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				card.showCOMANDS();
			}
		});

		buttons[3].addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				dentro3 = true;
			}

			public void mouseExited(MouseEvent e) {
				dentro3 = false;
			}
		});

		buttons[4].addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				finestraPrecedente.dispose();
				System.exit(0);
			}
		});

		buttons[4].addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				dentro4 = true;
			}

			public void mouseExited(MouseEvent e) {
				dentro4 = false;
			}
		});


		//Layout
		GridBagConstraints gbc = new GridBagConstraints();

		//Grandezza dei bottoni
		gbc.ipady = 30;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;

		//RIGA 1: bottone1
		gbc.gridx = 0;
		gbc.gridy = 0;

		gbc.weightx = 0.01;
		gbc.weighty = 0.1;

		gbc.insets = new Insets(30,20,2,20);

		add(buttons[0], gbc);

		//RIGA 2: bottone2
		gbc.gridx = 0;
		gbc.gridy = 1;

		gbc.weightx = 0.01;
		gbc.weighty = 0.1;

		gbc.insets = new Insets(0,20,2,20);

		add(buttons[1], gbc);

		//RIGA 3: bottone3
		gbc.gridx = 0;
		gbc.gridy = 2;

		gbc.weightx = 0.01;
		gbc.weighty = 0.1;

		gbc.insets = new Insets(0,20,2,20);

		add(buttons[2], gbc);

		//RIGA 4: bottone4
		gbc.gridx = 0;
		gbc.gridy = 3;

		gbc.weightx = 0.01;
		gbc.weighty = 0.1;

		gbc.insets = new Insets(0,20,2,20);

		add(buttons[3], gbc);

		//RIGA 5: bottone5
		gbc.gridx = 0;
		gbc.gridy = 4;

		gbc.weightx = 0.01;
		gbc.weighty = 1;

		gbc.insets = new Insets(0,20,-20,20);

		add(buttons[4], gbc);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		ImageIcon background = new ImageIcon("STONE_BACKGROUND.jpg");
		Image background2 = background.getImage();

		g.drawImage(background2, 0, 0, getWidth(), getHeight(), this);
	}
}


