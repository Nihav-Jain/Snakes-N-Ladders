package com.nj.snakesnladders;

import com.nj.snakesnladders.constants.CellType;

/**
 * @author Nihav Jain
 * @class Cell
 * represent one cell of the game board
 */
public class Cell {
	private int number;
	private CellType cellType;
	private int targetCellNumber;
	private CellCoordinates coords;
	private CellCoordinates targetCellCoords;
	
	/**
	 * @constructor
	 * @param number
	 * @param coords
	 */
	Cell(int number, CellCoordinates coords){
		this.setNumber(number);
		this.setCellType(CellType.NONE);
		this.setTargetCellNumber(-1);
		this.setCoords(coords);
	}

	public int getNumber() {
		return number;
	}

	private void setNumber(int number) {
		this.number = number;
	}

	public CellType getCellType() {
		return cellType;
	}

	public void setCellType(CellType cellType) {
		this.cellType = cellType;
	}

	public int getTargetCellNumber() {
		return targetCellNumber;
	}

	public void setTargetCellNumber(int targetCellNumber) {
		this.targetCellNumber = targetCellNumber;
	}

	public CellCoordinates getCoords() {
		return coords;
	}

	public void setCoords(CellCoordinates coords) {
		this.coords = coords;
	}

	public CellCoordinates getTargetCellCoords() {
		return targetCellCoords;
	}

	public void setTargetCellCoords(CellCoordinates targetCellCoords) {
		this.targetCellCoords = targetCellCoords;
	}
}
