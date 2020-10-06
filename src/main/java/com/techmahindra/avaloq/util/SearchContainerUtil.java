package com.techmahindra.avaloq.util;

import com.techmahindra.avaloq.model.container.SearchContainerHelper;

public class SearchContainerUtil {
	
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
