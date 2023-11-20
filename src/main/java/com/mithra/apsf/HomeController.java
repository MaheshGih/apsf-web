package com.mithra.apsf;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mithra.apsf.security.APSFUserDetailsService;
import com.mithra.apsf.user.model.User;
import com.mithra.apsf.user.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private APSFUserDetailsService apsfUserDetailsService;
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model,HttpSession session) {
		logger.info("Welcome home! The client locale is {}.", locale);
		User user = apsfUserDetailsService.getUserFromSession();
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("loggedUser", user);
		session.setAttribute("loggedUser", user);
		return "index";
	}

	@RequestMapping(value = "/reg_template", method = RequestMethod.GET)
	public String Registration(Model model) {

		return "/user/registration";
	}

	@RequestMapping(value = "/registration/static/data", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> signup(@RequestBody User user) {

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("countries", userService.findAllCountries());
			map.put("states", userService.findAllStates());
			map.put("departments", userService.findAllDepartments());
			map.put("qualifications", userService.findAllQualifications());
			map.put("professions", userService.findAllProfessions());
			map.put("castes", userService.findAllCastes());
			
			if( user != null && user.getId()!=null) {
				 user = userService.findUserById(user.getId());
				 if(user!=null) {
					User refUser = user.getReferedUser();
					if(refUser!=null)
						refUser.setReferedUser(null);
					 map.put("user", user); 
				 }
				 map.put("subCastes",userService.findAllSubCasteByCasteId(user.getCasteId()));
				 map.put("constituencies", userService.findAllConstituenciesByStateId(user.getStateId()));
				 map.put("villages", userService.findAllVillagesByConstituencyId(user.getConstituencyId()));
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		ResponseEntity<Map<String, Object>> res = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return res;
	}
	
	@RequestMapping("/users_template")
	public String users() {
		return "/user/users";
	}
	
	@RequestMapping("/my_reference_members")
	public String referedMembers() {
		return "/user/my_reference_members";
	}
	
	@RequestMapping("/dashboard_temp")
	public String dashboard() {
		return "/home/dashboard";
	}
	
	@RequestMapping("/login_temp")
	public String loginTemp() {
		return "/user/login";
	}
	
	@RequestMapping("/forgot_password")
	public String forgetPassword() {
		return "/user/forgot_password";
	}
}
