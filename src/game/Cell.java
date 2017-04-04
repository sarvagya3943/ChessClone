package game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pieces.Piece;

/*
 * Individual cells that make up the game board 
 */
public class Cell extends JPanel {

	public int r,c ; // everybody needs access to this  
	private Piece piece ; 
	private JLabel label ; 

	// Class Constructor 
	public Cell(int r,int c,Piece piece) {
		this.r = r ; 
		this.c = c ; 
		
		if(piece != null) {
			setPiece(piece) ; 
		}
	}
	
	// add the piece and its image to the cell panel 
	public void setPiece(Piece piece) {
		this.piece = piece ; 
		ImageIcon image = new ImageIcon(piece.getPath()) ; 
		label = new JLabel(image) ; 
		this.add(label) ; 
	}
	// returns true if (r,c) lies in the board 
	public static boolean valid(int r,int c) {
		return (r >= 0 && r < 8 && c >= 0 && c < 8) ; 
	}
	
	public Piece getPiece() {
		return this.piece ; 
	}
	
	public void removePiece() {
		this.piece = null ; 
		this.remove(label) ;
	}
}
