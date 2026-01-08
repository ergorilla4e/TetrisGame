import java.util.Random;

public class LogicMovements {

	private int row, col, x, y, k;
	private boolean fall, GameOver = false, rigaCompleta = false;

	private boolean Move = false;
	private int numeroRotazioni = 0;
	private boolean puòRuotare = true;

	public int[][] matricePassata;
	public int[][] matriceTmp;
	public int[][] matriceTetramino = new int[4][4];

	private Random rand = new Random();
	int int_random , upperBound = 7;

	private TetrisFrame frame;
	public Tetramini tetramino;
	
	private ClassePassaggio passaggio;
	
	protected Music music2;
	private final String song2 = "./LvUp.wav";
	private int Start2 , End2;

	
	public LogicMovements(TetrisFrame frame, Tetramini tetramino,int[][] matriceTmp, int[][] matricePassata, int row, int col, int x, int y)
	{		
		passaggio = new ClassePassaggio();
		music2 = new Music(song2);

		this.frame = frame;
		this.matriceTmp = matriceTmp;
		this.matricePassata = matricePassata;
		this.row = row;
		this.col = col;
		this.x = x;
		this.y = y;
		this.tetramino = tetramino;

		this.fall = false;
	}

	public void play(int [][] matricePassata, int row, int col){

		this.matricePassata = matricePassata;

		copiaMatrice(matricePassata,matriceTmp,row,col);

		if(fall == true) {

			avanzaTetromino(row,col);

		} else if(fall == false){

			rigaCompleta=true;

			while(rigaCompleta == true) {
				rigaCompleta = controlloRigaCompleta();

				copiaMatrice(matriceTmp, matricePassata, row, col);
				
			}
			
			setGameOver(controlloGameOver());

			matriceTetramino = sceltaTetromino();
			inserTetromino();

			numeroRotazioni = 0;
			fall = true;
		}
	}			

	private boolean controlloGameOver() {

		for(int j=1 ; j<col-1 ; j++) {

			if(matricePassata[0][j]!=0) {
				return GameOver = true;
			}
		}
		return GameOver = false;
	}

	public boolean controlloRigaCompleta() {

		int count = 0;
		boolean rigaCompleta = false;
		
		for(int i=row-2 ; i>0 ; i--) { 	
			for(int j=1 ; j<col-1 ; j++) {

				if(matriceTmp[i][j]!=0) {
					count++;

					if(count == col-2) {
						count = 0; 	
						rigaCompleta = true;

						Start2 = 1 * 44100;
						End2 = 3 * 44100;
						music2.playEffects(Start2, End2, rigaCompleta);
						rigaCompleta = false;
						music2.playEffects(Start2, End2, rigaCompleta);

						for(int k=i ; k>0 ; k--) {
							for(int h = 1 ; h<col-1 ; h++) {
								matriceTmp[k][h] = matriceTmp[k-1][h];
							}
						}
						passaggio.setTotalPoint(passaggio.getTotalPoint() + 100);
						return true;
					}	
				}
			}
			count = 0; 	
		}
		return false;
	}

	public void inserTetromino() {

		x = (col/2)-2; 	
		y = 0;
		k=0;

		for(int i=0 ; i<4 ; i++) {  
			for(k=0 ; k<4 ; k++) {

				if(matriceTetramino[i][k]!=0 && matriceTetramino[i][k]!=-1) {
					matricePassata[i+y][k+x] = matriceTetramino[i][k];		
				}
			}
		}
	}

	public void ristampaTetromino() {

		for(int i=0 ; i<4 ; i++) {  
			for(int j=0 ; j<4 ; j++) {

				if(matriceTetramino[i][j]!=0) {
					if(matriceTetramino[i][j]!=0) {
						matriceTmp[i+y][j+x] = matriceTetramino[i][j];		
					}
				}
			}
		}
	}

	public void cancellaVecchioTetromino(boolean direzione) {

		for(int i=3 ; i>=0 ; i--) {
			for(int j=0 ; j<4 ; j++) {	

				if(matriceTetramino[i][j]!=0) {

					if(direzione == true || (i+1<4 && matriceTetramino[i][j]!=0 && matriceTmp[y+i][x+j]!=0)) {
						matriceTmp[y+i][x+j] = 0;
					}
					if(direzione == false || (i+1<4 && matriceTetramino[i][j]!=0 && matriceTmp[y+i][x+j]!=0)) {
						matriceTmp[y+i][x+j] = 0;
					}
				}
			}
		}
		copiaMatrice(matriceTmp, matricePassata, row, col);
	}

	public void avanzaTetromino(int row, int col) {

		y++;

		for(int i=3 ; i>=0 ; i--) {
			for(int j=0 ; j<4 ; j++) {	


				if(i+y<row && matriceTetramino[i][j] !=0 ) {

					if(i+1<4 && matriceTetramino[i+1][j]!=0) {

					}else if(matriceTmp[y+i][x+j]!=0) {
						fall = false;
						return;
					}
					matriceTmp[y+i-1][x+j] = 0;
					matriceTmp[y+i][x+j] = matriceTetramino[i][j];	
				}
			}
		}	
		copiaMatrice(matriceTmp, matricePassata, row, col);
	}

	public void MoveRight() {

		Move = true;

		for(int i=0 ; i<4 ; i++) { 			
			for(int j=0 ; j<4 ; j++) {
				
				if(matriceTetramino[i][j]!=0) {
					
				if(j+1 < 4 && matriceTetramino[i][j+1]!=0) {
					continue;
				}else if(x+j<=col-1 && matriceTmp[y+i][x+j+1]!=0) {
					Move = false;
				}					
			}
		}
	}
		if(x+k <= col && Move == true) {
			cancellaVecchioTetromino(true);
			x=x+1;
			ristampaTetromino();
			copiaMatrice(matriceTmp, matricePassata, row, col);
		}
	}
	

	public void MoveLeft() {

		Move = true;

		for(int i=0 ; i<4 ; i++) { 			
			for(int j=0 ; j<4 ; j++) {

				if(matriceTetramino[i][j]!=0) {

					if(j-1 >= 0 && matriceTetramino[i][j-1]!=0) {
						continue;
					}else if( x + j >=1 && matriceTmp[i+y][x+j-1]!=0) {
						Move = false;
					}
				}
			}
		}
		
		if( x > 0 && Move == true) {
			cancellaVecchioTetromino(true);
			x = x - 1;;
			ristampaTetromino();
			copiaMatrice(matriceTmp, matricePassata, row, col);
		}
	}

	public void Drop() {

		while( isFall() )
		{ 
			avanzaTetromino(row,col);
			passaggio.setTotalPoint(passaggio.getTotalPoint()+1);
		}
	}

	public void MoveDown() {

		if( fall == true ) 
		avanzaTetromino(row,col);
		
		passaggio.setTotalPoint(passaggio.getTotalPoint()+1);
	}

	public boolean checkRotazione() {

		for(int i=0 ; i<4 ; i++) {
			for(int j=0 ; j<4 ;j++){

				if(matriceTmp[i+y][j+x]!=matriceTetramino[i][j]) {
					return puòRuotare = false; 
				}	
			}
		}
		return puòRuotare = true; 
	}

	public void MoveRotate() {

		switch(int_random) {

		case 1: //Esterno
			switch(numeroRotazioni) {

			case 0:	
				cancellaVecchioTetromino(true);
				for(int i=0  ; i<4 ; i++) {
					for(int j=0 ; j<4 ; j++) {
						matriceTetramino[i][j] = tetramino.getTetrominoL()[i][j];
					}
				}								
				ristampaTetromino();
				break;

			case 1:
				cancellaVecchioTetromino(true);	
				for(int i=0  ; i<4 ; i++) {
					for(int j=0 ; j<4 ; j++) {
						matriceTetramino[i][j] = tetramino.getTetrominoL90()[i][j];
					}
				}
				ristampaTetromino();
				break;

			case 2:
				cancellaVecchioTetromino(true);
				for(int i=0  ; i<4 ; i++) {
					for(int j=0 ; j<4 ; j++) {
						matriceTetramino[i][j] = tetramino.getTetrominoL180()[i][j];
					}
				}
				ristampaTetromino();
				break;

			case 3:
				cancellaVecchioTetromino(true);
				for(int i=0  ; i<4 ; i++) {
					for(int j=0 ; j<4 ; j++) {
						matriceTetramino[i][j] = tetramino.getTetrominoL270()[i][j];
					}
				}								
				ristampaTetromino();

				numeroRotazioni=-1;
				break;
			}	

			break;

		case 2: //Esterno
			switch(numeroRotazioni) {

			case 0:	
				cancellaVecchioTetromino(true);
				for(int i=0  ; i<4 ; i++) {
					for(int j=0 ; j<4 ; j++) {
						matriceTetramino[i][j] = tetramino.getTetrominoJ()[i][j];
					}
				}								
				ristampaTetromino();
				break;

			case 1:
				cancellaVecchioTetromino(true);	
				for(int i=0  ; i<4 ; i++) {
					for(int j=0 ; j<4 ; j++) {
						matriceTetramino[i][j] = tetramino.getTetrominoJ90()[i][j];
					}
				}
				ristampaTetromino();
				break;

			case 2:
				cancellaVecchioTetromino(true);
				for(int i=0  ; i<4 ; i++) {
					for(int j=0 ; j<4 ; j++) {
						matriceTetramino[i][j] = tetramino.getTetrominoJ180()[i][j];
					}
				}
				ristampaTetromino();
				break;

			case 3:
				cancellaVecchioTetromino(true);
				for(int i=0  ; i<4 ; i++) {
					for(int j=0 ; j<4 ; j++) {
						matriceTetramino[i][j] = tetramino.getTetrominoJ270()[i][j];
					}
				}								
				ristampaTetromino();

				numeroRotazioni=-1;
				break;
			}	

			break;

		case 3: //il quadrato non ha rotazioni
			break;

		case 4://Esterno
			switch(numeroRotazioni) {

			case 0:
				cancellaVecchioTetromino(true);
				for(int i=0  ; i<4 ; i++) {
					for(int j=0 ; j<4 ; j++) {
						matriceTetramino[i][j] = tetramino.getTetrominoI()[i][j];
					}
				}
				ristampaTetromino();
				break;

			case 1:
				cancellaVecchioTetromino(true);
				for(int i=0  ; i<4 ; i++) {
					for(int j=0 ; j<4 ; j++) {
						matriceTetramino[i][j] = tetramino.getTetrominoI90()[i][j];
					}
				}								
				ristampaTetromino();

				numeroRotazioni=-1;
				break;
			}	

			break;

		case 5://Esterno
			switch(numeroRotazioni) {

			case 0:
				cancellaVecchioTetromino(true);
				for(int i=0  ; i<4 ; i++) {
					for(int j=0 ; j<4 ; j++) {
						matriceTetramino[i][j] = tetramino.getTetrominoS()[i][j];
					}
				}
				ristampaTetromino();
				break;

			case 1:
				cancellaVecchioTetromino(true);
				for(int i=0  ; i<4 ; i++) {
					for(int j=0 ; j<4 ; j++) {
						matriceTetramino[i][j] = tetramino.getTetrominoS90()[i][j];
					}
				}								
				ristampaTetromino();

				numeroRotazioni=-1;
				break;
			}	

			break;

		case 6://Esterno
			switch(numeroRotazioni) {

			case 0:
				cancellaVecchioTetromino(true);
				for(int i=0  ; i<4 ; i++) {
					for(int j=0 ; j<4 ; j++) {
						matriceTetramino[i][j] = tetramino.getTetrominoZ()[i][j];
					}
				}
				ristampaTetromino();
				break;

			case 1:
				cancellaVecchioTetromino(true);
				for(int i=0  ; i<4 ; i++) {
					for(int j=0 ; j<4 ; j++) {
						matriceTetramino[i][j] = tetramino.getTetrominoZ90()[i][j];
					}
				}								
				ristampaTetromino();

				numeroRotazioni=-1;
				break;
			}	

			break;

		case 7://Esterno
			switch(numeroRotazioni) {

			case 0:	
				cancellaVecchioTetromino(true);
				for(int i=0  ; i<4 ; i++) {
					for(int j=0 ; j<4 ; j++) {
						matriceTetramino[i][j] = tetramino.getTetrominoT()[i][j];
					}
				}								
				ristampaTetromino();
				break;

			case 1:
				cancellaVecchioTetromino(true);	
				for(int i=0  ; i<4 ; i++) {
					for(int j=0 ; j<4 ; j++) {
						matriceTetramino[i][j] = tetramino.getTetrominoT90()[i][j];
					}
				}
				ristampaTetromino();
				break;

			case 2:
				cancellaVecchioTetromino(true);
				for(int i=0  ; i<4 ; i++) {
					for(int j=0 ; j<4 ; j++) {
						matriceTetramino[i][j] = tetramino.getTetrominoT180()[i][j];
					}
				}
				ristampaTetromino();
				break;

			case 3:
				cancellaVecchioTetromino(true);
				for(int i=0  ; i<4 ; i++) {
					for(int j=0 ; j<4 ; j++) {
						matriceTetramino[i][j] = tetramino.getTetrominoT270()[i][j];
					}
				}								
				ristampaTetromino();

				numeroRotazioni=-1;
				break;
			}
			break;
		}
		copiaMatrice(matriceTmp, matricePassata, row, col);
	}

	public int[][] sceltaTetromino() {

		int_random = 1 + rand.nextInt(upperBound);
		frame.setInt_Random(int_random);

		switch(int_random) {

		case 1:

			for(int i=0  ; i<4 ; i++) {
				for(int j=0 ; j<4 ; j++) {
					matriceTetramino[i][j] = tetramino.getTetrominoL()[i][j];
				}
			}
			break;

		case 2:

			for(int i=0  ; i<4 ; i++) {
				for(int j=0 ; j<4 ; j++) {
					matriceTetramino[i][j] = tetramino.getTetrominoJ()[i][j];
				}
			}
			break;

		case 3:

			for(int i=0  ; i<4 ; i++) {
				for(int j=0 ; j<4 ; j++) {
					matriceTetramino[i][j] = tetramino.getTetrominoO()[i][j];
				}
			}
			break;

		case 4:

			for(int i=0  ; i<4 ; i++) {
				for(int j=0 ; j<4 ; j++) {
					matriceTetramino[i][j] = tetramino.getTetrominoI()[i][j];
				}
			}
			break;

		case 5:

			for(int i=0  ; i<4 ; i++) {
				for(int j=0 ; j<4 ; j++) {
					matriceTetramino[i][j] = tetramino.getTetrominoS()[i][j];
				}
			}
			break;

		case 6:

			for(int i=0  ; i<4 ; i++) {
				for(int j=0 ; j<4 ; j++) {
					matriceTetramino[i][j] = tetramino.getTetrominoZ()[i][j];
				}
			}
			break;

		case 7:

			for(int i=0  ; i<4 ; i++) {
				for(int j=0 ; j<4 ; j++) {
					matriceTetramino[i][j] = tetramino.getTetrominoT()[i][j];
				}
			}
			break;
		}	
		return matriceTetramino;
	}

	void copiaMatrice(int[][] matriceIN,int[][] matriceOUT ,int row, int col){

		int i=0, j=0;

		for(i=0 ; i<row ; i++) {
			for(j=0 ; j<col ; j++) {
				matriceOUT[i][j] = matriceIN[i][j];

			}
		}
	}

	public int getX(){
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY(){
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getK(){
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}

	public int getInt_Random(){
		return int_random;
	}

	public boolean isGameOver() {
		return GameOver;
	}

	public void setGameOver(boolean GameOver) 
	{
		this.GameOver = GameOver;
	}

	public boolean isFall() {
		return fall;
	}

	public void setFall(boolean fall) 
	{
		this.fall = fall;
	}

	public int[][] getMatriceTetramino() {
		return matriceTetramino;
	}

	public void setInt_Random(int int_random)
	{
		this.int_random = int_random;
	}

	public int getNumeroRotazioni(){
		return numeroRotazioni;
	}

	public void setNumeroRotazioni(int numeroRotazioni) {
		this.numeroRotazioni = numeroRotazioni;
	}

	public boolean isPuòRuotare() {
		return puòRuotare;
	}

	public boolean setPuòRuotare(boolean puòRuotare) {
		this.puòRuotare = puòRuotare;
		return puòRuotare;
	}
}


