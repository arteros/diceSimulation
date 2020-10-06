package DIceTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Spy;

import com.techmahindra.avaloq.dao.impl.DiceDaoImpl;
import com.techmahindra.avaloq.model.beans.DiceGameBean;
import com.techmahindra.avaloq.model.beans.DiceSimulationBean;
import com.techmahindra.avaloq.model.beans.DiceSumCombinationBean;
import com.techmahindra.avaloq.model.beans.DiceValueBean;
import com.techmahindra.avaloq.model.container.SearchContainerHelper;
import com.techmahindra.avaloq.model.dto.Dice;
import com.techmahindra.avaloq.model.dto.DiceSimulation;
import com.techmahindra.avaloq.model.dto.DiceSumCombination;
import com.techmahindra.avaloq.model.entity.DiceEntity;
import com.techmahindra.avaloq.model.entity.DiceGame;
import com.techmahindra.avaloq.model.entity.DiceGameSmulation;
import com.techmahindra.avaloq.model.forms.DiceForm;
import com.techmahindra.avaloq.service.impl.DiceServiceImpl;

public class DiceRollTeest {
	@Spy
	private List<Dice> diceList = new ArrayList<Dice>();
	private static Dice dice;
	private static Dice dice2;
	private static DiceDaoImpl diceDao;
	private static DiceServiceImpl diceService;
	private static SearchContainerHelper container = new SearchContainerHelper();
	private static DiceForm form = new DiceForm();

	@BeforeClass
	public static void init() {

		// set EmployeeDAO mock object
		diceDao = mock(DiceDaoImpl.class);
		diceService = spy(DiceServiceImpl.class);

		dice = new Dice();
		dice.setDiceCount(7);
		dice.setSideCount(8);
		dice.setTotalRollCount(100);

		List<DiceSimulation> diceSimulationList = new ArrayList<DiceSimulation>();
		DiceSimulation diceSimulation = new DiceSimulation();
		diceSimulation.setRollCount(100);

		List<DiceSumCombination> diceSumCobinationList = new ArrayList<DiceSumCombination>();
		DiceSumCombination diceSumCombination = new DiceSumCombination();
		diceSumCombination.setCombinationSum(10);
		diceSumCombination.setRepeatCount(50);
		diceSumCobinationList.add(diceSumCombination);
		DiceSumCombination diceSumCombination1 = new DiceSumCombination();
		diceSumCombination1.setCombinationSum(11);
		diceSumCombination1.setRepeatCount(50);
		diceSumCobinationList.add(diceSumCombination1);

		diceSimulation.setDiceSumCombination(diceSumCobinationList);

		diceSimulationList.add(diceSimulation);
		dice.setDiceSimulation(diceSimulationList);

		dice2 = new Dice();
		dice2.setDiceCount(7);
		dice2.setSideCount(8);
		dice2.setTotalRollCount(200);

		List<DiceSimulation> diceSimulationList2 = new ArrayList<DiceSimulation>();
		DiceSimulation diceSimulation2 = new DiceSimulation();
		diceSimulation2.setRollCount(200);

		List<DiceSumCombination> diceSumCobinationList2 = new ArrayList<DiceSumCombination>();
		DiceSumCombination diceSumCombination2 = new DiceSumCombination();
		diceSumCombination2.setCombinationSum(10);
		diceSumCombination2.setRepeatCount(100);
		diceSumCobinationList2.add(diceSumCombination2);
		DiceSumCombination diceSumCombination3 = new DiceSumCombination();
		diceSumCombination3.setCombinationSum(11);
		diceSumCombination3.setRepeatCount(100);
		diceSumCobinationList2.add(diceSumCombination3);

		diceSimulation2.setDiceSumCombination(diceSumCobinationList2);

		diceSimulationList2.add(diceSimulation2);
		dice2.setDiceSimulation(diceSimulationList2);

		// stubbing is done for test cases
		when(diceDao.retrieveList(container)).thenReturn(Arrays.asList(dice, dice2));
		when(diceService.retreiveDiceList()).thenReturn(Arrays.asList(dice, dice2));
		when(diceService.saveDice(form)).thenReturn(dice);
		// when(diceService.saveDice(form)).thenReturn(dice);

	}

	@Test
	public void diceValueTest() {
		int maxCount = 6;
		DiceEntity dice = new DiceEntity(maxCount);
		// use mock in test....
		assertTrue(dice.getSideValue() <= maxCount);
	}

	@Test
	public void diceGameTest() {
		int maxCount = 6;
		int diceCount = 3;
		DiceGame diceGame = new DiceGame(diceCount, maxCount);
		// use mock in test....
		assertTrue(diceGame.getDiceSum() < (maxCount * diceCount));
	}

	@Test
	public void diceGameSimulationTest() {
		int maxCount = 6;
		int diceCount = 3;
		int rollCount = 10;
		DiceGameSmulation diceGameSmulation = new DiceGameSmulation();
		int value = 0;
		for (int ctr = 1; ctr <= rollCount; ctr++) {
			DiceGame diceGame = new DiceGame(diceCount, maxCount);
			diceGameSmulation.setValue(diceGame.getDiceSum());
		}
		for (Entry<Integer, Integer> mapValue : diceGameSmulation.getSimulationMap().entrySet()) {
			// System.out.println(mapValue.getKey()+":"+mapValue.getValue());
			value = value + +mapValue.getValue();
		}

		// use mock in test....
		assertEquals(value, rollCount);
	}

	@Test
	public void diceGameSimulationServiceTest() {

		int sideCount = 8;
		int diceCount = 7;
		int rollCount = 10;
		DiceForm form = new DiceForm();
		form.setDiceCount(diceCount);
		form.setRollCount(rollCount);
		form.setSize(sideCount);


		List<DiceValueBean> beanList = diceService.generateDiceSimulation(form);

		int value = 0;
		for (DiceValueBean valueBean : beanList) {
			System.out.println(valueBean.getSumValue() + ":" + valueBean.getRepeatCount());
			value = value + +valueBean.getRepeatCount();
		}

		// use mock in test....
		assertEquals(value, rollCount);
	}

	@Test
	public void retrieveDiceGameSimulationResultsTest() {

		DiceServiceImpl mockService = mock(DiceServiceImpl.class);

		DiceForm form = new DiceForm();
		DiceGameBean beans = new DiceGameBean();
		List<DiceSimulationBean> diceSimulationBeanList = new ArrayList<DiceSimulationBean>();
		DiceSimulationBean diceSimulationBean = new DiceSimulationBean();
		diceSimulationBean.setDiceValue(3);
		diceSimulationBean.setSideValue(5);
		diceSimulationBean.setSimulationCount(2);
		diceSimulationBean.setTotalRollCount(100);

		List<DiceSumCombinationBean> diceSumCombinationBeanList = new ArrayList<DiceSumCombinationBean>();
		DiceSumCombinationBean diceSumCombinationBean = new DiceSumCombinationBean();
		diceSumCombinationBean.setCombinationSum(10);
		diceSumCombinationBean.setRepeatCount(50);
		diceSumCombinationBean.setRepeatPercentage(50.0);
		diceSumCombinationBean.setRepeatPercentageString("50%");
		diceSumCombinationBeanList.add(diceSumCombinationBean);

		diceSumCombinationBean = new DiceSumCombinationBean();
		diceSumCombinationBean.setCombinationSum(11);
		diceSumCombinationBean.setRepeatCount(50);
		diceSumCombinationBean.setRepeatPercentage(50.0);
		diceSumCombinationBean.setRepeatPercentageString("50%");
		diceSumCombinationBeanList.add(diceSumCombinationBean);

		diceSimulationBean.setDiceSumCombinationBeanList(diceSumCombinationBeanList);

		diceSimulationBeanList.add(diceSimulationBean);
		beans.setDiceSimulationBeanList(diceSimulationBeanList);

		when(mockService.retrieveDiceSimulation(form)).thenReturn(beans);
		System.out.println(
				"##############:" + mockService.retrieveDiceSimulation(form).getDiceSimulationBeanList().size());

		DiceGameBean dgbean = mockService.retrieveDiceSimulation(form);

		for (DiceSimulationBean bean : dgbean.getDiceSimulationBeanList()) {

			System.out.println("##############################################");
			System.out.println("bean.getDiceValue():" + bean.getDiceValue());
			System.out.println("bean.getSideValue():" + bean.getSideValue());
			System.out.println("bean.getSimulationCount():" + bean.getSimulationCount());
			System.out.println("bean.getTotalRollCount():" + bean.getTotalRollCount());
			int sum = 0;
			for (DiceSumCombinationBean dscbean : bean.getDiceSumCombinationBeanList()) {

				System.out.println("dscbean.getCombinationSum():" + dscbean.getCombinationSum());
				System.out.println("dscbean.getRepeatCount():" + dscbean.getRepeatCount());
				System.out.println("dscbean.getRepeatPercentage():" + dscbean.getRepeatPercentage());
				sum = dscbean.getRepeatCount() + sum;
			}
			assertEquals(bean.getTotalRollCount(), sum);
		}

		// use mock in test....
	}

	@Test
	public void saveDiceTest() {
		Dice Dice = diceService.saveDice(form);
		assertEquals(7, Dice.getDiceCount());
	}

	@Test
	public void retrieveAllDaoDiceTest() {
		List<Dice> listDice = diceDao.retrieveList(container);
		assertEquals(2, listDice.size());
	}

	@Test
	public void retrieveDiceGameSimulationResultsDaoTest() {

		List<Dice> listDice = diceService.retreiveDiceList();
		assertEquals(7, listDice.get(0).getDiceCount());
	}

	@Test
	public void retrieveDiceGameSimulationResultsDao1Test() {

		DiceGameBean diceGameBean = diceService.retrieveDiceSimulation(form);
		System.out.println(diceGameBean.getDiceSimulationBeanList().size());
		assertEquals(7, diceGameBean.getDiceSimulationBeanList().get(0).getDiceValue());

		for (DiceSimulationBean bean : diceGameBean.getDiceSimulationBeanList()) {

			int sum = 0;
			for (DiceSumCombinationBean dscbean : bean.getDiceSumCombinationBeanList()) {

				sum = dscbean.getRepeatCount() + sum;
			}
			assertEquals(bean.getTotalRollCount(), sum);
		}
	}
}
