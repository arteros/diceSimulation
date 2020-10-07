package com.techmahindra.avaloq.model.enums;

public enum ErrorEnum {
		  DICE_COUNT("1","Dice Count should be greater than 0"),
		  ROLL_COUNT("2","Roll Count should be greater than 0"),
		  SIDE_COUNT("3","Sides of Dice should be greater than 3");
		  
	private String code;
	private String errorMessage;
	
	private ErrorEnum(String code, String errorMessage) {
		this.code = code;
		this.errorMessage = errorMessage;
	}
	public String getCode() {
		return code;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	
	
}
