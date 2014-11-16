package com.nj.snakesnladders;

import com.nj.snakesnladders.constants.GameConstants;

/**
 * @author Nihav Jain
 * @class CellCoordinates
 * represents the row-columns indexes of a cell
 */
public class CellCoordinates {
	
	private int x;	// column number
	private int y;	// row number
	
	/**
	 * @constructor
	 * @param x
	 * @param y
	 */
	CellCoordinates(int x, int y){
		this.setX(x);
		this.setY(y);
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		if(x >= 0 && x < GameConstants.ROWS)
			this.x = x;
	}
	
	public int getY() {
		return y;
	}
	public void setY(int y) {
		if(y >= 0 && y < GameConstants.COLS)
			this.y = y;
	}
}
