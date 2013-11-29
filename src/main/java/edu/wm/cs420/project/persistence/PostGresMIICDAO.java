package edu.wm.cs420.project.persistence;

import java.util.Collection;

import javax.management.Query;

import org.hibernate.SessionFactory;
import org.hibernate.context.CurrentSessionContext;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;


import gov.nasa.miic.common.ICPlan;
import gov.nasa.miic.common.Instrument;
import gov.nasa.miic.persistence.MIICDAO;

public class PostGresMIICDAO implements MIICDAO{
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	@Transactional
	public ICPlan getICPlanByID(Integer id) throws DataAccessException {
		return (ICPlan) sessionFactory.getCurrentSession().get(ICPlan.class, id);
	}

	@Override
	@Transactional
	public Collection<ICPlan> getICPlansByUser(String arg0)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Instrument getInstrumentByName(String name)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return (Instrument) sessionFactory.getCurrentSession().get(Instrument.class, name);
	}

	@Override
	@Transactional
	public Collection<Instrument> getInstruments() throws DataAccessException {
		Collection<Instrument> instruments =sessionFactory.getCurrentSession().createQuery("from Instrument").list();
		return instruments;
	}

	@Override
	@Transactional
	public void storeICPlan(ICPlan icp) {
		sessionFactory.getCurrentSession().persist(icp);
	
	}

	@Override
	@Transactional
	public void storeInstrument(Instrument instr) {
		sessionFactory.getCurrentSession().persist(instr);
		
	}

	@Override
	public void deleteICPlan(ICPlan arg0) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteInstrument(Instrument arg0) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

}
