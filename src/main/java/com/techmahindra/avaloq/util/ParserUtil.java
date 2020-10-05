package com.techmahindra.avaloq.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ParserUtil {
	public static Double convertToDouble(String value) {
		if (value!=null) {
			return Double.parseDouble(value);
		}else {
			return 0.00;
		}
	}
	public static Integer convertToInteger(String value) {
		if (value!=null) {
			return Integer.parseInt(value);
		}else {
			return 0;
		}
	}
	public static String convertDoubleToString(Double value) {
		if (value!=null) {
			return Double.toString((double) Math.round(value*100.0)/100.0);
		}else {
			return "0";
		}
	}
	public static String convertIntegerToString(Integer value) {
		if (value!=null) {
			return Integer.toString(value);
		}else {
			return "0";
		}
	}
	public static String convertLongToString(Long value) {
		if (value!=null) {
			return Long.toString(value);
		}else {
			return "0";
		}
	}
	public static Long convertStringToLong(String value) {
		if (value!=null) {
			return Long.parseLong(value);
		}else {
			return null;
		}
	}
	public static String converDoubleToAmountString(Double value) {

        // Example 1 - by using NumberFormat class
        NumberFormat myFormat = NumberFormat.getInstance();
        myFormat.setGroupingUsed(true); // this will also round numbers, 3
        // decimal places

//        double[] numbers = {1.16763, 443330, 3, 517827.17};
//
//        System.out.println("adding commas to number in Java using NumberFormat class");
//        for (double d : numbers) {
//            System.out.println(myFormat.format(d));
//        }
        myFormat.format(value);
        // Example 2 - By using DecimalFormat class
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);

//        System.out.println("adding commas to number in Java using DecimalFormat class");
//        for (double d : numbers) {
//            System.out.println(decimalFormat.format(d));
//        }
        return decimalFormat.format(value);
    }



}
