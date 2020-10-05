package com.techmahindra.avaloq.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.techmahindra.avaloq.dao.DiceDao;
import com.techmahindra.avaloq.dao.DiceSimulationDao;
import com.techmahindra.avaloq.dao.DiceSumCombinationDao;
import com.techmahindra.avaloq.dao.impl.DiceDaoImpl;
import com.techmahindra.avaloq.dao.impl.DiceSimulationDaoImpl;
import com.techmahindra.avaloq.dao.impl.DiceSumCombinationDaoImpl;
import com.techmahindra.avaloq.model.beans.DiceBean;
import com.techmahindra.avaloq.model.beans.DiceValueBean;
import com.techmahindra.avaloq.model.dto.Dice;
import com.techmahindra.avaloq.model.dto.DiceSimulation;
import com.techmahindra.avaloq.model.dto.DiceSumCombination;
import com.techmahindra.avaloq.model.entity.DiceGame;
import com.techmahindra.avaloq.model.entity.DiceGameSmulation;
import com.techmahindra.avaloq.model.forms.DiceForm;
import com.techmahindra.avaloq.service.DiceSimulationService;
import com.techmahindra.avaloq.util.HibernateUtil;

@Service 
public class DiceSimulationServiceImpl implements DiceSimulationService {

	public DiceSimulationServiceImpl() {
		HibernateUtil.getSessionFactory().openSession();
	}

	@Override
	public DiceBean retreiveDiceSimulation(DiceForm form) {
		DiceDao dao = new DiceDaoImpl();
		DiceSimulationDao daoSim = new DiceSimulationDaoImpl();
		DiceSumCombinationDao daoCombiSum = new DiceSumCombinationDaoImpl();
		Dice dice = new Dice();
		// TODO Auto-generated method stub
		DiceBean bean = new DiceBean();
		
		

		dice.setDiceCount(form.getDiceCount());
		dice.setSideCount(form.getSize());
		HibernateUtil.openSessionFactory();
		dice = dao.saveObjectwithReturn(dice);

		
		DiceSimulation diceSimulation = new DiceSimulation();
		diceSimulation.setRollCount(form.getRollCount());
		diceSimulation.setDice(dice);
		diceSimulation = daoSim.saveObjectwithReturn(diceSimulation);
		
		List<DiceValueBean> diceSimulationList = new ArrayList<DiceValueBean>();
		DiceGameSmulation diceGameSmulation = new DiceGameSmulation();
		for (int ctr = 1;ctr<=form.getRollCount();ctr++) {
			DiceGame diceGame = new DiceGame(form.getDiceCount(), form.getSize());
			diceGameSmulation.setValue(diceGame.getDiceSum());
		}
		for(Entry<Integer, Integer> mapValue: diceGameSmulation.getSimulationMap().entrySet()) {
			DiceValueBean keyValueBean = new DiceValueBean();
			keyValueBean.setSumValue(mapValue.getKey());
			keyValueBean.setRepeatCount(mapValue.getValue());
			
			DiceSumCombination diceSumCombination = new DiceSumCombination();
			diceSumCombination.setRepeatCount(mapValue.getValue());
			diceSumCombination.setCombinationSum(mapValue.getKey());
			diceSumCombination.setDiceSimulatio(diceSimulation);
			daoCombiSum.saveObject(diceSumCombination);
			diceSimulationList.add(keyValueBean);
		}

		HibernateUtil.closeSessionFactory();
		bean.setDiceSimulationList(diceSimulationList);
		return bean;
	}

}
