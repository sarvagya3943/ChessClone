package pieces;

import game.Cell;

import java.util.ArrayList;

/*
 * Knight piece (or "ghoda" for hindi medium people) 
 * moves 2.5 steps in all 8 directions 
 */
public class Knight extends Piece {

	public Knight(String id , String path , PieceColor color) {
		setId(id) ; 
		setColor(color) ; 
		setPath(path) ; 
	}

	@Override
	public ArrayList<Cell> getMoves(Cell[][] arr, int r, int c) {
		movesPossible.clear() ; 
		for(int i = -2 ; i <= 2 ; ++i) {
			for(int j = -2 ; j <= 2 ; ++j) {
				if(Math.abs(i) + Math.abs(j) == 3) {
					int _r = r + i ; 
					int _c = c + j ; 
					if(Cell.valid(_r, _c) && (arr[_r][_c].getPiece() == null || arr[_r][_c].getPiece().getColor() != this.getColor())) {
						movesPossible.add(arr[_r][_c]) ; 
					}
				}
			}
		}
		return movesPossible ; 
	}
	
}
