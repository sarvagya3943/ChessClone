package pieces;

import game.Cell;

import java.util.ArrayList;

/*
 * Queen class (or "Rani" for the hindi medium people) 
 * Can move any number of steps in all 8 directions 
 * combination of Rook and Bishop 
 */

public class Queen extends Piece {

	public Queen(String id , String path , PieceColor color) {
		setId(id) ; 
		setPath(path) ; 
		setColor(color) ; 
	}

	@Override
	public ArrayList<Cell> getMoves(Cell[][] arr, int r, int c) {
		
		movesPossible.clear() ; 
		for(int _c = c + 1 ; _c < 8 ; ++_c) {
			if(arr[r][_c].getPiece() == null) {
				movesPossible.add(arr[r][_c]) ; 
			}
			else {
				if(arr[r][_c].getPiece().getColor() != this.getColor()) {
					movesPossible.add(arr[r][_c]) ; 
				}
				break ; 
			}
		}
		for(int _c = c - 1 ; _c >= 0 ; --_c) {
			if(arr[r][_c].getPiece() == null) {
				movesPossible.add(arr[r][_c]) ; 
			}
			else {
				if(arr[r][_c].getPiece().getColor() != this.getColor()) {
					movesPossible.add(arr[r][_c]) ; 
				}
				break ; 
			}
		}
		for(int _r = r + 1 ; _r < 8 ; ++_r) {
			if(arr[_r][c].getPiece() == null) {
				movesPossible.add(arr[_r][c]) ; 
			}
			else {
				if(arr[_r][c].getPiece().getColor() != this.getColor()) {
					movesPossible.add(arr[_r][c]) ; 
				}
				break ; 
			}
		}
		for(int _r = r - 1 ; _r >= 0 ; --_r) {
			if(arr[_r][c].getPiece() == null) {
				movesPossible.add(arr[_r][c]) ; 
			}
			else {
				if(arr[_r][c].getPiece().getColor() != this.getColor()) {
					movesPossible.add(arr[_r][c]) ; 
				}
				break ; 
			}
		}
		for(int _r = r - 1 , _c = c - 1 ; _r >= 0 && _c >= 0 ; --_r,--_c) {
			if(arr[_r][_c].getPiece() == null) {
				movesPossible.add(arr[_r][_c]) ; 
			}
			else {
				if(arr[_r][_c].getPiece().getColor() != this.getColor()) {
					movesPossible.add(arr[_r][_c]) ; 
				}
				break ; 
			}
		}
		for(int _r = r - 1 , _c = c + 1 ; _r >= 0 && _c < 8 ; --_r,++_c) {
			if(arr[_r][_c].getPiece() == null) {
				movesPossible.add(arr[_r][_c]) ; 
			}
			else {
				if(arr[_r][_c].getPiece().getColor() != this.getColor()) {
					movesPossible.add(arr[_r][_c]) ; 
				}
				break ; 
			}
		}
		for(int _r = r + 1 , _c = c - 1 ; _r < 8 && _c >= 0 ; ++_r,--_c) {
			if(arr[_r][_c].getPiece() == null) {
				movesPossible.add(arr[_r][_c]) ; 
			}
			else {
				if(arr[_r][_c].getPiece().getColor() != this.getColor()) {
					movesPossible.add(arr[_r][_c]) ; 
				}
				break ; 
			}
		}
		for(int _r = r + 1 , _c = c + 1 ; _r < 8 && _c < 8 ; ++_r,++_c) {
			if(arr[_r][_c].getPiece() == null) {
				movesPossible.add(arr[_r][_c]) ; 
			}
			else {
				if(arr[_r][_c].getPiece().getColor() != this.getColor()) {
					movesPossible.add(arr[_r][_c]) ; 
				}
				break ; 
			}
		}
		return movesPossible ; 
	}

}
