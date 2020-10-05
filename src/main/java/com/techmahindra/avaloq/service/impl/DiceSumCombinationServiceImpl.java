package com.techmahindra.avaloq.service.impl;


import org.springframework.stereotype.Service;

import com.techmahindra.avaloq.dao.DiceSumCombinationDao;
import com.techmahindra.avaloq.dao.impl.DiceSumCombinationDaoImpl;
import com.techmahindra.avaloq.model.dto.DiceSumCombination;
import com.techmahindra.avaloq.service.DiceSumCombinationService;

@Service 
public class DiceSumCombinationServiceImpl implements DiceSumCombinationService {

	private DiceSumCombinationDao dao = new DiceSumCombinationDaoImpl();


	@Override
	public DiceSumCombination saveDiceSumCombinationDao(DiceSumCombination diceSumCombination) {
		// TODO Auto-generated method stub
		return dao.saveObjectwithReturn(diceSumCombination);
	}


}
