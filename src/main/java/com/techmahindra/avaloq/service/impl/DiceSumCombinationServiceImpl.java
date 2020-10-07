package com.techmahindra.avaloq.service.impl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.techmahindra.avaloq.dao.DiceSumCombinationDao;
import com.techmahindra.avaloq.dao.impl.DiceSumCombinationDaoImpl;
import com.techmahindra.avaloq.model.dto.DiceSumCombination;
import com.techmahindra.avaloq.service.DiceSumCombinationService;

@Service 
public class DiceSumCombinationServiceImpl implements DiceSumCombinationService {
    private static final Logger LOGGER = LogManager.getLogger(DiceSumCombinationServiceImpl.class);

	private DiceSumCombinationDao dao = new DiceSumCombinationDaoImpl();


	@Override
	public DiceSumCombination saveDiceSumCombinationDao(DiceSumCombination diceSumCombination) {
		LOGGER.info("in saveDiceSumCombinationDao");
		// TODO Auto-generated method stub
		return dao.saveObjectwithReturn(diceSumCombination);
	}


}
