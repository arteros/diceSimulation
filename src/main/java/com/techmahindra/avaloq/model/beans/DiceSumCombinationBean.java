package com.techmahindra.avaloq.model.beans;

public class DiceSumCombinationBean {

	private int combinationSum;
	private int repeatCount;
	private Double repeatPercentage;
	private String repeatPercentageString;
	
	public int getCombinationSum() {
		return combinationSum;
	}
	public void setCombinationSum(int combinationSum) {
		this.combinationSum = combinationSum;
	}
	public int getRepeatCount() {
		return repeatCount;
	}
	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}
	public double getRepeatPercentage() {
		return repeatPercentage;
	}
	public String getRepeatPercentageString() {
		return repeatPercentageString;
	}
	public void setRepeatPercentageString(String repeatPercentageString) {
		this.repeatPercentageString = repeatPercentageString;
	}
	public void setRepeatPercentage(Double repeatPercentage) {
		this.repeatPercentage = repeatPercentage;
		setRepeatPercentageString(repeatPercentage+"%");
	}
	
	
	
}
