import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class PannelloCardLayout extends JPanel{

	private ClassePassaggio passaggio;

	public JPanel main_panel;

	private JPanel new_panel;
	private JPanel score_panel;
	private JPanel difficulty_panel;
	private JPanel comands_panel;

	private CardLayout card_layout;

	//Bottoni della scelta delle difficoltà
	private JButton difficultyDefault;
	private JButton difficultyHard;
	private JButton difficultyImpossible;
	private JLabel difficultySelected;

	//Label del pannello comandi
	private JTextArea comandi;

	//Label del pannello Score
	private JTextArea score;

	private boolean dentro0,dentro1,dentro2;
	
	//Costanti utili
	private static final int sizeX = 470 , sizeY = 450;
	private static final int larghezzaBottoneX = 100 , altezzaBottoneY = 50;

	public PannelloCardLayout(Image Minecraft)
	{
		passaggio = new ClassePassaggio();

		//CardLayout
		card_layout = new CardLayout();
		main_panel = new JPanel();
		main_panel.setLayout(card_layout);

		//Creo gli altri pannelli
		new_panel = new JPanel() {

			protected void paintComponent(Graphics g) {
				g.drawImage(Minecraft, 0, 0, getWidth(), getHeight(), this);
			}
		};

		score_panel = new JPanel();

		difficulty_panel = new JPanel(){
			protected void paintComponent(Graphics g) { 

				ImageIcon back = new ImageIcon("WOOD_TEXTAREA.jpg");
				Image wood = back.getImage();
				g.drawImage(wood, 0, 0, getWidth(), getHeight(), this);

				super.paintComponent(g);
			}
		};

		difficulty_panel.setOpaque(false);

		comands_panel = new JPanel();

		//Creo i layout dei miei pannelli
		new_panel.setLayout(new BorderLayout());
		new_panel.setPreferredSize(new Dimension(sizeX,sizeY));

		score_panel.setLayout(new BorderLayout());
		score_panel.setPreferredSize(new Dimension(sizeX,sizeY));

		difficulty_panel.setLayout(new GridBagLayout());
		difficulty_panel.setPreferredSize(new Dimension(sizeX,sizeY));

		comands_panel.setLayout(new BorderLayout());
		comands_panel.setPreferredSize(new Dimension(sizeX,sizeY));

		//Creo il label score
		score = new JTextArea(""){
			protected void paintComponent(Graphics g) { 

				ImageIcon back = new ImageIcon("WOOD_TEXTAREA.jpg");
				Image wood = back.getImage();
				g.drawImage(wood, 0, 0, getWidth(), getHeight(), this);

				super.paintComponent(g);
			}
		};
		
		score.setOpaque(false);
		score.setForeground(Color.ORANGE);

		//Aggiungo il label al pannello score
		score_panel.add(score);

		//Creo i bottoni difficulties e il label di selezione
		difficultyDefault = new JButton(){
			protected void paintComponent(Graphics g) { 
				if(dentro0 == false){
					ImageIcon back = new ImageIcon("DIFFICOLTA_EASY.jpg");
					Image Minecraft1 = back.getImage();
					g.drawImage(Minecraft1, 0, 0, getWidth(), getHeight(), this);
				}
				else
				{
					ImageIcon back = new ImageIcon("DIFFICOLTA_EASY_SELECTED.jpg");
					Image Minecraft2 = back.getImage();
					g.drawImage(Minecraft2, 0, 0, getWidth(), getHeight(), this);
				}
			}
		};
		
		difficultyDefault.setPreferredSize(new Dimension(larghezzaBottoneX+100,altezzaBottoneY));
		
		difficultyHard = new JButton("HARD"){
			protected void paintComponent(Graphics g) { 
				if(dentro1 == false){
					ImageIcon back = new ImageIcon("DIFFICOLTA_HARD.jpg");
					Image Minecraft1 = back.getImage();
					g.drawImage(Minecraft1, 0, 0, getWidth(), getHeight(), this);
				}
				else
				{
					ImageIcon back = new ImageIcon("DIFFICOLTA_HARD_SELECTED.jpg");
					Image Minecraft2 = back.getImage();
					g.drawImage(Minecraft2, 0, 0, getWidth(), getHeight(), this);
				}
			}
	};
	
	difficultyHard.setPreferredSize(new Dimension(larghezzaBottoneX+100,altezzaBottoneY));

	difficultyImpossible = new JButton("IMPOSSIBLE"){
		protected void paintComponent(Graphics g) { 
			if(dentro2 == false){
				ImageIcon back = new ImageIcon("DIFFICOLTA_IMPOSSIBLE.jpg");
				Image Minecraft1 = back.getImage();
				g.drawImage(Minecraft1, 0, 0, getWidth(), getHeight(), this);
			}
			else
			{
				ImageIcon back = new ImageIcon("DIFFICOLTA_IMPOSSIBLE_SELECTED.jpg");
				Image Minecraft2 = back.getImage();
				g.drawImage(Minecraft2, 0, 0, getWidth(), getHeight(), this);
			}
		}
	};
	
	difficultyImpossible.setPreferredSize(new Dimension(larghezzaBottoneX+100,altezzaBottoneY));

	difficultyDefault.addMouseListener(new MouseAdapter() {

		public void mouseEntered(MouseEvent e) {
			dentro0 = true;
		}

		public void mouseExited(MouseEvent e) {
			dentro0 = false;
		}
	});
	
	difficultyHard.addMouseListener(new MouseAdapter() {

		public void mouseEntered(MouseEvent e) {
			dentro1 = true;
		}

		public void mouseExited(MouseEvent e) {
			dentro1 = false;
		}
	});
	
	difficultyImpossible.addMouseListener(new MouseAdapter() {

		public void mouseEntered(MouseEvent e) {
			dentro2 = true;
		}

		public void mouseExited(MouseEvent e) {
			dentro2 = false;
		}
	});
	
		difficultySelected = new JLabel("Difficoltà: EASY");

		difficultySelected.setOpaque(false);
		difficultySelected.setForeground(Color.ORANGE);

		difficultySelected.setPreferredSize(new Dimension(larghezzaBottoneX+250,altezzaBottoneY));

		Font fontGrassetto2 = new Font("Verdana", Font.BOLD, 30);

		difficultySelected.setFont(fontGrassetto2);

		//Aggiungo gli ActionListener ai bottoni
	
		difficultyDefault.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				difficultySelected.setText("Difficoltà: EASY");
				passaggio.setDifficoltà(1);
			}
		});

		difficultyHard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				difficultySelected.setText("Difficoltà: HARD");
				passaggio.setDifficoltà(2);
			}
		});

		difficultyImpossible.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				difficultySelected.setText("Difficoltà: IMPOSSIBLE" );
				passaggio.setDifficoltà(3);
			}
		});

		//Aggiungo i bottoni al pannello difficulty
		difficulty_panel.add(difficultyDefault,setButtonsPosition(0,0));
		difficulty_panel.add(difficultyHard,setButtonsPosition(0,1));
		difficulty_panel.add(difficultyImpossible,setButtonsPosition(0,2));
		difficulty_panel.add(difficultySelected,setButtonsPosition(0,3));

		//Creo il textArea comandi
		comandi = new JTextArea(""){
			protected void paintComponent(Graphics g) { 

				ImageIcon back = new ImageIcon("WOOD_TEXTAREA.jpg");
				Image wood = back.getImage();
				g.drawImage(wood, 0, 0, getWidth(), getHeight(), this);

				super.paintComponent(g);
			}
		};

		comandi.setOpaque(false);
		comandi.setForeground(Color.ORANGE);

		comandi.append("\r\n --  To Move:  --\r\n");
		comandi.append("\r\t Left: A or LeftArrowKey\r\n");
		comandi.append("\r\t Right: D or RightArrowKey\r\n");
		comandi.append("\r\t Down: S or DownArrowKey\r\n\n");
		comandi.append(" --  To Rotate:  --\r\n");
		comandi.append("\r\t Press: R\r\n\n");
		comandi.append(" --  To Drop:  --\r\n");
		comandi.append("\r\t Press: Spacebar\r\n\n");
		comandi.append(" --  To pause the game:  --\r\n");
		comandi.append("\r\t Press: P\r\n\n");
		comandi.append(" --  To view the comands: --\r\n");
		comandi.append("\r\t Press: C");

		comandi.setPreferredSize(new Dimension(sizeX,sizeY));
		Font fontGrassetto = new Font("Verdana", Font.BOLD, 19);
		comandi.setFont(fontGrassetto);
		comandi.setEditable(false); 

		//Aggiungo il label al pannello comandi
		comands_panel.add(comandi);

		//Aggiungo i panel al cardLayout
		main_panel.add(new_panel,"NEW");
		main_panel.add(score_panel,"SCORE");
		main_panel.add(difficulty_panel,"DIFFICULTY");
		main_panel.add(comands_panel,"COMANDS");

		add(main_panel);
	}

	public GridBagConstraints setButtonsPosition(int x, int y)
	{
		//Layout
		GridBagConstraints gbc = new GridBagConstraints();

		//Grandezza dei bottoni
		gbc.ipady = 20;
		gbc.ipadx = 70;
		gbc.anchor = GridBagConstraints.CENTER;

		//RIGA 1: bottone1
		gbc.gridx = x;
		gbc.gridy = y;

		gbc.weightx = 0.01;
		gbc.weighty = 0.1;

		gbc.insets = new Insets(5,5,2,5);

		return gbc;
	}

	public void aggiornaScore()
	{
		System.out.println("AggiornaScore");
		
		score.setText("");

		loadScore(passaggio.getScoreDifficultyDefault(),1);

		String pointScoreStringDefault = "";
		pointScoreStringDefault = pointScoreStringDefault +
				" " + ClassePassaggio.coordArrDefault[0].getX() + ")  " + ClassePassaggio.coordArrDefault[0].getName() + " : " + ClassePassaggio.coordArrDefault[0].getY() + " points\r\n" +
				" " + ClassePassaggio.coordArrDefault[1].getX() + ")  " + ClassePassaggio.coordArrDefault[1].getName() + " : " + ClassePassaggio.coordArrDefault[1].getY() + " points\r\n" +
				" " + ClassePassaggio.coordArrDefault[2].getX() + ")  " + ClassePassaggio.coordArrDefault[2].getName() + " : " + ClassePassaggio.coordArrDefault[2].getY() + " points\r\n" ;

		
		loadScore(passaggio.getScoreDifficultyHard(),2);
		String pointScoreStringHard = "";
		pointScoreStringHard = pointScoreStringHard +
				" " + ClassePassaggio.coordArrHard[0].getX() + ")  " + ClassePassaggio.coordArrHard[0].getName() + " : " + ClassePassaggio.coordArrHard[0].getY() + " points\r\n" +
				" " + ClassePassaggio.coordArrHard[1].getX() + ")  " + ClassePassaggio.coordArrHard[1].getName() + " : " + ClassePassaggio.coordArrHard[1].getY() + " points\r\n" +
				" " + ClassePassaggio.coordArrHard[2].getX() + ")  " + ClassePassaggio.coordArrHard[2].getName() + " : " + ClassePassaggio.coordArrHard[2].getY() + " points\r\n" ;

		loadScore(passaggio.getScoreDifficultyImpossible(),3);
		String pointScoreStringImpossible = "";
		pointScoreStringImpossible = pointScoreStringImpossible +
				" " + ClassePassaggio.coordArrImpossible[0].getX() + ")  " + ClassePassaggio.coordArrImpossible[0].getName() + " : " + ClassePassaggio.coordArrImpossible[0].getY() + " points\r\n" +
				" " + ClassePassaggio.coordArrImpossible[1].getX() + ")  " + ClassePassaggio.coordArrImpossible[1].getName() + " : " + ClassePassaggio.coordArrImpossible[1].getY() + " points\r\n" +
				" " + ClassePassaggio.coordArrImpossible[2].getX() + ")  " + ClassePassaggio.coordArrImpossible[2].getName() + " : " + ClassePassaggio.coordArrImpossible[2].getY() + " points\r\n" ;
		
		score.append(" Difficoltà Easy:\r\n");
		score.append(pointScoreStringDefault);
		score.append("\r\n Difficoltà Hard:\r\n");
		score.append(pointScoreStringHard);
		score.append("\r\n Difficoltà Impossible:\r\n");
		score.append(pointScoreStringImpossible);

		Font fontGrassetto1 = new Font("Verdana", Font.BOLD, 24);
		score.setFont(fontGrassetto1);
		score.setEditable(false);
		
		score.setPreferredSize(new Dimension(sizeX,sizeY));
	}

	public void loadScore(File file,int difficoltà) {

		ClassePassaggio.countScore = 0;
		ClassePassaggio.coordOld.clear();

		int tmp = 0, j;

		ClassePassaggio.coordOld = CoordinateIO.read(file);

		while(tmp==0) {
			try {
				ClassePassaggio.coordOld.get(ClassePassaggio.countScore);
				ClassePassaggio.countScore++;
			} catch (Exception e) {
				tmp = 1;
			}
		}

		if(difficoltà == 1) {
			ClassePassaggio.coordArrDefault = new Coordinate[ClassePassaggio.countScore];
			for(j=0; j<ClassePassaggio.countScore; j++) {
				ClassePassaggio.coordArrDefault[j]=ClassePassaggio.coordOld.get(j);
			}

		}else if(difficoltà == 2) {
			ClassePassaggio.coordArrHard = new Coordinate[ClassePassaggio.countScore];
			for(j=0; j<ClassePassaggio.countScore; j++) {
				ClassePassaggio.coordArrHard[j]=ClassePassaggio.coordOld.get(j);
			}

		}else if(difficoltà == 3){
			ClassePassaggio.coordArrImpossible = new Coordinate[ClassePassaggio.countScore];
			for(j=0; j<ClassePassaggio.countScore; j++) {
				ClassePassaggio.coordArrImpossible[j]=ClassePassaggio.coordOld.get(j);
			}
		}
	}

	public void showNEW()
	{
		card_layout.show(main_panel,"NEW");
	}

	public void showSCORE()
	{
		card_layout.show(main_panel,"SCORE");
	}

	public void showDIFFICULTY()
	{
		card_layout.show(main_panel,"DIFFICULTY");
	}

	public void showCOMANDS()
	{
		card_layout.show(main_panel,"COMANDS");
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		ImageIcon background = new ImageIcon("STONE_BACKGROUND2.jpg");
		Image background2 = background.getImage();

		g.drawImage(background2, 0, 0, getWidth(), getHeight(), this);
	}
}
