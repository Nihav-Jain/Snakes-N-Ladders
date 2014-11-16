package com.nj.snakesnladders;

import java.awt.Color;

import com.nj.snakesnladders.constants.PlayerStatus;

/**
 * @author Nihav Jain
 * @class Player
 * represents a Player of the board game
 */
public class Player 
{
	private String name;
	private int position;
	private Color color;
	private PlayerStatus status;
	
	/**
	 * @constructor
	 * @param name
	 * @param color
	 */
	Player(String name, Color color){
		this.setName(name);
		this.position = 0;
		this.color = color;
		this.setStatus(PlayerStatus.ALIVE);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void setPosition(int position){
		this.position = position;
	}
	
	/**
	 * updates & returns the current position by given dice value
	 * returns -1 if move is not possible
	 * @param diceValue
	 * @return
	 */
	public int updatePositionByDiceValue(int diceValue) {
		int newPos = this.position + diceValue;
		if(newPos < 0)
			return -1;
		if(newPos > 100)
			return -1;
		this.position = newPos;
		return this.position;
	}
	
	public Color getColor() {
		return color;
	}

	public PlayerStatus getStatus() {
		return status;
	}

	public void setStatus(PlayerStatus status) {
		this.status = status;
	}
	
}
