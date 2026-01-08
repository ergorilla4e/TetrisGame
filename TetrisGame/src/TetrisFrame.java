import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TetrisFrame extends KeyAnimationBoardFrame{

	private LogicMovements logic;
	private Tetramini tetramini;
	private SetDrawGame drawGame;

	private ClassePassaggio passaggio;

	private BoardPanel board;

	private int x=0 , y=0;
	public int difficoltà = 1, int_random;
	private int row=21, col=14;
	public int[][] matricePassata;
	public int[][] matriceTmp;
	
	private int Start1 , End1;

	private int velocitàGioco = 1000;

	private boolean pausa = false;
	private boolean stop = false;

	Image Bedrock = readImage(new File("Bedrock.jpg"));
	Image Stone = readImage(new File("Stone.jpg"));
	Image Diamond = readImage(new File("Diamond.jpg"));
	Image Iron = readImage(new File("Iron.jpg"));
	Image Gold = readImage(new File("Gold.jpg"));
	Image Emerald = readImage(new File("Emerald.jpg"));
	Image Redstone = readImage(new File("Redstone.jpg"));
	Image Lapis = readImage(new File("Lapis.jpg"));
	Image Charcoal = readImage(new File("Charcoal.jpg"));

	public TetrisFrame(String title, int row, int col, int size) 
	{
		super(title, row, col, size);

		passaggio = new ClassePassaggio();

		matricePassata = new int[row][col];
		matriceTmp = new int[row][col];

		tetramini = new Tetramini();
		board = getBoardPanel();
		logic = new LogicMovements(this,tetramini,matriceTmp,matricePassata,row,col,x,y);

		drawGame = new SetDrawGame(row, col, matricePassata, board);

		playAnimation();
	}

	public void setGameOption()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		loadScore(passaggio.getScoreDifficultyDefault(),1);
		loadScore(passaggio.getScoreDifficultyHard(),2);
		loadScore(passaggio.getScoreDifficultyImpossible(),3);

		drawGame.resetMat(matricePassata);
		drawGame.setScene(matricePassata, Bedrock, Stone);
	}

	@Override
	protected void processKey(KeyEvent e) {

		if(!isAnimPaused) {

			if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
				if(stop == false) {
					logic.MoveRight();
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
				if(stop == false) {
					logic.MoveLeft();
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
				if(stop == false) {
					logic.MoveDown();
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				if(stop == false) {
					logic.Drop();
				}
			}
			if((e.getKeyCode() == KeyEvent.VK_R) && logic.isFall()) {
				if(stop == false) {

					logic.checkRotazione();

					if(logic.isPuòRuotare()) {
						logic.setNumeroRotazioni(logic.getNumeroRotazioni() + 1);
						logic.MoveRotate();
					}
				}		
			}	
			drawGame.disMatrice(matricePassata,row,col,
					Stone,Iron,Lapis,Gold,Emerald,
					Charcoal,Redstone,Diamond);
			board.repaint();
		}

		if(e.getKeyCode() == KeyEvent.VK_P) {

			if(pausa == false) {
				pauseAnimation();
				pausa = true;
			}else if(pausa == true) {
				playAnimation();
				pausa = false;
			}
		}

		if(e.getKeyCode() == KeyEvent.VK_C) {
			showMessageDialog("--  Commands:  --\nMove Left: A or LeftArrowKey\nMove Right: D or RightArrowKey\nMove Down: S or DownArrowKey\n--  To Rotate:  --\nPress: R\n--  To Drop:  --\nPress: Spacebar\n-- To view the score:  --\nPress: C\n--  To pause the game:  --\nPress: P\n--  To view the comands: --\nPress: K", "How to play:");
		}
	}

	@Override
	protected void animateInit() {

		Start1 = 5 * 44100;
		End1 = 312 * 44100;

		music1.playMusic(Start1,End1);

		pausa = false;
		stop = false;
		logic.setGameOver(false);
		difficoltà = passaggio.getDifficoltà();

		logic.setNumeroRotazioni(0);
		passaggio.setTotalPoint(0);

		drawGame.resetMat(matricePassata);
		board.clear();
		drawGame.setScene(matricePassata, Bedrock, Stone);
		updateBoard();
		repaint();
	}

	@Override
	protected void animateNext() {

		if(!logic.isGameOver()) {

			if(difficoltà == 1) {
				if(velocitàGioco-passaggio.getTotalPoint()>500) {
					setAnimationDelay(velocitàGioco - passaggio.getTotalPoint());
				}else {
					setAnimationDelay(500);
				}		
			}
			if(difficoltà == 2) {
				if(velocitàGioco-250-passaggio.getTotalPoint()>250) {
					setAnimationDelay(velocitàGioco - 250 - passaggio.getTotalPoint());
				}else {
					setAnimationDelay(250);
				}
			}
			if(difficoltà == 3) {
				if(velocitàGioco-500-passaggio.getTotalPoint()>100) {
					setAnimationDelay(velocitàGioco - 500 - passaggio.getTotalPoint());
				}else {
					setAnimationDelay(100);
				}
			}

			passaggio.setTotalPoint(passaggio.getTotalPoint() + 1);			

			labelScoreVisibile.setText(" SCORE: " + passaggio.getTotalPoint());

			logic.play(matricePassata,row,col);

			if(logic.isGameOver())
			{
				music1.stopMusic();

				salvaNome();

				this.dispose();
				stopAnimation();
				FinestraMenuIniziale nuovaFinestra = new FinestraMenuIniziale("Menù iniziale");
				nuovaFinestra.setVisible(true);	
			}

			drawGame.disMatrice(matricePassata,row,col,
					Stone,Iron,Lapis,Gold,Emerald,
					Charcoal,Redstone,Diamond);
			updateBoard();
			repaint();
		}
	}

	@Override
	protected void animateFinal() {

		y=0;
		x = (col/2)-2;

		stop = true;

		board.clear();
		drawGame.resetMat(matricePassata);
		drawGame.setScene(matricePassata, Bedrock, Stone);
		updateBoard();

		if(logic.isGameOver() == true) {

			if(difficoltà == 1) {
				saveScore(passaggio.getScoreDifficultyDefault(),1);
				logic.setGameOver(false);
			}else if(difficoltà == 2) {
				saveScore(passaggio.getScoreDifficultyHard(),2);
				logic.setGameOver(false);
			}else if(difficoltà == 3) {
				saveScore(passaggio.getScoreDifficultyImpossible(),3);
				logic.setGameOver(false);
			}
		}	    
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

	private void saveScore(File file,int difficoltà) {

		int scambioInt;
		String scambioString;

		try {

			if(difficoltà == 1) {

				if(passaggio.getTotalPoint() >= ClassePassaggio.coordArrDefault[0].getY()) {
					scambioInt = ClassePassaggio.coordArrDefault[0].getY();
					scambioString = ClassePassaggio.coordArrDefault[0].getName();
					ClassePassaggio.coordArrDefault[0].setY(passaggio.getTotalPoint());
					ClassePassaggio.coordArrDefault[0].setName(passaggio.getPlayerName());
					ClassePassaggio.coordArrDefault[2].setY(ClassePassaggio.coordArrDefault[1].getY());
					ClassePassaggio.coordArrDefault[2].setName(ClassePassaggio.coordArrDefault[1].getName());
					ClassePassaggio.coordArrDefault[1].setY(scambioInt);
					ClassePassaggio.coordArrDefault[1].setName(scambioString);
				}else if(passaggio.getTotalPoint() >= ClassePassaggio.coordArrDefault[1].getY()) {
					scambioInt = ClassePassaggio.coordArrDefault[1].getY();
					scambioString = ClassePassaggio.coordArrDefault[1].getName();
					ClassePassaggio.coordArrDefault[1].setY(passaggio.getTotalPoint());
					ClassePassaggio.coordArrDefault[1].setName(passaggio.getPlayerName());
					ClassePassaggio.coordArrDefault[2].setY(scambioInt);
					ClassePassaggio.coordArrDefault[2].setName(scambioString);
				}else if(passaggio.getTotalPoint() >= ClassePassaggio.coordArrDefault[2].getY()) {
					ClassePassaggio.coordArrDefault[2].setY(passaggio.getTotalPoint());
					ClassePassaggio.coordArrDefault[2].setName(passaggio.getPlayerName());
				}

				ClassePassaggio.coordNew.clear();		

				for(int i=0 ; i<ClassePassaggio.countScore ; i++)
				{
					ClassePassaggio.coordNew.add(new Coordinate(ClassePassaggio.coordArrDefault[i].getX(),ClassePassaggio.coordArrDefault[i].getY(),ClassePassaggio.coordArrDefault[i].getName()));
				}

			}else if(difficoltà == 2) {

				if(passaggio.getTotalPoint() >= ClassePassaggio.coordArrHard[0].getY()) {
					scambioInt = ClassePassaggio.coordArrHard[0].getY();
					scambioString = ClassePassaggio.coordArrHard[0].getName();
					ClassePassaggio.coordArrHard[0].setY(passaggio.getTotalPoint());
					ClassePassaggio.coordArrHard[0].setName(passaggio.getPlayerName());
					ClassePassaggio.coordArrHard[2].setY(ClassePassaggio.coordArrHard[1].getY());
					ClassePassaggio.coordArrHard[2].setName(ClassePassaggio.coordArrHard[1].getName());
					ClassePassaggio.coordArrHard[1].setY(scambioInt);
					ClassePassaggio.coordArrHard[1].setName(scambioString);
				}else if(passaggio.getTotalPoint() >= ClassePassaggio.coordArrHard[1].getY()) {
					scambioInt = ClassePassaggio.coordArrHard[1].getY();
					scambioString = ClassePassaggio.coordArrHard[1].getName();
					ClassePassaggio.coordArrHard[1].setY(passaggio.getTotalPoint());
					ClassePassaggio.coordArrHard[1].setName(passaggio.getPlayerName());
					ClassePassaggio.coordArrHard[2].setY(scambioInt);
					ClassePassaggio.coordArrHard[2].setName(scambioString);
				}else if(passaggio.getTotalPoint() >= ClassePassaggio.coordArrHard[2].getY()) {
					ClassePassaggio.coordArrHard[2].setY(passaggio.getTotalPoint());
					ClassePassaggio.coordArrHard[2].setName(passaggio.getPlayerName());
				}

				ClassePassaggio.coordNew.clear();		

				for(int i=0 ; i<ClassePassaggio.countScore ; i++)
				{
					ClassePassaggio.coordNew.add(new Coordinate(ClassePassaggio.coordArrHard[i].getX(),ClassePassaggio.coordArrHard[i].getY(),ClassePassaggio.coordArrHard[i].getName()));
				}
			}
			else if(difficoltà == 3) {

				if(passaggio.getTotalPoint() >= ClassePassaggio.coordArrImpossible[0].getY()) {
					scambioInt = ClassePassaggio.coordArrImpossible[0].getY();
					scambioString = ClassePassaggio.coordArrImpossible[0].getName();
					ClassePassaggio.coordArrImpossible[0].setY(passaggio.getTotalPoint());
					ClassePassaggio.coordArrImpossible[0].setName(passaggio.getPlayerName());
					ClassePassaggio.coordArrImpossible[2].setY(ClassePassaggio.coordArrImpossible[1].getY());
					ClassePassaggio.coordArrImpossible[2].setName(ClassePassaggio.coordArrImpossible[1].getName());
					ClassePassaggio.coordArrImpossible[1].setY(scambioInt);
					ClassePassaggio.coordArrImpossible[1].setName(scambioString);
				}else if(passaggio.getTotalPoint() >= ClassePassaggio.coordArrImpossible[1].getY()) {
					scambioInt = ClassePassaggio.coordArrImpossible[1].getY();
					scambioString = ClassePassaggio.coordArrImpossible[1].getName();
					ClassePassaggio.coordArrImpossible[1].setY(passaggio.getTotalPoint());
					ClassePassaggio.coordArrImpossible[1].setName(passaggio.getPlayerName());
					ClassePassaggio.coordArrImpossible[2].setY(scambioInt);
					ClassePassaggio.coordArrImpossible[2].setName(scambioString);
				}else if(passaggio.getTotalPoint() >= ClassePassaggio.coordArrImpossible[2].getY()) {
					ClassePassaggio.coordArrImpossible[2].setY(passaggio.getTotalPoint());
					ClassePassaggio.coordArrImpossible[2].setName(passaggio.getPlayerName());
				}

				ClassePassaggio.coordNew.clear();		

				for(int i=0 ; i<ClassePassaggio.countScore ; i++)
				{
					ClassePassaggio.coordNew.add(new Coordinate(ClassePassaggio.coordArrImpossible[i].getX(),ClassePassaggio.coordArrImpossible[i].getY(),ClassePassaggio.coordArrImpossible[i].getName()));
				}
			}

			CoordinateIO.write(file,ClassePassaggio.coordNew);
			showMessageDialog("The game's Score is saved to " + file.getName(), "Saved");

		} catch (Exception e) {
			showErrorDialog("Failed to save Score to file.");
		}
	}

	public void salvaNome() {

		JDialog dialog = new JDialog(this, "Inserisci il tuo nome", true);
		dialog.setSize(300, 150);
		dialog.setLayout(new BorderLayout());
		dialog.setLocationRelativeTo(null);

		JTextField nameField = new JTextField("Nome"){
			protected void paintComponent(Graphics g) { 

				ImageIcon back = new ImageIcon("SaveWood.jpg");
				Image wood = back.getImage();
				g.drawImage(wood, 0, 0, getWidth(), getHeight(), this);

				super.paintComponent(g);
			}
		};
		
		nameField.setOpaque(false);
		
		nameField.setHorizontalAlignment(JTextField.CENTER);
		
		Font fontGrassetto = new Font("Verdana", Font.BOLD, 30);

		nameField.setFont(fontGrassetto);

        nameField.setForeground(Color.BLACK);
        
        nameField.selectAll();
        
		JButton closeButton = new JButton("SALVA"){
			protected void paintComponent(Graphics g) { 
				super.paintComponent(g);

				ImageIcon back = new ImageIcon("SaveButton.jpg");
				Image wood = back.getImage();
				g.drawImage(wood, 0, 0, getWidth(), getHeight(), this);

			}
		};
		
		closeButton.setOpaque(false);
		
		closeButton.setPreferredSize(new Dimension(0,40));
		
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				passaggio.setPlayerName(nameField.getText()); 

				dialog.dispose(); 
			}
		});

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(nameField, BorderLayout.CENTER);
		panel.add(closeButton, BorderLayout.SOUTH);

		dialog.add(panel);

		dialog.setVisible(true);
	}
	
	public void setInt_Random(int rand)
	{
		this.int_random = rand;
	}

}
