package edu.wm.cs420.project.persistence;

import java.util.List;

import org.springframework.dao.DataAccessException;

import edu.wm.cs420.project.entity.Analysis;

public class InMemoryAnalysisDAO implements AnalysisDAO{

	@Override
	public List<Analysis> getAnalysisListByIcPlanId(Integer id)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeAnalysis(Analysis a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAnalysis(Analysis a) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

}
