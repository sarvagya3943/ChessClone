package pieces;

import game.Cell;

import java.util.ArrayList;


/*
 * King class (or "Raja" for hindi medium people)
 * Can move 1 step in any direction
 * some extra comment
 */ 

public class King extends Piece {

	private int row , col ; 
	
	public King(String id , String path , PieceColor color , int row , int col) {
		setId(id) ; 
		setPath(path) ; 
		setColor(color) ; 
		this.row = row ; 
		this.col = col ; 
	}
	
	public void setRow(int row) {
		this.row = row ; 
	}
	
	public void setCol(int col) {
		this.col = col ; 
	}
	
	public int getRow() {
		return row ; 
	}

	public int getCol() {
		return col ; 
	}
	@Override
	public ArrayList<Cell> getMoves(Cell[][] arr, int r, int c) {
		
		movesPossible.clear() ; 
		for(int i = -1 ; i <= 1 ; ++i) {
			for(int j = -1 ; j <= 1 ; ++j) {
				if(i == 0 && j == 0) continue ; 
				int _r = r + i , _c = c + j ; 
				if(Cell.valid(_r, _c) && (arr[_r][_c].getPiece() == null || arr[_r][_c].getPiece().getColor() != this.getColor())) {
					movesPossible.add(arr[_r][_c]) ; 
				}
			}
		}
		return movesPossible ; 
	}
 
	public boolean IsKingInDanger(Cell[][] arr) {
		
		// downwards 
		for(int c = col , r = row + 1 ; r < 8 ; ++r) {
			if(arr[r][c].getPiece() == null) continue ; 
			if(arr[r][c].getPiece().getColor() == this.getColor()) break ;
			if(arr[r][c].getPiece() instanceof Rook || arr[r][c].getPiece() instanceof Queen) {
				return true ; 
			}
			else break ;
		}
		// upwards 
		for(int c = col , r = row - 1 ; r >= 0 ; --r) {
			if(arr[r][c].getPiece() == null) continue ; 
			if(arr[r][c].getPiece().getColor() == this.getColor()) break ;
			if(arr[r][c].getPiece() instanceof Rook || arr[r][c].getPiece() instanceof Queen) {
				return true ; 
			}
			else break ;
		}
		// right
		for(int c = col + 1 , r = row ; c < 8 ; ++c) {
			if(arr[r][c].getPiece() == null) continue ; 
			if(arr[r][c].getPiece().getColor() == this.getColor()) break ;
			if(arr[r][c].getPiece() instanceof Rook || arr[r][c].getPiece() instanceof Queen) {
				return true ; 
			}
			else break ;
		}
		// left
		for(int c = col - 1 , r = row ; c >= 0 ; --c) {
			if(arr[r][c].getPiece() == null) continue ; 
			if(arr[r][c].getPiece().getColor() == this.getColor()) break ;
			if(arr[r][c].getPiece() instanceof Rook || arr[r][c].getPiece() instanceof Queen) {
				return true ; 
			}
			else break ;
		}
		// up-left 
		for(int c = col - 1 , r = row - 1 ; r >= 0 && c >= 0 ; --r , --c) {
			if(arr[r][c].getPiece() == null) continue ; 
			if(arr[r][c].getPiece().getColor() == this.getColor()) break ;
			if(arr[r][c].getPiece() instanceof Bishop || arr[r][c].getPiece() instanceof Queen) {
				return true ; 
			}
			else break ;
		}
		// up-right
		for(int c = col + 1 , r = row - 1 ; r >= 0 && c < 8 ; --r , ++c) {
			if(arr[r][c].getPiece() == null) continue ; 
			if(arr[r][c].getPiece().getColor() == this.getColor()) break ;
			if(arr[r][c].getPiece() instanceof Bishop || arr[r][c].getPiece() instanceof Queen) {
				return true ; 
			}
			else break ;
		}
		// down-left 
		for(int c = col - 1 , r = row + 1 ; r < 8 && c >= 0 ; ++r , --c) {
			if(arr[r][c].getPiece() == null) continue ; 
			if(arr[r][c].getPiece().getColor() == this.getColor()) break ;
			if(arr[r][c].getPiece() instanceof Bishop || arr[r][c].getPiece() instanceof Queen) {
				return true ; 
			}
			else break ;
		}
		// down-right 
		for(int c = col + 1 , r = row + 1 ; r < 8 && c < 8 ; ++r , ++c) {
			if(arr[r][c].getPiece() == null) continue ; 
			if(arr[r][c].getPiece().getColor() == this.getColor()) break ;
			if(arr[r][c].getPiece() instanceof Bishop || arr[r][c].getPiece() instanceof Queen) {
				return true ; 
			}
			else break ;
		}
		// Knight-attack
		for(int i = -2 ; i <= 2 ; ++i) {
			for(int j = -2 ; j <= 2 ; ++j) {
				if(Math.abs(i) + Math.abs(j) == 3) {
					int r = row + i , c = col + j ; 
					if(Cell.valid(r, c) == false) continue ; 
					if(arr[r][c].getPiece() == null) continue ; 
					if(arr[r][c].getPiece().getColor() == this.getColor()) continue ; 
					if(arr[r][c].getPiece() instanceof Knight) return true ; 
				}
			}
		}
		// King-attack 
		for(int i = -1 ; i <= 1 ; ++i) {
			for(int j = -1 ; j <= 1 ; ++j) {
				if(i == 0 && j == 0) {} 
				else {
					int r = row + i , c = col + j ; 
					if(Cell.valid(r, c) == false) continue ; 
					if(arr[r][c].getPiece() == null) continue ; 
					if(arr[r][c].getPiece().getColor() == this.getColor()) continue ; 
					if(arr[r][c].getPiece() instanceof King) return true ; 
				}
			}
		}
		// Pawn-attack 
		if(this.getColor() == PieceColor.WHITE) {
			int r = row - 1 , c = col - 1 ; 
			if(Cell.valid(r, c) && (arr[r][c].getPiece() != null && arr[r][c].getPiece().getColor() != this.getColor() && arr[r][c].getPiece() instanceof Pawn)) {
				return true ; 
			}
			c = col + 1 ; 
			if(Cell.valid(r, c) && (arr[r][c].getPiece() != null && arr[r][c].getPiece().getColor() != this.getColor() && arr[r][c].getPiece() instanceof Pawn)) {
				return true ; 
			}
		}
		else {
			int r = row + 1 , c = col - 1 ; 
			if(Cell.valid(r, c) && (arr[r][c].getPiece() != null && arr[r][c].getPiece().getColor() != this.getColor() && arr[r][c].getPiece() instanceof Pawn)) {
				return true ; 
			}
			c = col + 1 ; 
			if(Cell.valid(r, c) && (arr[r][c].getPiece() != null && arr[r][c].getPiece().getColor() != this.getColor() && arr[r][c].getPiece() instanceof Pawn)) {
				return true ; 
			}
		}
		return false ; 
	}
}
