package com.nj.snakesnladders.constants;

/**
 * @author Nihav Jain
 * @enum CellType
 * enumerated values of the possible types of cells
 */
public enum CellType {
	NONE(0),
	LADDER_FOOT(1),
	SNAKE_HEAD(2);
	
	private int cellType;
	CellType(int cellType){
		this.cellType = cellType;
	}
}
