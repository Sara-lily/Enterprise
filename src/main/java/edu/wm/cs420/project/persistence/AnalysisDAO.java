package edu.wm.cs420.project.persistence;

import edu.wm.cs420.project.entity.Analysis;
import gov.nasa.miic.common.ICPlan;
import gov.nasa.miic.common.Instrument;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface AnalysisDAO {
	
	public List<Analysis> getAnalysisListByIcPlanId(Integer id) throws DataAccessException;
	
	public void storeAnalysis(Analysis a) ;

	public void deleteAnalysis(Analysis a) throws DataAccessException ;

}
