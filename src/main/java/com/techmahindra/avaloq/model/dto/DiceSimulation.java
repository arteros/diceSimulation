package com.techmahindra.avaloq.model.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "dice_simulation")
public class DiceSimulation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "dice_sim_generator")
	@SequenceGenerator(name="dice_sim_generator", sequenceName = "dice_sim_seq", allocationSize=50)
	@Column(name = "id", updatable = false, nullable = false)
	private long id;

	@Column(name = "roll_count")
	private int rollCount;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dice_id")
	private Dice dice;
	
	@OneToMany(mappedBy = "diceSimulatio")
	private List<DiceSumCombination> diceSumCombination = new ArrayList<DiceSumCombination>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getRollCount() {
		return rollCount;
	}

	public void setRollCount(int rollCount) {
		this.rollCount = rollCount;
	}

	public Dice getDice() {
		return dice;
	}

	public void setDice(Dice dice) {
		this.dice = dice;
	}

	public List<DiceSumCombination> getDiceSumCombination() {
		return diceSumCombination;
	}

	public void setDiceSumCombination(List<DiceSumCombination> diceSumCombination) {
		this.diceSumCombination = diceSumCombination;
	}
	
	
	

}
