/*package edu.wm.cs420.project.model;
import gov.nasa.miic.common.DataVariableRef;
import gov.nasa.miic.regression.RegressionFilter;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.netlib.util.intW;


public class Analysis implements Serializable{
// @Lob store
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Lob
	ArrayList<RegressionFilter> filters = new ArrayList<RegressionFilter>();
	DataVariableRef target;
	DataVariableRef ref;
	
}
*/

package edu.wm.cs420.project.entity;

import java.util.ArrayList;
import java.util.List;

import gov.nasa.miic.common.DataVariableRef;
import gov.nasa.miic.regression.RegressionFilter;

import javax.persistence.AssociationOverrides;
import javax.persistence.AssociationOverride;
import javax.persistence.JoinColumn;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Column;

@Entity
public class Analysis {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="band", column=@Column(name="target_band"))
	})
	@AssociationOverrides({
		@AssociationOverride(name = "dataVariable", joinColumns = {
				@JoinColumn(name="target_variableName", referencedColumnName="name"),
				@JoinColumn(name="target_instrumentName", referencedColumnName="instrumentName")
		})
	})
	DataVariableRef targetAnalysisVar;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="band", column=@Column(name="reference_band"))
	})
	@AssociationOverrides({
		@AssociationOverride(name = "dataVariable", joinColumns = {
				@JoinColumn(name="reference_variableName", referencedColumnName="name"),
				@JoinColumn(name="reference_instrumentName", referencedColumnName="instrumentName")
		})
	})
	DataVariableRef referenceAnalysisVar;

	@Lob
	ArrayList<RegressionFilter> filters = new ArrayList<RegressionFilter>();
	
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	
	/**
	 * @return the filters
	 */
	public List<RegressionFilter> getFilters() {
		return filters;
	}


	/**
	 * @param filters the filters to set
	 */
	public void setFilters(List<RegressionFilter> filters) {
		this.filters.clear();
		this.filters.addAll(filters);
	}


	/**
	 * @return the targetAnalysisVar
	 */
	public DataVariableRef getTargetAnalysisVar() {
		return targetAnalysisVar;
	}

	/**
	 * @param targetAnalysisVar the targetAnalysisVar to set
	 */
	public void setTargetAnalysisVar(DataVariableRef targetAnalysisVar) {
		this.targetAnalysisVar = targetAnalysisVar;
	}

	/**
	 * @return the referenceAnalysisVar
	 */
	public DataVariableRef getReferenceAnalysisVar() {
		return referenceAnalysisVar;
	}

	/**
	 * @param referenceAnalysisVar the referenceAnalysisVar to set
	 */
	public void setReferenceAnalysisVar(DataVariableRef referenceAnalysisVar) {
		this.referenceAnalysisVar = referenceAnalysisVar;
	}
	
	
}
