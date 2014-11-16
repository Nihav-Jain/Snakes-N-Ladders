package com.nj.snakesnladders;

import java.util.ArrayList;

import com.nj.snakesnladders.constants.GameConstants;
import com.nj.snakesnladders.constants.PlayerStatus;

/**
 * @author Nihav Jain
 * @class GameManager
 * runs the game loop and contains the main game logic
 */
public class GameManager {
	
	private Board board;
	private ArrayList<Player> players;
	private int currentChance;
	private ArrayList<Integer> rankList;
	
	/**
	 * @constructor
	 * @param pathToBoardJson
	 */
	GameManager(String pathToBoardJson){		
		this.board = new Board(pathToBoardJson);
		//this.board.printBoard();
		this.players = new ArrayList<Player>();
		this.currentChance = -1;
		this.rankList = new ArrayList<Integer>();
	}
	
	/**
	 * adds platers and starts the game
	 * TODO: adding players to be made dynamic, with data entered in View
	 * @return
	 */
	public boolean startGame(){
		this.addPlayer("Nihav");
		this.addPlayer("Ritika");
		// game loop
		do{
			chance();
		}while(rankList.size() != players.size() - 1);
		do{
			this.incrementChance();
		}while(players.get(this.currentChance).getStatus() == PlayerStatus.WON);
		rankList.add(this.currentChance);
		System.out.println("Rank List - ");
		for(int i=0;i<rankList.size();i++){
			System.out.println((i+1) + ". " + players.get(rankList.get(i)).getName());
		}
		System.out.println("Game Over!!!");
		return true;
	}
	
	/**
	 * one chance of a player - main logic
	 */
	private void chance(){
		Player curPlayer;
		do{
			this.incrementChance();
			curPlayer = players.get(this.currentChance);
		}while(curPlayer.getStatus() == PlayerStatus.WON);
		
		System.out.println(curPlayer.getName() + "\'s chance.");
		System.out.println("\tCurrent Position = " + curPlayer.getPosition());

		int diceValue = this.rollDice();
		System.out.println("\tDice Value = " + diceValue);
		
		int currentPlayerPos = curPlayer.updatePositionByDiceValue(diceValue);
		System.out.println("\tCurrent Position = " + curPlayer.getPosition());		
		// player needs to get to 100, cannot skip it
		if(currentPlayerPos < 0){
			return;
		}
		if(currentPlayerPos == 100){
			//player has won
			curPlayer.setStatus(PlayerStatus.WON);
			this.rankList.add(this.currentChance);
			System.out.println("\t" + curPlayer.getName() + " has completed the board.");
			return;
		}
		
		switch(board.getCellTypeOf(currentPlayerPos)){
			case LADDER_FOOT: 
				curPlayer.setPosition(board.getTargetCellNumberOf(currentPlayerPos));
				System.out.println("\tGot Ladder. Current Position = " + curPlayer.getPosition());
				break;
			case SNAKE_HEAD:
				curPlayer.setPosition(board.getTargetCellNumberOf(currentPlayerPos));
				System.out.println("\tBitten by snake. Current Position = " + curPlayer.getPosition());
				break;
			case NONE:
				break;
		}
		
		// check for players at same position
		int i;
		for(i=0;i<this.players.size();i++){
			if(i == this.currentChance)
				continue;
			Player ithPlayer = this.players.get(i);
			if(currentPlayerPos == ithPlayer.getPosition()){
				ithPlayer.setPosition(0);
				System.out.println("\t" + ithPlayer.getName() + " beaten");
			}
		}
		System.out.println("\t--Chance Complete--");
	}
	
	/**
	 * increments the index for the player with current turn
	 */
	private void incrementChance(){
		this.currentChance = (this.currentChance + 1) % this.players.size();
	}
	
	/**
	 * adds a new player to the game
	 * @param name
	 */
	private void addPlayer(String name){
		Player newPlayer = new Player(name, GameConstants.playerColors[players.size() % GameConstants.playerColors.length]);
		players.add(newPlayer);		
	}
	
	/**
	 * roll dice
	 * TODO: add bias
	 * @return
	 */
	private int rollDice(){
		return (int)(Math.random() * 6) + 1;
	}

}
