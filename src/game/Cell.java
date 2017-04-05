package game;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pieces.Piece;

/*
 * Individual cells that make up the game board 
 */
public class Cell extends JPanel implements Cloneable {

	private static final long serialVersionUID = 1L ;
	public int r,c ; // everybody needs access to this  
	private Piece piece ; 
	private JLabel label ; 
	private boolean isCheck = false ;
	private boolean isSelected = false ;
	private boolean isPossibleDestination = false ;
	
	// Class Constructor 
	public Cell(int r,int c,Piece piece) {
		this.r = r ; 
		this.c = c ; 
		
		if((r + c) % 2 == 0) {
			// setBackground(new Color(113,198,113)) ;
			setBackground(new Color(55, 200, 170)) ;
		}
		else {
			setBackground(Color.WHITE) ; 
		}
		
		if(piece != null) {
			setPiece(piece) ; 
		}
	}
	
	public Cell(Cell cell) throws CloneNotSupportedException
	{
		this.r = cell.r ; 
		this.c = cell.c ; 
		if((r + c) % 2 == 0) {
			// setBackground(new Color(113,198,113)) ; 
			setBackground(new Color(55, 200, 170)) ;
		}
		else setBackground(Color.WHITE) ; 
		if(cell.getPiece() != null) {
			setPiece(cell.getPiece().getcopy()) ; 
		}
		else piece = null ; 
	}
	
	// add the piece and its image to the cell panel 
	public void setPiece(Piece p) {
		this.piece = p ; 
		ImageIcon image = new javax.swing.ImageIcon(this.getClass().getResource(p.getPath())) ; 
		//ImageIcon image = new ImageIcon(piece.getPath()) ; 
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
	
	public boolean isSelected() {
		return this.isSelected ;
	}
	
	public void select() {
		this.setBorder(BorderFactory.createLineBorder(new Color(249, 52, 70), 6)) ;
		this.isSelected = true ; 
	}
	
	public void deselect() {
		this.setBorder(null) ; 
		this.isSelected = false ;
	}
	
	public boolean isPosDestination() {
		return isPossibleDestination ; 
	}
	
	public void setPosDestination() {
		this.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 4)) ;
		this.isPossibleDestination = true ;
	}
	
	public void removePosDestination() {
		this.setBorder(null) ; 
		this.isPossibleDestination = false ;
	}
	
	public void setCheck() {
		this.setBackground(new Color(249, 52, 70)) ;
		this.isCheck = true ; 
	}
	
	public boolean isCheck() {
		return this.isCheck ; 
	}
	
	public void removeCheck() {
		this.setBorder(null) ; 
		if((r + c) % 2 == 0) {
			// setBackground(new Color(113,198,113)) ;
			setBackground(new Color(55, 200, 170)) ;
		}
		else {
			setBackground(Color.WHITE) ; 
		}
		this.isCheck = false ;
	}
}
