package pieces;

import game.Cell;

import java.util.ArrayList;

/*
 * Every chess piece would extend this class. 
 */
public abstract class Piece implements Cloneable {

	private PieceColor color ; 
	private String id = null ; 
	private String path ; 
	protected ArrayList<Cell> movesPossible = new ArrayList<Cell>() ;  
	public abstract ArrayList<Cell> getMoves(Cell arr[][] , int r , int c) ; 
	
	// color setter 
	public void setColor(PieceColor color) {
		this.color = color ; 
	}
	
	// color getter 
	public PieceColor getColor() {
		return this.color ; 
	}
	
	// id setter
	public void setId(String id) {
		this.id = id ; 
	}
	
	// id getter 
	public String getId() {
		return id ; 
	}
	
	// path setter 
	public void setPath(String path) {
		this.path = path ; 
	}
	
	// path getter
	public String getPath() {
		return this.path ; 
	}
	
	public Piece getcopy() throws CloneNotSupportedException {
		return (Piece) this.clone(); 
	}
}
