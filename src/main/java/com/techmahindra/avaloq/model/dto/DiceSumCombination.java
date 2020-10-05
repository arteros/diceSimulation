package com.techmahindra.avaloq.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Dice_sum_combination")
public class DiceSumCombination {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "dice_sum_combi_generator")
	@SequenceGenerator(name="dice_sum_combi_generator", sequenceName = "dice_sum_combi_seq", allocationSize=50)
	@Column(name = "id", updatable = false, nullable = false)
	private long id;

	@Column(name = "combination_sum")
	private int combinationSum;
	
	@Column(name = "repeat_count")
	private int repeatCount;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dice_simulation_id")
	private DiceSimulation diceSimulatio;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public DiceSimulation getDiceSimulatio() {
		return diceSimulatio;
	}

	public void setDiceSimulatio(DiceSimulation diceSimulatio) {
		this.diceSimulatio = diceSimulatio;
	}

	
	
	

}
