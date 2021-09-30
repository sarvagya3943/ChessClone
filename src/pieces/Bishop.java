package pieces;

import game.Cell;

import java.util.ArrayList;

/*
 * Bishop class (or the "camel" class for the hindi medium people) 
 * can only move diagonally
 * Some extra comment
 */

public class Bishop extends Piece {

	public Bishop(String id , String path , PieceColor color) {
		setId(id) ; 
		setPath(path) ; 
		setColor(color) ; 
	}

	@Override
	public ArrayList<Cell> getMoves(Cell[][] arr, int r, int c) {
		
		movesPossible.clear() ; 
		
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
