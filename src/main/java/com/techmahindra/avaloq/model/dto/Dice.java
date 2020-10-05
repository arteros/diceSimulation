package com.techmahindra.avaloq.model.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "dice")
public class Dice {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "dice_generator")
	@SequenceGenerator(name="dice_generator", sequenceName = "dice_seq", allocationSize=50)
	@Column(name = "id", updatable = false, nullable = false)
	private long id;

	@Column(name = "dice_count")
	private int diceCount;
	
	@Column(name = "side_count")
	private int sideCount;
	
	@Column(name = "total_roll_count")
	private int totalRollCount;
	
	@OneToMany(mappedBy = "dice")
	private List<DiceSimulation> diceSimulation = new ArrayList<DiceSimulation>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getDiceCount() {
		return diceCount;
	}

	public void setDiceCount(int diceCount) {
		this.diceCount = diceCount;
	}

	public int getSideCount() {
		return sideCount;
	}

	public void setSideCount(int sideCount) {
		this.sideCount = sideCount;
	}

	public List<DiceSimulation> getDiceSimulation() {
		return diceSimulation;
	}

	public void setDiceSimulation(List<DiceSimulation> diceSimulation) {
		this.diceSimulation = diceSimulation;
	}

	public int getTotalRollCount() {
		return totalRollCount;
	}

	public void setTotalRollCount(int totalRollCount) {
		this.totalRollCount = totalRollCount;
	}
	
	
	

}
