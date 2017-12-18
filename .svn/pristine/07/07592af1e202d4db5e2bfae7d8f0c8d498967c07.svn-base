/*
 * AdministratorController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.GymService;
import services.ManagerService;
import domain.Customer;
import domain.Gym;
import domain.Manager;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public AdministratorController() {
		super();
	}


	// Services ---------------------------------------------------------------

	@Autowired
	private ManagerService	managerService;

	@Autowired
	private GymService		gymService;

	@Autowired
	private CustomerService	customerService;


	@RequestMapping(value = "/ban", method = RequestMethod.GET)
	public ModelAndView ban(@RequestParam int managerId) {
		ModelAndView resul;
		try {
			managerService.ban(managerId);
			resul = new ModelAndView("redirect:/administrator/listOfManagers.do");
		} catch (Throwable exception) {
			resul = new ModelAndView("redirect:/administrator/listOfManagers.do");
		}

		return resul;
	}

	@RequestMapping(value = "/listOfManagers", method = RequestMethod.GET)
	public ModelAndView listManagers() {
		ModelAndView result;
		Collection<Manager> managers;

		managers = managerService.findAll();

		result = new ModelAndView("administrator/listOfManagers");

		result.addObject("requestURI", "trainer/listOfManagers.do");
		result.addObject("managers", managers);

		return result;
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;

		Collection<Object[]> firstQuery = new ArrayList<Object[]>();
		Collection<Object[]> secondQuery = new ArrayList<Object[]>();
		Collection<Object[]> thirstQuery = new ArrayList<Object[]>();
		Collection<Gym> forthQuery = new ArrayList<Gym>();
		Collection<Customer> fifthQuery = new ArrayList<Customer>();

		firstQuery = gymService.firstQuery();
		secondQuery = gymService.secondQuery();
		thirstQuery = customerService.thirstQuery();
		forthQuery = gymService.forthQuery();
		fifthQuery = customerService.fifthQuery();

		result = new ModelAndView("administrator/dashboard");
		result.addObject("firstQuery", firstQuery);
		result.addObject("secondQuery", secondQuery);
		result.addObject("thirstQuery", thirstQuery);
		result.addObject("forthQuery", forthQuery);
		result.addObject("fifthQuery", fifthQuery);

		result.addObject("requestURI", "administrator/dashboard.do");

		return result;
	}

}
