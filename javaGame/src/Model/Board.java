package Model;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;

public abstract class Board 
{
	protected int row, col, width, height, x, y, size, amountOfBoxes, score, counter= 0;
	protected Color[] colors= {Color.LIGHTCORAL, Color.LIGHTSALMON, Color.LIGHTSEAGREEN, Color.LEMONCHIFFON, Color.LIGHTSKYBLUE, Color.PINK, Color.MEDIUMPURPLE};
	protected Random randNumber;
	protected ArrayList<Color> colorsOfTheBoxes;
	protected String type;
	protected int[] indexesSoFar = new int[4];	
	public Board(String s)
	{
		width = 300;
		height = 300;
		x = 50;
		y = 50;
		size = 30;
		amountOfBoxes = 100;
		type = s;
		randNumber = new Random();
		score = 0;
		//username = "Bob";	
	}
	public int getWidth() 
	{
		return width;
	}
	public int getHeight() 
	{
		return height;
	}
	public int getX() 
	{
		return x;
	}
	public int getY() 
	{
		return y;
	}
	public int getSize() 
	{
		return size;
	}
	public int getScore() 
	{
		return score;
	}
	public int getAmountOfBoxes() 
	{
		return amountOfBoxes;
	}
	public String getType() 
	{
		return type;
	}
	public ArrayList<Color> getColorsOfTheBoxes() 
	{
		return colorsOfTheBoxes;
	}
	public int[] getIndexesSoFar() 
	{
		return indexesSoFar;
	}
	public void setScoreToZero() 
	{
		score = 0;
	}	
	public abstract void addColorstoArray(int index);
	public abstract void updateColorModel(int index);
	public abstract void updateTheseModelBoxes(int[] indexes);
	public abstract void putColorsBack(int[] indexes);

}
