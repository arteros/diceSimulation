package com.techmahindra.avaloq.dao.impl;

import org.springframework.stereotype.Repository;

import com.techmahindra.avaloq.dao.DiceSimulationDao;
import com.techmahindra.avaloq.model.dto.DiceSimulation;

@Repository
public class DiceSimulationDaoImpl extends AbstractDaoImpl<DiceSimulation>  implements DiceSimulationDao  {

	public DiceSimulationDaoImpl() {
		super(DiceSimulation.class);
		// TODO Auto-generated constructor stub
	}

}
