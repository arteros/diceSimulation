package com.techmahindra.avaloq.service;

import com.techmahindra.avaloq.model.beans.DiceBean;
import com.techmahindra.avaloq.model.beans.DiceGameBean;
import com.techmahindra.avaloq.model.forms.DiceForm;

public interface DiceSimulationService {
	
	DiceBean processDiceSimulation(DiceForm form);
	DiceGameBean retrieveDiceSimulation(DiceForm form);
	
}
