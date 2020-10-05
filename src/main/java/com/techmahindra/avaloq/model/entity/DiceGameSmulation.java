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
	public void setKey(int key, int value) {
		this.simulationMap.put(key, value);
	}
	public int getValue(int value) {
		return value;
	}
	public void setValue(int value) {
		Integer existingValue = this.simulationMap.get(value);
		if (existingValue==null) {
			setKey(value, 1);
		}else {
			existingValue = existingValue+1;
			this.simulationMap.replace(value, existingValue);
		}
	}
	public void setKeyValue(int key, int value) {
		Integer existingValue = this.simulationMap.get(key);
		if (existingValue==null) {
			setKey(key, value);
		}else {
			existingValue = existingValue+value;
			this.simulationMap.replace(key, existingValue);
		}
	}
	
	
	
	
}
