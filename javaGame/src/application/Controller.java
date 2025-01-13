package application;
import Model.Model;
import View.View;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.*;
import javafx.scene.input.*;


public class Controller 
{ 
	private View theView;
	private Model theModel;	
	public Controller(Model m, View v) 
	{
		theModel = m;
		theView = v;
		
		//this event handler is for when a box is clicked in the board. 
		EventHandler <MouseEvent> boxHasBeenClickedDo = new EventHandler<MouseEvent>() 
		{
			@Override
			public void handle(MouseEvent event) 
			{
				//we get the index of the box clicked, and change the color of the box in theModel and in theView
				//and if clicking this box makes a rectangle then we update the boxes in theModel and in theView
				//if not we return the colors of the boxes in both classes.
				int boxIndex = theView.getBoxIndex(event.getSource());
				theModel.updateColorModel(boxIndex);				
				Boolean isRectangle= theView.updateColor(theModel.getBoard(), boxIndex);
				if(isRectangle)				
				{
					theModel.updateTheseModelBoxes(theModel.getBoard().getIndexesSoFar());
					theView.updateTheseViewBoxes(theModel.getBoard());				
				}
				else 
				{
					if(theView.fourBoxesClicked()) 
					{
						theModel.putColorsBack(theModel.getBoard().getIndexesSoFar());
						theView.putColorsBackView(theModel.getBoard());
					}		
				}	
			}
		};
		//add the event handler to our boxes in the board.
		theView.boxHasBeenClicked(boxHasBeenClickedDo);

		//The event handler for pressing the new Game button. The level buttons are visible and the new game button disabled.
		EventHandler <MouseEvent> newGameClicked = new EventHandler<MouseEvent>() 
		{
			@Override
			public void handle(MouseEvent event) 
			{
				theView.emptyBoard();
				theView.addButtons();
				theView.disableButtons(false,false,false);
				theView.disableNewGame();
				theView.ableEndGame();
				theModel.setScoreToZero();
				theView.setScoreToZeroView(theModel.getBoard());
			}
		};
		//add the new game event handler to our new game button.
		theView.gameButtonClicked(newGameClicked);
		
		//the event handler for pressing level 1. Disable the appropriate buttons, add this level board to the scene, and
		//add the box clicking event handler to our boxes/board here.
		EventHandler <MouseEvent> clickLevel1 = new EventHandler<MouseEvent>() 
		{
			@Override
			public void handle(MouseEvent event) 
			{
				theView.ableBoard();
				theView.disableButtons(true,true,true);
				theModel.addBoard("level1");
				theView.addBoardLevelToScene(theModel.getBoard());
				theView.boxHasBeenClicked(boxHasBeenClickedDo);
				System.out.println("level 1 clicked");			
			}
		};	
		//add the level 1 event handler to our level 1 button.
		theView.level1Clicked(clickLevel1);
		
		//the event handler for pressing level 2. Disable the appropriate buttons, add this level board to the scene, and
		//add the box clicking event handler to our boxes/board here.
		EventHandler <MouseEvent> clickLevel2 = new EventHandler<MouseEvent>() 
		{
			@Override
			public void handle(MouseEvent event) 
			{
				theView.ableBoard();
				theView.disableButtons(true,true,true);
				theModel.addBoard("level2");
				theView.addBoardLevelToScene(theModel.getBoard());
				theView.boxHasBeenClicked(boxHasBeenClickedDo);
				System.out.println("level 2 clicked");	
			}
		};
		
		//add the level 2 event handler to our level 2 button.
		theView.level2Clicked(clickLevel2);
	
		//the event handler for pressing level 3. Disable the appropriate buttons, add this level board to the scene, and
		//add the box clicking event handler to our boxes/board here.
		EventHandler <MouseEvent> clickLevel3 = new EventHandler<MouseEvent>() 
		{
			@Override
			public void handle(MouseEvent event) 
			{
				theView.ableBoard();
				theView.disableButtons(true,true,true);
				theModel.addBoard("level3");
				theView.addBoardLevelToScene(theModel.getBoard());
				theView.boxHasBeenClicked(boxHasBeenClickedDo);
				System.out.println("level 3 clicked");			
			}
		};
		
		//add the level 3 event handler to our level 3 button.
		theView.level3Clicked(clickLevel3);
		
		//the event handler for pressing end game. disable the appropriate buttons/board and
		//call the dialog function (to show the td dialog and high scores).
		EventHandler <MouseEvent> endGameClicked = new EventHandler<MouseEvent>() 
		{
			@Override
			public void handle(MouseEvent event) 
			{
				theView.hideButtons();
				theView.ableNewGame();
				System.out.println("end game clicked");
				theView.getTD();
				theView.disableEndGame();
				theView.disableBoard();			
			}
		};
		//add the end game event handler to our end game button
		theView.endGameButtonClicked(endGameClicked);
	}
}

