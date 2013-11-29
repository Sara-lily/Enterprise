
package edu.wm.cs420.project.web;

import gov.nasa.miic.persistence.MIICDAO;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 */
@Controller
public class WelcomeController {

	/**
	 * Custom handler for the welcome view.
	 * <p>
	 * Note that this handler relies on the RequestToViewNameTranslator to
	 * determine the logical view name based on the request URL: "/welcome.jsp"
	 * -&gt; "welcome".
	 */
	@RequestMapping("/")
	public String welcomeHandler() {
		return "welcome";
	}

	
}
