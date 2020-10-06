package com.techmahindra.avaloq.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.techmahindra.avaloq.dao.DiceDao;
import com.techmahindra.avaloq.dao.impl.DiceDaoImpl;
import com.techmahindra.avaloq.model.beans.DiceBean;
import com.techmahindra.avaloq.model.beans.DiceGameBean;
import com.techmahindra.avaloq.model.beans.DiceSimulationBean;
import com.techmahindra.avaloq.model.beans.DiceSumCombinationBean;
import com.techmahindra.avaloq.model.beans.DiceValueBean;
import com.techmahindra.avaloq.model.container.SearchContainerHelper;
import com.techmahindra.avaloq.model.dto.Dice;
import com.techmahindra.avaloq.model.dto.DiceSimulation;
import com.techmahindra.avaloq.model.dto.DiceSumCombination;
import com.techmahindra.avaloq.model.entity.DiceGame;
import com.techmahindra.avaloq.model.entity.DiceGameSmulation;
import com.techmahindra.avaloq.model.forms.DiceForm;
import com.techmahindra.avaloq.service.DiceService;
import com.techmahindra.avaloq.service.DiceSimulaltionService;
import com.techmahindra.avaloq.service.DiceSumCombinationService;
import com.techmahindra.avaloq.util.HibernateUtil;
import com.techmahindra.avaloq.util.ParserUtil;
import com.techmahindra.avaloq.util.SearchContainerUtil;

@Service 
public class DiceServiceImpl implements DiceService {
	private DiceDao dao;
	public DiceServiceImpl(DiceDaoImpl diceDaoImpl) {
		dao = diceDaoImpl;
		HibernateUtil.getSessionFactory().openSession();
	}
	public DiceServiceImpl() {
		dao = new DiceDaoImpl();
		HibernateUtil.getSessionFactory().openSession();
	}

	@Override
	public DiceBean processDiceSimulation(DiceForm form) {
		DiceSimulaltionService diceSimService = new DiceSimulationServiceImpl();
		DiceSumCombinationService dicesumCombService = new DiceSumCombinationServiceImpl();
		DiceBean bean = new DiceBean();;
		// TODO Auto-generated method stub
		HibernateUtil.openSessionFactory();
		
		Dice dice = saveDice(form);

		
		DiceSimulation diceSimulation = new DiceSimulation();
		diceSimulation.setRollCount(form.getRollCount());
		diceSimulation.setDice(dice);
		diceSimulation = diceSimService.saveDiceSimulation(diceSimulation);
		
		List<DiceValueBean> diceSimulationList = generateDiceSimulation(form);

		for (DiceValueBean diceValueBean: diceSimulationList) {

			DiceSumCombination diceSumCombination = new DiceSumCombination();
			diceSumCombination.setRepeatCount(diceValueBean.getRepeatCount());
			diceSumCombination.setCombinationSum(diceValueBean.getSumValue());
			diceSumCombination.setDiceSimulatio(diceSimulation);
			dicesumCombService.saveDiceSumCombinationDao(diceSumCombination);
		}

		HibernateUtil.closeSessionFactory();
		bean.setDiceSimulationList(diceSimulationList);
		return bean;
	}
	public List<DiceValueBean> generateDiceSimulation(DiceForm form) {
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
			
			diceSimulationList.add(keyValueBean);
		}
		return diceSimulationList;
	}


	public Dice saveDice(DiceForm form) {
		Dice dice = new Dice();
		if (form.getDiceCount() > 0) {
			List<Dice> diceList = dao.retrieveList(SearchContainerUtil.addTwoSearchCriteria("diceCount", "",
					form.getDiceCount(), "sideCount", "", form.getSize()));

			if (diceList.size() > 0) {
				dice = diceList.get(0);
			}
			dice.setDiceCount(form.getDiceCount());
			dice.setSideCount(form.getSize());
			dice.setTotalRollCount(dice.getTotalRollCount() + form.getRollCount());
			dice = dao.saveObjectwithReturn(dice);
		}
		return dice;
	}

	
	@Override
	public DiceGameBean retrieveDiceSimulation(DiceForm form) {
		// TODO Auto-generated method stub
		DiceGameBean diceGameBean = new DiceGameBean();

		HibernateUtil.openSessionFactory();
		List<Dice> diceList = retreiveDiceList();
		
		List<DiceSimulationBean> diceSimulationBeanList = new ArrayList<DiceSimulationBean>();
		for (Dice dice:diceList) {
			DiceSimulationBean diceSimulationBean = new DiceSimulationBean();
			diceSimulationBean.setDiceValue(dice.getDiceCount());
			diceSimulationBean.setSideValue(dice.getSideCount());
			
			int simulationCount = 0;
			List<DiceSimulation> diceSimulationList = dice.getDiceSimulation();
			List<DiceSumCombinationBean> diceSumCombinationBeanList = new ArrayList<DiceSumCombinationBean>();

			DiceGameSmulation diceGameSmulation = new DiceGameSmulation();
			for (DiceSimulation diceSimulation: diceSimulationList) {
				simulationCount++;
				
				for (DiceSumCombination dSim: diceSimulation.getDiceSumCombination()) {
					diceGameSmulation.setKeyValue(dSim.getCombinationSum(), dSim.getRepeatCount());
				}

			}

			processDiceSumCobinationBean(dice, diceSumCombinationBeanList, diceGameSmulation);
			
			diceSimulationBean.setDiceSumCombinationBeanList(diceSumCombinationBeanList);
			diceSimulationBean.setSimulationCount(simulationCount);
			diceSimulationBean.setTotalRollCount(dice.getTotalRollCount());
			diceSimulationBeanList.add(diceSimulationBean);
		}

		HibernateUtil.closeSessionFactory();
		diceGameBean.setDiceSimulationBeanList(diceSimulationBeanList);
		return diceGameBean;
	}
	public List<Dice> retreiveDiceList() {
		SearchContainerHelper container = new SearchContainerHelper();
		return dao.retrieveList(container);
	}
	

	private void processDiceSumCobinationBean(Dice dice, List<DiceSumCombinationBean> diceSumCombinationBeanList,
			DiceGameSmulation diceGameSmulation) {
		for(Entry<Integer, Integer> mapValue: diceGameSmulation.getSimulationMap().entrySet()) {
			DiceSumCombinationBean diceSumCombinationBean = new DiceSumCombinationBean();
			diceSumCombinationBean.setCombinationSum(mapValue.getKey());
			Integer value = mapValue.getValue();
			diceSumCombinationBean.setRepeatCount(value);
			diceSumCombinationBean.setRepeatPercentage(ParserUtil.convertToPercentage(new Double(value)/new Double(dice.getTotalRollCount())));
			diceSumCombinationBeanList.add(diceSumCombinationBean);
		}
	}

}
