package com.techmahindra.avaloq.util;

import com.techmahindra.avaloq.model.container.SearchContainerHelper;

public class SearchContainerUtil {
	
	public static SearchContainerHelper addOneSearchCriteria(String value) {
		SearchContainerHelper container = new SearchContainerHelper();
		container.setSearchString_1(value);
		return container;
	}
	public static SearchContainerHelper addOneSearchCriteria(String parameter, String value) {
		SearchContainerHelper container = new SearchContainerHelper();
		container.setSearchParameter_1(parameter);
		container.setSearchString_1(value);
		return container;
	}
	public static SearchContainerHelper addOneSearchCriteria(String parameter, String value, String direction, String orderByString) {
		SearchContainerHelper container = new SearchContainerHelper();
		container.setSearchParameter_1(parameter);
		container.setSearchString_1(value);
		container.setOrderByDirection(direction);
		container.setOrderByString(orderByString);
		return container;
	}
	
	public static SearchContainerHelper addOneSearchCriteria(String value, String direction, String orderByString) {
		SearchContainerHelper container = new SearchContainerHelper();
		container.setSearchString_1(value);
		container.setOrderByDirection(direction);
		container.setOrderByString(orderByString);
		return container;
	}
	public static SearchContainerHelper addLongSearchCriteria(String parameter, String value) {
		SearchContainerHelper container = new SearchContainerHelper();
		container.setSearchString_1(value);
		container.setSearchIds(parameter);
		return container;
	}
	public static SearchContainerHelper addTwoSearchCriteria(String parameter1, String value1,Integer intValue1, String parameter2, String value2,Integer intValue2) {
		SearchContainerHelper container = new SearchContainerHelper();
		container.setSearchParameter_1(parameter1);
		container.setSearchString_1(value1);
		container.setSearchInt_1(intValue1);
		container.setSearchParameter_2(parameter2);
		container.setSearchString_2(value2);
		container.setSearchInt_2(intValue2);
		return container;
	}
}
