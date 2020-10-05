package com.techmahindra.avaloq.model.entity;

public class DiceGame {
	
	int diceCount;
	int sizeCount;
	int diceSum;;
	
	public DiceGame(int diceCount, int sizeCount) {
		super();
		this.diceCount = diceCount;
		this.sizeCount = sizeCount;
	}
	
	public int getDiceCount() {
		return diceCount;
	}
	public void setDiceCount(int diceCount) {
		this.diceCount = diceCount;
	}
	public int getSizeCount() {
		return sizeCount;
	}
	public void setSizeCount(int sizeCount) {
		this.sizeCount = sizeCount;
	}
	public int getDiceSum() {
		for (int ctr=0;ctr<this.diceCount;ctr++) {
			DiceEntity dice = new DiceEntity(this.sizeCount);
			this.diceSum = this.diceSum + dice.getSideValue();
		}
		return diceSum;
	}
	public void setDiceSum(int diceSum) {
		this.diceSum = diceSum;
	}
	
	
	
	
}
