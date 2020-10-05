package com.techmahindra.avaloq.model.entity;

import java.util.TreeMap;

public class DiceGameSmulation {
	
	TreeMap<Integer, Integer> simulationMap;
	int key;
	int value;
	
	public DiceGameSmulation() {
		super();
		this.simulationMap =new TreeMap<Integer, Integer>();
	}
	public TreeMap<Integer, Integer> getSimulationMap() {
		return simulationMap;
	}
	public void setSimulationMap(TreeMap<Integer, Integer> simulationMap) {
		this.simulationMap = simulationMap;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.simulationMap.put(key, 1);
	}
	public int getValue(int value) {
		return value;
	}
	public void setValue(int value) {
		Integer existingValue = this.simulationMap.get(value);
		if (existingValue==null) {
			setKey(value);
		}else {
			existingValue = existingValue+1;
			this.simulationMap.replace(value, existingValue);
		}
	}
	
	
	
	
}
