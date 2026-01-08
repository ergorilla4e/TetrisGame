
public class Tetramini{

	private int[][] tetrominoL = {{0,1,0,0},{0,1,0,0},{0,1,1,0},{0,0,0,0}};
	private int[][] tetrominoL90 = {{0,1,1,1},{0,1,0,0},{0,0,0,0},{0,0,0,0}};
	private int[][] tetrominoL180 = {{0,1,1,0},{0,0,1,0},{0,0,1,0},{0,0,0,0}};
	private int[][] tetrominoL270 = {{0,0,1,0},{1,1,1,0},{0,0,0,0},{0,0,0,0}};

	private int[][] tetrominoJ = {{0,0,2,0},{0,0,2,0},{0,2,2,0},{0,0,0,0}};
	private int[][] tetrominoJ90 = {{0,2,0,0},{0,2,2,2},{0,0,0,0},{0,0,0,0}};
	private int[][] tetrominoJ180 = {{0,2,2,0},{0,2,0,0},{0,2,0,0},{0,0,0,0}};
	private int[][] tetrominoJ270 = {{2,2,2,0},{0,0,2,0},{0,0,0,0},{0,0,0,0}};
	
	private int[][] tetrominoO = {{0,3,3,0},{0,3,3,0},{0,0,0,0},{0,0,0,0}};
	
	private int[][] tetrominoS = {{0,4,4,0},{4,4,0,0},{0,0,0,0},{0,0,0,0}};
	private int[][] tetrominoS90 = {{0,4,0,0},{0,4,4,0},{0,0,4,0},{0,0,0,0}};

	private int[][] tetrominoT = {{5,5,5,0},{0,5,0,0},{0,0,0,0},{0,0,0,0}};
	private int[][] tetrominoT90 = {{0,0,5,0},{0,5,5,0},{0,0,5,0},{0,0,0,0}};
	private int[][] tetrominoT180 = {{0,5,0,0},{5,5,5,0},{0,0,0,0},{0,0,0,0}};
	private int[][] tetrominoT270 = {{0,5,0,0},{0,5,5,0},{0,5,0,0},{0,0,0,0}};
	
	private int[][] tetrominoZ = {{0,6,6,0},{0,0,6,6},{0,0,0,0},{0,0,0,0}};
	private int[][] tetrominoZ90 = {{0,0,6,0},{0,6,6,0},{0,6,0,0},{0,0,0,0}};
	
	private int[][] tetrominoI = {{7,7,7,7},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
	private int[][] tetrominoI90 = {{0,7,0,0},{0,7,0,0},{0,7,0,0},{0,7,0,0}};
	
	public Tetramini(){}

	public int[][] getTetrominoL() {
		return tetrominoL;
	}

	public void setTetrominoL(int[][] tetrominoL) {
		this.tetrominoL = tetrominoL;
	}

	public int[][] getTetrominoJ() {
		return tetrominoJ;
	}

	public void setTetrominoJ(int[][] tetrominoJ) {
		this.tetrominoJ = tetrominoJ;
	}

	public int[][] getTetrominoO() {
		return tetrominoO;
	}

	public void setTetrominoO(int[][] tetrominoO) {
		this.tetrominoO = tetrominoO;
	}

	public int[][] getTetrominoS() {
		return tetrominoS;
	}

	public void setTetrominoS(int[][] tetrominoS) {
		this.tetrominoS = tetrominoS;
	}

	public int[][] getTetrominoT() {
		return tetrominoT;
	}

	public void setTetrominoT(int[][] tetrominoT) {
		this.tetrominoT = tetrominoT;
	}

	public int[][] getTetrominoZ() {
		return tetrominoZ;
	}

	public void setTetrominoZ(int[][] tetrominoZ) {
		this.tetrominoZ = tetrominoZ;
	}

	public int[][] getTetrominoI() {
		return tetrominoI;
	}

	public void setTetrominoI(int[][] tetrominoI) {
		this.tetrominoI = tetrominoI;
	}

	public int[][] getTetrominoL90() {
		return tetrominoL90;
	}

	public void setTetrominoL90(int[][] tetrominoL90) {
		this.tetrominoL90 = tetrominoL90;
	}

	public int[][] getTetrominoL180() {
		return tetrominoL180;
	}

	public void setTetrominoL180(int[][] tetrominoL180) {
		this.tetrominoL180 = tetrominoL180;
	}

	public int[][] getTetrominoL270() {
		return tetrominoL270;
	}

	public void setTetrominoL270(int[][] tetrominoL270) {
		this.tetrominoL270 = tetrominoL270;
	}

	public int[][] getTetrominoJ90() {
		return tetrominoJ90;
	}

	public void setTetrominoJ90(int[][] tetrominoJ90) {
		this.tetrominoJ90 = tetrominoJ90;
	}

	public int[][] getTetrominoJ180() {
		return tetrominoJ180;
	}

	public void setTetrominoJ180(int[][] tetrominoJ180) {
		this.tetrominoJ180 = tetrominoJ180;
	}

	public int[][] getTetrominoJ270() {
		return tetrominoJ270;
	}

	public void setTetrominoJ270(int[][] tetrominoJ270) {
		this.tetrominoJ270 = tetrominoJ270;
	}

	public int[][] getTetrominoS90() {
		return tetrominoS90;
	}

	public void setTetrominoS90(int[][] tetrominoS90) {
		this.tetrominoS90 = tetrominoS90;
	}

	public int[][] getTetrominoT180() {
		return tetrominoT180;
	}

	public void setTetrominoT180(int[][] tetrominoT180) {
		this.tetrominoT180 = tetrominoT180;
	}

	public int[][] getTetrominoT90() {
		return tetrominoT90;
	}

	public void setTetrominoT90(int[][] tetrominoT90) {
		this.tetrominoT90 = tetrominoT90;
	}

	public int[][] getTetrominoT270() {
		return tetrominoT270;
	}

	public void setTetrominoT270(int[][] tetrominoT270) {
		this.tetrominoT270 = tetrominoT270;
	}

	public int[][] getTetrominoZ90() {
		return tetrominoZ90;
	}

	public void setTetrominoZ90(int[][] tetrominoZ90) {
		this.tetrominoZ90 = tetrominoZ90;
	}

	public int[][] getTetrominoI90() {
		return tetrominoI90;
	}

	public void setTetrominoI90(int[][] tetrominoI90) {
		this.tetrominoI90 = tetrominoI90;
	}

}
