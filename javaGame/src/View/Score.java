package View;

import java.util.Scanner;
import java.io.PrintWriter;
import Model.Model;

public class Score 
{
	int score;
	String username;	
	public Score(int sc, String user) 
	{
		score =sc;
		username =user;
	}
	public Score(Scanner s) 
	{
		score = s.nextInt();
		username = s.next();	
	}
	public String getUsername() 
	{
		return username;
	}
	public int getScore() 
	{
		return score;
	}
	public void writeToFile(PrintWriter pw) 
	{
		pw.printf("%d %d %d\n", username,score);
	}

}
