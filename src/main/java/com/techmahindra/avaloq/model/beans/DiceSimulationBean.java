package com.techmahindra.avaloq.model.beans;

import java.util.List;

public class DiceSimulationBean {

	private int diceValue;
	private int sideValue;
	private int simulationCount;
	private int totalRollCount;
	private List<DiceSumCombinationBean> diceSumCombinationBeanList;
	public int getDiceValue() {
		return diceValue;
	}
	public void setDiceValue(int diceValue) {
		this.diceValue = diceValue;
	}
	public int getSideValue() {
		return sideValue;
	}
	public void setSideValue(int sideValue) {
		this.sideValue = sideValue;
	}
	public int getSimulationCount() {
		return simulationCount;
	}
	public void setSimulationCount(int simulationCount) {
		this.simulationCount = simulationCount;
	}
	public int getTotalRollCount() {
		return totalRollCount;
	}
	public void setTotalRollCount(int totalRollCount) {
		this.totalRollCount = totalRollCount;
	}
	public List<DiceSumCombinationBean> getDiceSumCombinationBeanList() {
		return diceSumCombinationBeanList;
	}
	public void setDiceSumCombinationBeanList(List<DiceSumCombinationBean> diceSumCombinationBeanList) {
		this.diceSumCombinationBeanList = diceSumCombinationBeanList;
	}
	
	
	
}
