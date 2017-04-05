package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.Timer;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.PieceColor;
import pieces.Queen;
import pieces.Rook;

@SuppressWarnings("serial")
public class Main extends JFrame implements MouseListener {
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
	private ArrayList<Cell> toList = new ArrayList<Cell>() ;
	
	private PieceColor turn  = PieceColor.WHITE ;
	private boolean gameEndedYet = false ;
	
	private static String playerTurn ; 
	public static int TimeRemaining = 60 ; // in seconds
	private TimerStuff timer ;
	
	private Cell grid[][] ; 
	// keep track of previous cell
	private Cell previousCell ;  
	
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

	private King getKing(PieceColor color) {
		if(color == PieceColor.WHITE) {
			return white_king ; 
		}
		return black_king ; 
	}
	
	private void cleanToList(ArrayList<Cell> arr) {
		for(Cell cell : arr) { 
			cell.removePosDestination() ; 
		}
	}
	
	private void highlightToList(ArrayList<Cell> arr) {
		for(Cell cell : arr) {
			cell.setPosDestination() ; 
		}
	}
	
	private boolean WillKingBeInDanger(Cell from ,Cell to) {
		Cell new_grid[][] = new Cell[8][8] ; 
		for(int i = 0 ; i < 8 ; ++i) {
			for(int j = 0 ; j < 8 ; ++j) {
				try {
					new_grid[i][j] = new Cell(grid[i][j]) ; 
				} catch (CloneNotSupportedException e) {
					e.printStackTrace() ;
				}
			}
		}
		if(new_grid[to.r][to.c].getPiece() != null) {
			new_grid[to.r][to.c].removePiece() ; 
		}
		new_grid[to.r][to.c].setPiece(grid[from.r][from.c].getPiece()) ;
		if(new_grid[from.r][from.c].getPiece() instanceof King) {
			((King)(new_grid[to.r][to.c].getPiece())).setRow(to.r) ;
			((King)(new_grid[to.r][to.c].getPiece())).setCol(to.c) ;
		}
		new_grid[from.r][from.c].removePiece() ; 
		if(((King)new_grid[to.r][to.c].getPiece()).IsKingInDanger(new_grid) == false) {
			return false ;
		}
		return true ;
	}
	
	// filters moves which results in check from the other player 
	// would be called for kings only 
	private ArrayList<Cell> KingFilter(ArrayList<Cell> arr , Cell from) {
		ArrayList<Cell> res = new ArrayList<Cell>() ; 
		Cell new_grid[][] = new Cell[8][8] ; 
		for(Cell cell : arr) {
			for(int i = 0 ; i < 8 ; ++i) {
				for(int j = 0 ; j < 8 ; ++j) {
					try {
						new_grid[i][j] = new Cell(grid[i][j]) ; 
					} catch (CloneNotSupportedException e) {
						e.printStackTrace() ;
					}
				}
			}
			if(new_grid[cell.r][cell.c].getPiece() != null) {
				new_grid[cell.r][cell.c].removePiece() ; 
			}
			new_grid[cell.r][cell.c].setPiece(grid[from.r][from.c].getPiece()) ;
			if(new_grid[from.r][from.c].getPiece() instanceof King) {
				((King)(new_grid[cell.r][cell.c].getPiece())).setRow(cell.r) ;
				((King)(new_grid[cell.r][cell.c].getPiece())).setCol(cell.c) ;
			}
			new_grid[from.r][from.c].removePiece() ; 
			if(((King)new_grid[cell.r][cell.c].getPiece()).IsKingInDanger(new_grid) == false) {
				res.add(cell) ; 
			}
		}
		return res ; 
	}
	
	public void changeTurns() {
		if(grid[getKing(turn).getRow()][getKing(turn).getCol()].isCheck()) {
			turn = Othercolor(turn) ; 
			GameEnded() ; 
		}
		if(!toList.isEmpty()) {
			cleanToList(toList) ;
		}
		if(previousCell != null) {
			previousCell.deselect() ;
		}
		previousCell = null ; 
		turn = Othercolor(turn) ; 
		if(!gameEndedYet && timer != null) {
			timer.resetTimer() ; 
			timer.startTimer() ; 
			if(Main.playerTurn == "White") {
				Main.playerTurn = "Black" ; 
			}
			else Main.playerTurn = "White" ;
		}
	}
	
	PieceColor Othercolor(PieceColor color) {
		if(color == PieceColor.WHITE) {
			return PieceColor.BLACK ; 
		}
		return PieceColor.WHITE ;
	}
	
	private int DuringCheckFilter(ArrayList<Cell> arr , Cell from , PieceColor color) {
		int res = 0 ;
		Cell new_grid[][] = new Cell[8][8] ; 
		for(Cell cell : arr) {
			for(int i = 0 ; i < 8 ; ++i) {
				for(int j = 0 ; j < 8 ; ++j) {
					try {
						new_grid[i][j] = new Cell(grid[i][j]) ; 
					} catch (CloneNotSupportedException e) {
						e.printStackTrace() ;
					}
				}
			}
			if(new_grid[cell.r][cell.c].getPiece() != null) {
				new_grid[cell.r][cell.c].removePiece() ; 
			}
			new_grid[cell.r][cell.c].setPiece(grid[from.r][from.c].getPiece()) ;
			if(new_grid[from.r][from.c].getPiece() instanceof King) {
				((King)(new_grid[cell.r][cell.c].getPiece())).setRow(cell.r) ;
				((King)(new_grid[cell.r][cell.c].getPiece())).setCol(cell.c) ;
			}
			new_grid[from.r][from.c].removePiece() ; 
			if(((King)new_grid[cell.r][cell.c].getPiece()).IsKingInDanger(new_grid) == false) {
				res++ ; 
			}
			if(res > 0) return res ; 
		}
		return res ; 
	}
	
	public boolean CheckMate(PieceColor color) {
		ArrayList<Cell> arr = new ArrayList<Cell>() ;
		int availableMoves = 0 ; 
		for(int i = 0 ; i < 8 ; ++i) {
			for(int j = 0 ; j < 8 ; ++j) {
				if(grid[i][j].getPiece() != null && grid[i][j].getPiece().getColor() == color) {
					arr.clear() ;
					arr = grid[i][j].getPiece().getMoves(grid, i, j) ;
					availableMoves = DuringCheckFilter(arr,grid[i][j],color) ; 
					if(availableMoves != 0) return false ;
				}
			}
		}
		return true ;
	}
	
	private void GameEnded() {
		cleanToList(toList) ; 
		timer.countDown.stop() ;
		if(previousCell != null) {
			previousCell.removePiece() ; 
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		// which cell was clicked ?
		Cell temp = (Cell)arg0.getSource() ; 
		
		if(previousCell == null) {
			// user is selecting the piece to move i.e the "from" cell 
			if(temp.getPiece() != null ) {
				if(temp.getPiece().getColor() != turn) return ; 
				temp.select() ; 
				previousCell = temp ; 
				toList.clear() ; 
				toList = temp.getPiece().getMoves(grid, temp.r, temp.c) ; 
				if(temp.getPiece() instanceof King) {
					toList = KingFilter(toList, temp) ; 
				}
				else {
					if(grid[getKing(turn).getRow()][getKing(turn).getCol()].isCheck()) {
						toList = new ArrayList<Cell>(KingFilter(toList, temp)) ;
					}
					else if(!toList.isEmpty() && WillKingBeInDanger(temp, toList.get(0))) {
						toList.clear(); 
					}
				}
				highlightToList(toList) ; 
			}
		}
		else {
			if(temp.r == previousCell.r && temp.c == previousCell.c) {
				temp.deselect() ;
				cleanToList(toList) ;
				toList.clear() ; 
				previousCell = null ; 
			}
			else if(temp.getPiece() == null || temp.getPiece().getColor() != previousCell.getPiece().getColor()) {
				if(temp.isPosDestination()) {
					if(temp.getPiece() != null) {
						temp.removePiece() ; 
					}
					temp.setPiece(previousCell.getPiece()) ; 
					if(previousCell.isCheck()) {
						previousCell.removeCheck() ;
					}
					previousCell.removePiece() ; 
					if(getKing(Othercolor(turn)).IsKingInDanger(grid)) {
						grid[getKing(Othercolor(turn)).getRow()][getKing(Othercolor(turn)).getCol()].setCheck() ;
						if(CheckMate(Othercolor(turn))) {
							previousCell.deselect() ; 
							if(previousCell.getPiece() != null ) {
								previousCell.removePiece() ; 
							}
							GameEnded() ;
						}
					}
					if(getKing(turn).IsKingInDanger(grid) == false) {
						grid[getKing(turn).getRow()][getKing(turn).getCol()].removeCheck() ;
					}
					if(temp.getPiece() instanceof King) {
						((King)temp.getPiece()).setRow(temp.r) ; 
						((King)temp.getPiece()).setCol(temp.c) ; 
					}
					changeTurns() ;
					if(!gameEndedYet) {
						timer.resetTimer() ; 
						timer.startTimer() ; 
					}
				}
				if(previousCell != null) {
					previousCell.deselect() ;
					previousCell = null ; 
				}
				cleanToList(toList) ; 
				toList.clear(); 
			}
			else if(previousCell.getPiece().getColor() == temp.getPiece().getColor()) {
				previousCell.deselect() ; 
				cleanToList(toList) ;
				toList.clear() ;
				temp.select() ; 
				previousCell = temp ; 
				toList = temp.getPiece().getMoves(grid, temp.r, temp.c) ;
				if(temp.getPiece() instanceof King) {
					toList = KingFilter(toList, temp) ; 
				}
				else {
					if(grid[getKing(turn).getRow()][getKing(turn).getCol()].isCheck()) {
						toList = new ArrayList<Cell>(KingFilter(toList, temp)) ;
					}
					else if(!toList.isEmpty() && WillKingBeInDanger(temp, toList.get(0))) {
						toList.clear(); 
					}
				}
				highlightToList(toList) ; 
			}
		}
		if(temp.getPiece() != null && temp.getPiece() instanceof King) {
			((King)temp.getPiece()).setRow(temp.r) ; 
			((King)temp.getPiece()).setCol(temp.c) ; 
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
