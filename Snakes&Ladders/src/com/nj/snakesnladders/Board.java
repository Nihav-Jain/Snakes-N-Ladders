package com.nj.snakesnladders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nj.snakesnladders.constants.CellType;
import com.nj.snakesnladders.constants.GameConstants;

/**
 * @author Nihav Jain
 * @class Board
 * represents the playing board of the game
 */
public class Board 
{
	private Cell[][] board;
	private int numRows;
	private int numCols;
	
	/**
	 * @constructor
	 * @param boardJsonPath
	 */
	Board(String boardJsonPath){
		this.numRows = GameConstants.ROWS;
		this.numCols = GameConstants.COLS;
		this.board = new Cell[this.numRows][this.numCols];
		
		int i, j, k = 0;
		int cellNumber = 1;
		for(i = this.numRows-1; i >= 0; i--){
			if(i % 2 == 0){
				for(j = this.numCols-1; j >= 0; j--){
					this.board[i][j] = new Cell(cellNumber, new CellCoordinates(j, i));
					cellNumber++;
				}
			}
			else{
				for(j = 0; j < this.numCols; j++){
					this.board[i][j] = new Cell(cellNumber, new CellCoordinates(j, i));
					cellNumber++;
				}
			}
		}
		this.setSnakesAndLadders(boardJsonPath);
	}
	
	/**
	 * reads the json containing information about snakes and ladders
	 * and modifies the board
	 * @param boardJsonPath
	 */
	private void setSnakesAndLadders(String boardJsonPath){
		BufferedReader dd;
		String boardJson = "";
		int i;
		try {
			dd = new BufferedReader(new FileReader(boardJsonPath));
			String s;
			while((s = dd.readLine()) != null){
				boardJson += s;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			JSONObject jsonObj = new JSONObject(boardJson);
			JSONArray snakes = jsonObj.getJSONArray("snakes");
			for(i=0;i<snakes.length();i++){
				JSONObject snakeProp = snakes.getJSONObject(i);
				CellCoordinates snakeMouth = this.getCellCoordsFromNumber(snakeProp.getInt("mouth"));
				CellCoordinates snakeTail = this.getCellCoordsFromNumber(snakeProp.getInt("tail"));
				this.board[snakeMouth.getY()][snakeMouth.getX()].setTargetCellCoords(snakeTail);
				this.board[snakeMouth.getY()][snakeMouth.getX()].setCellType(CellType.SNAKE_HEAD);
				this.board[snakeMouth.getY()][snakeMouth.getX()].setTargetCellNumber(snakeProp.getInt("tail"));
			}
			JSONArray ladders = jsonObj.getJSONArray("ladders");
			for(i=0;i<ladders.length();i++){
				JSONObject ladderProp = ladders.getJSONObject(i);
				CellCoordinates ladderFoot = this.getCellCoordsFromNumber(ladderProp.getInt("foot"));
				CellCoordinates ladderHead = this.getCellCoordsFromNumber(ladderProp.getInt("head"));
				this.board[ladderFoot.getY()][ladderFoot.getX()].setTargetCellCoords(ladderHead);
				this.board[ladderFoot.getY()][ladderFoot.getX()].setCellType(CellType.LADDER_FOOT);
				this.board[ladderFoot.getY()][ladderFoot.getX()].setTargetCellNumber(ladderProp.getInt("head"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * returns row-column coordinates (indexes) of the given board cell number
	 * @param number
	 * @return
	 */
	public CellCoordinates getCellCoordsFromNumber(int number){
		int x, y;
		y = (this.numRows - 1) - ((number - 1) / this.numRows);
		if(y % 2 == 0)
			x = (this.numCols - 1) - ((number - 1) % this.numCols);
		else
			x = (number - 1) % this.numCols;
		return new CellCoordinates(x, y);
	}
	
	/**
	 * returns cell type of given cell number
	 * @param cellNumber
	 * @return
	 */
	public CellType getCellTypeOf(int cellNumber){
		CellCoordinates curCellCoords = this.getCellCoordsFromNumber(cellNumber);
		return this.board[curCellCoords.getY()][curCellCoords.getX()].getCellType();
	}
	
	/**
	 * returns coordinates where the player needs to be transferred 
	 * after landing on given cell number
	 * @param cellNumber
	 * @return
	 */
	public CellCoordinates getTargetCellCoordsOf(int cellNumber){
		CellCoordinates curCellCoords = this.getCellCoordsFromNumber(cellNumber);
		return this.board[curCellCoords.getY()][curCellCoords.getX()].getTargetCellCoords();
	}
	
	/**
	 * return cell number where the player needs to be transferred
	 * after landing on given cell number
	 * @param cellNumber
	 * @return
	 */
	public int getTargetCellNumberOf(int cellNumber){
		CellCoordinates curCellCoords = this.getCellCoordsFromNumber(cellNumber);
		return this.board[curCellCoords.getY()][curCellCoords.getX()].getTargetCellNumber();
	}
	
	/**
	 * prints the matrix of the board
	 * (for debugging only)
	 */
	public void printBoard(){
		int i,j;
		for(i=0;i<this.numRows;i++){
			for(j=0;j<this.numCols;j++){
				if(board[i][j].getCellType() == CellType.LADDER_FOOT)
					System.out.print("L" + board[i][j].getTargetCellNumber() + "\t");
				else if(board[i][j].getCellType() == CellType.SNAKE_HEAD)
					System.out.print("S" + board[i][j].getTargetCellNumber() + "\t");
				else
					System.out.print(board[i][j].getNumber() + "\t");
			}
			System.out.println();
		}
	}
}
