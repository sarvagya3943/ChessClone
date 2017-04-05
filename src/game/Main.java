package game;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.PieceColor;
import pieces.Queen;
import pieces.Rook;


public class Main extends JFrame implements MouseListener {
	
	private static final long serialVersionUID = 1L ;
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
	private boolean selectedYet = false , gameEndedYet = false ;
	
	private JPanel show_time , chess_board , control_panel , TimeDisplay;
	private JPanel game_not_started , show_player ;  
	private JPanel white_details , black_details ; 
	private JPanel black_combo_panel , white_combo_panel ; 
	private JPanel white_player , black_player ;
	private ArrayList<String> white_player_names , black_player_names ; 
	private String[] white_player_Names , black_player_Names ;  
	private ArrayList<Player> white_players , black_players ; 
	private static String playerTurn ;
	private JSplitPane split_bar ; 
	private JScrollPane white_scroll , black_scroll ; 
	private JComboBox<String> white_com , black_com ; 
	public static int TimeRemaining = 60 ; // in seconds
	private TimerStuff timer ;
	private Player whitePlayer , blackPlayer , tempPlayer ; 
	private Button start , white_select , black_select , white_new_player , black_new_player ; 
	private String white_name , black_name , winner_name ; 
	private JSlider timeSlider ; 
	private BufferedImage img ; 
	private JLabel current_move , label , timeSetter , current_player ;
	
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
		TimeRemaining = 60 ; // 1 minute by default 
		playerTurn = "White" ;
		white_name = null ; 
		black_name = null ; 
		winner_name = null ; 
		chess_board = new JPanel(new GridLayout(8, 8)) ; 
		white_details = new JPanel(new GridLayout(3, 3)) ; 
		black_details = new JPanel(new GridLayout(3, 3)) ;
		black_combo_panel = new JPanel() ; 
		white_combo_panel = new JPanel() ; 
		white_player_names = new ArrayList<String>() ;
		black_player_names = new ArrayList<String>() ;
		
		chess_board.setMinimumSize(new Dimension(800,700)) ;
		
		ImageIcon image = new ImageIcon(this.getClass().getResource("/icon.png"));
		this.setIconImage(image.getImage()) ;
		
		// time slider stuff 
		timeSlider = new JSlider(1, 15, 1) ; 
		timeSlider.setMajorTickSpacing(2) ; 
		timeSlider.setPaintLabels(true) ; 
		timeSlider.setPaintTicks(true) ; 
		timeSlider.addChangeListener(new TimeChanges()) ; 
		
		white_players = Player.getPlayers() ;
		for(Player player : white_players) {
			white_player_names.add(player.getName()) ;
		}
		black_players = Player.getPlayers() ;
		for(Player player : black_players) {
			black_player_names.add(player.getName()) ;
		}
		
		white_player_Names = white_player_names.toArray(white_player_Names) ; 
		black_player_Names = black_player_names.toArray(black_player_Names) ;
		
		chess_board.setBorder(BorderFactory.createLoweredBevelBorder());
		
		setSize(width, height) ; 
		setTitle("Chess Game") ;
		content = getContentPane() ; 
		content.setBackground(Color.BLACK) ;
		content.setLayout(new BorderLayout()) ; 
		
		control_panel = new JPanel() ; 
		control_panel.setLayout(new GridLayout(3, 3)) ; 
		control_panel.setBorder(BorderFactory.createTitledBorder(null, "STATS", TitledBorder.TOP, TitledBorder.CENTER, new Font("Lucida Calligraphy",Font.PLAIN,20), Color.ORANGE)) ;
		
		white_player = new JPanel() ; 
		white_player.setBorder(BorderFactory.createTitledBorder(null, "White Player", TitledBorder.TOP,TitledBorder.CENTER, new Font("times new roman",Font.BOLD,18), Color.RED));
		white_player.setLayout(new BorderLayout()) ;
		
		black_player = new JPanel() ; 
		black_player.setBorder(BorderFactory.createTitledBorder(null, "Black Player", TitledBorder.TOP,TitledBorder.CENTER, new Font("times new roman",Font.BOLD,18), Color.BLUE));
		black_player.setLayout(new BorderLayout()) ;
		
		JPanel white_stats = new JPanel(new GridLayout()) ; 
		JPanel black_stats = new JPanel(new GridLayout()) ;
		
		white_com = new JComboBox<String>(white_player_Names) ;
		black_com = new JComboBox<String>(black_player_Names) ;
		
		white_scroll = new JScrollPane(white_com) ;
		black_scroll = new JScrollPane(black_com) ;
		
		white_combo_panel.setLayout(new FlowLayout()) ; 
		black_combo_panel.setLayout(new FlowLayout()) ;
		
		white_select = new Button("Select") ; 
		black_select = new Button("Select") ; 
		
		white_select.addActionListener(new SelectionHandler(PieceColor.WHITE)) ; 
		black_select.addActionListener(new SelectionHandler(PieceColor.BLACK)) ; 
		
		white_new_player = new Button("New Player") ;
		black_new_player = new Button("New Player") ;
		
		white_new_player.addActionListener(new NewHandler(PieceColor.WHITE)) ;
		black_new_player.addActionListener(new NewHandler(PieceColor.BLACK)) ;
		
		// add stuff now 
		white_combo_panel.add(white_scroll) ; 
		white_combo_panel.add(white_select) ; 
		white_combo_panel.add(white_new_player) ;
		
		black_combo_panel.add(black_scroll) ;
		black_combo_panel.add(black_select) ;
		black_combo_panel.add(black_new_player) ;
		
		white_player.add(white_combo_panel , BorderLayout.NORTH) ; 
		black_player.add(black_combo_panel , BorderLayout.NORTH) ; 
	
		white_stats.add(new JLabel("Player Name  : ")) ;
		white_stats.add(new JLabel("Games Played : ")) ;
		white_stats.add(new JLabel("Games Won    : ")) ;
		
		black_stats.add(new JLabel("Player Name  : ")) ;
		black_stats.add(new JLabel("Games Played : ")) ;
		black_stats.add(new JLabel("Games Won    : ")) ;
		
		white_player.add(white_stats , BorderLayout.WEST) ;
		black_player.add(black_stats , BorderLayout.WEST) ;
		
		control_panel.add(white_player) ; 
		control_panel.add(black_player) ;
		
		
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
				cell.addMouseListener(this) ;
				chess_board.add(cell) ;
				grid[i][j] = cell ; 
			}
		}
		
		show_player = new JPanel(new FlowLayout()) ; 
		show_player.add(timeSlider) ;
		
		timeSetter = new JLabel("Set Timer(in minutes) : ") ;
		timeSetter.setFont(new Font("Arial",Font.BOLD,16)) ;
		
		start = new Button("Start") ;
		start.setBackground(Color.BLACK) ; 
		start.setForeground(Color.WHITE) ;
		start.addActionListener(new GameStarter()) ; 
		start.setPreferredSize(new Dimension(120,40)) ;
		
		label = new JLabel("Time Starts Now" , JLabel.CENTER) ;
		label.setFont(new Font("SERIF", Font.BOLD, 30)); 
		
		TimeDisplay = new JPanel(new FlowLayout()) ; 
		
		show_time = new JPanel(new GridLayout(3,3)) ;
		show_time.add(timeSetter) ; 
		show_time.add(show_player) ;
		TimeDisplay.add(start) ; 
		show_time.add(TimeDisplay) ;
		control_panel.add(show_time) ;
		
		chess_board.setMinimumSize(new Dimension(800, 700)) ;
		
		game_not_started = new JPanel() {
			
			@Override 
			public void paintComponent(Graphics g) {
				try {
					img = ImageIO.read(this.getClass().getResource("/clash.jpg")) ;
				} catch (IOException e) {
					System.out.println(" NOT FOUND :( ") ;
				}
				g.drawImage(img, 0, 0, null) ;
			}
		} ;
		game_not_started.setMinimumSize(new Dimension(800, 700)) ; 
		
		control_panel.setMinimumSize(new Dimension(285, 700)) ;
		
		split_bar = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT , game_not_started , control_panel) ;
		
		content.add(split_bar) ;
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
			show_player.remove(current_player) ; 
			if(Main.playerTurn == "White") {
				Main.playerTurn = "Black" ; 
			}
			else Main.playerTurn = "White" ;
			current_player.setText(Main.playerTurn) ; 
			show_player.add(current_player) ; 
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
		TimeDisplay.disable() ; 
		timer.countDown.stop() ;
		if(previousCell != null) {
			previousCell.removePiece() ; 
		}
		if(turn == PieceColor.WHITE) {
			whitePlayer.updateGamesWon() ; 
			whitePlayer.UpdatePlayer() ; 
			winner_name = whitePlayer.getName() ; 
		}
		else {
			blackPlayer.updateGamesWon() ; 
			blackPlayer.UpdatePlayer() ; 
			winner_name = blackPlayer.getName() ; 
		}
		
		JOptionPane.showMessageDialog(chess_board, "Checkmate !!!\n" + winner_name + "wins :)" ) ; 
		white_player.remove(white_details) ; 
		black_player.remove(black_details) ;
		TimeDisplay.remove(label) ;
		
		TimeDisplay.add(start) ; 
		show_player.remove(current_move) ; 
		show_player.remove(current_player) ;
		show_player.revalidate() ; 
		show_player.add(timeSlider) ;
		
		split_bar.remove(chess_board) ; 
		split_bar.add(game_not_started) ; 
		
		white_new_player.enable() ; 
		black_new_player.enable() ;
		white_select.enable() ; 
		black_select.enable() ; 
		
		gameEndedYet = true ; 
		gameBoard.disable() ; 
		gameBoard.dispose() ; 
		gameBoard = new Main() ; 
		gameBoard.setVisible(true) ; 
		gameBoard.setResizable(false) ; 
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
	
	class TimeChanges implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent arg0) {

			TimeRemaining = timeSlider.getValue() * 60 ;
		
		}
		
	}
	
	class SelectionHandler implements ActionListener 
	{
		private PieceColor color ; 
		public SelectionHandler(PieceColor color) {
			this.color = color ; 
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {

			tempPlayer = null ; 
			String curString = (color == PieceColor.WHITE) ? white_name : black_name ;  
			JComboBox<String> jC = (color == PieceColor.WHITE) ? white_com : black_com ; 
			JComboBox<String> ojC = (color == PieceColor.WHITE) ? black_com : white_com ; 
			ArrayList<Player> players = (color == PieceColor.WHITE) ? white_players : black_players ; 
			ArrayList<Player> oplayers = Player.getPlayers() ; 
			if(oplayers.isEmpty()) return ; 
			JPanel details = (color == PieceColor.WHITE) ? white_details : black_details ; 
			JPanel pl = (color == PieceColor.WHITE) ? white_player : black_player ;
			if(selectedYet) {
				details.removeAll() ; 
			}
			curString = (String)jC.getSelectedItem() ; 
			for(Player player : players) {
				if(player.getName().equals(curString)) {
					tempPlayer = player ; 
					break ; 
				}
			}
			Iterator<Player> oit = oplayers.iterator() ; 
			while(oit.hasNext()) {
				Player p = oit.next() ;  
				if(p.getName().equals(curString)) {
					oplayers.remove(p) ;
					break ; 
				}
			}
			if(tempPlayer == null) return ; 
			if(color == PieceColor.WHITE) {
				whitePlayer = tempPlayer ; 
			}
			else blackPlayer = tempPlayer ; 
			black_players = oplayers ; 
			ojC.removeAllItems() ; 
			for(Player player : oplayers) {
				ojC.addItem(player.getName()) ; 
			}
			
			details.add(new JLabel(" "+tempPlayer.getName()));
			details.add(new JLabel(" "+tempPlayer.getGamesPlayed()));
			details.add(new JLabel(" "+tempPlayer.getGamesWon()));
	
			pl.revalidate() ; 
			pl.repaint() ; 
			pl.add(details) ; 
			
			selectedYet = true ; 
		}
		
	}
	
	class NewHandler implements ActionListener {
		private PieceColor color ; 
		public NewHandler(PieceColor color) {
			this.color = color ; 
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String curString = (color == PieceColor.WHITE) ? white_name : black_name ; 
			JPanel curPanel = (color == PieceColor.WHITE) ? white_player : black_player ; 
			JPanel details = (color == PieceColor.WHITE) ? white_details : black_details ;
			ArrayList<Player> arr = Player.getPlayers() ; 
			curString = JOptionPane.showInputDialog(curPanel, "Enter your stupid Name") ; 
			if(curString != null) {
				for(Player player : arr) {
					if(player.getName().equals(curString)) {
						JOptionPane.showMessageDialog(curPanel, "Player already exists -_- ") ; 
						return ;
					}
				}
				if(curString.length() != 0) {
					Player res = new Player(curString) ; 
					res.UpdatePlayer() ; 
					if(color == PieceColor.WHITE) {
						whitePlayer = res ; 
					}
					else blackPlayer = res ;
				}
				else return ; 
			}
			else return ; 
			
			details.removeAll() ; 
			details.add(new JLabel(" " + curString)) ;
			details.add(new JLabel(" 0")) ;
			details.add(new JLabel(" 0")) ;
			
			curPanel.revalidate() ;
			curPanel.repaint() ; 
			curPanel.add(details) ;
			
			selectedYet = true ; 
		}
		
	}
	
	class GameStarter implements ActionListener {

		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {

			if(whitePlayer == null || blackPlayer == null) {
				JOptionPane.showMessageDialog(control_panel, "Details to bhar de chutiye") ;
				return ; 
			}
			
			whitePlayer.updateGamesPlayed() ; 
			whitePlayer.UpdatePlayer() ; 
			
			blackPlayer.updateGamesPlayed() ; 
			blackPlayer.UpdatePlayer() ; 
			
			white_new_player.disable() ; 
			black_new_player.disable() ;
			
			white_select.disable() ; 
			black_select.disable() ;
			
			split_bar.remove(game_not_started) ;
			split_bar.add(chess_board) ; 
			
			show_player.remove(timeSlider) ;
			current_move = new JLabel("MOVE : ") ;
			current_move.setFont(new Font("Comic Sans MS", Font.PLAIN ,20)) ; 
			current_move.setForeground(Color.RED) ; 
			show_player.add(current_move) ; 
			
			current_player = new JLabel(playerTurn) ; 
			current_player.setFont(new Font("Comic Sans MS", Font.BOLD ,20)) ; 
			current_player.setForeground(Color.BLUE) ; 
			show_player.add(current_player) ;
			
			TimeDisplay.remove(start) ; 
			TimeDisplay.add(label) ; 
			
			timer = new TimerStuff(label) ; 
			timer.startTimer() ;
			
		}
		
	}
}
