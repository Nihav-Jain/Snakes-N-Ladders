package com.nj.snakesnladders;

/**
 * @author Nihav Jain
 * @class Driver
 * driver class, starts the game
 */
public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GameManager manager = new GameManager("assets/data/board.json");
		manager.startGame();
	}

}
