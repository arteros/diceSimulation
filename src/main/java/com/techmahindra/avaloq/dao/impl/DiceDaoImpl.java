package com.techmahindra.avaloq.dao.impl;

import org.springframework.stereotype.Repository;

import com.techmahindra.avaloq.dao.DiceDao;
import com.techmahindra.avaloq.model.dto.Dice;

@Repository
public class DiceDaoImpl extends AbstractDaoImpl<Dice>  implements DiceDao  {

	public DiceDaoImpl() {
		super(Dice.class);
		// TODO Auto-generated constructor stub
	}

}
