package View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import Model.Board;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.FileWriter;

public abstract class BoardView 
{
	protected int width, height, amountOfBoxes, x, y, score, count = -1;
	protected Board theBoard;
	protected ArrayList<Rectangle> theBoxesArrayView;
	protected int[] indexesClicked = new int[4];
	protected int[] recIndexes = new int[4];
	protected Color[] indexesColors = new Color[4];
	private File f;
	private ArrayList<Score> highestScores;
	private TextInputDialog td;
	private String inputField;
	private Rectangle icon;
	
	public BoardView(Board b) 
	{
		theBoard = b;
		width = b.getWidth();
		height = b.getHeight();
		amountOfBoxes = b.getAmountOfBoxes();
		x = b.getX();
		y = b.getY();
		score = b.getScore();
		theBoxesArrayView = new ArrayList<Rectangle>(); 
	}	
	//add the event handler to each box in the board
	public void boxHasBeenClicked(EventHandler <MouseEvent> boxClick) 
	{
		for(int i = 0;i<amountOfBoxes;i++)
		{
			theBoxesArrayView.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, boxClick);
		}
	}
	//returns the index of the source/box clicked
	public int getBoxIndex(Object src) 
	{ 
		return theBoxesArrayView.indexOf(src);
	}
	//this updates the color of a box to darker when clicked (and if we've only clicked less than/= 4 boxes) also
	//this function returns true if we have a good rectangle or false if we dont have a good rectangle
	public Boolean updateColor(Board b, int index) 
	{				
		count++;		
		theBoard = b;			
		if(count <= 3)
		{	
			//if weve clicked less than/= 4 boxes
			indexesClicked[count] = index; 
			indexesColors[count] = theBoard.getColorsOfTheBoxes().get(index);		
			for(int i=0; i<100;i++) 
			{
				theBoxesArrayView.get(i).setFill(theBoard.getColorsOfTheBoxes().get(i));
			}
			if(count == 3)
			{
				Arrays.sort(indexesClicked);
				//if all 4 points make a good rectangle
				if(indexesColors[0].equals(indexesColors[1]) && indexesClicked[0]/10 == indexesClicked[1]/10)	
				{		
					if(indexesColors[1].equals(indexesColors[3]) && indexesClicked[1]%10 == indexesClicked[3]%10)
					{		
						if(indexesColors[3].equals(indexesColors[2]) && indexesClicked[3]/10 == indexesClicked[2]/10)
						{		
							if(indexesColors[2].equals(indexesColors[0]) && indexesClicked[2]%10 == indexesClicked[0]%10)
							{
								for(int r = 0; r<4;r++)
								{
									recIndexes[r] = indexesClicked[r];
								}
								indexesClicked = new int[4];
								indexesColors = new Color[4];
								count = -1;
								return true;
							}
						}
					}	
				}		
				//else the 4 points dont make a good rectangle
				indexesColors = new Color[4];
				//indexesClicked = new int[4];
				count = -1;
				System.out.println("not a square");
				return false;								
			}
			//less than 4 boxes are clicked
			else
			{
				System.out.println("less than 4");
				return false;
			}
		}
		//our count is now larger than 4 so start over
		else 
		{
			System.out.println("greater than 4");
			indexesColors = new Color[4];
			indexesClicked = new int[4];
			count = -1;
			return false;
		}		
	}
	//if we have a good rectangle then fill these boxes with the new colors from colorsoftheboxes and update our score here
	public void updateTheseViewBoxes(Board b) 
	{
		theBoard= b;
		int across = recIndexes[1] - recIndexes[0];
		int down = recIndexes[2]-recIndexes[0];	
		for(int j= 0; j<= down/10; j++)
		{
			for(int i = (j*10) + recIndexes[0]; i<= recIndexes[0]+across+ (j*10); i++)
			{
				theBoxesArrayView.get(i).setFill(theBoard.getColorsOfTheBoxes().get(i));
			}
		}
		score = theBoard.getScore();
		recIndexes= new int[4];
	}
	//this returns true/false if we've clicked 4 boxes
	public Boolean fourBoxesClicked() 
	{
		if(count == -1)
			return true;
		else return false;
	}
	//if we dont have a good rectangle then we call this function to restore theBoxesarrayview arraylist with the colorsoftheboxes arraylist's
	//colors
	public void putColorsBackView(Board b) 
	{
		theBoard = b;
		for(int p=0; p<4; p++)
		{			
			theBoxesArrayView.get(indexesClicked[p]).setFill(theBoard.getColorsOfTheBoxes().get(indexesClicked[p]));
		}
		indexesClicked = new int[4];
	}	
	//this function asks the user for his name and compares his score with the highest scores list and shows the new highest scores list at the end
	public void showHighestScores() 
	{	
		f = new File("HighestScores.txt");
		//if our file/High scores list doesnt exist then we create one with 0's and null values.
		if (!f.exists())
		{
		    try 
		    {
		        FileWriter myWriter = new FileWriter("HighestScores.txt");
		        myWriter.write("0 None\n0 None\n0 None\n0 None\n0 None\n0 None\n0 None\n0 None\n0 None\n0 None\n");
		        myWriter.close();
		        System.out.println("Successfully wrote to the file.");
		    }
		    catch (IOException e)
		    {
		        System.out.println("An error occurred.");
		        e.printStackTrace();
		    }
		}	
		//make a new arraylist and scan our new/existing file and place these values in the arraylist
		highestScores = new ArrayList<Score>();
	    try 
	    {
	        File myObj = new File("HighestScores.txt");
	        Scanner myReader = new Scanner(myObj);
	        while (myReader.hasNextLine()) 
	        {
	        	String data = myReader.nextLine();
	        	String[] split_data = data.split("\\s+");
	        	int data_score = Integer.parseInt(split_data[0]);
	        	String data_name = split_data[1];
	        	highestScores.add(new Score(data_score, data_name));
	        	System.out.println(data);
	        }
	        myReader.close();
	    } 
	    catch (FileNotFoundException e) 
	    {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	    }  
	    //add our current score to the arraylist too and sort the arraylist using the comparator below. Then add each score and username to a string
	    //and try to add this string to a new file highestScores.
		highestScores.add(new Score(score, inputField));		
	    highestScores.sort(new ScoreComparator());
	    int score_num = 1;
	    String highscore_text = "";
		for (Score sc : highestScores)
		{
			if (score_num > 10)
				break;
			highscore_text += sc.score + " " + sc.username + "\n";
			score_num++;
		}		
	    try 
	    {
	        FileWriter myWriter = new FileWriter("HighestScores.txt");
			myWriter.write(highscore_text);
	        myWriter.close();
	        System.out.println("Successfully wrote to the file.");
	    } 
	    catch (IOException e) 
	    {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	    }
	    //Also add this string to an alert window- showing the highest scores.
	    Alert a = new Alert(AlertType.INFORMATION);
	    a.setTitle("Highest Scores");
	    a.setHeaderText("Highest Scores");
	    a.setContentText(highscore_text);
	    icon = new Rectangle(100,100,48,48);
	    icon.setFill(Color.LIGHTSKYBLUE);
	    a.getDialogPane().setGraphic(icon);
	    a.showAndWait();
	}
	//this function makes a new dialog box for the user to enter in his username. once filled we call the function above.
	public void getTD() 
	{
		td = new TextInputDialog("");
		td.setTitle("Are you in the top scores?");
		td.setHeaderText("Enter Your Username");
		icon = new Rectangle(100,100,48,48);
	    icon.setFill(Color.PINK);
	    td.getDialogPane().setGraphic(icon);
		td.showAndWait();
		inputField = td.getEditor().getText();
		if(!(inputField == ""))
		{
			showHighestScores();
		}
	}
	//this function sets our score to zero after we end a game.
	public void setScoreToZeroView(Board b, VBox vBoxRight)
	{
		score = b.getScore();
		showScore(vBoxRight);
	}
	public abstract void show(Pane drawPane);	
	public abstract void showScore(VBox vBoxRight);
}

class ScoreComparator implements Comparator<Score> 
{
    //this overrides the compare() method
    public int compare(Score s1, Score s2)
    {
        if (s1.score == s2.score)
            return 0;
        else if (s1.score > s2.score)
            return -1;
        else
            return 1;
    }
}