package Model;

import java.util.ArrayList;

public class Model 
{
	private Board theBoard;	
	public Model() 
	{
		addBoard("level1");
	}
	public void addBoard(String type) 
	{
		switch(type) 
		{
		case "level1":
			theBoard = new BoardLevel1();
			break;
		case "level2":
			theBoard = new BoardLevel2();
			break;
		case "level3":
			theBoard = new BoardLevel3();
			break;		
		}
	}
	public Board getBoard() 
	{
		return theBoard;
	}
	public void updateColorModel(int index) 
	{
		theBoard.updateColorModel(index);
	}
	public void updateTheseModelBoxes(int[] indexes) 
	{
		theBoard.updateTheseModelBoxes(indexes);
	}
	public void putColorsBack(int[] indexes) 
	{
		theBoard.putColorsBack(indexes);
	}
	public void setScoreToZero() 
	{
		theBoard.setScoreToZero();
	}
	
}
