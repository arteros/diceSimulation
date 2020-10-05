package com.techmahindra.avaloq.controller;

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
import com.techmahindra.avaloq.model.forms.DiceForm;
import com.techmahindra.avaloq.service.DiceSimulationService;
import com.techmahindra.avaloq.service.impl.DiceSimulationServiceImpl;

@Controller
public class DiceController {
	DiceSimulationService service = new DiceSimulationServiceImpl();

	@RequestMapping(value = "/generateDiceRollSimulations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> generateDiceRollSimulations(int diceCount, int sideCount, int rollCount) {
		System.out.println("hello world welcome:");

		DiceForm form = new DiceForm();
		form.setDiceCount(diceCount);
		form.setRollCount(rollCount);
		String response = validateQueryParameter(diceCount, sideCount, rollCount);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		if (response.equals("")) {
			form.setSize(sideCount);
			DiceBean bean = service.processDiceSimulation(form);
			response = gson.toJson(bean);
			System.out.print(response);
		} else {
			response = gson.toJson(response);
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

	private String validateQueryParameter(int diceCount, int sideCount, int rollCount) {
		String resposnse = "";
		if (diceCount < 1) {
			resposnse = "Dice Count should be greater than 0";
		} else if (rollCount < 1) {
			resposnse = "Roll Count should be greater than 0";
		} else if (sideCount < 4) {
			resposnse = "Sides of Dice should be greater than 3";
		}
		return resposnse;
	}

}