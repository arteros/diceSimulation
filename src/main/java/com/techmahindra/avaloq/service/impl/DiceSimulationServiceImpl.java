package com.techmahindra.avaloq.service.impl;


import org.springframework.stereotype.Service;

import com.techmahindra.avaloq.dao.DiceSimulationDao;
import com.techmahindra.avaloq.dao.impl.DiceSimulationDaoImpl;
import com.techmahindra.avaloq.model.dto.DiceSimulation;
import com.techmahindra.avaloq.service.DiceSimulaltionService;

@Service 
public class DiceSimulationServiceImpl implements DiceSimulaltionService {

	private DiceSimulationDao dao = new DiceSimulationDaoImpl();

	@Override
	public DiceSimulation saveDiceSimulation(DiceSimulation diceSimulation) {
		// TODO Auto-generated method stub
		return dao.saveObjectwithReturn(diceSimulation);
	}


}
