
package edu.wm.cs420.project.web;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import gov.nasa.miic.common.ICPlan;
import gov.nasa.miic.common.Instrument;
import gov.nasa.miic.common.Instruments;
import gov.nasa.miic.persistence.MIICDAO;
import gov.nasa.miic.planprocessing.ICPlanExecutor;
import gov.nasa.miic.planprocessing.MIICService;
import gov.nasa.miic.web.InstrumentEditor;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 */
@Controller
public class SimplePlanController {

	@Autowired
	private final MIICService miicService = null;

	/**
	 * The binder tells the controller how to convert instrument name to object
	 * @param binder
	 */
	@InitBinder
	private void instrumentBinder(WebDataBinder binder) {	    
	    binder.registerCustomEditor(Instrument.class, new InstrumentEditor(miicService));
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/allPlans")
	public ModelMap allPlans() {
		ModelMap planMap = new ModelMap();
		
		Collection<ICPlan> plans = this.miicService.getICPlansByUser("");
		
		planMap.addAttribute("ICPlans", plans);
		return planMap;
	}
	
	/**
	 * 
	 */
	@RequestMapping(method=RequestMethod.GET, value="/plan")
	public ModelMap planHandler(HttpServletRequest request) {
		ModelMap model = new ModelMap();
		
		ICPlan plan;
		
		// get the plan
		if (request.getParameter("planID") != null) { 
			int id = Integer.parseInt(request.getParameter("planID"));
			
			
			// get the plan from database
			plan = miicService.getICPlanByID(id);		
			
		} else {
			plan = new ICPlan();
		}
		
		model.addAttribute("ICPlan", plan);
		
		Instruments instrs = new Instruments();
		instrs.getInstrumentList().addAll(this.miicService.getInstruments());
		model.addAttribute("instruments",instrs);
		
		return model;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/addPlan")
	public ModelAndView editPlan(HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView();
				
		// get plan name
		String name = request.getParameter("planName");
		
		String ref = request.getParameter("refInstrument");
		String tgt = request.getParameter("tgtInstrument");
		
		// if FORM error, return the /plan view w/ error messages
		
		Instrument tgtInst = miicService.getInstrumentByName(tgt);
		Instrument refInst = miicService.getInstrumentByName(ref);
		
		String planID = request.getParameter("planID");
		
		ICPlan plan = null;
		
		if (!planID.isEmpty()) {
			plan = miicService.getICPlanByID(Integer.parseInt(planID));
			plan.setRef(refInst);
			plan.setTgt(tgtInst);
		} else {
			plan = new ICPlan(tgtInst,refInst);
		}
		plan.setName(name);
	
		// store plan in database
		miicService.storeICPlan(plan);
			
		mv.setViewName("welcome");
				
		return mv;
	}

	
}
