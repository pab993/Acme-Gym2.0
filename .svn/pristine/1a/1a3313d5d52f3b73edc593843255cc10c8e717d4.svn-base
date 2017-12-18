
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActivityService;
import services.CustomerService;
import services.GymService;
import services.ManagerService;
import domain.Activity;
import domain.Customer;
import domain.Gym;
import domain.Manager;

@Controller
@RequestMapping("/gym")
public class GymController extends AbstractController {

	//Services
	// ============================================================================

	@Autowired
	private GymService		gymService;

	@Autowired
	private ActivityService	activityService;

	@Autowired
	private ManagerService	managerService;

	@Autowired
	private CustomerService	customerService;


	//Constructors
	// ============================================================================

	public GymController() {
		super();
	}

	//Listing
	//=============================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Gym> gyms;

		gyms = gymService.findAllNotClosed();

		result = new ModelAndView("gym/list");
		result.addObject("gyms", gyms);
		result.addObject("requestURI", "gym/list.do");

		return result;
	}

	@RequestMapping(value = "/listForCustomer", method = RequestMethod.GET)
	public ModelAndView listForCustomer() {
		ModelAndView result;
		Collection<Gym> gyms;
		Boolean var = true;

		Customer principal = customerService.findByPrincipal();

		gyms = gymService.findAllNotClosed();

		result = new ModelAndView("gym/list");
		result.addObject("var", var);
		result.addObject("principal", principal);
		result.addObject("gyms", gyms);
		result.addObject("requestURI", "gym/listForCustomer.do");

		return result;
	}

	@RequestMapping(value = "/myList", method = RequestMethod.GET)
	public ModelAndView myList() {
		ModelAndView result;
		Collection<Gym> gyms;

		Manager manager = managerService.findByPrincipal();

		gyms = gymService.findAllMyGyms(manager.getId());

		result = new ModelAndView("gym/myList");
		result.addObject("gyms", gyms);
		result.addObject("requestURI", "gym/myList.do");

		return result;
	}

	@RequestMapping(value = "/listByActivity", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int activityId) {
		ModelAndView result;
		Collection<Gym> gyms;

		gyms = gymService.findGymByActivity(activityId);
		Activity activity = activityService.findOne(activityId);

		if (activity == null) {
			result = new ModelAndView("redirect:/panic/misc.do");
		} else {

			result = new ModelAndView("gym/list");
			result.addObject("gyms", gyms);
			result.addObject("requestURI", "gym/listByActivity.do");
		}
		return result;
	}

	//Edition
	//=============================================================================

	@RequestMapping(value = "/joinGym", method = RequestMethod.GET)
	public ModelAndView join(@RequestParam int gymId) {

		ModelAndView resul;
		resul = new ModelAndView("redirect:/gym/listForCustomer.do");

		try {

			Gym gym = gymService.findOne(gymId);
			gymService.join(gym);

		} catch (Throwable oops) {

			resul.addObject("message", "gym.commit.error");
		}
		return resul;
	}

	@RequestMapping(value = "/leaveGym", method = RequestMethod.GET)
	public ModelAndView leave(@RequestParam int gymId) {

		ModelAndView resul;
		resul = new ModelAndView("redirect:/gym/listForCustomer.do");

		try {

			Gym gym = gymService.findOne(gymId);
			gymService.leave(gym);

		} catch (Throwable oops) {

			resul.addObject("message", "gym.commit.error");
		}
		return resul;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int gymId) {
		ModelAndView result;
		Gym gym = gymService.findOne(gymId);

		Manager manager = managerService.findByPrincipal();

		Assert.isTrue(gym.getManager().equals(manager));

		result = this.createEditModelAndView(gym);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Gym gymPruned, BindingResult binding) {
		ModelAndView result;

		Gym gym = gymService.reconstruct(gymPruned, binding);

		if (binding.hasErrors()) {
			result = createEditModelAndView(gym);
		} else {
			try {

				gymService.save(gym);
				result = new ModelAndView("redirect:/gym/myList.do");

			} catch (Throwable oops) {

				result = createEditModelAndView(gym, "gym.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Gym gymPruned, BindingResult binding) {
		ModelAndView result;

		Gym gym = gymService.recover(gymPruned);

		try {
			gymService.delete(gym);

			result = new ModelAndView("redirect:/gym/myList.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(gym, "gym.commit.error");
		}
		return result;
	}

	//Creating
	// ===========================================================================

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		Gym gym = gymService.create();

		result = this.createEditModelAndView(gym);

		return result;
	}

	// Ancillary Methods
	// ===============================================================================

	protected ModelAndView createEditModelAndView(Gym gym) {
		ModelAndView result;

		result = createEditModelAndView(gym, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(Gym gym, String message) {
		ModelAndView result;

		result = new ModelAndView("gym/edit");

		result.addObject("gym", gym);
		result.addObject("message", message);

		return result;
	}

}
