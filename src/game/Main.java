package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

@SuppressWarnings("serial")
public class Main extends JFrame {
	// width of the game board 
	private static final int width = 1110 ;
	// height of the game board 
	private static final int height = 700 ;
	private Container content ; 
	// everything can access the main game board 
	public static Main gameBoard ; 
	// entry point of the project 
	public static void main(String[] args) 
	{
		// initialise the variables 
		
		// set up the main board 
		gameBoard = new Main() ; 
		// make it visible 
		gameBoard.setVisible(true) ; 
		// dont allow the user to resize the window
		gameBoard.setResizable(false) ; 
	}
	
	// Constructor shit 
	private Main() 
	{
		setSize(width, height) ; 
		setTitle("Chess Game") ;
		content = getContentPane() ; 
		content.setBackground(Color.BLACK) ;
		content.setLayout(new BorderLayout()) ; 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		JSplitPane
	}
}
