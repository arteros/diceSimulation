package com.techmahindra.avaloq.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import com.techmahindra.avaloq.service.DiceSimulationService;
import com.techmahindra.avaloq.util.HibernateUtil;
import com.techmahindra.avaloq.util.SearchContainerUtil;

@Service 
public class DiceSimulationServiceImpl implements DiceSimulationService {

	public DiceSimulationServiceImpl() {
		HibernateUtil.getSessionFactory().openSession();
	}
	private DiceDao dao = new DiceDaoImpl();

	@Override
	public DiceBean processDiceSimulation(DiceForm form) {
		DiceSimulationDao daoSim = new DiceSimulationDaoImpl();
		DiceSumCombinationDao daoCombiSum = new DiceSumCombinationDaoImpl();
		DiceBean bean = new DiceBean();;
		// TODO Auto-generated method stub
		HibernateUtil.openSessionFactory();
		List<Dice> diceList = dao.retrieveList(SearchContainerUtil.addTwoSearchCriteria("diceCount","",form.getDiceCount(), "sideCount", "",form.getSize()));
		Dice dice = new Dice();
		if (diceList.size()>0) {
			dice = diceList.get(0);
		}
		

		dice.setDiceCount(form.getDiceCount());
		dice.setSideCount(form.getSize());
		dice.setTotalRollCount(dice.getTotalRollCount()+form.getRollCount());
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

	@Override
	public DiceGameBean retrieveDiceSimulation(DiceForm form) {
		// TODO Auto-generated method stub
		DiceGameBean diceGameBean = new DiceGameBean();

		HibernateUtil.openSessionFactory();
		List<Dice> diceList = dao.retrieveList(new SearchContainerHelper());
		
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

			for(Entry<Integer, Integer> mapValue: diceGameSmulation.getSimulationMap().entrySet()) {
				DiceSumCombinationBean diceSumCombinationBean = new DiceSumCombinationBean();
				diceSumCombinationBean.setCombinationSum(mapValue.getKey());
				Integer value = mapValue.getValue();
				diceSumCombinationBean.setRepeatCount(value);
				Double  repeatPercentage = new Double(value)/new Double(dice.getTotalRollCount());
				repeatPercentage = repeatPercentage * 100.0;
				BigDecimal bd = new BigDecimal(repeatPercentage).setScale(2, RoundingMode.HALF_UP);
				repeatPercentage = bd.doubleValue();
				
				diceSumCombinationBean.setRepeatPercentage(repeatPercentage);
				diceSumCombinationBeanList.add(diceSumCombinationBean);
			}
			diceSimulationBean.setDiceSumCombinationBeanList(diceSumCombinationBeanList);
			diceSimulationBean.setSimulationCount(simulationCount);
			diceSimulationBean.setTotalRollCount(dice.getTotalRollCount());
			diceSimulationBeanList.add(diceSimulationBean);
		}

		HibernateUtil.closeSessionFactory();
		diceGameBean.setDiceSimulationBeanList(diceSimulationBeanList);
		return diceGameBean;
	}

}
