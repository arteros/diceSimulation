package com.techmahindra.avaloq.service.impl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.techmahindra.avaloq.dao.DiceSimulationDao;
import com.techmahindra.avaloq.dao.impl.DiceSimulationDaoImpl;
import com.techmahindra.avaloq.model.dto.DiceSimulation;
import com.techmahindra.avaloq.service.DiceSimulaltionService;

@Service 
public class DiceSimulationServiceImpl implements DiceSimulaltionService {
    private static final Logger LOGGER = LogManager.getLogger(DiceSimulationServiceImpl.class);

	private DiceSimulationDao dao = new DiceSimulationDaoImpl();

	@Override
	public DiceSimulation saveDiceSimulation(DiceSimulation diceSimulation) {
		LOGGER.info("in saveDiceSimulation");
		// TODO Auto-generated method stub
		return dao.saveObjectwithReturn(diceSimulation);
	}


}
