import java.awt.Image;

public class SetDrawGame{

	private BoardPanel board;
	
	private int row, col;
	private int[][] matrice;
	
	public SetDrawGame(int row,int col, int[][] matrice, BoardPanel board)
	{
		this.row = row;
		this.col = col;
		this.board = board;
		this.matrice = matrice;
	}
	
	public void resetMat(int[][] matrice) 
	{
		for(int i=0 ; i<board.getRows() ; i++) 
		{
			for(int j=0 ; j<board.getColumns() ; j++) 
			{
				matrice[i][j] = 0;

				if ((i <= board.getRows() - 1 && j == 0) || (i <= board.getRows() - 1 && j == board.getColumns() - 1) || (i >= board.getRows() - 1 && j <= board.getColumns() - 1)) 
				{					
					matrice[i][j] = -1; 
				}
			}	
		}
	}
	
	public void disMatrice(int[][] matrice, int row, int col, 
			Image Stone, Image Iron, Image Lapis, Image Gold,
			Image Emerald, Image Charcoal, Image Redstone, Image Diamond)
	{
		for(int i=0;i<row-1;i++) 
		{
			for(int j=1;j<col-1;j++) 
			{
				switch(matrice[i][j]) 
				{
				case 0:
					board.drawImage(i, j, Stone);
					break;
				case 1:
					board.drawImage(i, j, Iron);
					break;
				case 2:
					board.drawImage(i, j, Lapis);
					break;
				case 3:
					board.drawImage(i, j, Gold);
					break;
				case 4:
					board.drawImage(i, j, Emerald);
					break;
				case 5:
					board.drawImage(i, j, Charcoal);
					break;
				case 6:
					board.drawImage(i, j, Redstone);
					break;
				case 7:
					board.drawImage(i, j, Diamond);
					break;
				}
			}
		}
	}
	
	public void setScene(int[][] matrice, Image Bedrock, Image Stone) {
		
		for(int i=0 ; i<board.getRows() ; i++) {
			for(int j=0 ; j<board.getColumns() ; j++) {

				if ((i <= board.getRows() - 1 && j == 0) || (i <= board.getRows() - 1 && j == board.getColumns() - 1) || (i >= board.getRows() - 1 && j <= board.getColumns() - 1)) 
					board.drawImage(i, j, Bedrock);

				if(matrice[i][j]==0) 
					board.drawImage(i, j, Stone);
			}
		}
	}
}

