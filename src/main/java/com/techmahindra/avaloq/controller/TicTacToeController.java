package com.techmahindra.avaloq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techmahindra.avaloq.model.dto.Dice;
import com.techmahindra.avaloq.service.DiceSimulationService;
import com.techmahindra.avaloq.service.impl.DiceSimulationServiceImpl;


@Controller
public class TicTacToeController {
	Dice dice;
	@RequestMapping("/")
	public String welcome() {
		dice = new Dice();
		System.out.println("hello world welcome");
		return "\"hello world welcome\"";
	}


}