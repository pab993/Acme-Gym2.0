
package controllers;

import java.util.ArrayList;
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
import services.TrainerService;
import domain.Activity;
import domain.Customer;
import domain.Gym;
import domain.Manager;
import domain.Trainer;
import forms.SearchTemplateForm;

@Controller
@RequestMapping("/activity")
public class ActivityController extends AbstractController {

	//Services
	// ============================================================================

	@Autowired
	private ActivityService	activityService;

	@Autowired
	private GymService		gymService;

	@Autowired
	private ManagerService	managerService;

	@Autowired
	private TrainerService	trainerService;

	@Autowired
	private CustomerService	customerService;


	//Constructors
	// ============================================================================

	public ActivityController() {
		super();
	}

	//Listing
	//=============================================================================

	@RequestMapping(value = "/listForCustomer", method = RequestMethod.GET)
	public ModelAndView listForCustomer() {
		ModelAndView result;
		Collection<Activity> activities;
		Boolean var = true;

		Customer principal = customerService.findByPrincipal();
		activities = activityService.findActivitiesNotClosed();

		result = new ModelAndView("activity/list");
		result.addObject("var", var);
		result.addObject("principal", principal);
		result.addObject("activities", activities);
		result.addObject("requestURI", "activity/listForCustomer.do");

		return result;

	}

	@RequestMapping(value = "/listByGym", method = RequestMethod.GET)
	public ModelAndView listActivitiesByGym(@RequestParam int gymId) {
		ModelAndView result;
		Collection<Activity> activities;

		activities = activityService.findActivitiesByGymId(gymId);
		Gym gym = gymService.findOne(gymId);

		if (gym == null) {
			result = new ModelAndView("redirect:/panic/misc.do");
		} else {

			result = new ModelAndView("activity/list");
			result.addObject("activities", activities);
			result.addObject("requestURI", "activity/listByGym.do");
		}
		return result;
	}

	@RequestMapping(value = "/listOfGym", method = RequestMethod.GET)
	public ModelAndView listActivitiesOfGym(@RequestParam int gymId) {
		ModelAndView result;
		Collection<Activity> activities;

		activities = activityService.findActivitiesOfGymId(gymId);
		Gym gym = gymService.findOne(gymId);
		Manager manager = managerService.findByPrincipal();

		if (gym == null) {
			result = new ModelAndView("redirect:/panic/misc.do");
		} else {

			result = new ModelAndView("activity/list");
			result.addObject("gym", gym);
			result.addObject("manager", manager);
			result.addObject("activities", activities);
			result.addObject("requestURI", "activity/listOfGym.do");
		}
		return result;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Activity> activities;

		activities = activityService.findActivitiesNotClosed();

		result = new ModelAndView("activity/list");
		result.addObject("activities", activities);
		result.addObject("requestURI", "activity/list.do");

		return result;
	}

	//Search
	// ===================================================================================

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search() {
		ModelAndView result;
		Collection<Activity> activities = new ArrayList<Activity>();

		result = new ModelAndView("activity/search");
		result.addObject("searchTemplateForm", new SearchTemplateForm());
		result.addObject("activities", activities);
		result.addObject("requestURI", "activity/search.do");

		return result;

	}

	@RequestMapping(value = "/search", method = RequestMethod.POST, params = "search")
	public ModelAndView search(SearchTemplateForm searchTemplateForm, BindingResult binding) {
		ModelAndView result;
		Collection<Activity> activities;

		if (binding.hasErrors()) {
			result = createEditModelAndView(searchTemplateForm);
		} else {
			try {
				activities = activityService.search(searchTemplateForm.getKeyword(), searchTemplateForm.getDay(), searchTemplateForm.getTime());
				result = new ModelAndView("activity/search");

				result.addObject("searchTemplateForm", searchTemplateForm);
				result.addObject("activities", activities);
				result.addObject("requestURI", "activity/search.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(searchTemplateForm, "searchTemplateForm.commit.error");
			}
		}

		return result;
	}

	//Creating
	// ===========================================================================

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int gymId) {
		ModelAndView result;

		Gym gym = gymService.findOne(gymId);

		if (gym == null) {
			result = new ModelAndView("redirect:/panic/misc.do");
		} else {
			Activity activity = activityService.create(gym);
			result = this.createEditModelAndView2(activity);
		}
		return result;
	}

	//Edition
	//=============================================================================

	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int activityId) {
		ModelAndView result;
		Activity activity = activityService.findOne(activityId);

		Manager manager = managerService.findByPrincipal();

		Assert.isTrue(activity.getGym().getManager().equals(manager));

		activity = activityService.cancelActivity(activity);

		result = new ModelAndView("redirect:/activity/listOfGym.do?gymId=" + activity.getGym().getId());
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Activity activityPruned, BindingResult binding) {
		ModelAndView result;

		Activity activity = activityService.reconstruct(activityPruned, binding);

		if (binding.hasErrors()) {
			result = createEditModelAndView2(activity);
		} else {
			try {
				activity = activityService.save(activity);
				activityService.save3(activity);
				result = new ModelAndView("redirect:/activity/listOfGym.do?gymId=" + activity.getGym().getId());

			} catch (Throwable oops) {

				result = createEditModelAndView2(activity, "activity.commit.error");
			}
		}
		return result;
	}

	//	@RequestMapping(value = "/assign", method = RequestMethod.GET)
	//	public ModelAndView editAssign(@RequestParam int activityId) {
	//
	//		ModelAndView result;
	//
	//		Activity activity = activityService.findOne(activityId);
	//		Manager manager = managerService.findByPrincipal();
	//		Collection<Trainer> trainers = trainerService.findTrainerOfAGym(activityId);
	//
	//		if (activity == null) {
	//			result = new ModelAndView("redirect:/panic/misc.do");
	//		} else {
	//
	//			result = new ModelAndView("activity/assign");
	//			result.addObject("trainers", trainers);
	//			result.addObject("manager", manager);
	//			result.addObject("gym", activity.getGym());
	//			result.addObject("activity", activity);
	//		}
	//		return result;
	//	}
	//
	//	@RequestMapping(value = "/assign", method = RequestMethod.POST, params = "save")
	//	public ModelAndView saveAssign(Activity activityPruned, BindingResult binding) {
	//		ModelAndView result;
	//
	//		try {
	//			Activity activity = activityService.reconstruct2(activityPruned, binding);
	//
	//			if (binding.hasErrors())
	//				result = this.createEditModelAndView3(activity, "activity.commit.error");
	//			else {
	//				result = new ModelAndView("redirect:/activity/listOfGym.do?gymId=" + activity.getGym().getId());
	//				activity = activityService.save3(activity);
	//
	//			}
	//		} catch (final Throwable oops) {
	//			result = this.createEditModelAndView3(activityPruned, "activity.commit.error");
	//		}
	//
	//		return result;
	//	}

	@RequestMapping(value = "/joinActivity", method = RequestMethod.GET)
	public ModelAndView join(@RequestParam int activityId) {

		ModelAndView resul;
		resul = new ModelAndView("redirect:/activity/listForCustomer.do");

		try {

			Activity activity = activityService.findOne(activityId);
			activityService.join(activity);

		} catch (Throwable oops) {

			resul.addObject("message", "activity.commit.error");
		}
		return resul;
	}

	@RequestMapping(value = "/leaveActivity", method = RequestMethod.GET)
	public ModelAndView leave(@RequestParam int activityId) {

		ModelAndView resul;
		resul = new ModelAndView("redirect:/activity/listForCustomer.do");

		try {

			Activity activity = activityService.findOne(activityId);
			activityService.leave(activity);

		} catch (Throwable oops) {

			resul.addObject("message", "activity.commit.error");
		}
		return resul;
	}

	// Ancillary Methods
	// ===============================================================================

	protected ModelAndView createEditModelAndView(SearchTemplateForm searchTemplateForm) {
		ModelAndView result;

		result = createEditModelAndView(searchTemplateForm, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(SearchTemplateForm searchTemplateForm, String message) {
		ModelAndView result;

		result = new ModelAndView("activity/search");

		result.addObject("searchTemplateForm", searchTemplateForm);
		result.addObject("message", message);

		return result;
	}

	// ========================================================================================================

	protected ModelAndView createEditModelAndView2(Activity activity) {
		ModelAndView result;

		result = createEditModelAndView2(activity, null);
		return result;
	}

	protected ModelAndView createEditModelAndView2(Activity activity, String message) {
		ModelAndView result;

		Collection<Trainer> trainers = trainerService.findTrainers(activity.getGym().getId());

		result = new ModelAndView("activity/edit");

		result.addObject("trainers", trainers);
		result.addObject("activity", activity);
		result.addObject("gym", activity.getGym());
		result.addObject("message", message);

		return result;
	}

	// ========================================================================================================

}
