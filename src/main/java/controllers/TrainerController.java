
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActivityService;
import services.GymService;
import services.ManagerService;
import services.TrainerService;
import domain.Activity;
import domain.Gym;
import domain.Manager;
import domain.Trainer;
import forms.TrainerForm;

@Controller
@RequestMapping("/trainer")
public class TrainerController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public TrainerController() {
		super();
	}


	//Services
	// ============================================================================

	@Autowired
	private TrainerService	trainerService;

	@Autowired
	private ActivityService	activityService;

	@Autowired
	private GymService		gymService;

	@Autowired
	private ManagerService	managerService;


	//Listing
	// ============================================================================

	@RequestMapping(value = "/listByActivity", method = RequestMethod.GET)
	public ModelAndView listByActivity(@RequestParam int activityId) {
		ModelAndView result;
		Collection<Trainer> trainers;
		Activity activity;

		trainers = trainerService.findTrainerByActivity(activityId);
		activity = activityService.findOne(activityId);

		if (activity == null) {
			result = new ModelAndView("redirect:/panic/misc.do");
		} else {

			result = new ModelAndView("trainer/list");
			result.addObject("requestURI", "trainer/list.do");
			result.addObject("trainers", trainers);
		}
		return result;
	}

	@RequestMapping(value = "/listOfTrainer", method = RequestMethod.GET)
	public ModelAndView listTrainer() {
		ModelAndView result;
		Collection<Trainer> trainers;

		trainers = trainerService.findAll();

		result = new ModelAndView("trainer/listOfTrainer");

		result.addObject("requestURI", "trainer/listOfTrainer.do");
		result.addObject("trainers", trainers);

		return result;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(@RequestParam("keyword") final String keyword) {
		ModelAndView result;
		Collection<Trainer> trainers;

		trainers = trainerService.trainersByKeyWord(keyword);

		result = new ModelAndView("trainer/listOfTrainer");
		result.addObject("requestURI", "trainer/listOfTrainer.do");
		result.addObject("trainers", trainers);

		return result;
	}

	//Edition
	//=============================================================================

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView edit() {

		ModelAndView result;
		result = new ModelAndView("trainer/edit");

		//		Manager manager = managerService.findByPrincipal();
		//		Collection<Gym> gyms = gymService.findGymsByManagerNotClosed(manager.getId());
		//
		//		result.addObject("gyms", gyms);
		result.addObject("trainerForm", new TrainerForm());

		return result;
	}

	@RequestMapping(value = "/assign", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int trainerId) {

		ModelAndView result;
		result = new ModelAndView("trainer/assign");

		Trainer trainer = trainerService.findOne(trainerId);
		Manager manager = managerService.findByPrincipal();

		result.addObject("gyms", manager.getGyms());
		result.addObject("trainer", trainer);

		return result;
	}

	// Save
	// ====================================================================

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final TrainerForm trainerForm, final BindingResult binding) {
		ModelAndView result;
		Trainer trainer;

		try {
			trainer = trainerService.reconstruct(trainerForm, binding);

			if (binding.hasErrors())
				result = this.createEditModelAndView(trainerForm, "trainer.save.error");
			else {
				result = new ModelAndView("redirect:/welcome/index.do");
				trainer = this.trainerService.save(trainer);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(trainerForm, "trainer.save.error");
		}

		return result;
	}

	@RequestMapping(value = "/assign", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final Trainer trainerPruned, BindingResult binding) {
		ModelAndView result;

		Trainer trainer = trainerService.reconstruct(trainerPruned, binding);

		try {

			if (binding.hasErrors())
				result = this.createEditModelAndView2(trainer, "trainer.save.error");
			else {
				result = new ModelAndView("redirect:/trainer/listOfTrainer.do");
				trainer = this.trainerService.save3(trainer);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView2(trainerPruned, "trainer.save.error");
		}

		return result;
	}

	// Ancilliary methods
	// =================================================================================================

	private ModelAndView createEditModelAndView(TrainerForm trainerForm, String message) {

		ModelAndView result = new ModelAndView("trainer/edit");

		Manager manager = managerService.findByPrincipal();
		Collection<Gym> gyms = gymService.findGymsByManagerNotClosed(manager.getId());

		result.addObject("gyms", gyms);

		result.addObject("trainerForm", trainerForm);
		result.addObject("message", message);
		return result;
	}

	// ========================================================================================================

	protected ModelAndView createEditModelAndView2(Trainer trainer) {
		ModelAndView result;

		result = createEditModelAndView2(trainer, null);
		return result;
	}

	protected ModelAndView createEditModelAndView2(Trainer trainer, String message) {
		ModelAndView result;

		result = new ModelAndView("trainer/assign");

		Manager manager = managerService.findByPrincipal();

		result.addObject("trainer", trainer);
		result.addObject("gyms", manager.getGyms());
		result.addObject("message", message);

		return result;
	}
}
