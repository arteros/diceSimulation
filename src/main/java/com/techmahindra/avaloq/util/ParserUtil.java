package com.techmahindra.avaloq.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ParserUtil {
	public static Long convertStringToLong(String value) {
		if (value!=null) {
			return Long.parseLong(value);
		}else {
			return null;
		}
	}
	public static Double convertToPercentage(Double value) {
		Double newValue = 0.0;
		newValue = value * 100.0;
		BigDecimal bd = new BigDecimal(newValue).setScale(2, RoundingMode.HALF_UP);
		newValue = bd.doubleValue();
		return newValue;
	}


}
