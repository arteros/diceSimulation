package com.techmahindra.avaloq.dao.impl;

import org.springframework.stereotype.Repository;

import com.techmahindra.avaloq.dao.DiceSumCombinationDao;
import com.techmahindra.avaloq.model.dto.DiceSumCombination;

@Repository
public class DiceSumCombinationDaoImpl extends AbstractDaoImpl<DiceSumCombination>  implements DiceSumCombinationDao  {

	public DiceSumCombinationDaoImpl() {
		super(DiceSumCombination.class);
		// TODO Auto-generated constructor stub
	}

}
