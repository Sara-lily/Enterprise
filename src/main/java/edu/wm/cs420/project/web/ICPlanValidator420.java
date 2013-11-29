package edu.wm.cs420.project.web;

import java.io.IOException;

import gov.nasa.miic.common.ICPlan;
import gov.nasa.miic.planprocessing.ICPlanExecutor;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ICPlanValidator420 implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ICPlan.class.isAssignableFrom(clazz);
	}

	/**
	 * To be a valid ICPlan, it must have a target & reference instrument 
	 */
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "tgt", "required.target","Target instrument is required.");
		ValidationUtils.rejectIfEmpty(errors, "ref", "required.reference","Reference instrument is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name","Plan name is required.");		
	}

}
