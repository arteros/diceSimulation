package com.techmahindra.avaloq.model.beans;

import java.util.List;

import com.techmahindra.avaloq.model.entity.ErrorMessage;

public class DiceBean {
	List<DiceValueBean> diceSimulationList;
	List<ErrorMessage> errorMessageList;

	public List<DiceValueBean> getDiceSimulationList() {
		return diceSimulationList;
	}

	public void setDiceSimulationList(List<DiceValueBean> diceSimulationList) {
		this.diceSimulationList = diceSimulationList;
	}

	public List<ErrorMessage> getErrorMessageList() {
		return errorMessageList;
	}

	public void setErrorMessageList(List<ErrorMessage> errorMessageList) {
		this.errorMessageList = errorMessageList;
	}

	
	
}
