package com.techmahindra.avaloq.dao;

import java.util.List;

import com.techmahindra.avaloq.model.container.SearchContainerHelper;


public interface AbstractInterfaceDao<E> {

	public List<E> retrieveList(SearchContainerHelper container);
	public void saveObject(E object);
	public Long save(E object);
	public E saveObjectwithReturn(E object);
	public E findById(SearchContainerHelper container);
	
}
