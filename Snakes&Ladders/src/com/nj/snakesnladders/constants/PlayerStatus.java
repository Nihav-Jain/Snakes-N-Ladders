package com.nj.snakesnladders.constants;

/**
 * @author Nihav Jain
 * @enum PlayerStatus
 * represents statuses of the players
 * TODO: Add active, waiting states also for UI representations
 */
public enum PlayerStatus {
	ALIVE(0),
	WON(1);
	
	private int playerStatus;
	PlayerStatus(int playerStatus){
		this.playerStatus = playerStatus;
	}
}
