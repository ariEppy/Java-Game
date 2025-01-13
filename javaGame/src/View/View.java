package View;
import javafx.stage.Stage;


import java.util.ArrayList;
import Model.Board;
import Model.BoardLevel1;
import Model.BoardLevel2;
import Model.BoardLevel3;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;


public class View {
	
	private Stage primaryStage;
	private Scene scene;
	private BorderPane bp;
	private Button newGameButton;
	private Button level1Button;
	private Button level2Button;
	private Button level3Button;
	private Button endGameButton;
	private Pane centerPane;
	private VBox VBoxLeft, VBoxRight;
	private HBox topBox;
	protected ArrayList<Rectangle> allTheBoxes;
	private BoardView theBoardView;
	private Rectangle board;
	
	public View(Stage stage) 
	{	
		primaryStage = stage;
		theBoardView = new BoardLevel1View(new BoardLevel1());
		
		//setting up the scene
		newGameButton = new Button("New Game");			
		newGameButton.setPadding(new Insets(5,20,5,20)); 
			
		level1Button = new Button("level 1");
		level1Button.setPadding(new Insets(5,20,5,20));
		level1Button.setVisible(false);
		level1Button.setManaged(false);
		
		level2Button = new Button("level 2");
		level2Button.setPadding(new Insets(5,20,5,20));
		level2Button.setVisible(false);
		level2Button.setManaged(false);
		
		level3Button = new Button("level 3");
		level3Button.setPadding(new Insets(5,20,5,20));
		level3Button.setVisible(false);
		level3Button.setManaged(false);
	
		endGameButton = new Button("End Game");
		endGameButton.setPadding(new Insets(5,20,5,20));
				
		VBoxLeft = new VBox();
		VBoxLeft.getChildren().addAll(newGameButton, level1Button, level2Button, level3Button, endGameButton);
		VBoxLeft.setAlignment(Pos.CENTER_LEFT);
		
		Text scoreLabel = new Text("Score");
		VBoxRight = new VBox();
		VBoxRight.setPadding(new Insets(5,20,5,0));
		VBoxRight.getChildren().addAll(scoreLabel);
		VBoxRight.setAlignment(Pos.TOP_LEFT);
		
		Text gameTitle = new Text("Colors Game");
		topBox = new HBox();
		topBox.setPadding(new Insets(15,0,15,0));
		topBox.getChildren().add(gameTitle);
		topBox.setAlignment(Pos.CENTER);
			
		centerPane = new Pane();
		bp = new BorderPane();
		board = new Rectangle(50,50,300,300);
		board.setFill(Color.LAVENDER);
		board.setStroke(Color.BLACK);
		centerPane.getChildren().add(board);		
		
		bp.setLeft(VBoxLeft);
		bp.setRight(VBoxRight);
		bp.setTop(topBox);
		bp.setCenter(centerPane);		
	
		scene = new Scene(bp,600,500);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	//add all the event handlers to the buttons/board
	public void gameButtonClicked(EventHandler <MouseEvent> gameClick) 
	{
		newGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, gameClick);	
	}
	public void endGameButtonClicked(EventHandler <MouseEvent> endGameClick) 
	{
		endGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, endGameClick);	
	}
	public void level1Clicked(EventHandler <MouseEvent> level1Click) 
	{
		level1Button.addEventHandler(MouseEvent.MOUSE_CLICKED, level1Click);
	}
	public void level2Clicked(EventHandler <MouseEvent> level2Click)
	{
		level2Button.addEventHandler(MouseEvent.MOUSE_CLICKED, level2Click);
	}
	public void level3Clicked(EventHandler <MouseEvent> level3Click) 
	{
		level3Button.addEventHandler(MouseEvent.MOUSE_CLICKED, level3Click);
	}
	public void boxHasBeenClicked(EventHandler <MouseEvent> boxClick) 
	{
		theBoardView.boxHasBeenClicked(boxClick);
	}
	//get the index of the box clicked
	public int getBoxIndex(Object src) 
	{
		return theBoardView.getBoxIndex(src);
	}
	//make a new board and add it to the view
	public void addBoardLevelToScene(Board b) 
	{
		if(b instanceof BoardLevel1)
		{
			theBoardView = new BoardLevel1View(b);
		}
		else if(b instanceof BoardLevel2)
		{
			theBoardView = new BoardLevel2View(b);
		}
		else 
		{
			theBoardView = new BoardLevel3View(b);
		}		
		theBoardView.show(centerPane);
	}
	//empty the board and add the empty purple board
	public void emptyBoard() 
	{
		centerPane.getChildren().clear();
		centerPane.getChildren().addAll(board);
	}
	//disable and make able all these buttons/board
	public void disableNewGame() 
	{
		newGameButton.setDisable(true);
	}
	public void ableNewGame() 
	{
		newGameButton.setDisable(false);
	}
	public void disableEndGame() 
	{
		endGameButton.setDisable(true);
	}
	public void ableEndGame() 
	{
		endGameButton.setDisable(false);
	}
	public void disableBoard() 
	{
		centerPane.setDisable(true);
	}
	public void ableBoard() 
	{
		centerPane.setDisable(false);
	}
	//show/hide level buttons 
	public void addButtons() 
	{
		level1Button.setVisible(true);
		level1Button.setManaged(true);
		level2Button.setVisible(true);
		level2Button.setManaged(true);
		level3Button.setVisible(true);
		level3Button.setManaged(true);		
	}
	public void hideButtons() 
	{
		level1Button.setVisible(false);
		level1Button.setManaged(false);
		level2Button.setVisible(false);
		level2Button.setManaged(false);
		level3Button.setVisible(false);
		level3Button.setManaged(false);		
	}
	public void disableButtons(Boolean a, Boolean b, Boolean c) 
	{
		level1Button.setDisable(b);
		level2Button.setDisable(b);
		level3Button.setDisable(c);
	}
	//all these functions call functions in boardView
	public Boolean updateColor(Board b, int index) 
	{ 
		return theBoardView.updateColor(b, index);
	}
	public void updateTheseViewBoxes(Board b) 
	{
		theBoardView.updateTheseViewBoxes(b);
		theBoardView.show(centerPane);
		theBoardView.showScore(VBoxRight);
	}
	public Boolean fourBoxesClicked() 
	{
		return theBoardView.fourBoxesClicked();
	}
	public void putColorsBackView(Board b) 
	{
		theBoardView.putColorsBackView(b);
		theBoardView.show(centerPane);
	}
	public void showHighestScores() 
	{
		theBoardView.showHighestScores();	
	}
	public void getTD()
	{
		theBoardView.getTD();
	}
	public void setScoreToZeroView(Board b)
	{
		theBoardView.setScoreToZeroView(b, VBoxRight);
	}
}

	
	
	
	

