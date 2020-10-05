package DIceTesting;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map.Entry;

import org.junit.Test;

import com.techmahindra.avaloq.model.beans.DiceBean;
import com.techmahindra.avaloq.model.beans.DiceGameBean;
import com.techmahindra.avaloq.model.beans.DiceSimulationBean;
import com.techmahindra.avaloq.model.beans.DiceSumCombinationBean;
import com.techmahindra.avaloq.model.beans.DiceValueBean;
import com.techmahindra.avaloq.model.entity.DiceEntity;
import com.techmahindra.avaloq.model.entity.DiceGame;
import com.techmahindra.avaloq.model.entity.DiceGameSmulation;
import com.techmahindra.avaloq.model.forms.DiceForm;
import com.techmahindra.avaloq.service.DiceService;
import com.techmahindra.avaloq.service.impl.DiceServiceImpl;

public class DiceRollTeest {


	@Test
	public void diceValueTest() {
		int maxCount = 6;
		DiceEntity dice = new DiceEntity(maxCount);
		// use mock in test....
		assertTrue(dice.getSideValue()< maxCount);
	}

	@Test
	public void diceGameTest() {
		int maxCount = 6;
		int diceCount = 3;
		DiceGame diceGame = new DiceGame(diceCount, maxCount);
		// use mock in test....
		assertTrue(diceGame.getDiceSum()< (maxCount*diceCount));
	}
	

	@Test
	public void diceGameSimulationTest() {
		int maxCount = 6;
		int diceCount = 3;
		int rollCount = 10;
		DiceGameSmulation diceGameSmulation = new DiceGameSmulation();
		int value = 0;
		for (int ctr = 1;ctr<=rollCount;ctr++) {
			DiceGame diceGame = new DiceGame(diceCount, maxCount);
			diceGameSmulation.setValue(diceGame.getDiceSum());
		}
		for(Entry<Integer, Integer> mapValue: diceGameSmulation.getSimulationMap().entrySet()) {
//			System.out.println(mapValue.getKey()+":"+mapValue.getValue());
			value = value + +mapValue.getValue();
		}
		
		// use mock in test....
		assertEquals(value, rollCount);
	}
	
//	@Test
	public void diceGameSimulationServiceTest() {
		DiceService service = new DiceServiceImpl();
		
		
		int maxCount = 6;
		int diceCount = 3;
		int rollCount = 10;
		DiceForm form = new DiceForm();
		form.setDiceCount(diceCount);
		form.setRollCount(rollCount);
		form.setSize(maxCount);
		DiceBean bean = service.processDiceSimulation(form);

		int value = 0;
		for(DiceValueBean valueBean: bean.getDiceSimulationList()) {
			System.out.println(valueBean.getSumValue()+":"+valueBean.getRepeatCount());
			value = value + +valueBean.getRepeatCount();
		}
		
		// use mock in test....
		assertEquals(value, rollCount);
	}
	

	@Test
	public void retrieveDiceGameSimulationResultsTest() {
		DiceService service = new DiceServiceImpl();
		
		DiceGameBean dgbean = service.retrieveDiceSimulation(new DiceForm());

		for (DiceSimulationBean bean: dgbean.getDiceSimulationBeanList()) {

			System.out.println("##############################################");
			System.out.println("bean.getDiceValue():" + bean.getDiceValue());
			System.out.println("bean.getSideValue():" + bean.getSideValue());
			System.out.println("bean.getSimulationCount():" + bean.getSimulationCount());
			System.out.println("bean.getTotalRollCount():" + bean.getTotalRollCount());
			int sum = 0;
			for (DiceSumCombinationBean dscbean: bean.getDiceSumCombinationBeanList()) {

				System.out.println("dscbean.getCombinationSum():" + dscbean.getCombinationSum());
				System.out.println("dscbean.getRepeatCount():" + dscbean.getRepeatCount());
				System.out.println("dscbean.getRepeatPercentage():" + dscbean.getRepeatPercentage());
				sum = dscbean.getRepeatCount() + sum;
			}
			assertEquals(bean.getTotalRollCount(), sum);
		}
		
		// use mock in test....
	}
}
