package com.techmahindra.avaloq.model.entity;

public class DiceEntity {
	
	int sizeCount;
	int sideValue;
	
	public DiceEntity(int sizeCount) {
		super();
		this.sizeCount = sizeCount;
	}
	public int getSizeCount() {
		return sizeCount;
	}
	public void setSizeCount(int sizeCount) {
		this.sizeCount = sizeCount;
	}
	public int getSideValue() {
        int max = this.sizeCount; 
        int min = 1; 
        int range = max - min + 1; 
        int value = (int)(Math.random() * range) + min; 
		return value;
	}
	public void setSideValue(int sideValue) {
		this.sideValue = sideValue;
	}
	
	
}
