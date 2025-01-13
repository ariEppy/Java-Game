package Model;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardLevel3 extends Board
{
	protected int maxColor = 7;
	protected Color boxColor;	
	public BoardLevel3() 
	{
		super("level3");
		addColorstoArray(maxColor);	
	}
	//choose randomly a color from 1-7 from the colors array and add it to the arraylist colorsoftheboxes
	@Override
	public void addColorstoArray(int maxCol)
	{
		colorsOfTheBoxes = new ArrayList<Color>();
		for(int i= 0; i<10; i++)
		{
			for(int j= 0; j <10; j++)
			{	
				boxColor = colors[randNumber.nextInt(maxCol)];
				colorsOfTheBoxes.add(boxColor);
			}
		}
	}
	//if we've clicked less than/= 4 boxes on the board then we darken the box's color in our colors arraylist.
	//If we've clicked more than 4 boxes then we renew our indexes array and call this function again.	
	@Override
	public void updateColorModel(int index) 
	{
		if(counter<=3)
		{
			colorsOfTheBoxes.set(index, colorsOfTheBoxes.get(index).darker());
			indexesSoFar[counter]=index;
			counter++;
		}
		else
		{
			indexesSoFar = new int[4];
			counter=0;
			updateColorModel(index);
		}
	}
	//if we have a good rectangle then we change the colors in between these indexes in the "colorsofthearray" arraylist
	//and update our score here.
	@Override
	public void updateTheseModelBoxes(int[] indexes) 
	{
		Arrays.sort(indexes);
		int across = indexes[1]-indexes[0];
		int down = indexes[2]-indexes[0];
		for(int j= 0; j<= down/10; j++)
		{
			for(int i = (j*10) + indexes[0]; i<= indexes[0]+across+ (j*10); i++)
			{
				boxColor = colors[randNumber.nextInt(maxColor)];			
				colorsOfTheBoxes.set(i, boxColor);
			}
		}
		updateScoreModel(across+1,(down/10)+1);
	}
	//if our rectangle is not a good rectangle then we brighten these box indexes in the coloroftheboxes arraylist
	@Override
	public void putColorsBack(int[] indexes) 
	{
		for(int p=0; p<4; p++)
		{
			colorsOfTheBoxes.set(indexes[p], colorsOfTheBoxes.get(indexes[p]).brighter());
		}
	}
	//update the score variable with our (rec length across* rec length down)
	public void updateScoreModel(int i, int j) 
	{
		score += i*j;
	}
}
