package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.PieceColor;
import pieces.Queen;
import pieces.Rook;

@SuppressWarnings("serial")
public class Main extends JFrame {
	// width of the game board 
	private static final int width = 1110 ;
	// height of the game board 
	private static final int height = 700 ;
	private Container content ; 
	// everything can access the main game board 
	public static Main gameBoard ;
	private static Rook white_rook1 , white_rook2 ; 
	private static Rook black_rook1 , black_rook2 ;
	private static Bishop white_bishop1 , white_bishop2 ; 
	private static Bishop black_bishop1 , black_bishop2 ; 
	private static Knight white_knight1 , white_knight2 ; 
	private static Knight black_knight1 , black_knight2 ;
	private static Queen white_queen , black_queen ;
	private static King white_king , black_king ;
	private static Pawn white_pawns[] , black_pawns[] ; 
	
	private Cell grid[][] ; 
	// entry point of the project 
	public static void main(String[] args) 
	{
		// initialise the variables 
		white_rook1 = new Rook("white_rook1", "White_Rook.png", PieceColor.WHITE) ; 
		white_rook2 = new Rook("white_rook2", "White_Rook.png", PieceColor.WHITE) ; 
		black_rook1 = new Rook("black_rook1", "Black_Rook.png", PieceColor.BLACK) ; 
		black_rook2 = new Rook("black_rook2", "Black_Rook.png", PieceColor.BLACK) ;
		
		white_bishop1 = new Bishop("white_bishop1", "White_Bishop.png", PieceColor.WHITE) ; 
		white_bishop2 = new Bishop("white_bishop2", "White_Bishop.png", PieceColor.WHITE) ;
		black_bishop1 = new Bishop("black_bishop1", "Black_Bishop.png", PieceColor.BLACK) ;
		black_bishop2 = new Bishop("black_bishop2", "Black_Bishop.png", PieceColor.BLACK) ;
		
		white_knight1 = new Knight("white_knight1", "White_Knight.png", PieceColor.WHITE) ; 
		white_knight2 = new Knight("white_knight2", "White_Knight.png", PieceColor.WHITE) ;
		black_knight1 = new Knight("black_knight1", "Black_Knight.png", PieceColor.BLACK) ;
		black_knight2 = new Knight("black_knight2", "Black_Knight.png", PieceColor.BLACK) ;
		
		white_queen = new Queen("white_queen", "White_Queen.png", PieceColor.WHITE) ;
		black_queen = new Queen("black_queen", "Black_Queen.png", PieceColor.BLACK) ;
		
		white_king = new King("white_king", "White_King.png", PieceColor.WHITE,7,3) ;
		black_king = new King("black_king", "Black_King.png", PieceColor.BLACK,0,3) ;
		
		white_pawns = new Pawn[8] ; 
		black_pawns = new Pawn[8] ;
		
		for(int i = 0 ; i < 8 ; ++i) {
			white_pawns[i] = new Pawn("white_pawn" + (i + 1), "White_Pawn.png", PieceColor.WHITE) ; 
			black_pawns[i] = new Pawn("black_pawn" + (i + 1), "Black_Pawn.png", PieceColor.BLACK) ; 
		}
		
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
		grid = new Cell[8][8] ; 
		for(int i = 0 ; i < 8 ; ++i) {
			for(int j = 0 ; j < 8 ; ++j) {
				pieces.Piece temp = null ;
				if(i == 0 && j == 0) {
					temp = black_rook1 ; 
				}
				else if(i == 0 && j == 7) {
					temp = black_rook2 ; 
				}
				else if(i == 0 && j == 1) {
					temp = black_knight1 ;
				}
				else if(i == 0 && j == 6) {
					temp = black_knight2 ;
				}
				else if(i == 0 && j == 2) {
					temp = black_bishop1 ;
				}
				else if(i == 0 && j == 5) {
					temp = black_bishop2 ;
				}
				else if(i == 0 && j == 3) {
					temp = black_king ;
				}
				else if(i == 0 && j == 4) {
					temp = black_queen ;
				}
				else if(i == 1) {
					temp = black_pawns[j] ; 
				}
				else if(i == 6) {
					temp = white_pawns[j] ; 
				}
				else if(i == 7 && j == 0) {
					temp = white_rook1 ; 
				}
				else if(i == 7 && j == 7) {
					temp = white_rook2 ; 
				}
				else if(i == 7 && j == 1) {
					temp = white_knight1 ; 
				}
				else if(i == 7 && j == 6) {
					temp = white_knight2 ; 
				}
				else if(i == 7 && j == 2) {
					temp = white_bishop1 ; 
				}
				else if(i == 7 && j == 5) {
					temp = white_bishop2 ; 
				}
				else if(i == 7 && j == 3) {
					temp = white_king ; 
				}
				else if(i == 7 && j == 4) {
					temp = white_queen ; 
				}
				Cell cell = new Cell(i, j, temp) ; 
				grid[i][j] = cell ; 
			}
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		
	}
}
