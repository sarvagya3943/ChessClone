package pieces;

import game.Cell;

import java.util.ArrayList;

/*
 * Pawn piece (or the "pyaada" for the hindi medium people) 
 * only moves one step forward , except in the first move 
 * where it can move two steps forward , also while attacking it can move diagonally  
 */

public class Pawn extends Piece {

	// constructor 
	public Pawn(String id , String path , PieceColor color) {
		setId(id) ; 
		setPath(path) ; 
		setColor(color) ; 
	}
	// return arraylist of possible moves that user can perform 
	@Override
	public ArrayList<Cell> getMoves(Cell[][] arr, int r, int c) {

		movesPossible.clear() ; 
		if(getColor() == PieceColor.WHITE) {
			if(r == 0) {
				return movesPossible ; 
			}
			if(arr[r-1][c].getPiece() == null) {
				movesPossible.add(arr[r-1][c]) ;
				if(r == 6) {
					// what if this is the first move 
					if(arr[4][c].getPiece() == null) {
						movesPossible.add(arr[4][c]) ; 
					}
				}
			}
			if(c-1 >= 0 && arr[r-1][c-1].getPiece() != null && arr[r-1][c-1].getPiece().getColor() != this.getColor()) {
				movesPossible.add(arr[r-1][c-1]) ; 
			}
			if(c+1 <= 7 && arr[r-1][c+1].getPiece() != null && arr[r-1][c+1].getPiece().getColor() != this.getColor()) {
				movesPossible.add(arr[r-1][c+1]) ; 
			}
		}
		else {
			if(r == 7) {
				return movesPossible ; 
			}
			if(arr[r+1][c].getPiece() == null) {
				movesPossible.add(arr[r+1][c]) ;
				if(r == 1) {
					// what if this is the first move 
					if(arr[3][c].getPiece() == null) {
						movesPossible.add(arr[3][c]) ; 
					}
				}
			}
			if(c-1 >= 0 && arr[r+1][c-1].getPiece() != null && arr[r+1][c-1].getPiece().getColor() != this.getColor()) {
				movesPossible.add(arr[r+1][c-1]) ; 
			}
			if(c+1 <= 7 && arr[r+1][c+1].getPiece() != null && arr[r+1][c+1].getPiece().getColor() != this.getColor()) {
				movesPossible.add(arr[r+1][c+1]) ; 
			}
		}
		return movesPossible ; 
	}

}
