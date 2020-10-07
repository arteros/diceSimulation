package com.techmahindra.avaloq.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.techmahindra.avaloq.model.beans.DiceBean;
import com.techmahindra.avaloq.model.beans.DiceGameBean;
import com.techmahindra.avaloq.model.entity.ErrorMessage;
import com.techmahindra.avaloq.model.enums.ErrorEnum;
import com.techmahindra.avaloq.model.forms.DiceForm;
import com.techmahindra.avaloq.service.DiceService;
import com.techmahindra.avaloq.service.impl.DiceServiceImpl;

@Controller
public class DiceController {
	DiceService service = new DiceServiceImpl();

	@RequestMapping(value = "/generateDiceRollSimulations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> generateDiceRollSimulations(int diceCount, int sideCount, int rollCount) {
		System.out.println("hello world welcome:");

		String response = "";
		DiceBean diceBean = validateQueryParameter(diceCount, sideCount, rollCount);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		if (diceBean.getErrorMessageList().size()==0) {
			DiceForm form = new DiceForm();
			form.setDiceCount(diceCount);
			form.setRollCount(rollCount);
			form.setSize(sideCount);
			DiceBean bean = service.processDiceSimulation(form);
			response = gson.toJson(bean);
		} else {
			response = gson.toJson(diceBean);
		}
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/retrieveDiceRollSimulations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> retrieveDiceRollSimulations() {
		System.out.println("hello world welcome:");

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		DiceGameBean bean = service.retrieveDiceSimulation(new DiceForm());
		String response = gson.toJson(bean);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	private DiceBean validateQueryParameter(int diceCount, int sideCount, int rollCount) {
		DiceBean diceBean = new DiceBean();
		List<ErrorMessage> errorMessageList = new ArrayList<ErrorMessage>();
		if (diceCount < 1) {
			ErrorMessage errorMessage = new ErrorMessage();
			errorMessage.setErrorCode(ErrorEnum.DICE_COUNT.getCode());
			errorMessage.setErrorMessages(ErrorEnum.DICE_COUNT.getErrorMessage());
			errorMessageList.add(errorMessage);
		} 
		if (rollCount < 1) {
			ErrorMessage errorMessage = new ErrorMessage();
			errorMessage.setErrorCode(ErrorEnum.ROLL_COUNT.getCode());
			errorMessage.setErrorMessages(ErrorEnum.ROLL_COUNT.getErrorMessage());
			errorMessageList.add(errorMessage);
		} 
		if (sideCount < 4) {
			ErrorMessage errorMessage = new ErrorMessage();
			errorMessage.setErrorCode(ErrorEnum.SIDE_COUNT.getCode());
			errorMessage.setErrorMessages(ErrorEnum.SIDE_COUNT.getErrorMessage());
			errorMessageList.add(errorMessage);
		}

		diceBean.setErrorMessageList(errorMessageList);
		return diceBean;
	}

}