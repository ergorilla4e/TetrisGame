import java.io.File;
import java.util.ArrayList;

public class ClassePassaggio {

	private static int difficoltà = 1;
	private static int totalPoint;
	public static int countScore;
	
	public static File ScoreDifficultyDefault = new File("ScoreDifficultyDefault.txt");
	public static File ScoreDifficultyHard = new File("ScoreDifficultyHard.txt");
	public static File ScoreDifficultyImpossible = new File("ScoreDifficultyImpossible.txt");

	public static ArrayList<Coordinate> coordOld = new ArrayList<Coordinate>();
	public static ArrayList<Coordinate> coordNew = new ArrayList<Coordinate>();
	
	public static Coordinate[] coordArrDefault;
	public static Coordinate[] coordArrHard;
	public static Coordinate[] coordArrImpossible;

	public static String playerName;
	
	public ClassePassaggio(){}
	
	public void setPlayerName(String playerName)
	{
		ClassePassaggio.playerName = playerName;
	}
	
	public String getPlayerName()
	{
		return playerName;
	}
	
	public void setDifficoltà(int diff)
	{
		ClassePassaggio.difficoltà = diff;
	}
	
	public int getDifficoltà()
	{
		return difficoltà;
	}
	
	public File getScoreDifficultyDefault()
	{
		return ScoreDifficultyDefault;
	}
	
	public File getScoreDifficultyHard()
	{
		return ScoreDifficultyHard;
	}
	
	public File getScoreDifficultyImpossible()
	{
		return ScoreDifficultyImpossible;
	}

	public int getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(int totalPoint) {
		ClassePassaggio.totalPoint = totalPoint;
	}
	
}
