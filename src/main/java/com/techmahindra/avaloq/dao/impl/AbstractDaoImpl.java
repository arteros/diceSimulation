package com.techmahindra.avaloq.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.techmahindra.avaloq.model.container.SearchContainerHelper;
import com.techmahindra.avaloq.util.HibernateUtil;
import com.techmahindra.avaloq.util.ParserUtil;

public abstract class AbstractDaoImpl<E> {

	Class<E> entity;
	public AbstractDaoImpl(Class<E> e) {
		entity = e;
	}
	@SuppressWarnings("unchecked")
	public List<E> retrieveList(SearchContainerHelper container) {
	    Session sessionObj= HibernateUtil.retriveSession();
	    if (sessionObj==null) {
	    	sessionObj=HibernateUtil.getSessionFactory().openSession();
	    }
        if (!sessionObj.getTransaction().isActive()) {
        	sessionObj.beginTransaction() ;
        }

        Criteria criteria = sessionObj.createCriteria(entity);
        if (container.getSearchString_1()!=null && !container.getSearchString_1().equals("")) {
    		criteria.add(Restrictions.eq(container.getSearchParameter_1(),container.getSearchString_1() ));
        }else if (container.getSearchInt_1()!=null && !container.getSearchInt_1().equals("")) {
    		criteria.add(Restrictions.eq(container.getSearchParameter_1(),container.getSearchInt_1() ));
        }
        if (container.getSearchInt_2()!=null && !container.getSearchInt_2().equals("")) {
    		criteria.add(Restrictions.eq(container.getSearchParameter_2(),container.getSearchInt_2() ));
        }else if (container.getSearchString_2()!=null && !container.getSearchString_2().equals("")) {
    		criteria.add(Restrictions.eq(container.getSearchParameter_2(),container.getSearchString_2() ));
        }

        List<E> list = new ArrayList<E>();
        for(Object p:criteria.list()){
        	E object=(E)p;
        	list.add(object);
        }
         
        // Execute query with pagination
        
		sessionObj.getTransaction().commit();
		return list;
	}

	public void saveObject(E object) {
	    Session sessionObj= HibernateUtil.retriveSession();
		// TODO Auto-generated method stub
        if (!sessionObj.getTransaction().isActive()) {
        	sessionObj.beginTransaction() ;
        }
		sessionObj.saveOrUpdate(object);
		sessionObj.getTransaction().commit();
	}


	public Long save(E object) {
	    Session sessionObj= HibernateUtil.retriveSession();
		// TODO Auto-generated method stub
        if (!sessionObj.getTransaction().isActive()) {
        	sessionObj.beginTransaction() ;
        }
		Serializable value = sessionObj.save(object);
		sessionObj.getTransaction().commit();
		return ParserUtil.convertStringToLong(value.toString());
	}
	@SuppressWarnings("unchecked")
	public E saveObjectwithReturn(E details) {
	    Session sessionObj= HibernateUtil.retriveSession();
		// TODO Auto-generated method stub

	    if (sessionObj==null) {
	    	sessionObj=HibernateUtil.getSessionFactory().openSession();
	    }
        if (!sessionObj.getTransaction().isActive()) {
        	sessionObj.beginTransaction() ;
        }
        long id = (long) sessionObj.save(details);
        Criteria criteria = sessionObj.createCriteria(entity);
        criteria.add(Restrictions.eq("id", id));

        for(Object p:criteria.list()){
        	details=(E)p;
        }
		sessionObj.getTransaction().commit();
		return details;
	}

	@SuppressWarnings("unchecked")
	public E findById(SearchContainerHelper container) {
	    Session sessionObj= HibernateUtil.retriveSession();
        if (!sessionObj.getTransaction().isActive()) {
        	sessionObj.beginTransaction() ;
        }

        Criteria criteria = sessionObj.createCriteria(entity);
        if (container.getSearchString_1()!=null && !container.getSearchString_1().equals("0")) {
    		criteria.add(Restrictions.eq("id", Long.parseLong(container.getSearchString_1())));
        }
        E object= null;
        for(Object p:criteria.list()){
        	object=(E)p;
        }
         
        // Execute query with pagination
        
		sessionObj.getTransaction().commit();
		return object;
	}
}
