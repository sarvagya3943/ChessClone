package pieces;

/*
 * Every chess piece would extend this class. 
 */
public abstract class Piece implements Cloneable {

	private int color ; 
	private String id = null ; 
	private String path ; 
	
	// color setter 
	public void setColor(int color) {
		this.color = color ; 
	}
	
	// color getter 
	public int getColor() {
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
