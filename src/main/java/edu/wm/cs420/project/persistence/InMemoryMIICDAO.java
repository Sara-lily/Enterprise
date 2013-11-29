package edu.wm.cs420.project.persistence;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import gov.nasa.miic.common.ICPlan;
import gov.nasa.miic.common.Instrument;
import gov.nasa.miic.persistence.MIICDAO;


public class InMemoryMIICDAO implements MIICDAO {

	private Collection<Instrument> instruments;
	
	private Map<Integer,ICPlan> planMap = Collections.synchronizedMap(new HashMap<Integer,ICPlan>());
	
	private Integer nextID = 1;
	
	public void setInstruments(Collection<Instrument> instruments) {
		this.instruments = instruments;
	}
	
	public ICPlan getICPlanByID(Integer id) throws DataAccessException {
		if (!planMap.containsKey(id)) return null;
		return planMap.get(id);
	}

	public Collection<ICPlan> getICPlansByUser(String user)
			throws DataAccessException {
		Collection<ICPlan> userPlans = new ArrayList<ICPlan>();
		
		for (ICPlan plan : planMap.values()) {
			if (plan.getOwner().equalsIgnoreCase( user )) {
				userPlans.add(plan);
			}
		}
		return userPlans;
	}

	public Instrument getInstrumentByName(String name)
			throws DataAccessException {
		// find in list
		for (Instrument inst : instruments) {
			if (inst.getName().equalsIgnoreCase(name)) {
				return inst;
			}
		}
		
		// not found
		return null;
	}

	public Collection<Instrument> getInstruments() throws DataAccessException {
		return instruments;
	}

	public void storeICPlan(ICPlan plan) {
		
		// generate ID if doesn't have one
		if (plan.getId() == null) {
			plan.setId(this.nextID++);
		}
		
		planMap.put(plan.getId(), plan);
	}

	public void storeInstrument(Instrument inst) {
		instruments.add(inst);
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
