package edu.wm.cs420.project.web;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import gov.nasa.miic.common.DataVariable;
import gov.nasa.miic.common.DataVariableRef;
import gov.nasa.miic.common.EventPredictionRange;
import gov.nasa.miic.common.ICPlan;
import gov.nasa.miic.common.Instrument;
import gov.nasa.miic.common.Instruments;
import gov.nasa.miic.planprocessing.ICPlanExecutor;
import gov.nasa.miic.planprocessing.ICPlanExecutorFactory;
import gov.nasa.miic.planprocessing.MIICService;
import gov.nasa.miic.planprocessing.ICPlanExecutor.TaskType;
import gov.nasa.miic.util.MiicUtils;
import gov.nasa.miic.web.InstrumentEditor;

import opendap.dap.functions.Length;

import org.apache.commons.math3.geometry.euclidean.threed.Plane;
import org.netlib.util.intW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudyController {
	
	@Autowired
	private MIICService miicService;
	
	@Autowired 
	private ICPlanExecutorFactory execFactory = null;
	
	@Autowired
	private ICPlanValidator420 icPlanValidator;
	
	@InitBinder
	private void instrumentBinder(WebDataBinder binder) {	    
	    binder.registerCustomEditor(Instrument.class, new InstrumentEditor(miicService));
	}
	
	@InitBinder
	private void dvrBinder(WebDataBinder binder) {	    
	    binder.registerCustomEditor(DataVariableRef.class, new DataVariableRefEditor(miicService));
	}
	
	@InitBinder
	private void gmtDateBinder(WebDataBinder binder) {
	    // The date format to parse or output your dates
	    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	    sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
	    // Create a new CustomDateEditor
	    CustomDateEditor editor = new CustomDateEditor(sdf, true);
	    // Register it as custom editor for the Date type
	    binder.registerCustomEditor(Date.class, editor);
	    binder.registerCustomEditor(Calendar.class, editor);
	}
	
	/**
	 * Make instruments always available in model for this controller
	 * @return
	 */
	@ModelAttribute("instruments")
	public Instruments allInstruments() {
		Instruments instrs = new Instruments();
		instrs.getInstrumentList().addAll(this.miicService.getInstruments());
		return instrs;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/eventStudyParameters")
	public ModelMap allPlans(HttpServletRequest request) {
		ModelMap m = new ModelMap();
		
		ICPlan plan;
		
		// get the plan
		if (request.getParameter("planID") != null) { 
			int id = Integer.parseInt(request.getParameter("planID"));
			
			
			// get the plan from database
			plan = miicService.getICPlanByID(id);		
			
		} else {
			plan = new ICPlan();
			EventPredictionRange epr = new EventPredictionRange();
			plan.addEventPredictionRange(epr);
		}

		m.addAttribute("ICPlan", plan);
		return m;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/createPlan")
	public ModelAndView createPlan(@ModelAttribute("ICPlan") ICPlan plan, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		this.icPlanValidator.validate(plan, result);
		
		if (result.hasErrors()) {
			// go back to edit plan page w/ errors
			mv.addObject("ICPlan", plan);
			mv.setViewName("eventStudyParameters");
			return mv;
		}
		
		try {
			// store plan in database
			miicService.storeICPlan(plan);
		} catch (Exception e) {
			// go back to edit plan page w/ error
			result.reject("bad.icplan",e.getLocalizedMessage());
			mv.addObject("ICPlan", plan);
			mv.setViewName("eventStudyParameters");
			return mv;
		}

		Properties props = plan.getPredictorProperties();
		System.out.println(plan.getId());
		mv.addObject("ICPlan", plan);
		mv.addObject("properties", props);
		mv.setViewName("eventPredictionParameters");
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/eventList")
	public ModelMap eventList(HttpServletRequest request) {
		ModelMap eventMap = new ModelMap();
		
		int id = Integer.parseInt(request.getParameter("id"));
		ICPlan plan = miicService.getICPlanByID(id);
		Properties newProps = new Properties();
		Map params = request.getParameterMap();
		Iterator i = params.keySet().iterator();
		while ( i.hasNext() ) {
			String key = (String) i.next();
			String value = ((String[]) params.get( key ))[ 0 ];
			if (!key.equals("id")) {
				newProps.setProperty(key, value);
			}
		}
		plan.setPredictorProperties(newProps);
		
		ICPlanExecutor planExec = execFactory.createICPlanExecutor(plan);
		System.out.println(newProps);
		
		PrintWriter writer = new PrintWriter(System.out);
		try {
			newProps.store(writer, "Event Predictor Properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Predicting events...");			
		planExec.executePlanUntilTask(TaskType.PREDICT);
		
		// prediction happens in a different thread -- wait for that to finish
		planExec.waitUntilDone();
	
		// print the plan (shows events)
		eventMap.addAttribute("events", plan.getEvents());
		eventMap.addAttribute("ICPlanId", plan.getId());
		
		return eventMap;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/dataCollection")
	public ModelMap dataCollection(HttpServletRequest request) {
		ModelMap dataCollectionMap = new ModelMap();
		int id = Integer.parseInt(request.getParameter("id"));
		ICPlan plan = miicService.getICPlanByID(id);
		dataCollectionMap.addAttribute("ICPlan", plan);
		
		return dataCollectionMap;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/addPlanVars")
	public ModelAndView addPlanVars(@ModelAttribute("ICPlan") ICPlan plan2, BindingResult result, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		int id = Integer.parseInt(request.getParameter("id"));
		ICPlan plan = miicService.getICPlanByID(id);
		
		plan.setTargetDataVariableRefs(plan2.getTargetDataVariableRefs());
		plan.setReferenceDataVariableRefs(plan2.getReferenceDataVariableRefs());
		
		if (plan2.getTargetDataVariableRefs().isEmpty() || plan2.getReferenceDataVariableRefs().isEmpty() ) {
			// go back to edit plan page w/ errors
			mv.addObject("error", "You must select at least one variable from both the target and the reference boxes");
			mv.addObject("ICPlan", plan);
			mv.setViewName("dataCollection");
			System.out.println("here");
			return mv;
		}
		
		try {
			// store plan in database
			miicService.storeICPlan(plan);
		} catch (Exception e) {
			// go back to edit plan page w/ error
			result.reject("bad.icplan",e.getLocalizedMessage());
			mv.addObject("ICPlan", plan);
			mv.setViewName("dataCollection");
			return mv;
		}

		ICPlanExecutor planExec = execFactory.createICPlanExecutor(plan);
		System.out.println("Locating data...");
		planExec.executePlanUntilTask(TaskType.LOCATE);
		planExec.waitUntilDone();
			
		// print the plan
		System.out.println("Plan after event location:");
		plan.print(System.out);
		
		System.out.println("Acquiring data...");
		planExec.executePlanUntilTask(TaskType.COLLECT);
		planExec.waitUntilDone();
		
		mv.addObject("ICPlan", plan);
		mv.setViewName("analysisOptions");
		System.out.println(plan.getTgt());
		System.out.println("I am about to print out data refs");
		System.out.println(plan.getReferenceDataVariableRefs());
		System.out.println(plan.getTargetDataVariableRefs());
		
		return mv;
	}
	

	
}