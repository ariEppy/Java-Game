package View;

import java.util.ArrayList;

import Model.Board;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;

public class BoardLevel3View extends BoardView 
{
	int size;
	ArrayList<Color> colorsOfTheBoxesView;	
	public BoardLevel3View(Board b) 
	{
		super(b);
		this.size = b.getSize();
		this.colorsOfTheBoxesView = b.getColorsOfTheBoxes();
		int row,col;
		//add 100 boxes/recs to theboxesarrayview arraylist with the colors from colorsoftheboxesview arraylist
		for(col =0; col<10; col++)
		{
			for(row =0; row<10; row++)
			{
				Rectangle rec = new Rectangle((row*size)+50, (col*size)+50, size, size);
				rec.setFill(colorsOfTheBoxesView.get(row+(col*10)));
				rec.setStroke(Color.BLACK);			
				theBoxesArrayView.add(rec);
			}
		}		
	}
	//show our arraylist/board
	@Override
	public void show(Pane drawPane) 
	{
		drawPane.getChildren().clear();
		drawPane.getChildren().addAll(theBoxesArrayView);
		System.out.println("showing board3");
		
	}
	//show our score 
	@Override
	public void showScore(VBox vbox) 
	{
		vbox.getChildren().clear();
		String sc = String.valueOf(score);
		Text scoreT = new Text(sc);
		Text s = new Text("Score");
		vbox.getChildren().addAll(s,scoreT);
	}
}